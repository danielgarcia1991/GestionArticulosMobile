package com.example.lenovo.gestionarticulosm;

import com.example.lenovo.gestionarticulosm.models.Article;
import com.example.lenovo.gestionarticulosm.models.Category;

import java.util.ArrayList;

public class ArticleResponse {

    private ArrayList<Article> articles;
    private boolean error;


    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
