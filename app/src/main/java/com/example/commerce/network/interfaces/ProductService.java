package com.example.commerce.network.interfaces;

import com.example.commerce.model.Response;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ProductService {

    @GET("products")
    Call<List<Response>> getResponse(@QueryMap Map<String, String> queries);
}
