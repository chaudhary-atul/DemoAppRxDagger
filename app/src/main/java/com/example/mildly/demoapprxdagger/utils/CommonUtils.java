package com.example.mildly.demoapprxdagger.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.mildly.demoapprxdagger.R;

public class CommonUtils {

    public static Dialog showLoadingDialog(Context context) {

        Dialog progressDialog = new Dialog(context);

        progressDialog.show();

        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
    public static void showInternetConnectivitySnackBar(View v, final Context context) {
        try {
            Snackbar snackbar = Snackbar.make(v, "No Internet Connection", Snackbar.LENGTH_LONG)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isNetworkConnected(context)) {
                                Snackbar snackbar1 = Snackbar.make(v, "Connected with Internet",
                                        Snackbar.LENGTH_LONG);
                                snackbar1.show();
                            } else {
                                Snackbar snackbar1 = Snackbar.make(v, "Check Your Internet Setting",
                                        Snackbar.LENGTH_LONG);
                                snackbar1.show();
                            }

                        }
                    });
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
