package com.example.commerce.view.listProduct;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.commerce.R;
import com.example.commerce.view.networkCheck.NetworkActivity;

public class AllProductActivity extends NetworkActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_TYPE_OF_LIST = "type_Of_list";
    public static final String EXTRA_QUERY = "query";
    public static final String TAG_ORDERCLASS = "orderclass";
    private Toolbar mToolbar;
    private TextView mTxtOrderby, mTxtFilter;
    String mTypeOfList;
    String mQuery;

    public static Intent newIntent(Context context, String typeOfList, String query) {
        Intent intent = new Intent(context, AllProductActivity.class);
        intent.putExtra(EXTRA_TYPE_OF_LIST, typeOfList);
        intent.putExtra(EXTRA_QUERY, query);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);
        mTypeOfList = getIntent().getStringExtra(EXTRA_TYPE_OF_LIST);
        mQuery = getIntent().getStringExtra(EXTRA_QUERY);
        setUpToolbar();
        initView();

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_all_product);//
        if (fragment == null) {
            if (mTypeOfList.equals("search")) {
                fragmentManager
                        .beginTransaction()
                        .add(R.id.container_all_product, AllProductSearchFragment.newInstance(mQuery))
                        .commit();
            } else {
                fragmentManager
                        .beginTransaction()
                        .add(R.id.container_all_product, AllProductFragment.newInstance(mTypeOfList))
                        .commit();
            }
        }
    }

    private void setUpToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void initView() {
        mTxtOrderby = findViewById(R.id.orderby);
        mTxtFilter = findViewById(R.id.filter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String orderby;
        String text = adapterView.getItemAtPosition(i).toString();
        switch (text){
            case "جدید ترین" : {
                orderby = "date";
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_all_product, AllProductFragment.newInstance(orderby))
                        .commit();
                break;
            }
            case "قیمت از کم به زیاد" : {
                orderby = "price";
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_all_product, AllProductFragment.newInstance(orderby))
                        .commit();
                break;
            }
            case "قیمت از زیاد به کم" : {
                orderby = "price-desc";
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_all_product, AllProductFragment.newInstance(orderby))
                        .commit();
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
