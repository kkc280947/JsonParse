package com.example.cbluser3.jsonparse;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.view.View;




/**
 * Created by HOME on 3/3/2015.
 */
public class ConnectionDetector {

    private Activity context;

    public ConnectionDetector(Activity context) {
        this.context = context;
    }

    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null
                    && activeNetwork.isConnected();
             /*  context.sendBroadcast(new Intent(Constants.NETWORK_BROADCAST));*/
            return isConnected;
        }
        return false;
    }

/*    public void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }*/

  /*  public void createLoginExpiredDialog() {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(context.getResources().getString(R.string.login_expired))
                .setMessage(context.getResources().getString(R.string.login_again))
                .setPositiveButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PrefsManager.with(context).removeAll();
                        dialog.dismiss();
                        context.startActivity(new Intent(context, LoginActivity.class));
                        context.finishAffinity();
                    }
                }).show();

    }*/
}
