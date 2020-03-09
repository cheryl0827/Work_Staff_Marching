package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

public class TaskWorkUserInformationAdapter extends BaseRecyclerViewAdapter<UserBean, RecyclerViewHolder> {


    public TaskWorkUserInformationAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, UserBean data, int position, int viewType) {
        TextView username,sex,useraddress,phone;
        Button dailphone;
        phone=(TextView)holder.getView(R.id.phone);
        username=(TextView)holder.getView(R.id.username);
        sex=(TextView)holder.getView(R.id.sex);
        useraddress=(TextView)holder.getView(R.id.useraddress);
        dailphone=(Button)holder.getView(R.id.dailphone);
        holder.addOnClickListener(R.id.dailphone);
        username.setText(data.getUserName());
        sex.setText(data.getSex());
        phone.setText(data.getPhone());
        useraddress.setText(data.getWorkuserNo());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.activity_work_user_detail;
    }

    @Override
    protected int getViewType(int position, UserBean data) {
        return 0;
    }
}

