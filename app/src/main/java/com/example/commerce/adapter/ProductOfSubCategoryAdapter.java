package com.example.commerce.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commerce.model.Response;

import java.util.List;

public class ProductOfSubCategoryAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Response> mProduct;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    public class ProductOfSubCategoryHolder extends RecyclerView.ViewHolder{

        public ProductOfSubCategoryHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
