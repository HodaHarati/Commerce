package com.example.commerce.viewmodel;

import android.app.Application;
import android.app.ListActivity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.commerce.model.user.User;
import com.example.commerce.network.CustomerRepository;

import java.util.List;

public class CustomerViewModel extends AndroidViewModel {

    public static final String TAG = "customer_viewmodel";
    private CustomerRepository mCustomerRepository;
    private MutableLiveData<List<User>> mAllCustomerLivedata;

    public MutableLiveData<List<User>> getAllCustomerLivedata() {
        return mAllCustomerLivedata;
    }

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance(application);
        mAllCustomerLivedata = mCustomerRepository.getListCustomerLivedata();
    }

    public MutableLiveData<List<User>> getAllCustomer() {
        return mCustomerRepository.getAllCustomer();
    }

    public void postCustomer(User user) {
        mCustomerRepository.postCustomer(user);
        Log.d(TAG, "postCustomer: " + "post is ok");
    }
}
