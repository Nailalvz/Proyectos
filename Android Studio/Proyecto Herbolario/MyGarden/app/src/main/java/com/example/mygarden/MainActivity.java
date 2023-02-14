package com.example.mygarden;

import static com.example.mygarden.R.drawable.colgante_x64;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    CardView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12;
    ImageView imagen1, imagen2, imagen3, imagen4, imagen5, imagen6, imagen7, imagen8, imagen9, imagen10, imagen11, imagen12;
    TextView texto1, texto2, texto3, texto4, texto5, texto6, texto7, texto8, texto9, texto10, texto11, texto12;
    int id;

    FloatingActionButton info, add;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(colgante_x64);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        card1 = (CardView) findViewById(R.id.card1);
        card2 = (CardView) findViewById(R.id.card2);
        card3 = (CardView) findViewById(R.id.card3);
        card4 = (CardView) findViewById(R.id.card4);
        card5 = (CardView) findViewById(R.id.card5);
        card6 = (CardView) findViewById(R.id.card6);
        card7 = (CardView) findViewById(R.id.card7);
        card8 = (CardView) findViewById(R.id.card8);
        card9 = (CardView) findViewById(R.id.card9);
        card10 = (CardView) findViewById(R.id.card10);
        card11 = (CardView) findViewById(R.id.card11);
        card12 = (CardView) findViewById(R.id.card12);

        imagen1 = (ImageView) findViewById(R.id.imagen1);
        imagen2 = (ImageView) findViewById(R.id.imagen2);
        imagen3 = (ImageView) findViewById(R.id.imagen3);
        imagen4 = (ImageView) findViewById(R.id.imagen4);
        imagen5 = (ImageView) findViewById(R.id.imagen5);
        imagen6 = (ImageView) findViewById(R.id.imagen6);
        imagen7 = (ImageView) findViewById(R.id.imagen7);
        imagen8 = (ImageView) findViewById(R.id.imagen8);
        imagen9 = (ImageView) findViewById(R.id.imagen9);
        imagen10 = (ImageView) findViewById(R.id.imagen10);
        imagen11 = (ImageView) findViewById(R.id.imagen11);
        imagen12 = (ImageView) findViewById(R.id.imagen12);

        texto1 = (TextView) findViewById(R.id.texto_1);
        texto2 = (TextView) findViewById(R.id.texto_2);
        texto3 = (TextView) findViewById(R.id.texto_3);
        texto4 = (TextView) findViewById(R.id.texto_4);
        texto5 = (TextView) findViewById(R.id.texto_5);
        texto6 = (TextView) findViewById(R.id.texto_6);
        texto7 = (TextView) findViewById(R.id.texto_7);
        texto8 = (TextView) findViewById(R.id.texto_8);
        texto9 = (TextView) findViewById(R.id.texto_9);
        texto10 = (TextView) findViewById(R.id.texto_10);
        texto11 = (TextView) findViewById(R.id.texto_11);
        texto12 = (TextView) findViewById(R.id.texto_12);

        info = (FloatingActionButton) findViewById(R.id.info);
        add = (FloatingActionButton) findViewById(R.id.add);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearAlertDialog();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addActivity.class);
                startActivity(intent);
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 1;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 2;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 3;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 4;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 5;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 6;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 7;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 8;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 9;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 10;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 11;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = 12;
                Intent intent = new Intent(MainActivity.this, MostrarCardView.class);
                intent.putExtra("cardview", id);
                startActivity(intent);
            }
        });

        card1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card1.setEnabled(false);
                imagen1.setImageResource(R.drawable.anadir);
                texto1.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card2.setEnabled(false);
                imagen2.setImageResource(R.drawable.anadir);
                texto2.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card3.setEnabled(false);
                imagen3.setImageResource(R.drawable.anadir);
                texto3.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card4.setEnabled(false);
                imagen4.setImageResource(R.drawable.anadir);
                texto4.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card5.setEnabled(false);
                imagen5.setImageResource(R.drawable.anadir);
                texto5.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card6.setEnabled(false);
                imagen6.setImageResource(R.drawable.anadir);
                texto6.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card7.setEnabled(false);
                imagen7.setImageResource(R.drawable.anadir);
                texto7.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card8.setEnabled(false);
                imagen8.setImageResource(R.drawable.anadir);
                texto8.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card9.setEnabled(false);
                imagen9.setImageResource(R.drawable.anadir);
                texto9.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card10.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card10.setEnabled(false);
                imagen10.setImageResource(R.drawable.anadir);
                texto10.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card11.setEnabled(false);
                imagen11.setImageResource(R.drawable.anadir);
                texto11.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        card12.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card12.setEnabled(false);
                imagen12.setImageResource(R.drawable.anadir);
                texto12.setText("Añadir planta");
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.cuenta:
            case R.id.ajustes:
                Toast.makeText(this, "En desarrollo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.salir:
                //Salir de la app
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void crearAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información");
        builder.setMessage(R.string.dialog);
        builder.setPositiveButton("Aceptar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}