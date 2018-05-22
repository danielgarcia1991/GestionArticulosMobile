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

    @GET("api/api_aregistroelementos/{id_category}")
    Call<Category> getCategory(@Path("id_category") String id_category);

    @POST("api/api_registroelementos")
    Call<CategoryStatus> postAddCategories(@Query("name") String name,
                                           @Query("description") String description,
                                           @Query("user_id") String user_id);

    @DELETE("api/api_registroelementos/{id_category}")
    Call<CategoryStatus> getDeleteCategory(@Path("id_category") String id_category);



    @GET("api/api_registroarticulos/{id_user}")
    Call<ArticleResponse> getArticles(@Path("id_user") String id_user);

    @GET("api/api_aregistroarticulos/{id_article}")
    Call<Article> getArticle(@Path("id_article") String id_article);

    @DELETE("api/api_registroarticulos/2")
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

