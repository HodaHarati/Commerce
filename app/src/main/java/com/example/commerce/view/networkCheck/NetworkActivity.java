package com.example.commerce.view.networkCheck;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.example.commerce.R;
import com.example.commerce.databinding.ActivityNetworkBinding;
import com.google.android.material.snackbar.Snackbar;

public class NetworkActivity extends AppCompatActivity  {

    private CheckNetwork mCheckNetworkReceiver = new CheckNetwork();
    private ActivityNetworkBinding mBinding;
    private boolean mBoolean = false;
    private NetworkFragment mNetworkFragment ;
    //private Callbackconnection mCallback;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, NetworkActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_network);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_network);
        mNetworkFragment = NetworkFragment.newInstance();
        initListener();
    }


    private void initListener() {
        mBinding.btnCheckConnection.setOnClickListener(view -> {
             if (mBoolean == false) {//if (!mCallback.isConnect()) {
                Snackbar snackbar = Snackbar
                        .make(view, "اتصال اینترنت خود را بررسی کنید", Snackbar.LENGTH_LONG);

                snackbar.show();
            }else {
                //getSupportFragmentManager().beginTransaction().remove(NetworkFragment.this).commit();
                this.finish();

            }
        });
    }


    public class CheckNetwork extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (isOnline(context))
                mBoolean = true;
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
        registerReceiver(mCheckNetworkReceiver,intentFilter);  // az inienti ke ferestade mishavad estefadei nemishavad dar methode bala
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(mCheckNetworkReceiver);
    }



   /* @Override
    protected void onResume() {
        super.onResume();
        if (this instanceof NetworkActivity.Callbackconnection)
            mCallback = (NetworkActivity.Callbackconnection) this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCallback = null;
    }

    public interface Callbackconnection {
        boolean isConnect();
    }*/
}
