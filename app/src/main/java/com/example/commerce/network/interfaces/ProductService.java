package com.example.commerce.network.interfaces;

import com.example.commerce.model.CategoriesItem;
import com.example.commerce.model.Response;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ProductService {

    @GET("products/categories")
    Call<List<CategoriesItem>> Categories(@QueryMap Map<String, String> queries);

    @GET("products")
    Call<List<Response>> getResponse(@QueryMap Map<String, String> queries);

    @GET("products/{id}")
    Call<Response> item (@Path("id") String id, @QueryMap Map<String, String> queries);

    @GET("products/categories")
    Call<List<CategoriesItem>> subCategories (@QueryMap Map<String, String> queries);

    @GET("products/")
    Call<List<Response>> listProductInCategory(@QueryMap Map<String, String> queries);

}
