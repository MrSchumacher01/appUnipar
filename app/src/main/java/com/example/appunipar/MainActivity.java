package com.example.appunipar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonShowMessage = findViewById(R.id.buttonShowMessage);
        TextView textViewMessage = findViewById(R.id.textViewMessage);

        buttonShowMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewMessage.setVisibility(View.VISIBLE);  // Mostrar a mensagem
                textViewMessage.setText("Olá, esta é a mensagem exibida!");  // Exibir o texto
            }
        });
    }
}
