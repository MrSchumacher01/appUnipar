package com.example.appunipar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherFragment extends Fragment {

    private TextView temperatureTextView, descriptionTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        temperatureTextView = view.findViewById(R.id.temperatureTextView);
        descriptionTextView = view.findViewById(R.id.descriptionTextView);

        getWeatherData();

        return view;
    }

    private void getWeatherData() {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY&units=metric";

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main = response.getJSONObject("main");
                    double temperature = main.getDouble("temp");

                    String description = response.getJSONArray("weather").getJSONObject(0).getString("description");

                    temperatureTextView.setText(String.format("%.2f°C", temperature));
                    descriptionTextView.setText(description);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }
}
