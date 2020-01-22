package com.example.commerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.commerce.R;
import com.example.commerce.databinding.ItemProductSubcategoryBinding;
import com.example.commerce.model.product.Response;
import com.example.commerce.view.category.ItemOfProductActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.AllProductHolder> {

    private Context mContext;
    private List<Response> mResponseList;

    public AllProductAdapter(Context context, List<Response> responseList) {
        mContext = context;
        mResponseList = responseList;
    }

    public void setResponseList(List<Response> responseList) {
        mResponseList = responseList;
    }

    @NonNull
    @Override
    public AllProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductSubcategoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_product_subcategory, parent,false);
        return new AllProductHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductHolder holder, int position) {
        holder.bind(mResponseList.get(position));

    }

    @Override
    public int getItemCount() {
        return mResponseList.size();
    }

    public class AllProductHolder extends RecyclerView.ViewHolder {
        private Response mResponse;
        ItemProductSubcategoryBinding binding;

        public AllProductHolder(ItemProductSubcategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = ItemOfProductActivity.newIntent(mContext, mResponse.getId());
                    mContext.startActivity(intent);
                }
            });
        }

        public void bind (Response response) {
            mResponse = response;
            binding.txtNameProductSubcategory.setText(response.getName());
            binding.txtOrginalpriceProductSubcategory.setText(mResponse.getRegularPrice() + " تومان ");
            String price = response.getSalePrice();
            if (price != null && !price.isEmpty()) {
                binding.txtSalePriceProductSubcategory.setText(response.getSalePrice() + " تومان ");
                binding.txtSalePriceProductSubcategory.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                binding.txtOrginalpriceProductSubcategory.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                binding.txtOrginalpriceProductSubcategory.setTextColor(Color.RED);
            }else {
                binding.txtOrginalpriceProductSubcategory.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                binding.txtSalePriceProductSubcategory.setVisibility(View.GONE);
            }
            Picasso.with(mContext)
                    .load(response.getImages().get(0).getSrc())
                    .placeholder(R.drawable.image_loading)
                    .into(binding.imgProductSubcategory);
        }
    }
}
