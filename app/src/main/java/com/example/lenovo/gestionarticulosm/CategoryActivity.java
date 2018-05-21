package com.example.lenovo.gestionarticulosm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.gestionarticulosm.models.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    ListView listCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getCategories("1");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "Debe diligenciar todos los campos", Toast.LENGTH_SHORT);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    private void getCategories(String id_user) {



        //Toast toast1 = Toast.makeText(getApplicationContext(), "Debe diligenciar todos los campos", Toast.LENGTH_SHORT);
        Call<CategoryResponse> call = ApiAdapter.getApiService().getCategories();
        call.enqueue( new CategoryActivity.CategoryCallback());
    }

    class CategoryCallback implements Callback<CategoryResponse> {

        @Override
        public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {


            if(response.isSuccessful()){
                CategoryResponse categoryResponse = response.body();
                createListViewCategores(categoryResponse.getCategories());
                //Toast.makeText(CategoryActivity.this, "sisas:" + categoryResponse.getCategories() , Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(CategoryActivity.this, "Error en el formato de respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<CategoryResponse> call, Throwable t) {


        }
    }

    private void createListViewCategores(ArrayList<Category> categories){

        List<String> listProductsName = new ArrayList<String>();
        for(Category c : categories){
            listProductsName.add(c.getName());
        }

        listCategories = (ListView)findViewById(R.id.listVC);
        ArrayAdapter<String> ArrayProducts = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listProductsName);
        listCategories.setAdapter(ArrayProducts);
    }


    public void onVerCategoria(View view)
    {
        Intent intent = new Intent(this, DetalleCategoryActivity.class);
        startActivity(intent);
    }

    public void onAgregar(View view)
    {
        Intent intent = new Intent(this, AgregarCategoryActivity.class);
        startActivity(intent);
    }

}
