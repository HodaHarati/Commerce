package com.example.commerce.network;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.commerce.model.user.User;
import com.example.commerce.network.interfaces.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerRepository {

    private static CustomerRepository sInstance;

    private String TAG = "CustomerRepository";
    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_afcde41bdfa7c7ab871bd26f950ce0101ac96c92";
    public static final String CONSUMER_SECRET = "cs_48ed218ae80a2d28cf1b88378f66f75ead30d99a";

    private Map<String, String> mQueries;
    private Retrofit mRetrofit;
    private ProductService mProductService;
    
    private MutableLiveData<List<User>> mListCustomerLivedata = new MutableLiveData<>();

    public static CustomerRepository getInstance(Application application) {
        if (sInstance == null)
            sInstance = new CustomerRepository(application);
        return sInstance;
    }

    private CustomerRepository(Application application) {
        mQueries = new HashMap<>();
        mQueries.put("consumer_key", CONSUMER_KEY);
        mQueries.put("consumer_secret", CONSUMER_SECRET);

        mRetrofit = new Retrofit.Builder()     //create object from Retrofit
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mProductService = mRetrofit.create(ProductService.class);    // create object from Interface
    }

    public MutableLiveData<List<User>> getListCustomerLivedata() {
        return mListCustomerLivedata;
    }
    
    public MutableLiveData<List<User>> getCustomer() {
        HashMap<String, String> map = new HashMap<>();
        map.putAll(mQueries);
        Call<List<User>> call = mProductService.AllUsers(map);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    mListCustomerLivedata.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t );
            }
        });
        return mListCustomerLivedata;
    }

    public void postCustomer(User user) {
        HashMap<String, String> map = new HashMap<>();
        map.putAll(mQueries);
        Call<User> call = mProductService.createCustomer(map, user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful())
                    Log.i(TAG, "onResponse: " + "post is successful");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t );
            }
        });
    }
}
