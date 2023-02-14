package com.example.codigoqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Principal extends AppCompatActivity {

    TextView txtResult;
    com.getbase.floatingactionbutton.FloatingActionsMenu fabMenu;
    com.getbase.floatingactionbutton.FloatingActionButton escanear;
    com.getbase.floatingactionbutton.FloatingActionButton generarQR;
    com.getbase.floatingactionbutton.FloatingActionButton generarCB;
    ImageView codigo, fondo2;
    EditText etDatos;
    EditText etDatosCB;
    Button btngenerar;
    Button btngenerarCB;
    FloatingActionButton compartir;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Ocultar toolbar
        getSupportActionBar().hide();

        fabMenu = (com.getbase.floatingactionbutton.FloatingActionsMenu) findViewById(R.id.floatingButton);
        escanear = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.accion_escanear);
        generarQR = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.accion_generarQR);
        generarCB = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.accion_generarCB);
        fondo2 = (ImageView) findViewById(R.id.fondo2);


        txtResult = (TextView) findViewById(R.id.textResult);

        codigo = (ImageView) findViewById(R.id.codigo);
        etDatos = (EditText) findViewById(R.id.editText);
        etDatosCB = (EditText) findViewById(R.id.editTextCB);
        btngenerar = (Button) findViewById(R.id.generar);
        btngenerarCB = (Button) findViewById(R.id.generarCD);
        compartir = (FloatingActionButton) findViewById(R.id.compartir);

        codigo.setVisibility(View.INVISIBLE);
        etDatos.setVisibility(View.INVISIBLE);
        etDatosCB.setVisibility(View.INVISIBLE);
        btngenerar.setVisibility(View.INVISIBLE);
        btngenerarCB.setVisibility(View.INVISIBLE);
        compartir.setVisibility(View.INVISIBLE);

        //Esto era para hacer pruebas
        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Hola", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                 */
            }
        });

        //Cuando clicas en el fab de escanear se abre la ventana para escanear el código y se ocultan unas cosas y se muestran otras
        escanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fondo2.setVisibility(View.INVISIBLE);
                codigo.setVisibility(View.INVISIBLE);
                etDatos.setVisibility(View.INVISIBLE);
                btngenerar.setVisibility(View.INVISIBLE);
                compartir.setVisibility(View.INVISIBLE);
                etDatosCB.setVisibility(View.INVISIBLE);
                btngenerarCB.setVisibility(View.INVISIBLE);
                txtResult.setVisibility(View.VISIBLE);
                escanearQR();
                if(fabMenu.isExpanded()){
                    fabMenu.collapse();
                }
            }
        });

        //Cuando clicas en el fab de generarQR se muestra el editText para generar el QR y el boton para generarlo
        generarQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fondo2.setVisibility(View.INVISIBLE);
                txtResult.setVisibility(View.INVISIBLE);
                codigo.setVisibility(View.INVISIBLE);
                compartir.setVisibility(View.INVISIBLE);
                etDatosCB.setVisibility(View.INVISIBLE);
                btngenerarCB.setVisibility(View.INVISIBLE);
                etDatos.setVisibility(View.VISIBLE);
                btngenerar.setVisibility(View.VISIBLE);
                etDatos.setText("");
                txtResult.setText("");

                if(fabMenu.isExpanded()){
                    fabMenu.collapse();
                }

            }
        });

        //Genera el QR, lo muestra por pantalla y lo guarda en el external_storage
        btngenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codigo.setVisibility(View.VISIBLE);
                if(etDatos.getText().toString().isEmpty()){
                    Snackbar.make(view, "Debe introducir un texto para generar el QR", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    codigo.setImageResource(R.color.grey_fondo);
                }else {
                    try {
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        bitmap = barcodeEncoder.encodeBitmap(
                                etDatos.getText().toString(),
                                BarcodeFormat.QR_CODE,
                                550,
                                550
                        );
                        saveTempBitmap(bitmap);

                        codigo.setImageBitmap(bitmap);
                        compartir.setVisibility(View.VISIBLE);

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        //Puedes compartir el code creado
        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "En desarrollo", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                 */

                share(bitmap);
            }
        });

        //Cuando clicas en el fab de generarCB se muestra el editText para generar el UPC y el boton para generarlo
        generarCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fondo2.setVisibility(View.INVISIBLE);
                txtResult.setVisibility(View.INVISIBLE);
                codigo.setVisibility(View.INVISIBLE);
                compartir.setVisibility(View.INVISIBLE);
                etDatos.setVisibility(View.INVISIBLE);
                btngenerar.setVisibility(View.INVISIBLE);
                etDatosCB.setVisibility(View.VISIBLE);
                btngenerarCB.setVisibility(View.VISIBLE);
                txtResult.setText("");
                etDatosCB.setText("");
                if(fabMenu.isExpanded()){
                    fabMenu.collapse();
                }
            }
        });

        //Genera el UPC, lo muestra por pantalla y lo guarda en el external_storage
        btngenerarCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codigo.setVisibility(View.VISIBLE);
                if(etDatosCB.getText().toString().isEmpty()){
                    Snackbar.make(view, "Debe introducir los dígitos para generar el UPC", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }else if(etDatosCB.length() != 11){
                    Snackbar.make(view, "Debe introducir 11 dígitos", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    codigo.setImageResource(R.color.grey_fondo);
                } else {
                    try {
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        bitmap = barcodeEncoder.encodeBitmap(
                                etDatosCB.getText().toString(),
                                BarcodeFormat.UPC_A,
                                550,
                                550
                        );

                        saveTempBitmap(bitmap);

                        codigo.setImageBitmap(bitmap);
                        compartir.setVisibility(View.VISIBLE);

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    //Escanea cualquier tipo de código
    public void escanearQR(){
        IntentIntegrator integrador = new IntentIntegrator(this);
        integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);

        integrador.setPrompt("Escanear Código");
        integrador.setCameraId(0);
        integrador.setBeepEnabled(true);
        integrador.setBarcodeImageEnabled(true);
        integrador.initiateScan();

    }

    //Muestra el resultado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult resultado = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(resultado != null){
            if(resultado.getContents() == null){
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            } else {
                txtResult.setText(resultado.getContents().toString());
                txtResult.setTextSize(24);
            }
        }

    }

    //Comparte la imagen del código
    public void share(Bitmap b){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Title", null);
        Uri imageUri = Uri.parse(path);

        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, "Select"));
    }

    //Comprueba si se puede guardar la imagen y llama a la función de abajo para guardarla
    public void saveTempBitmap(Bitmap bitmap) {
        if (isExternalStorageWritable()) {
            saveImage(bitmap);
        }else{
            Toast.makeText(this, "No se puede guardar la imagen", Toast.LENGTH_SHORT).show();
        }
    }

    //Guarda la imagen en el External_storage
    private void saveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "Shutta_"+ timeStamp +".jpg";

        File file = new File(myDir, fname);
        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, new String[]{file.getName()}, null);
    }

    // Comprueba si el almacenamiento externo está disponible para escribir y leer
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}