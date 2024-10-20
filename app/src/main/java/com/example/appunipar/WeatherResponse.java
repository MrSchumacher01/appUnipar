package com.example.appunipar;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("name")
    private String cityName;

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private Weather[] weather;

    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return main.temp;
    }

    public String getDescription() {
        return weather[0].description;
    }

    public static class Main {
        @SerializedName("temp")
        public double temp;
    }

    public static class Weather {
        @SerializedName("description")
        public String description;
    }
}
