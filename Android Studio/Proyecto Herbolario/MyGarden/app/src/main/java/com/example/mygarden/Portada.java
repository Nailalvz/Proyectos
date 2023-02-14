package com.example.mygarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Portada extends AppCompatActivity {

    ImageView portada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        //Ocultar toolbar
        getSupportActionBar().hide();


        portada = (ImageView) findViewById(R.id.Portada);

        super.onStart();
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Portada.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }, 4000);

        portada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.cancel();
                Intent inten = new Intent(Portada.this, MainActivity.class);
                startActivity(inten);
            }
        });
    }
}