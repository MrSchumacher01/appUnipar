
package com.example.appunipar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherFragment extends Fragment {

    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private List<WeatherData> weatherList;
    private EditText cityEditText;
    private Button searchButton;
    private FloatingActionButton fab;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(
            new ScanContract(), result -> {
                if (result.getContents() != null) {
                    cityEditText.setText(result.getContents());
                    getWeatherData(result.getContents());
                } else {
                    Toast.makeText(getContext(), "Scan cancelado", Toast.LENGTH_SHORT).show();
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewWeather);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        weatherList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(weatherList);
        recyclerView.setAdapter(weatherAdapter);

        cityEditText = view.findViewById(R.id.cityEditText);
        searchButton = view.findViewById(R.id.searchButton);

        fab = view.findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(v -> startQRScanner());

        if (getContext() != null) {if (getActivity() != null) {
            getActivity().runOnUiThread(() -> weatherAdapter.notifyDataSetChanged());
            }
        } else {
            Toast.makeText(getContext(), "Scan cancelado", Toast.LENGTH_SHORT).show();
            }
        if (fab != null) {
            fab.setVisibility(View.VISIBLE);
            fab.setOnClickListener(v -> startQRScanner());
        }

        searchButton.setOnClickListener(v -> {
            String cidade = cityEditText.getText().toString();
            getWeatherData(cidade);
        });

        return view;
    }

    private void startQRScanner() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Escaneie um QR Code");
        options.setBeepEnabled(true);
        options.setBarcodeImageEnabled(true);
        options.setCaptureActivity(CaptureActivity.class);
        barcodeLauncher.launch(options);
    }

    private void getWeatherData(String cidade) {
        new Thread(() -> {
            try {
                // Limpa a lista antes de adicionar novos resultados
                getActivity().runOnUiThread(() -> weatherList.clear());

                // URL da API nova
                String urlString = "https://api.hgbrasil.com/weather?format=json-cors&key=SUA_CHAVE_API&city_name=" + cidade;

                // Abre a conexão com a API
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                // Lê a resposta da API
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Converte a resposta para JSON
                String jsonResponse = response.toString();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONObject results = jsonObject.getJSONObject("results");
                double temperature = results.getDouble("temp");
                String description = results.getString("description");

                // Adiciona a nova previsão à lista
                weatherList.add(new WeatherData(cidade, temperature, description));

                // Atualiza o RecyclerView na thread principal
                getActivity().runOnUiThread(() -> weatherAdapter.notifyDataSetChanged());

            } catch (Exception e) {
                e.printStackTrace();
                getActivity().runOnUiThread(() -> {
                    // Adiciona uma entrada de erro se houver falha na API
                    weatherList.add(new WeatherData(cidade, 0.0, "Erro ao buscar previsão."));
                    weatherAdapter.notifyDataSetChanged();
                });
            }
        }).start();
    }
}
