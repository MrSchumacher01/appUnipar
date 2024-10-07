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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherFragment extends Fragment {

    private TextView weatherInfoTextView;
    private EditText cityEditText;
    private Button searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // Inicializando os componentes
        weatherInfoTextView = view.findViewById(R.id.weatherInfoTextView);
        cityEditText = view.findViewById(R.id.cityEditText);
        searchButton = view.findViewById(R.id.searchButton);

        // Ação do botão de busca
        searchButton.setOnClickListener(v -> {
            String cidade = cityEditText.getText().toString();
            if (!cidade.isEmpty()) {
                getWeatherData(cidade); // Busca a previsão para a cidade digitada
            } else {
                weatherInfoTextView.setText("Por favor, insira uma cidade.");
            }
        });

        return view;
    }

    private void getWeatherData(String cidade) {
        String apiKey = "6f1876a8439eb59d77020df82a7b6300";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + cidade + "&appid=" + apiKey + "&units=metric";

        new Thread(() -> {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                JSONObject jsonObject = new JSONObject(result.toString());
                double temperature = jsonObject.getJSONObject("main").getDouble("temp");

                getActivity().runOnUiThread(() -> weatherInfoTextView.setText("Temperatura em " + cidade + ": " + temperature + "°C"));

            } catch (Exception e) {
                e.printStackTrace();
                getActivity().runOnUiThread(() -> weatherInfoTextView.setText("Erro ao buscar a previsão do tempo."));
            }
        }).start();
    }
}
