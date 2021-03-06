package com.example.commerce.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.commerce.model.product.CartProduct;
import com.example.commerce.model.product.CategoriesItem;
import com.example.commerce.model.product.Response;
import com.example.commerce.network.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository mProductRepository;
    private MutableLiveData<List<CategoriesItem>> mAllCategoriesLiveData;
    private MutableLiveData<List<Response>> mNewestProductLiveData;
    private MutableLiveData<List<Response>> mMostVisitedLiveData;
    private MutableLiveData<List<Response>> mBestLiveData;
    private MutableLiveData<Response> mItemProductLiveData;


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

/*
    public List<Response> searchProduct(List<Response> listProduct, String query) {

            List<Response> list = new ArrayList<>();
            for (Response response : listProduct) {
                if (response.getName().contains(query.trim())) ;
                list.add(response);
            }
        return list;
    }*/

   /* public void insert(Response responseID) {
        mProductRepository.insert(responseID);
    }*/

    public MutableLiveData<List<Response>> getProductOfCart(int[] Ids) {
        return mProductRepository.getProductOfCart(Ids);
    }

   /* public Integer[] getItemsOfCart() {
        Realm realm = Realm.getDefaultInstance();
        ArrayList<Integer> listId = new ArrayList<>();
        for (CartProduct cartProduct: realm.where(CartProduct.class).findAll()) {
            listId.add(cartProduct.getId());
        }
        return listId.toArray(new Integer[0]);
    }*/


    public void deleteRecord(Context application){
        Realm.init(application);
        Realm realm;
        realm = Realm.getDefaultInstance();
        RealmResults<CartProduct> results = realm.where(CartProduct.class).findAll();

        realm.beginTransaction();

        results.deleteAllFromRealm();

        realm.commitTransaction();
    }
}
