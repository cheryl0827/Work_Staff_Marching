package com.example.work_staff_marching.cyf.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class UiUtils {
    /**
     * StaggeredGridLayoutManager时，查找position最大的列
     *
     * @param lastVisiblePositions
     * @return
     */
    public static int findMax(int[] lastVisiblePositions) {
        int max = lastVisiblePositions[0];
        for (int value : lastVisiblePositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static View inflate(Context context, int layoutId) {
        if (layoutId <= 0 || context == null) {
            return null;
        }
        return LayoutInflater.from(context).inflate(layoutId, null);
    }
}
