package com.example.commerce.view;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.commerce.R;
import com.example.commerce.databinding.FragmentNetworkBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkFragment extends Fragment { //implements NetworkActivity.Callbackconnection{

    CheckNetwork mCheckNetworkReceiver = new CheckNetwork();
    private FragmentNetworkBinding mBinding;

    public static NetworkFragment newInstance() {

        Bundle args = new Bundle();

        NetworkFragment fragment = new NetworkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public NetworkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_network, container, false);
        return mBinding.getRoot();
    }


    public class CheckNetwork extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (!isOnline(context)){
                    /*Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_fragment);
                    if (fragment == null)*/
                    /*getSupportFragmentManager().beginTransaction()
                            .add(R.id.container_fragment, NetworkFragment.newInstance())
                            .commit();*/
                    Intent brIntent = NetworkActivity.newIntent(getContext());
                    startActivity(brIntent);
                }
            }catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();

        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        getActivity().registerReceiver(mCheckNetworkReceiver,intentFilter);  // az inienti ke ferestade mishavad estefadei nemishavad dar methode bala
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mCheckNetworkReceiver);
    }



   /* @Override
    public boolean isConnect() {
        return isOnline(getActivity());
    }*/

}
