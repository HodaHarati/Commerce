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
    private MutableLiveData<List<Response>> mNewestProductLiveData;
    private MutableLiveData<List<Response>> mMostVisitedLiveData;
    private MutableLiveData<List<Response>> mBestLiveData;
    private MutableLiveData<Response> mItemProductLiveData;


    public MutableLiveData<List<Response>> getNewestProductLiveData() {
        return mNewestProductLiveData;
    }

    public MutableLiveData<List<Response>> getMostVisitedLiveData() {
        return mMostVisitedLiveData;
    }

    public MutableLiveData<List<Response>> getBestLiveData() {
        return mBestLiveData;
    }

    public MutableLiveData<Response> getItemProductLiveData() {
        return mItemProductLiveData;
    }

    public CommerceFragmentViewModel(@NonNull Application application) {
        super(application);

        mProductRepository = ProductRepository.getInstance();
        mNewestProductLiveData = mProductRepository.getNewestLiveData();
        mMostVisitedLiveData = mProductRepository.getMostvisitedLiveData();
        mBestLiveData = mProductRepository.getBestLiveData();
        mItemProductLiveData = mProductRepository.getItemProductLiveData();

    }

    public MutableLiveData<List<Response>> getAllNewestProduct(){
       return mProductRepository.getNewestProduct();
    }

    public MutableLiveData<List<Response>> getMostVisitedProduct(){
        return mProductRepository.getMostVisitedProduct();
    }

    public MutableLiveData<List<Response>> getBestProduct(){
        return mProductRepository.getBestProduct();
    }

    public MutableLiveData<Response> getItem(int productid){
        return mProductRepository.getItem(productid);
    }

}
