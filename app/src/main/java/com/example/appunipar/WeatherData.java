package com.example.appunipar;

public class WeatherData {
    private String city;
    private double temp;
    private String description;

    public WeatherData(String city, double temp, String description) {
        this.city = city;
        this.temp = temp;
        this.description = description;
    }

    public String getCityName() {
        return city;
    }

    public double getTemperature() {
        return temp;
    }

    public String getDescription() {
        return description;
    }
}
