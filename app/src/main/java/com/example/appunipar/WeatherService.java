package com.example.appunipar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Interface da API HG Brasil
public interface WeatherService {
    @GET("weather")
    Call<WeatherData> getWeather(
            @Query("woeid") String woeid  // Usando o woeid conforme a documentação da HG Brasil
    );
}
