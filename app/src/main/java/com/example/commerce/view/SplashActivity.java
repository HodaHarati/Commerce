package com.example.commerce.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.commerce.R;
import com.example.commerce.viewmodel.ProductViewModel;

public class SplashActivity extends AppCompatActivity {

    private ProductViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 8000);

        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mViewModel.getAllCategories();
        mViewModel.getAllNewestProduct();
        mViewModel.getBestProduct();
    }
}
