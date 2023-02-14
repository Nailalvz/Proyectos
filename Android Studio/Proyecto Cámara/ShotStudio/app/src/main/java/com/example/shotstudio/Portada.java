package com.example.shotstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Portada extends AppCompatActivity {

    ImageView logo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);


        //Ocultar toolbar
        getSupportActionBar().hide();
        Typewriter txt = findViewById(R.id.typewriter);
        logo = (ImageView) findViewById(R.id.logo);
        txt.setCharacterDelay(100);
        txt.animatedText("SHOT STUDIO");

        super.onStart();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Portada.this, Principal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }, 6000);
    }
}