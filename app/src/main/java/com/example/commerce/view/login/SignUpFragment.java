package com.example.commerce.view.login;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.commerce.R;
import com.example.commerce.databinding.FragmentSignUpBinding;
import com.example.commerce.model.user.User;
import com.example.commerce.viewmodel.CustomerViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    public static final String TAG = "SignUpFragment";
    public static final String EXTRA_USERNAME = "username";
    public static final String EXTRA_PASSWORD = "password";
    private FragmentSignUpBinding mBinding;
    private CustomerViewModel mCustomerViewModel;
    private String mUsername;
    private String mPass;
    private boolean flag = false;

    public static SignUpFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);
        /*mCustomerViewModel.getAllCustomerLivedata().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user: users) {
                    if (user.getEmail().equals(mBinding.signUsername.getText().toString()))
                        flag = true;
                }
            }
        });
        mCustomerViewModel.getAllCustomer();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        mBinding.executePendingBindings();
       // View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initListener();
        return mBinding.getRoot();
    }

    public void initListener() {
        mBinding.signSign.setOnClickListener(view -> {
            mUsername = mBinding.signUsername.getText().toString();
            mPass = mBinding.signPass.getText().toString();
            if (mBinding.signUsername.getText().toString().equals("") || mBinding.signPass.getText().toString().equals(""))
                Toast.makeText(getContext(), "لطفا نام کاربری و کلمه عبور را وارد نمایید", Toast.LENGTH_LONG).show();
            else if (flag == true)
                Toast.makeText(getContext(), "این نام کاربری قبلا ثبت شده است", Toast.LENGTH_LONG).show();
            else {
                User user = new User(mUsername);
                mCustomerViewModel.postCustomer(user);
                Intent intent = new Intent();
                intent.putExtra(EXTRA_USERNAME, user.getEmail());
                intent.putExtra(EXTRA_PASSWORD, user.getPassword());
                Fragment fragment = getTargetFragment();
                fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                getActivity().getSupportFragmentManager()
                             .beginTransaction()
                             .remove(this)
                             .commit();
            }
        });
    }

}
