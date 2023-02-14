package com.example.Carrusel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.newtrend.R;

public class Portada extends AppCompatActivity {
    ImageView portada;
    ImageButton entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        portada = (ImageView) findViewById(R.id.portada);
        entrar = (ImageButton) findViewById(R.id.entrar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Portada.this, Principal.class);
                startActivity(intent);
            }
        });
    }
}