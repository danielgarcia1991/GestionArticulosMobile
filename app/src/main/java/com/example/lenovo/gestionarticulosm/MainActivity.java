package com.example.lenovo.gestionarticulosm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Intent intent = getIntent();
        //String id_user = intent.getStringExtra(Login.ID_USER);

        //Intent intent = new Intent(this, CategoryActivity.class);
        //startActivity(intent);

    }







    public void onCategorias(View view)
    {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void onArticulos(View view)
    {
        Intent intent = new Intent(this, ArticleActivity.class);
        startActivity(intent);
    }
}
