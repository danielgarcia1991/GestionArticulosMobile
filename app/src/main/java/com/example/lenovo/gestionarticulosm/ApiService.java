package com.example.lenovo.gestionarticulosm;


import com.example.lenovo.gestionarticulosm.models.Article;
import com.example.lenovo.gestionarticulosm.models.ArticleStatus;
import com.example.lenovo.gestionarticulosm.models.Category;
import com.example.lenovo.gestionarticulosm.models.CategoryStatus;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {


    @GET("api/api_registroelementos/{id_user}")
    Call<CategoryResponse> getCategories(@Path("id_user") String id_user);

    @GET("api/api_aregistroelementos/3")
    Call<Category> getCategory();

    @POST("api/api_registroelementos")
    Call<CategoryStatus> postAddCategories(@Query("name") String name,
                                           @Query("description") String description,
                                           @Query("user_id") String user_id);

    @DELETE("api/api_registroelementos/7")
    Call<CategoryStatus> getDeleteCategory();



    @GET("api/api_registroarticulos/1")
    Call<ArticleResponse> getArticles();

    @GET("api/api_aregistroarticulos/1")
    Call<Article> getArticle();

    @DELETE("api/api_registroarticulos/1")
    Call<ArticleStatus> getDeleteArticle();

    @POST("api/api_registroarticulos")
    Call<ArticleStatus> postAddArticles(@Query("name") String name,
                                           @Query("description") String description,
                                           @Query("mileage") String mileage,
                                           @Query("date_expiration") String date_expiration,
                                           @Query("type") String type,
                                           @Query("user_id") String user_id,
                                           @Query("category_id") String category_id);

    @GET("api/api_login/{mail}")
    Call<LoginResponse> getLogin(@Path("mail") String mail);

}

