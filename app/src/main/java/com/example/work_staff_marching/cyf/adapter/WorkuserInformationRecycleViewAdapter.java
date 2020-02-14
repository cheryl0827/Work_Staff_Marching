package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

public class WorkuserInformationRecycleViewAdapter extends BaseRecyclerViewAdapter<WorkuserEvaluatingIndicatorBean, RecyclerViewHolder> {
    private Context mContext;
    public WorkuserInformationRecycleViewAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, WorkuserEvaluatingIndicatorBean data, int position, int viewType) {

    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_workuser_information;
    }

    @Override
    protected int getViewType(int position, WorkuserEvaluatingIndicatorBean data) {
        return 0;
    }
}
