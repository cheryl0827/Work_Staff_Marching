package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

public class WorkUserInformationAdapter extends BaseRecyclerViewAdapter<UserBean, RecyclerViewHolder> {


    public WorkUserInformationAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, UserBean data, int position, int viewType) {
        TextView username,sex,useraddress,phone;
        Button pingjia,update;
        phone=(TextView)holder.getView(R.id.phone);
        username=(TextView)holder.getView(R.id.username);
        sex=(TextView)holder.getView(R.id.sex);
        useraddress=(TextView)holder.getView(R.id.useraddress);
        pingjia=(Button)holder.getView(R.id.pingjia);
        update=(Button)holder.getView(R.id.update);
        holder.addOnClickListener(R.id.pingjia);
        holder.addOnClickListener(R.id.update);
        username.setText(data.getUserName());
        sex.setText(data.getSex());
        phone.setText(data.getPhone());
        useraddress.setText(data.getWorkuserNo());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.activity_work_user_evaluate;
    }

    @Override
    protected int getViewType(int position, UserBean data) {
        return 0;
    }
}


