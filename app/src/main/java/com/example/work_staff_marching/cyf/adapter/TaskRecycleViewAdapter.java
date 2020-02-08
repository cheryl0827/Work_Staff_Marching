package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

import java.util.List;

public class TaskRecycleViewAdapter extends BaseRecyclerViewAdapter<TaskBean,RecyclerViewHolder>{
    public TextView task_catagery,task_content,task_time;
    public Button update,delete,pingjiabutton;
    public LinearLayout pingjia,opetation,detailEndLinearLayout,detailnoLinearLayout;

    public TaskRecycleViewAdapter(Context context) {
        super(context);
    }
    @Override
    protected void convert(RecyclerViewHolder holder, TaskBean data, int position, int viewType) {
        task_catagery=(TextView)holder.getView(R.id.task_catagery);
        task_content=(TextView)holder.getView(R.id.task_content);
        task_time=(TextView)holder.getView(R.id.task_time);
        task_catagery.setText(data.getTaskCatagery());
        task_content.setText(data.getTaskContent());
        task_time.setText(data.getTaskTime());
        pingjia=(LinearLayout)holder.getView(R.id.pingjia);
        opetation=(LinearLayout)holder.getView(R.id.opetation);
        detailEndLinearLayout=(LinearLayout)holder.getView(R.id.detailEndLinearLayout);
        detailnoLinearLayout=(LinearLayout)holder.getView(R.id.detailnoLinearLayout);

        switch(data.getTaskStatus()){
            case 1:
                opetation.setVisibility(View.VISIBLE);
                pingjia.setVisibility(View.GONE);
                detailEndLinearLayout.setVisibility(View.GONE);
                detailnoLinearLayout.setVisibility(View.GONE);
                break;
            case 2:
                pingjia.setVisibility(View.VISIBLE);
                opetation.setVisibility(View.GONE);
                detailEndLinearLayout.setVisibility(View.GONE);
                detailnoLinearLayout.setVisibility(View.GONE);
                break;
            case 3:
                detailEndLinearLayout.setVisibility(View.VISIBLE);
                pingjia.setVisibility(View.GONE);
                opetation.setVisibility(View.GONE);
                detailnoLinearLayout.setVisibility(View.GONE);
                break;
            case 4:
                detailnoLinearLayout.setVisibility(View.VISIBLE);
                pingjia.setVisibility(View.GONE);
                detailEndLinearLayout.setVisibility(View.GONE);
                opetation.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_taskrecycle_view;
    }

    @Override
    protected int getViewType(int position, TaskBean data) {
        return 0;
    }
}
