package com.example.commerce.view.login;


import android.app.Activity;
import android.content.Intent;
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
import com.example.commerce.view.listProduct.MainActivity;
import com.example.commerce.viewmodel.CustomerViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public static final int LOGIN_FRAGMENT_REQUEST_CODE = 0;
    public static final String TAG_LOGIN_FRAGMENT = "login_fragment";
    private FragmentLoginBinding mBinding;
    private CustomerViewModel mCustomerViewModel;
    private String username;
    private String password;
    private String str;

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

            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_fragment, signupFragment)
                    .addToBackStack(SignUpFragment.TAG)
                    .commit();

        });

        mBinding.loginLogin.setOnClickListener(view -> {
            mCustomerViewModel.getUserByEmail(mBinding.loginUsername.getText().toString());
            mCustomerViewModel.getUserLiveData().observe(this, new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    str = user.getEmail();
                }
            });


            if (mBinding.loginUsername.getText().equals("") || mBinding.loginPass.getText().equals(""))
                Toast.makeText(getContext(), "لطفا نام کاربری و کلمه عبور را وارد نمایید", Toast.LENGTH_LONG).show();
            else if (str==null)
                Toast.makeText(getContext(), "این نام کاربری وجود ندارد لطفا ثبت نام کنید", Toast.LENGTH_LONG).show();
            else {
                Intent intent = MainActivity.newIntent(getActivity());
                startActivity(intent);
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == LOGIN_FRAGMENT_REQUEST_CODE){
            username = data.getStringExtra(SignUpFragment.EXTRA_USERNAME);
            password = data.getStringExtra(SignUpFragment.EXTRA_PASSWORD);
            mBinding.loginUsername.setText(username);
            mBinding.loginPass.setText(password);
        }
    }

}
