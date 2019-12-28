package com.example.commerce.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.commerce.model.Response;
import com.example.commerce.network.ProductRepository;

import java.util.List;

public class CommerceFragmentViewModel extends AndroidViewModel {

    private ProductRepository mProductRepository;
    private MutableLiveData<List<Response>> mProductLiveData;
    private MutableLiveData<List<Response>> mMostVisitedLiveData;

    public MutableLiveData<List<Response>> getProductLiveData() {
        return mProductLiveData;
    }

    public MutableLiveData<List<Response>> getMostVisitedLiveData() {
        return mMostVisitedLiveData;
    }

    public CommerceFragmentViewModel(@NonNull Application application) {
        super(application);

        mProductRepository = ProductRepository.getInstance();
        mProductLiveData = mProductRepository.getItemsLiveData();
        mMostVisitedLiveData = mProductRepository.getMostvisitedLiveData();
    }

    public MutableLiveData<List<Response>> getAllProduct(){
       return mProductRepository.getAllProduct();
    }

    public MutableLiveData<List<Response>> getMostVisitedProduct(){
        return mProductRepository.getMostVisitedProduct();
    }
}
