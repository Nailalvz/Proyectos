package com.example.mygarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class addActivity extends AppCompatActivity {

    FloatingActionButton aceptar, cancelar;
    ImageView seleccionar_imagen;
    Button seleccionar;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        aceptar = (FloatingActionButton) findViewById(R.id.aceptar);
        cancelar = (FloatingActionButton) findViewById(R.id.cancelar);

        seleccionar_imagen = (ImageView) findViewById(R.id.imagen_seleccionada);
        seleccionar = (Button) findViewById(R.id.seleccionar);




        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(addActivity.this, "Agregada con éxito", Toast.LENGTH_SHORT).show();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(addActivity.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });

        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });


    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            seleccionar_imagen.setImageURI(imageUri);
        }
    }


}