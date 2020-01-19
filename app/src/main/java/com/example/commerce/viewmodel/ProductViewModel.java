package com.example.commerce.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.commerce.model.product.CategoriesItem;
import com.example.commerce.model.product.Response;
import com.example.commerce.network.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository mProductRepository;
    private MutableLiveData<List<CategoriesItem>> mAllCategoriesLiveData;
    private MutableLiveData<List<Response>> mNewestProductLiveData;
    private MutableLiveData<List<Response>> mMostVisitedLiveData;
    private MutableLiveData<List<Response>> mBestLiveData;
    private MutableLiveData<List<Response>> mListAllProduct;
    private MutableLiveData<Response> mItemProductLiveData;

    private LiveData<List<Response>> mListProductID;
    private MutableLiveData<List<Response>> mListResponseOfCart = new MutableLiveData<>();

    public MutableLiveData<List<CategoriesItem>> getAllCategoriesLiveData() {
        return mAllCategoriesLiveData;
    }

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

    public LiveData<List<Response>> getListProductID() {
        return mListProductID;
    }

    public ProductViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance(application);
        mAllCategoriesLiveData = mProductRepository.getAllCategoriesItemLiveData();
        mNewestProductLiveData = mProductRepository.getNewestLiveData();
        mMostVisitedLiveData = mProductRepository.getMostvisitedLiveData();
        mBestLiveData = mProductRepository.getBestLiveData();
        mItemProductLiveData = mProductRepository.getItemProductLiveData();

    }

    public MutableLiveData<List<CategoriesItem>> getAllCategories() {
        Log.d("ViewModel", "getAllCategories: " + mProductRepository.getAllCategories());
        return mProductRepository.getAllCategories();
    }

    public MutableLiveData<List<Response>> getAllNewestProduct() {
        return mProductRepository.getNewestProduct();
    }

    public MutableLiveData<List<Response>> getMostVisitedProduct() {
        return mProductRepository.getMostVisitedProduct();
    }

    public MutableLiveData<List<Response>> getBestProduct() {
        return mProductRepository.getBestProduct();
    }

    public MutableLiveData<List<Response>> getListProductInCategory(int categoryId) {
        return mProductRepository.getListProductInCategory(categoryId);
    }

    public MutableLiveData<Response> getItem(int productid) {
        return mProductRepository.getItem(productid);
    }

    public MutableLiveData<List<CategoriesItem>> getSubCategories(int parentid){
        return mProductRepository.getSubCategories(parentid);
    }

    public List<Response> searchProduct(List<Response> listProduct, String query) {

            List<Response> list = new ArrayList<>();
            for (Response response : listProduct) {
                if (response.getName().contains(query.trim())) ;
                list.add(response);
            }
        return list;
    }

    public void insert(Response responseID) {
        mProductRepository.insert(responseID);
    }
    public LiveData<List<Response>> getProductOfCart() {
        mListProductID = mProductRepository.getAllResponseID();
        LiveData<Response> products;
        for (int i = 0; i <getListProductID().getValue().size() ; i++) {
            products = getItem(getListProductID().getValue().get(i).getId());
            mListResponseOfCart.setValue((List<Response>) products);
        }
        return mListResponseOfCart;
    }
}
