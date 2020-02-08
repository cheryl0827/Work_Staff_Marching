package com.example.work_staff_marching.cyf.inteface;


import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

public interface OnItemClickListener<T> {
    void onItemClick(RecyclerViewHolder viewHolder, T data, int position);
}
