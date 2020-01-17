package com.example.commerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.commerce.R;

public class AllProductActivity extends SingleFragmentActivity {

    public static final String EXTRA_TYPE_OF_LIST = "type_Of_list";

    public static Intent newIntent(Context context, String typeOfList) {
        Intent intent = new Intent(context, AllProductActivity.class);
        intent.putExtra(EXTRA_TYPE_OF_LIST, typeOfList);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        String typeOfList = getIntent().getStringExtra(EXTRA_TYPE_OF_LIST);
        return AllProductFragment.newInstance(typeOfList);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.single_fragment_activit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_all_product);
    }
}
