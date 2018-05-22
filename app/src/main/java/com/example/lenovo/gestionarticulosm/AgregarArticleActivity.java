package com.example.lenovo.gestionarticulosm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.gestionarticulosm.models.ArticleStatus;
import com.example.lenovo.gestionarticulosm.models.CategoryStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarArticleActivity extends AppCompatActivity {

    private EditText nameTa;
    private EditText descriptionTa;
    private EditText editText3;
    private EditText dateExpTa;
    private EditText kmTa;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_article);

        nameTa = (EditText)findViewById(R.id.nameTa);
        descriptionTa = (EditText)findViewById(R.id.descriptionTa);
        editText3 = (EditText)findViewById(R.id.editText3);
        dateExpTa = (EditText)findViewById(R.id.dateExpTa);
        kmTa = (EditText)findViewById(R.id.kmTa);
        this.id_user = "1";
    }

    public void onAgregar(View view) {

        //llama el web service para agregar un nuevo articulo
        addArticle(nameTa.getText().toString(),descriptionTa.getText().toString(),editText3.getText().toString(),dateExpTa.getText().toString()
                ,kmTa.getText().toString(),this.id_user);
    }

    private void addArticle(String name, String description, String type, String dateExp, String km, String id_user) {

        String user_id = JsonPreferences.getInstance(getApplicationContext()).getUser();
        Call<ArticleStatus> call = ApiAdapter.getApiService().postAddArticles(name, description, km, dateExp, type, user_id, "3");
        call.enqueue( new AgregarArticleActivity.ArticleCallback());
    }

    class ArticleCallback implements Callback<ArticleStatus> {

        @Override
        public void onResponse(Call<ArticleStatus> call, Response<ArticleStatus> response) {


            if(response.isSuccessful()){
                ArticleStatus articleStatus = response.body();
                if(articleStatus.isStatus() == true){
                    Toast.makeText(AgregarArticleActivity.this, "Articulo registrado con exito! ", Toast.LENGTH_SHORT).show();
                    callArticles();
                }
            }else{
                Toast.makeText(AgregarArticleActivity.this, "Error en el formato de respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ArticleStatus> call, Throwable t) {


        }
    }

    private void callArticles(){

        Intent intent = new Intent(this, ArticleActivity.class);
        startActivity(intent);
    }

}
