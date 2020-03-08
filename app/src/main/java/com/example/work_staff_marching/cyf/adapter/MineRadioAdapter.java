package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;

public class MineRadioAdapter extends  BaseRecyclerViewAdapter<TaskBean, RecyclerViewHolder>{
    private Context mContext;
    private List<TaskBean> mTaskBean= new ArrayList<>();
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public MineRadioAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, TaskBean data, int position, int viewType) {
        TextView task_catagery,task_time,task_content,address,detailaddress;
        RelativeLayout mRootView;
        QMUIRoundButton ivIsSelect = (QMUIRoundButton) holder.getView(R.id.iv_is_select);
        task_catagery=(TextView)holder.getView(R.id.task_catagery);
        task_time=(TextView)holder.getView(R.id.task_time);
        address=(TextView)holder.getView(R.id.address);
        detailaddress=(TextView)holder.getView(R.id.detailaddress);
        task_content=(TextView)holder.getView(R.id.task_content);
        task_catagery.setText(data.getTaskCatagery());
        task_content.setText(data.getTaskContent());
        task_time.setText(data.getTaskTime());
        detailaddress.setText(data.getTaskDetaiAdress());
        address.setText(data.getTaskAdress());
        if (isSelect()) {//判断复选框的出现
            ivIsSelect.setVisibility(View.VISIBLE);
        } else {
            ivIsSelect.setVisibility(View.GONE);
        }
        if (data.isSelect()) {//判断数据是否选择
            ivIsSelect.setBackgroundColor(mContext.getResources().getColor(R.color.firebrick));
        } else {
            ivIsSelect.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
        }

    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_task_marching;
    }

    @Override
    protected int getViewType(int position, TaskBean data) {
        return 0;
    }

}