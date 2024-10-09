package com.example.appunipar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        // Configurar as views
        ImageView profileImage = findViewById(R.id.profile_image);
        TextView nameTextView = findViewById(R.id.name_text);
        TextView raTextView = findViewById(R.id.ra_text);
        TextView courseTextView = findViewById(R.id.course_text);

        // Definir os dados
        profileImage.setImageResource(R.drawable.foto_marcos);
        nameTextView.setText("Marcos Roberto Schumacher");
        raTextView.setText("RA: 09037296");
        courseTextView.setText("Curso: Análise e Desenvolvimento de Sistemas");
    }
}
