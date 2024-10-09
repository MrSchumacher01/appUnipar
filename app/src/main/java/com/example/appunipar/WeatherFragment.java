package com.example.appunipar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import androidx.activity.result.ActivityResultLauncher;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherFragment extends Fragment {

    private TextView weatherInfoTextView;
    private EditText cityEditText;
    private Button searchButton;
    private ActivityResultLauncher<ScanOptions> barcodeLauncher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // Inicializa o FloatingActionButton
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia o scanner de QR code
                IntentIntegrator integrator = IntentIntegrator.forSupportFragment(WeatherFragment.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Escaneie o QR Code");
                integrator.setBeepEnabled(true);
                integrator.initiateScan();
            }
        });

        // Inicializando os componentes
        weatherInfoTextView = view.findViewById(R.id.weatherInfoTextView);
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
                // Mostra o texto de carregamento enquanto busca os dados
                getActivity().runOnUiThread(() -> weatherInfoTextView.setText("Carregando previsão do tempo..."));

                // URL da API com a chave e a cidade
                String apiKey = "6f1876a8439eb59d77020df82a7b6300\n"; // Substitua pela sua chave de API
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

                // Atualiza o TextView com a temperatura
                getActivity().runOnUiThread(() -> {
                    weatherInfoTextView.setText("Temperatura em " + cidade + ": " + temperature + "ºC");
                });
            } catch (Exception e) {
                e.printStackTrace();
                getActivity().runOnUiThread(() -> weatherInfoTextView.setText("Erro ao buscar a previsão do tempo."));
            }
        }).start();
    }


    // Função para iniciar o scanner do QR Code
    public void startQRScanner() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Escaneie o QR Code");
        barcodeLauncher.launch(options);
    }

}
