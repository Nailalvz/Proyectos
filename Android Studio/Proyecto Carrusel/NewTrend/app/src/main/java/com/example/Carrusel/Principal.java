package com.example.Carrusel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.newtrend.R;

public class Principal extends AppCompatActivity {

    ImageView imagen;
    ImageButton derecha, izquierda;
    int cont = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        imagen = (ImageView) findViewById(R.id.foto);
        derecha = (ImageButton) findViewById(R.id.flechaderecha);
        izquierda = (ImageButton) findViewById(R.id.flechaizquierda);

        derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (cont){
                    case 1:
                        cont++;
                        imagen.setImageResource(R.drawable.foto2);
                        break;
                    case 2:
                        cont++;
                        imagen.setImageResource(R.drawable.foto3);
                        break;
                    case 3:
                        cont++;
                        imagen.setImageResource(R.drawable.foto4);
                        break;
                    case 4:
                        cont++;
                        imagen.setImageResource(R.drawable.foto5);
                        break;
                    case 5:
                        cont++;
                        imagen.setImageResource(R.drawable.foto6);
                        break;
                    case 6:
                        cont++;
                        imagen.setImageResource(R.drawable.foto7);
                        break;
                    case 7:
                        cont++;
                        imagen.setImageResource(R.drawable.foto8);
                        break;
                    case 8:
                        cont++;
                        imagen.setImageResource(R.drawable.foto9);
                        break;
                    case 9:
                        cont++;
                        imagen.setImageResource(R.drawable.foto10);
                        break;

                }
            }
        });

        izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (cont){
                    case 1:
                        cont--;
                        imagen.setImageResource(R.drawable.foto1);
                        break;
                    case 2:
                        cont--;
                        imagen.setImageResource(R.drawable.foto2);
                        break;
                    case 3:
                        cont--;
                        imagen.setImageResource(R.drawable.foto3);
                        break;
                    case 4:
                        cont--;
                        imagen.setImageResource(R.drawable.foto4);
                        break;
                    case 5:
                        cont--;
                        imagen.setImageResource(R.drawable.foto5);
                        break;
                    case 6:
                        cont--;
                        imagen.setImageResource(R.drawable.foto6);
                        break;
                    case 7:
                        cont--;
                        imagen.setImageResource(R.drawable.foto7);
                        break;
                    case 8:
                        cont--;
                        imagen.setImageResource(R.drawable.foto8);
                        break;
                    case 9:
                        cont--;
                        imagen.setImageResource(R.drawable.foto9);
                        break;

                }
            }
        });

    }
}