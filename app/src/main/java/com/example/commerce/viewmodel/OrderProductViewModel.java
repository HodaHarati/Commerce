package com.example.commerce.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.commerce.model.Response;
import com.example.commerce.network.ProductRepository;

import java.util.List;

public class OrderProductViewModel extends AndroidViewModel {

    private ProductRepository mProductRepository;

    public OrderProductViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance(application);
    }

    public MutableLiveData<List<Response>> getAllProduct(String order, int page) {
        return mProductRepository.getAllProduct(order, page);
    }
}
