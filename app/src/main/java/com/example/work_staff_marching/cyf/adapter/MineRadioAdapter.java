package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MineRadioAdapter extends  BaseRecyclerViewAdapter<TaskBean, RecyclerViewHolder>{
    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;
    private Context mContext;
    private int secret = 0;
    private List<TaskBean> mTaskBean= new ArrayList<>();
   // private OnItemClickListener mOnItemClickListener;

    public MineRadioAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, TaskBean data, int position, int viewType) {
        TextView task_catagery,task_time,task_content;
        ImageView mCheckBox;
        RelativeLayout mRootView;
        task_catagery=(TextView)holder.getView(R.id.task_catagery);
        task_time=(TextView)holder.getView(R.id.task_time);
        task_content=(TextView)holder.getView(R.id.task_content);
        task_catagery.setText(data.getTaskCatagery());
        task_content.setText(data.getTaskContent());
        task_time.setText(data.getTaskTime());
        mCheckBox=(ImageView)holder.getView(R.id.check_box);
     /*
        if (mEditMode == MYLIVE_MODE_CHECK) {
          mCheckBox.setVisibility(View.GONE);
         }
        else {
            mCheckBox.setVisibility(View.VISIBLE);
            if (data.isSelect()) {
                mCheckBox.setImageResource(R.mipmap.ic_checked);
              } else {
                mCheckBox.setImageResource(R.mipmap.ic_uncheck);
    }
}
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(holder.getAdapterPosition(), mTaskBean);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mTaskBean.size();
    }
//    public void notifyAdapter(List<TaskBean> mTaskBean, boolean isAdd) {
//        if (!isAdd) {
//            this.mTaskBean = mTaskBean;
//        } else {
//            this.mTaskBean.addAll(mTaskBean);
//        }
//        notifyDataSetChanged();
//    }

    public List<TaskBean> getMyLiveList() {
        if (mTaskBean == null) {
            mTaskBean = new ArrayList<>();
        }
        return mTaskBean;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClickListener(int pos,List<TaskBean> myLiveList);
    }
    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
        */
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