package com.example.lenovo.gestionarticulosm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.gestionarticulosm.models.Article;
import com.example.lenovo.gestionarticulosm.models.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity {

    List<String> listArticlesCode = new ArrayList<String>();
    public static final String ID_CATEGORY = "";
    ListView listArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast toast1 = Toast.makeText(getApplicationContext(), "Debe diligenciar todos los campos", Toast.LENGTH_SHORT);



        getArticles("1");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getArticles(String id_user) {



        Toast toast1 = Toast.makeText(getApplicationContext(), "Debe diligenciar todos los campos", Toast.LENGTH_SHORT);
        Call<ArticleResponse> call = ApiAdapter.getApiService().getArticles();
        call.enqueue( new ArticleActivity.ArticleCallback());
    }

    class ArticleCallback implements Callback<ArticleResponse> {

        @Override
        public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {


            if(response.isSuccessful()){
                ArticleResponse articleResponse = response.body();
                createListViewArticles(articleResponse.getArticles());
            }else{
                Toast.makeText(ArticleActivity.this, "Error en el formato de respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ArticleResponse> call, Throwable t) {

            Toast.makeText(ArticleActivity.this, "failure: ", Toast.LENGTH_SHORT).show();
        }
    }

    private void createListViewArticles(ArrayList<Article> articles){


        List<String> listArticlesName = new ArrayList<String>();

        for(Article a : articles){
            this.listArticlesCode.add(a.getId());
            listArticlesName.add(a.getName());
        }

        listArticles = (ListView)findViewById(R.id.listVA);
        ArrayAdapter<String> ArrayProducts = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listArticlesName);
        listArticles.setAdapter(ArrayProducts);

        listArticles.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callDetalleArticleActivity(listArticlesCode.get(position));
            }
        });
    }

    private void  callDetalleArticleActivity(String position){
        Intent intent = new Intent(this, DetalleArticleActivity.class);
        intent.putExtra(ID_CATEGORY, position);
        startActivity(intent);
    }

    public void verArticulos(View view)
    {
        Intent intent = new Intent(this, DetalleArticleActivity.class);
        startActivity(intent);
    }

    public void onAgregar(View view)
    {
        Intent intent = new Intent(this, AgregarArticleActivity.class);
        startActivity(intent);
    }

}
