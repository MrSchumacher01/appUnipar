package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciando o botão no layout
        Button button = findViewById(R.id.my_button);

        // Definindo um listener para o clique no botão
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Exibir uma mensagem ao clicar no botão
                Toast.makeText(MainActivity.this, "Botão clicado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
