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
import com.example.commerce.model.product.Response;
import com.example.commerce.view.ItemOfProductActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private Context mContext;
    private List<Response> mResponseList;

    public ListAdapter(Context context, List<Response> responseList) {
        mContext = context;
        mResponseList = responseList;
    }

    public void setResponseList(List<Response> responseList) {
        mResponseList = responseList;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_list, parent, false);
        return new ListHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        holder.bind(mResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return mResponseList.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        private Response mResponse;
        private ItemListBinding binding;

        public ListHolder(ItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.imgListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = ItemOfProductActivity.newIntent(mContext, mResponse.getId());
                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(Response response) {
            mResponse = response;
            binding.txtNameListItem.setText(mResponse.getName());
            binding.txtPriceListItem.setText(mResponse.getPrice() + " تومان");
            Picasso.with(mContext)
                    .load(response.getImages().get(0).getSrc())
                    .placeholder(R.drawable.image_loading)
                    .into(binding.imgListItem);
        }
    }

}
