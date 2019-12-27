package com.example.commerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commerce.R;
import com.example.commerce.databinding.ItemListBinding;
import com.example.commerce.model.ImagesItem;
import com.example.commerce.model.Response;
import com.example.commerce.view.ItemOfProductActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context mContext;
    private List<Response> mItems;

    private String mProductID;

    public void setItems(List<Response> items) {
        mItems = items;
    }

    public ProductAdapter(Context context, List<Response> items) {
        mContext = context;
        mItems = items;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_list, parent, false);
        return new ProductHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public class ProductHolder extends RecyclerView.ViewHolder{
        ItemListBinding mListBinding;
        Response mResponse;

        public ProductHolder(@NonNull ItemListBinding itemView) {
            super(itemView.getRoot());
            mListBinding = itemView;

            mListBinding.imgListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = ItemOfProductActivity.newIntent(mContext, mProductID);
//                    intent.putExtra(EXTRA_PRODUCT_ID, mProductID);
                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(Response response){
            mResponse = response;
            mProductID = String.valueOf(mResponse.getId());
            mListBinding.txtNameListItem.setText(mResponse.getName());
            mListBinding.txtPriceListItem.setText(mResponse.getPrice());
            for (ImagesItem imagesItem : response.getImages()) {
                Picasso.with(mContext).load(imagesItem.getSrc())
                        .placeholder(R.drawable.image_loading)
                        .into(mListBinding.imgListItem);/////  aya kheili barname sangin nemishad???

            }

        }
    }
}
