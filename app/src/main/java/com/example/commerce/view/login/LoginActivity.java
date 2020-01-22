package com.example.commerce.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.commerce.R;
import com.example.commerce.view.listProduct.SingleFragmentActivity;

public class LoginActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return LoginFragment.newInstance();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.single_fragment_activit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
