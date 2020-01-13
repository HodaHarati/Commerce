package com.example.commerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commerce.R;
import com.example.commerce.databinding.ItemAddToCartBinding;
import com.example.commerce.model.Response;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    private Context mContext;
    private List<Response> mResponseList;

    public CartAdapter(Context context, List<Response> responseList) {
        mContext = context;
        mResponseList = responseList;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAddToCartBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_add_to_cart, parent, false);
        return new CartHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        holder.bind(mResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return mResponseList.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder{
        private Response mResponse;
        ItemAddToCartBinding binding;


        public CartHolder(ItemAddToCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Response response) {
            mResponse = response;
        }
    }

}
