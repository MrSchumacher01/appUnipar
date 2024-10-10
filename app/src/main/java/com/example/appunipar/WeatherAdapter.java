package com.example.appunipar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherData> weatherList;

    public WeatherAdapter(List<WeatherData> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherData weather = weatherList.get(position);
        holder.cityName.setText(weather.getCityName());
        holder.temperature.setText("Temperatura: " + weather.getTemperature() + "ºC");
        holder.weatherDescription.setText(weather.getDescription());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView cityName, temperature, weatherDescription;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
            temperature = itemView.findViewById(R.id.temperature);
            weatherDescription = itemView.findViewById(R.id.weatherDescription);
        }
    }
}
