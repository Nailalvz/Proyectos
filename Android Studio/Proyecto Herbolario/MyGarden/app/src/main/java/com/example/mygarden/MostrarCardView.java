package com.example.mygarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class MostrarCardView extends AppCompatActivity {

    int id;
    FloatingActionButton back;

    ImageView imagen;
    TextView titulo, texto1, texto2, texto3, texto4, texto5, texto6, texto7, texto8, texto9, texto10, texto11, texto12, texto13, texto14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_card_view);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("cardview");


        imagen = (ImageView) findViewById(R.id.imagen);
        titulo = (TextView) findViewById(R.id.tituloPlanta);
        texto1 = (TextView) findViewById(R.id.text1);
        texto2 = (TextView) findViewById(R.id.text2);
        texto3 = (TextView) findViewById(R.id.text3);
        texto4 = (TextView) findViewById(R.id.text4);
        texto5 = (TextView) findViewById(R.id.text5);
        texto6 = (TextView) findViewById(R.id.text6);
        texto7 = (TextView) findViewById(R.id.text7);

        texto8 = (TextView) findViewById(R.id.text8);
        texto9 = (TextView) findViewById(R.id.text9);
        texto10 = (TextView) findViewById(R.id.text10);


        back = (FloatingActionButton) findViewById(R.id.back);

        switch(id){
            case 1:
                imagen.setImageResource(R.drawable.glicina);
                titulo.setText(R.string.glicina);
                texto1.setText(R.string.floracion_glicina);
                texto2.setText(R.string.riego_glicina);
                texto3.setText(R.string.titulo_caract_glicina);
                texto4.setText(R.string.c_p_1_glicina);
                texto5.setText(R.string.titulo_cultivo_glicina);
                texto6.setText(R.string.cul_p_1_glicina);
                texto7.setText(R.string.cul_p_2_glicina);

                texto8.setText(R.string.titulo_plagas_glicina);
                texto9.setText(R.string.cp_p_1_glicina);
                texto10.setText(R.string.cp_p_2_glicina);

                break;
            case 2:
                imagen.setImageResource(R.drawable.hiedra);
                titulo.setText(R.string.hiedra);
                texto1.setText(R.string.ubicacion_hiedra);
                texto2.setText(R.string.riego_hiedra);
                texto3.setText(R.string.titulos_descrip_hiedra);
                texto4.setText(R.string.d_1_hiedra);
                texto5.setText(R.string.titulo_cuidados_hiedra);
                texto6.setText(R.string.c_1_hiedra);
                texto7.setText(R.string.c_2_hiedra);

                texto8.setText(R.string.titulo_plagas_hiedra);
                texto9.setText(R.string.cp_p_1_hiedra);
                texto10.setText(R.string.cp_p_2_hiedra);
                break;
            case 3:
                imagen.setImageResource(R.drawable.jazmin);
                titulo.setText(R.string.jazmin);
                texto1.setText(R.string.ubicacion_jazmin);
                texto2.setText(R.string.riego_jazmin);
                texto3.setText(R.string.titulos_descrip_jazmin);
                texto4.setText(R.string.d_1_jazmin);
                texto5.setText(R.string.titulo_cuidados_jazmin);
                texto6.setText(R.string.c_1_jazmin);
                texto7.setText(R.string.c_2_jazmin);

                texto8.setText(R.string.titulo_plagas_jazmin);
                texto9.setText(R.string.cp_p_1_jazmin);
                texto10.setText(R.string.cp_p_2_jazmin);
                break;
            case 4:
                imagen.setImageResource(R.drawable.lavanda);
                titulo.setText(R.string.lavanda);
                texto1.setText(R.string.ubicacion_lavanda);
                texto2.setText(R.string.riego_lavanda);
                texto3.setText(R.string.titulos_descrip_lavanda);
                texto4.setText(R.string.d_1_lavanda);
                texto5.setText(R.string.titulo_cuidados_lavanda);
                texto6.setText(R.string.c_1_lavanda);
                texto7.setText(R.string.c_2_lavanda);

                texto8.setText(R.string.titulo_plagas_lavanda);
                texto9.setText(R.string.cp_p_1_lavanda);
                texto10.setText(R.string.cp_p_2_lavanda);
                break;
            case 5:
                imagen.setImageResource(R.drawable.jacinto);
                titulo.setText(R.string.jacinto);
                texto1.setText(R.string.ubicacion_jacinto);
                texto2.setText(R.string.riego_jacinto);
                texto3.setText(R.string.titulos_descrip_jacinto);
                texto4.setText(R.string.d_1_jacinto);
                texto5.setText(R.string.titulo_cuidados_jacinto);
                texto6.setText(R.string.c_1_jacinto);
                texto7.setText(R.string.c_2_jacinto);

                texto8.setText(R.string.titulo_plagas_jacinto);
                texto9.setText(R.string.cp_p_1_jacinto);
                texto10.setText(R.string.cp_p_2_jacinto);
                break;
            case 6:
                imagen.setImageResource(R.drawable.limonero);
                titulo.setText(R.string.limonero);
                texto1.setText(R.string.ubicacion_limonero);
                texto2.setText(R.string.riego_limonero);
                texto3.setText(R.string.titulos_descrip_limonero);
                texto4.setText(R.string.d_1_limonero);
                texto5.setText(R.string.titulo_cuidados_limonero);
                texto6.setText(R.string.c_1_limonero);
                texto7.setText(R.string.c_2_limonero);

                texto8.setText(R.string.titulo_plagas_limonero);
                texto9.setText(R.string.cp_p_1_limonero);
                texto10.setText(R.string.cp_p_2_limonero);
                break;
            case 7:
                imagen.setImageResource(R.drawable.lirios);
                titulo.setText(R.string.lirios);
                texto1.setText(R.string.ubicacion_lirios);
                texto2.setText(R.string.riego_lirios);
                texto3.setText(R.string.titulos_descrip_lirios);
                texto4.setText(R.string.d_1_lirios);
                texto5.setText(R.string.titulo_cuidados_lirios);
                texto6.setText(R.string.c_1_lirios);
                texto7.setText(R.string.c_2_lirios);

                texto8.setText(R.string.titulo_plagas_lirios);
                texto9.setText(R.string.cp_p_1_lirios);
                texto10.setText(R.string.cp_p_2_lirios);
                break;
            case 8:
                imagen.setImageResource(R.drawable.menta);
                titulo.setText(R.string.menta);
                texto1.setText(R.string.ubicacion_menta);
                texto2.setText(R.string.riego_menta);
                texto3.setText(R.string.titulos_descrip_menta);
                texto4.setText(R.string.d_1_menta);
                texto5.setText(R.string.titulo_cuidados_menta);
                texto6.setText(R.string.c_1_menta);
                texto7.setText(R.string.c_2_menta);

                texto8.setText(R.string.titulo_plagas_menta);
                texto9.setText(R.string.cp_p_1_menta);
                texto10.setText(R.string.cp_p_2_menta);
                break;
            case 9:
                imagen.setImageResource(R.drawable.peonias2);
                titulo.setText(R.string.peonias);
                texto1.setText(R.string.ubicacion_peonias);
                texto2.setText(R.string.riego_peonias);
                texto3.setText(R.string.titulos_descrip_peonias);
                texto4.setText(R.string.d_1_peonias);
                texto5.setText(R.string.titulo_cuidados_peonias);
                texto6.setText(R.string.c_1_peonias);
                texto7.setText(R.string.c_2_peonias);

                texto8.setText(R.string.titulo_plagas_peonias);
                texto9.setText(R.string.cp_p_1_peonias);
                texto10.setText(R.string.cp_p_2_peonias);
                break;
            case 10:
                imagen.setImageResource(R.drawable.romero);
                titulo.setText(R.string.romero);
                texto1.setText(R.string.ubicacion_romero);
                texto2.setText(R.string.riego_romero);
                texto3.setText(R.string.titulos_descrip_romero);
                texto4.setText(R.string.d_1_romero);
                texto5.setText(R.string.titulo_cuidados_romero);
                texto6.setText(R.string.c_1_romero);
                texto7.setText(R.string.c_2_romero);

                texto8.setText(R.string.titulo_plagas_romero);
                texto9.setText(R.string.cp_p_1_romero);
                texto10.setText(R.string.cp_p_2_romero);
                break;
            case 11:
                imagen.setImageResource(R.drawable.tomillo3);
                titulo.setText(R.string.tomillo);
                texto1.setText(R.string.ubicacion_tomillo);
                texto2.setText(R.string.riego_tomillo);
                texto3.setText(R.string.titulos_descrip_tomillo);
                texto4.setText(R.string.d_1_tomillo);
                texto5.setText(R.string.titulo_cuidados_tomillo);
                texto6.setText(R.string.c_1_tomillo);
                texto7.setText(R.string.c_2_tomillo);

                texto8.setText(R.string.titulo_plagas_tomillo);
                texto9.setText(R.string.cp_p_1_tomillo);
                texto10.setText(R.string.cp_p_2_tomillo);
                break;
            case 12:
                imagen.setImageResource(R.drawable.tulipanes);
                titulo.setText(R.string.tulipanes);
                texto1.setText(R.string.ubicacion_tulipanes);
                texto2.setText(R.string.riego_tulipanes);
                texto3.setText(R.string.titulos_descrip_tulipanes);
                texto4.setText(R.string.d_1_tulipanes);
                texto5.setText(R.string.titulo_cuidados_tulipanes);
                texto6.setText(R.string.c_1_tulipanes);
                texto7.setText(R.string.c_2_tulipanes);

                texto8.setText(R.string.titulo_plagas_tulipanes);
                texto9.setText(R.string.cp_p_1_tulipanes);
                texto10.setText(R.string.cp_p_2_tulipanes);
                break;


        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarCardView.this, MainActivity.class);
                startActivity(intent);
            }
        });





    }
}