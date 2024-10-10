package com.example.appunipar;

public class WeatherData {
    private String cityName;
    private double temperature;
    private String description;

    public WeatherData(String cityName, double temperature, String description) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }
}
