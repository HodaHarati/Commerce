package com.example.commerce.view.listProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inspector.StaticInspectionCompanionProvider;
import android.widget.Button;

import com.example.commerce.R;
import com.example.commerce.view.category.CategoryPagerActivity;
import com.example.commerce.view.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import retrofit2.http.Header;

public class MainActivity extends SingleFragmentActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return CommerceFragment.newInstance();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.drawer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.drawer);

        initView();
        toolbarSetup();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void toolbarSetup() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.start_drawer, R.string.end_drawer);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home: {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.nav_category: {
                Intent intent = CategoryPagerActivity.newIntent(this, 9);
                startActivity(intent);
                break;
            }
            case R.id.nav_shoppingcart: {
                break;
            }
            case R.id.nav_newest: {
                Intent intent = AllProductActivity.newIntent(this, "date", " ");
                startActivity(intent);
                break;
            }
            case R.id.nav_mostVisited: {
                Intent intent = AllProductActivity.newIntent(this, "popularity", " ");
                startActivity(intent);
                break;
            }
            case R.id.nav_best: {
                Intent intent = AllProductActivity.newIntent(this, "rating", " ");
                startActivity(intent);
                break;
            }
            case R.id.nav_about: {
                Intent intent = LoginActivity.newIntent(this);
                startActivity(intent);
                break;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



/*
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }*/
}
