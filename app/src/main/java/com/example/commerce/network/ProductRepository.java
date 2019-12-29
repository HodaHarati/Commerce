package com.example.commerce.network;

import androidx.lifecycle.MutableLiveData;

import com.example.commerce.model.Response;
import com.example.commerce.network.interfaces.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductRepository {

    private static ProductRepository sInstance;

    private String TAG = "ProductRepository";
    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_afcde41bdfa7c7ab871bd26f950ce0101ac96c92";
    public static final String CONSUMER_SECRET = "cs_48ed218ae80a2d28cf1b88378f66f75ead30d99a";

    private Map<String, String> mQueries;
    private Retrofit mRetrofit;
    private ProductService mProductService;
    private MutableLiveData<List<Response>> mNewestLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Response>> mMostvisitedLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Response>> mBestLiveData = new MutableLiveData<>();
    private MutableLiveData<Response> mItemProductLiveData = new MutableLiveData<>();


    public static ProductRepository getInstance() {
        if (sInstance == null)
            sInstance = new ProductRepository();
        return sInstance;
    }

    private ProductRepository(){
        mQueries = new HashMap<>();
        mQueries.put("consumer_key", CONSUMER_KEY);
        mQueries.put("consumer_secret", CONSUMER_SECRET);

        mRetrofit = new Retrofit.Builder()     //create object from Retrofit
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mProductService = mRetrofit.create(ProductService.class);    // create object from Interface
    }

    public MutableLiveData<List<Response>> getNewestLiveData() {
        return mNewestLiveData;
    }

    public MutableLiveData<List<Response>> getMostvisitedLiveData() {
        return mMostvisitedLiveData;
    }

    public MutableLiveData<List<Response>> getBestLiveData() {
        return mBestLiveData;
    }

    public MutableLiveData<Response> getItemProductLiveData() {
        return mItemProductLiveData;
    }

    public MutableLiveData<List<Response>> getNewestProduct(){
        mQueries.put("orderby", "date");
        Call<List<Response>> call = mProductService.getResponse(mQueries);
        getEnqueue(call, mNewestLiveData);
        return mNewestLiveData;
    }

    public MutableLiveData<List<Response>> getMostVisitedProduct(){
        mQueries.put("orderby", "popularity");
        Call<List<Response>> call = mProductService.mostVisitedProducts(mQueries);
        getEnqueue(call, mMostvisitedLiveData);
        return mMostvisitedLiveData;
    }

    public MutableLiveData<List<Response>> getBestProduct(){
        mQueries.put("orderby", "rating");
        Call<List<Response>> call = mProductService.bestProduct(mQueries);
        getEnqueue(call, mBestLiveData);
        return mBestLiveData;
    }

    private void getEnqueue(Call<List<Response>> call, MutableLiveData<List<Response>> liveData) {
        call.enqueue(new Callback<List<Response>>() {
            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<Response> getItem(int productid){
        Call<Response> call = mProductService.item(String.valueOf(productid), mQueries);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                mItemProductLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
        return mItemProductLiveData;
    }
}
