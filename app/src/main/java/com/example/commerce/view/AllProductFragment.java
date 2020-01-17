package com.example.commerce.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commerce.R;
import com.example.commerce.adapter.AllProductAdapter;
import com.example.commerce.databinding.FragmentAllProductBinding;
import com.example.commerce.model.Response;
import com.example.commerce.viewmodel.OrderProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllProductFragment extends Fragment {

    public static final String ARG_TYPE_OF_LIST = "type_of_list";
    private FragmentAllProductBinding mBinding;
    private OrderProductViewModel mViewModel;
    private AllProductAdapter mAdapter;
    String typeOfList;
    int page =1;

    public static AllProductFragment newInstance(String typeOfList) {

        Bundle args = new Bundle();
        args.putString(ARG_TYPE_OF_LIST, typeOfList);
        AllProductFragment fragment = new AllProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AllProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeOfList = getArguments().getString(ARG_TYPE_OF_LIST);
        mViewModel = ViewModelProviders.of(this).get(OrderProductViewModel.class);
        mViewModel.getAllProduct(typeOfList, page).observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(List<Response> responseList) {
                setUpAllProductAdapter(responseList);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_product, container, false);
        mBinding.recyclerAllProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return mBinding.getRoot();
    }

    public void setUpAllProductAdapter(List<Response> responseList) {
        if (isAdded()) {
            mAdapter = new AllProductAdapter(getContext(), responseList);
            mBinding.recyclerAllProduct.setAdapter(mAdapter);
           /* mBinding.recyclerAllProduct.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    if (mBinding.recyclerAllProduct.getScrollState() == mBinding.recyclerAllProduct.SCROLL_STATE_SETTLING && page <10)
                        page++;
                    mViewModel.getAllProduct(typeOfList, page);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });*/
        }else {
            mAdapter.setResponseList(responseList);
            mAdapter.notifyDataSetChanged();
        }
    }

}
