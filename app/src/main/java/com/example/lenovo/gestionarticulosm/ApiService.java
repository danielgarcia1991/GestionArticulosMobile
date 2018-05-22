package com.example.lenovo.gestionarticulosm;


import com.example.lenovo.gestionarticulosm.models.Article;
import com.example.lenovo.gestionarticulosm.models.Category;
import com.example.lenovo.gestionarticulosm.models.CategoryStatus;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {


    @GET("api/api_registroelementos/1")
    Call<CategoryResponse> getCategories();

    @GET("api/api_aregistroelementos/1")
    Call<Category> getCategory();

    @POST("api/api_registroelementos")
    Call<CategoryStatus> postAddCategories(@Query("name") String name,
                                           @Query("description") String description,
                                           @Query("user_id") String user_id);

    @DELETE("api/api_registroelementos/1")
    Call<CategoryStatus> getDeleteCategory();

    @GET("api/api_registroarticulos/1")
    Call<ArticleResponse> getArticles();

    @GET("api/api_aregistroarticulos/1")
    Call<Article> getArticle();

}

