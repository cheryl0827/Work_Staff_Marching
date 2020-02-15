package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

public class TaskAuditRecycleViewAdapter extends BaseRecyclerViewAdapter<TaskBean, RecyclerViewHolder> {
    private Context mContext;
    public TextView task_catagery,task_time,task_content;
    public Button detail;

    public TaskAuditRecycleViewAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, TaskBean data, int position, int viewType) {
        detail=(Button)holder.getView(R.id.detail);
        holder.addOnClickListener(R.id.detail);
        task_catagery=(TextView)holder.getView(R.id.task_catagery);
        task_time=(TextView)holder.getView(R.id.task_time);
        task_content=(TextView)holder.getView(R.id.task_content);
        task_catagery.setText(data.getTaskCatagery());
        task_content.setText(data.getTaskContent());
        task_time.setText(data.getTaskTime());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_task_audit_recycleview;
    }

    @Override
    protected int getViewType(int position, TaskBean data) {
        return 0;
    }
}
