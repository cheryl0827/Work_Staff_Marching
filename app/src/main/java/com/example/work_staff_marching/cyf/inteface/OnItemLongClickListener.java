package com.example.work_staff_marching.cyf.inteface;


import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

/**
 * 条目长按点击事件
 */
public interface OnItemLongClickListener<T> {
    void onItemLongClick(RecyclerViewHolder viewHolder, T data, int position);
}
