package com.example.appunipar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import androidx.activity.result.ActivityResultLauncher;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class WeatherFragment extends Fragment {

    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private List<WeatherData> weatherList;
    private EditText cityEditText;
    private Button searchButton;
    private ActivityResultLauncher<ScanOptions> barcodeLauncher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // Inicializando o FloatingActionButton
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            IntentIntegrator integrator = IntentIntegrator.forSupportFragment(WeatherFragment.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            integrator.setPrompt("Escaneie o QR Code");
            integrator.setBeepEnabled(true);
            integrator.initiateScan();
        });

        // Inicializando o RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewWeather);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        weatherList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(weatherList);
        recyclerView.setAdapter(weatherAdapter);

        // Inicializando os componentes
        cityEditText = view.findViewById(R.id.cityEditText);
        searchButton = view.findViewById(R.id.searchButton);

        // Configurando o QR Code scanner
        barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
            if (result.getContents() != null) {
                // Ao escanear o QR code, o resultado será a cidade
                cityEditText.setText(result.getContents());
                getWeatherData(result.getContents());
            }
        });

        // Configuração do botão de busca
        searchButton.setOnClickListener(v -> {
            String cidade = cityEditText.getText().toString();
            getWeatherData(cidade);
        });

        return view;
    }

    // Função para buscar dados do tempo
    private void getWeatherData(String cidade) {
        new Thread(() -> {
            try {
                // URL da API com a chave e a cidade
                String apiKey = "6f1876a8439eb59d77020df82a7b6300";
                String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + cidade + "&appid=" + apiKey + "&units=metric";

                // Abre a conexão com a API
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                // Lê a resposta
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Converte a resposta em JSON
                String jsonResponse = response.toString();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONObject main = jsonObject.getJSONObject("main");
                double temperature = main.getDouble("temp");
                String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

                // Adiciona os dados na lista
                weatherList.add(new WeatherData(cidade, temperature, description));

                // Atualiza o RecyclerView
                getActivity().runOnUiThread(() -> {
                    weatherAdapter.notifyDataSetChanged();
                });

            } catch (Exception e) {
                e.printStackTrace();
                getActivity().runOnUiThread(() -> {
                    // Mostra uma mensagem de erro
                    weatherList.add(new WeatherData(cidade, 0.0, "Erro ao buscar previsão."));
                    weatherAdapter.notifyDataSetChanged();
                });
            }
        }).start();
    }

    // Função para iniciar o scanner do QR Code
    public void startQRScanner() {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        options.setPrompt("Escaneie um QR Code");
        options.setCameraId(0);
        barcodeLauncher.launch(options);
    }
}
