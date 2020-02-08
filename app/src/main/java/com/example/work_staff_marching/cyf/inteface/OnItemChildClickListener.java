package com.example.work_staff_marching.cyf.inteface;

import android.view.View;


import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;

public interface OnItemChildClickListener {
    void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position);
}
