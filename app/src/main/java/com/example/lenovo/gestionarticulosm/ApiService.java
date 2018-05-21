package com.example.lenovo.gestionarticulosm;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {


    @GET("api/api_registroelementos")
    Call<CategoryResponse> getCategories();

    @POST("api/api_registroelementos")
    Call<CategoryResponse> postAddCategories(@Query("name") String name,
                                 @Query("description") String description,
                                 @Query("user_id") String user_id);

}

