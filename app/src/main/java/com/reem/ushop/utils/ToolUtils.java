package com.reem.ushop.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

public class ToolUtils {
    public static boolean isNetworkConnected() {
        boolean HaveConnectedWifi = false;
        boolean HaveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) APP.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equals("WIFI")) ;
            if (ni.isConnected())
                HaveConnectedWifi = true;
            if (ni.getTypeName().equals("MOBILE"))
                if (ni.isConnected())
                    HaveConnectedMobile = true;
        }
        return HaveConnectedWifi || HaveConnectedMobile;
    }
    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (imm != null) {
                if (view == null) {
                    view = new View(activity);
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }
    public static void setColorIcon(Context context, ImageView imageView, int color){
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(ContextCompat.getColor(context,color)));
    }
}
