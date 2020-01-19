package com.example.commerce.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.commerce.model.product.Response;
import com.example.commerce.network.ProductRepository;

import java.util.List;

public class OrderProductViewModel extends AndroidViewModel {

    private ProductRepository mProductRepository;
    private MutableLiveData<List<Response>> mProductList;
    //private MutableLiveData<List<Response>> mAllSearchProduct;

    public MutableLiveData<List<Response>> getProductList() {
        return mProductList;
    }

//    public MutableLiveData<List<Response>> getAllSearchProduct() {
//        return mAllSearchProduct;
//    }

    public OrderProductViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance(application);
        mProductList = mProductRepository.getProductList();
       // mAllSearchProduct = mProductRepository.getProductSearchList();
    }

    public MutableLiveData<List<Response>> getAllProduct(String order, int page) {
        return mProductRepository.getAllProduct(order, page);
    }

    public MutableLiveData<List<Response>> searchProduct(String query, int page) {
        return mProductRepository.searchProduct(query, page);
    }
}
