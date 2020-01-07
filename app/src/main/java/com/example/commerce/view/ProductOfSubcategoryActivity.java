package com.example.commerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.commerce.R;

public class ProductOfSubcategoryActivity extends SingleFragmentActivity {

    public static final String EXTRA_CATEGORY_ID = "categoryId";

    public static Intent newIntent(Context context, int categoryId) {
        Intent intent = new Intent(context, ProductOfSubcategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        int categoryid = getIntent().getIntExtra(EXTRA_CATEGORY_ID, 0);
        return ProductOfSubcategoryFragment.newInstance(categoryid);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_prodyct_of_subcategory;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_prodyct_of_subcategory);
    }
}
