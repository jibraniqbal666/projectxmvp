package com.example.jibran666.projectxmvp.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NetworkChangeReceiver extends BroadcastReceiver {

    public static ConnectivityReceiverListener connectivityReceiverListener;

    @Override
    public void onReceive(final Context context, final Intent intent) {

        if (connectivityReceiverListener != null) {
            if (NetworkUtil.isNetworkConnected(context)) {
                // Do something
                connectivityReceiverListener.onNetworkConnectionChanged(true);
                Log.d("NetworkChangeReceiver", "Network Available");
            } else {
                connectivityReceiverListener.onNetworkConnectionChanged(false);
                Log.d("NetworkChangeReceiver", "Network Not Available");
            }
        }
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}