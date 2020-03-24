package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.MarchingBean;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.Map;

public class AdminMarchedTaskAdapter extends  BaseRecyclerViewAdapter<TaskBean, RecyclerViewHolder> {
    private Context mContext;
    private TaskBean taskBean;
    private UserBean userBean;
    public AdminMarchedTaskAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    protected void convert(RecyclerViewHolder holder, TaskBean data, int position, int viewType) {
        TextView task_catagery,task_content,task_time,address,detailaddress;
        Button machedbutton,jilubutton;
        task_catagery=(TextView)holder.getView(R.id.task_catagery);
        task_content=(TextView)holder.getView(R.id.task_content);
        task_time=(TextView)holder.getView(R.id.task_time);
        detailaddress=(TextView)holder.getView(R.id.detailaddress);
        address=(TextView)holder.getView(R.id.address);
        machedbutton=(Button) holder.getView(R.id.machedbutton);
        jilubutton=(Button) holder.getView(R.id.jilubutton);
        holder.addOnClickListener(R.id.machedbutton);
        holder.addOnClickListener(R.id.jilubutton);
                    task_catagery.setText(data.getTaskCatagery());
                    task_content.setText(data.getTaskContent());
                    task_time.setText(data.getTaskTime());
                    address.setText(data.getTaskAdress());
                    detailaddress.setText(data.getTaskDetaiAdress());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_admin_marched_task;
    }

    @Override
    protected int getViewType(int position, TaskBean data) {
        return 0;
    }
}
