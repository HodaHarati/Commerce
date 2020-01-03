package com.example.commerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commerce.R;
import com.example.commerce.databinding.ItemSubcategoryBinding;
import com.example.commerce.model.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAndSearchAdapter extends RecyclerView.Adapter<CategoryAndSearchAdapter.CategoryAndSearchHolder> {

    private Context mContext;
    private List<Response> mResponseList = new ArrayList<>();

    public CategoryAndSearchAdapter(Context context, List<Response> responseList) {
        mContext = context;
        mResponseList = responseList;
    }

    public void setResponseList(List<Response> responseList) {
        mResponseList = responseList;
    }

    @NonNull
    @Override
    public CategoryAndSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSubcategoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_subcategory, parent, false);
        return new CategoryAndSearchHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAndSearchHolder holder, int position) {
        holder.bind(mResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return mResponseList.size();
    }


    public class CategoryAndSearchHolder extends RecyclerView.ViewHolder{

        ItemSubcategoryBinding mItemSubcategoryBinding;
        private Response mResponse;

        public CategoryAndSearchHolder(@NonNull ItemSubcategoryBinding itemView) {
            super(itemView.getRoot());
            mItemSubcategoryBinding = itemView;

            //onclick
        }
            public void bind(Response response) {
                mResponse = response;
                mItemSubcategoryBinding.txtSubcategory.setText(mResponse.getName());
                Picasso.with(mContext)
                        .load(mResponse.getImages().get(0).getSrc())
                        .placeholder(R.drawable.image_loading)
                        .into(mItemSubcategoryBinding.imgSubcategory);
            }
    }

}
