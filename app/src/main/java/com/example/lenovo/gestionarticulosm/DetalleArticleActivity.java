package com.example.lenovo.gestionarticulosm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetalleArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_article);
    }

    public void onActualizar(View view)
    {
        //Realiza la actualizacion del articulo actual
    }

    public void onEliminar(View view)
    {
        //Realiza la eliminacion del articulo actual
    }
}
