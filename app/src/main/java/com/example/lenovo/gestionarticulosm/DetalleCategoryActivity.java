package com.example.lenovo.gestionarticulosm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.gestionarticulosm.models.Category;
import com.example.lenovo.gestionarticulosm.models.CategoryStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleCategoryActivity extends AppCompatActivity {

    private EditText nameTc;
    private EditText descriptionTc;
    private TextView textView11;
    private TextView textView12;
    private String idCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_category);

        nameTc = (EditText)findViewById(R.id.nameTc);
        descriptionTc = (EditText) findViewById(R.id.descriptionTc);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView12 = (TextView) findViewById(R.id.textView12);

        Intent intent = getIntent();
        String id_category = intent.getStringExtra(CategoryActivity.ID_CATEGORY);
        this.idCategory = id_category;

        getCategory(id_category);
    }

    private void getCategory(String id_category) {

        Call<Category> call = ApiAdapter.getApiService().getCategory(id_category);
        call.enqueue( new DetalleCategoryActivity.CategoryCallback());
    }

    class CategoryCallback implements Callback<Category> {

        @Override
        public void onResponse(Call<Category> call, Response<Category> response) {


            if(response.isSuccessful()){
                Category category = response.body();

                nameTc.setText(category.getName());
                descriptionTc.setText(category.getDescription());
                textView11.setText(category.getCreated_at());
                textView12.setText(category.getUpdated_at());

            }else{
                Toast.makeText(DetalleCategoryActivity.this, "Error en el formato de respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<Category> call, Throwable t) {


        }
    }

    public void onActualizar(View view)
    {
        //Realiza la actualizacion de la categoria actual
    }

    public void onEliminar(View view)
    {
        //Realiza la eliminacion de la categoria actual
        getDeleteCategory(this.idCategory);
    }

    private void getDeleteCategory(String id_category) {

        Call<CategoryStatus> call = ApiAdapter.getApiService().getDeleteCategory(id_category);
        call.enqueue( new DetalleCategoryActivity.CategoryDelCallback());
    }

    class CategoryDelCallback implements Callback<CategoryStatus> {

        @Override
        public void onResponse(Call<CategoryStatus> call, Response<CategoryStatus> response) {


            if(response.isSuccessful()){
                CategoryStatus categoryStatus = response.body();
                if(categoryStatus.isStatus() == true){
                    Toast.makeText(DetalleCategoryActivity.this, "Categoria eliminada con exito! ", Toast.LENGTH_SHORT).show();
                    callCategories();
                }
            }else{
                Toast.makeText(DetalleCategoryActivity.this, "Error en el formato de respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<CategoryStatus> call, Throwable t) {


        }
    }

    private void callCategories(){

        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
}
