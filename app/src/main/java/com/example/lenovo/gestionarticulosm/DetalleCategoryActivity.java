package com.example.lenovo.gestionarticulosm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetalleCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_category);
    }

    public void onActualizar(View view)
    {
        //Realiza la actualizacion de la categoria actual
    }

    public void onEliminar(View view)
    {
        //Realiza la eliminacion de la categoria actual
    }
}
