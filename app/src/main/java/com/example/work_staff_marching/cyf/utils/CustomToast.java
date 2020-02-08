package com.example.work_staff_marching.cyf.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 用于管理Toast
 */
public class CustomToast {
    private static Toast mToast;
    private static Handler mhandler = new Handler(Looper.getMainLooper());
    private static Runnable r = new Runnable() {
        public void run() {
            if (null != mToast) {
                mToast.cancel();
            }
        }

        ;
    };

    public static void showToast(Context context, String text) {
        mhandler.removeCallbacks(r);
        if (text != null && !TextUtils.isEmpty(text.trim()) && !"null".equals(text) && context != null) {
            if (mToast == null) {
                mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
        mhandler.postDelayed(r, 5000);
    }

    public static void showToast(Context context, int strId) {
        if(null!=context) {
            showToast(context, context.getString(strId));
        }
    }

}
