package com.example.commerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.commerce.R;

public class ItemOfProductActivity extends SingleFragmentActivity {

    public static final String EXTRA_PRODUCT_ID = "product_id";

    public static Intent newIntent(Context context, int productID){
        Intent intent = new Intent(context, ItemOfProductActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productID);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        int id = getIntent().getIntExtra(EXTRA_PRODUCT_ID, 0);
        return ItemOfProductFragment.newInstance(id);
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_of_product);
    }*/
}
