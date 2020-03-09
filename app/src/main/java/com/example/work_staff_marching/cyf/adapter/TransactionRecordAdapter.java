package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.RecordBean;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

public class TransactionRecordAdapter extends BaseRecyclerViewAdapter<RecordBean, RecyclerViewHolder> {
    private Context mContext;

    public TransactionRecordAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    protected void convert(RecyclerViewHolder holder, RecordBean data, int position, int viewType) {
        TextView recordContent,nextVisitTime,recordTime,userName,workuserNo;
        recordContent=(TextView)holder.getView(R.id.recordContent);
        nextVisitTime=(TextView)holder.getView(R.id.nextVisitTime);
        recordTime=(TextView)holder.getView(R.id.recordTime);
        workuserNo=(TextView)holder.getView(R.id.workuserNo);
        userName=(TextView)holder.getView(R.id.userName);
        recordContent.setText(data.getRecordContent());
        nextVisitTime.setText(data.getNextVisitTime());
        recordTime.setText(data.getRecordTime());
        userName.setText(data.getUserName());
        workuserNo.setText(data.getWorkuserNo());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_transaction_record;
    }

    @Override
    protected int getViewType(int position, RecordBean data) {
        return 0;
    }
}
