package com.example.commerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commerce.R;
import com.example.commerce.databinding.CategoryListBinding;
import com.example.commerce.databinding.ItemListBinding;
import com.example.commerce.databinding.ItemProductSubcategoryBinding;
import com.example.commerce.databinding.ItemSubcategoryBinding;
import com.example.commerce.databinding.SubcategoryBinding;
import com.example.commerce.model.CategoriesItem;
import com.example.commerce.model.Response;
import com.example.commerce.view.CategoryPagerActivity;
import com.example.commerce.view.ItemOfProductActivity;
import com.example.commerce.view.ProductOfSubcategoryActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List mItems;
    private String mTag;

    public void setItems(List items) {
        mItems = items;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public ProductAdapter(Context context, List items, String tag) {
        mContext = context;
        mItems = items;
        mTag = tag;
    }

    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof CategoriesItem && mTag.equals("CommerceFragment"))
            return 0;
        else if (mItems.get(position) instanceof Response && mTag.equals("CommerceFragment"))
            return 1;
        else if (mItems.get(position) instanceof CategoriesItem && mTag.equals("CategoryPagerFragment"))
            return 2;
        else if (mItems.get(position) instanceof Response && mTag.equals("ProductOfSubcategory"))
            return 3;
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case 0:
                CategoryListBinding binding2 = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.category_list, parent, false);
                viewHolder = new CategoryHolder(binding2);
                break;
            case 1:
                ItemListBinding binding1 = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_list, parent, false);
                viewHolder = new ProductHolder(binding1);
                break;
            case 2:
                SubcategoryBinding binding3 = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.subcategory, parent, false);
                viewHolder = new SubCategoryHolder(binding3);
                break;
            case 3:
                ItemProductSubcategoryBinding binding4 = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_product_subcategory, parent, false);
                viewHolder = new ProductOfSubCategoryHolder(binding4);
                break;
            default:
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0)
            ((CategoryHolder) holder).bind((CategoriesItem) mItems.get(position));
        else if (holder.getItemViewType() == 1)
            ((ProductHolder) holder).bind((Response)(mItems.get(position)));
        else if (holder.getItemViewType() == 2)
            ((SubCategoryHolder) holder).bind((CategoriesItem) mItems.get(position));
        else if (holder.getItemViewType() == 3)
            ((ProductOfSubCategoryHolder) holder).bind((Response) mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        ItemListBinding mListBinding;
        Response mResponse;

        public ProductHolder(@NonNull ItemListBinding itemView) {
            super(itemView.getRoot());
            mListBinding = itemView;

            mListBinding.imgListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent = ItemOfProductActivity.newIntent(mContext, mResponse.getId());
                        mContext.startActivity(intent);
                }
            });
        }

        public void bind(Response response) {
            mResponse = response;
            mListBinding.txtNameListItem.setText(mResponse.getName());
            mListBinding.txtPriceListItem.setText(mResponse.getPrice() + " تومان");
            Picasso.with(mContext)
                    .load(response.getImages().get(0).getSrc())
                    .placeholder(R.drawable.image_loading)
                    .into(mListBinding.imgListItem);
        }
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        CategoryListBinding mCategoryListBinding;
        CategoriesItem mCategoriesItem;

        public CategoryHolder(@NonNull CategoryListBinding itemView) {
            super(itemView.getRoot());
            mCategoryListBinding = itemView;

            mCategoryListBinding.btnCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        Intent intent = CategoryPagerActivity.newIntent(mContext, mCategoriesItem.getId());
                        mContext.startActivity(intent);
                }
            });
        }

        public void bind(CategoriesItem categoriesItem) {
            mCategoriesItem = categoriesItem;
            mCategoryListBinding.btnCategory.setText(mCategoriesItem.getName());
        }
    }

    public class SubCategoryHolder extends RecyclerView.ViewHolder {
        SubcategoryBinding mSubcategoryBinding;
        CategoriesItem mCategoriesItem;

        public SubCategoryHolder(@NonNull SubcategoryBinding itemView) {
            super(itemView.getRoot());
            mSubcategoryBinding = itemView;

           mSubcategoryBinding.txtSubcategory.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    Intent intent = ProductOfSubcategoryActivity.newIntent(mContext, mCategoriesItem.getId());
                    mContext.startActivity(intent);
               }
           });

        }
        public void bind(CategoriesItem categoriesItem) {
            mCategoriesItem = categoriesItem;
            mSubcategoryBinding.txtSubcategory.setText(mCategoriesItem.getName());
/*            Picasso.with(mContext)
                    .load(mCategoriesItem.getImagesItem().getSrc())
                    .placeholder(R.drawable.image_loading)
                    .into(mSubcategoryBinding.imgSubcategory);
        }*/
        }
    }

    public class ProductOfSubCategoryHolder extends RecyclerView.ViewHolder {
        ItemProductSubcategoryBinding mProductSubcategoryBinding;
        Response mResponse;

        public ProductOfSubCategoryHolder(@NonNull ItemProductSubcategoryBinding itemView) {
            super(itemView.getRoot());
            mProductSubcategoryBinding = itemView;

            //listener
        }
        public void bind(Response response) {
            mResponse = response;
            mProductSubcategoryBinding.txtNameProductSubcategory.setText(response.getName());
            mProductSubcategoryBinding.txtOrginalpriceProductSubcategory.setText(mResponse.getRegularPrice() + " تومان ");
            String price = response.getSalePrice();
            if (price != null && !price.isEmpty()) {
                mProductSubcategoryBinding.txtSalePriceProductSubcategory.setText(response.getSalePrice() + " تومان ");
                mProductSubcategoryBinding.txtSalePriceProductSubcategory.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                mProductSubcategoryBinding.txtOrginalpriceProductSubcategory.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                mProductSubcategoryBinding.txtOrginalpriceProductSubcategory.setTextColor(Color.RED);
            }else {
                mProductSubcategoryBinding.txtOrginalpriceProductSubcategory.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                mProductSubcategoryBinding.txtSalePriceProductSubcategory.setVisibility(View.GONE);
            }
            Picasso.with(mContext)
                    .load(response.getImages().get(0).getSrc())
                    .placeholder(R.drawable.image_loading)
                    .into(mProductSubcategoryBinding.imgProductSubcategory);
        }
    }
}
