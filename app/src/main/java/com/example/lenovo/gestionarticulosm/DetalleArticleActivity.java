package com.example.lenovo.gestionarticulosm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.gestionarticulosm.models.Article;
import com.example.lenovo.gestionarticulosm.models.ArticleStatus;
import com.example.lenovo.gestionarticulosm.models.Category;
import com.example.lenovo.gestionarticulosm.models.CategoryStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleArticleActivity extends AppCompatActivity {

    private String idCategory;

    private EditText nameTa;
    private EditText descriptionTa;
    private EditText editText3;
    private EditText dateExpTa;
    private EditText kmTa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_article);

        nameTa = (EditText)findViewById(R.id.nameTa);
        descriptionTa = (EditText)findViewById(R.id.descriptionTa);
        editText3 = (EditText)findViewById(R.id.editText3);
        dateExpTa = (EditText)findViewById(R.id.dateExpTa);
        kmTa = (EditText)findViewById(R.id.kmTa);

        Intent intent = getIntent();
        String id_category = intent.getStringExtra(CategoryActivity.ID_CATEGORY);
        this.idCategory = id_category;

        getArticle(id_category);
    }

    private void getArticle(String id_category) {

        Call<Article> call = ApiAdapter.getApiService().getArticle();
        call.enqueue( new DetalleArticleActivity.ArticleCallback());
    }


    class ArticleCallback implements Callback<Article> {

        @Override
        public void onResponse(Call<Article> call, Response<Article> response) {


            if(response.isSuccessful()){
                Article article = response.body();

                nameTa.setText(article.getName());
                descriptionTa.setText(article.getDescription());
                editText3.setText(article.getType());
                dateExpTa.setText(article.getDate_expiration());
                kmTa.setText(article.getMileage());

            }else{
                Toast.makeText(DetalleArticleActivity.this, "Error en el formato de respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<Article> call, Throwable t) {


        }
    }
    public void onActualizar(View view)
    {
        //Realiza la actualizacion del articulo actual
    }

    public void onEliminar(View view)
    {
        //Realiza la eliminacion del articulo actual
        getDeleteArticle(this.idCategory);
    }

    private void getDeleteArticle(String id_category) {

        Call<ArticleStatus> call = ApiAdapter.getApiService().getDeleteArticle();
        call.enqueue( new DetalleArticleActivity.ArticleDelCallback());
    }

    class ArticleDelCallback implements Callback<ArticleStatus> {

        @Override
        public void onResponse(Call<ArticleStatus> call, Response<ArticleStatus> response) {


            if(response.isSuccessful()){
                ArticleStatus articleStatus = response.body();
                if(articleStatus.isStatus() == true){
                    Toast.makeText(DetalleArticleActivity.this, "Articulo eliminado con exito! ", Toast.LENGTH_SHORT).show();
                    callArticles();
                }
            }else{
                Toast.makeText(DetalleArticleActivity.this, "Error en el formato de respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
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
