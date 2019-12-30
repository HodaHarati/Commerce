package com.example.commerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.commerce.R;
import com.example.commerce.model.Response;
import com.example.commerce.network.ProductRepository;
import com.example.commerce.network.interfaces.ProductService;

import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;

public class SplashActivity extends AppCompatActivity {

    private MutableLiveData<List<Response>> mNewestLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Response>> mMostvisitedLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Response>> mBestLiveData = new MutableLiveData<>();
    private ProductRepository mRepository;
    private ProductService mProductService;
    private Retrofit mRetrofit;
    private Map<String, String> mQueries;

    public MutableLiveData<List<Response>> getNewestLiveData() {
        return mNewestLiveData;
    }

    public MutableLiveData<List<Response>> getMostvisitedLiveData() {
        return mMostvisitedLiveData;
    }

    public MutableLiveData<List<Response>> getBestLiveData() {
        return mBestLiveData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 8000);
    }

    /*public MutableLiveData<List<Response>> getNewestProduct(){
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

    private void getEnqueue(Call<List<Response>> call, MutableLiveData<List<Response>> liveData) { call.enqueue(new Callback<List<Response>>() {
        @Override
        public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
            liveData.postValue(response.body());
        }

        @Override
        public void onFailure(Call<List<Response>> call, Throwable t) {

        }
    });
    }
    */
}
