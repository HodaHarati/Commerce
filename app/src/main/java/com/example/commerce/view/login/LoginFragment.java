package com.example.commerce.view.login;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.commerce.R;
import com.example.commerce.databinding.FragmentLoginBinding;
import com.example.commerce.model.user.User;
import com.example.commerce.viewmodel.CustomerViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public static final int LOGIN_FRAGMENT_REQUEST_CODE = 0;
    public static final String TAG_SIGNUP_FRAGMENT = "signup_fragment";
    private FragmentLoginBinding mBinding;
    private CustomerViewModel mCustomerViewModel;
    private boolean flag = false;

    public static LoginFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);
        mCustomerViewModel.getAllCustomerLivedata().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user: users) {
                    if (user.getEmail().equals(mBinding.loginUsername.getText().toString()))
                        flag = true;
                }
            }
        });
        mCustomerViewModel.getAllCustomer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        initListener();
        return mBinding.getRoot();
    }

    public void initListener() {
        mBinding.loginSignUp.setOnClickListener(view -> {
            FragmentManager fragmentManager = getFragmentManager();
            SignUpFragment signupFragment =SignUpFragment.newInstance();
            signupFragment.setTargetFragment(LoginFragment.this , LOGIN_FRAGMENT_REQUEST_CODE);

            fragmentManager.beginTransaction()// add to backStack baraye chiye???????????
                    .replace(R.id.container_fragment , signupFragment , TAG_SIGNUP_FRAGMENT)
                    .addToBackStack(SignUpFragment.TAG)
                    .commit();
        });

        mBinding.loginLogin.setOnClickListener(view -> {
            if (mBinding.loginUsername.getText().equals("") || mBinding.loginPass.getText().equals(""))
                Toast.makeText(getContext(), "لطفا نام کاربری و کلمه عبور را وارد نمایید", Toast.LENGTH_LONG).show();
            else if (flag == false)
                Toast.makeText(getContext(), "این نام کاربری وجود ندارد", Toast.LENGTH_LONG).show();
        });
    }

}