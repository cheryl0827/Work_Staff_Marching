package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.MarchingBean;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;
import com.example.work_staff_marching.cyf.utils.Result;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.HashMap;
import java.util.Map;

public class WorkUserTaskAdapter extends BaseRecyclerViewAdapter<TaskBean, RecyclerViewHolder> {
    private Context mContext;
    private TaskBean taskBean;

    public WorkUserTaskAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, TaskBean data, int position, int viewType) {
        TextView task_catagery,task_time,task_content,address,detailaddress;
        Button banlibutton,pingjiabutton;
        banlibutton=(Button)holder.getView(R.id.banlibutton);
        detailaddress=(TextView)holder.getView(R.id.detailaddress);
        address=(TextView)holder.getView(R.id.address);
        holder.addOnClickListener(R.id.banlibutton);
        pingjiabutton=(Button)holder.getView(R.id.pingjiabutton);
        holder.addOnClickListener(R.id.pingjiabutton);
        task_catagery=(TextView)holder.getView(R.id.task_catagery);
        task_time=(TextView)holder.getView(R.id.task_time);
        task_content=(TextView)holder.getView(R.id.task_content);
        task_catagery.setText(data.getTaskCatagery());
        task_content.setText(data.getTaskContent());
        task_time.setText(data.getTaskTime());
        address.setText(data.getTaskAdress());
        detailaddress.setText(data.getTaskDetaiAdress());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_workuser_task;
    }

    @Override
    protected int getViewType(int position, TaskBean data) {
        return 0;
    }
}

