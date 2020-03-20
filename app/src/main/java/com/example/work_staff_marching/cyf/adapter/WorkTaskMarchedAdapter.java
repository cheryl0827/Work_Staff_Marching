package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.MarchingBean;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.fragment.WorkTaskMarchedFragment;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.Map;

public class WorkTaskMarchedAdapter extends BaseRecyclerViewAdapter<MarchingBean, RecyclerViewHolder> {
    private Context mContext;
    private TaskBean taskBean;

    public WorkTaskMarchedAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, MarchingBean data, int position, int viewType) {
        LinearLayout item;
        item=(LinearLayout)holder.getView(R.id.item);
        Map<String, String> map = new HashMap<>();
        map.put("taskID",data.getTaskID()+"");
        map.put("recordStatus","1");
        OkHttp.get(mContext, Constant.get_showtaskl, map, new OkCallback<Result<TaskBean>>() {
            @Override
            public void onResponse(Result<TaskBean> response) {
                taskBean=response.getData();
                if(taskBean!=null){
                   item.setVisibility(View.VISIBLE);
                    TextView task_catagery,task_content,task_time;
                    Button banlibutton,detail;

                    task_catagery=(TextView)holder.getView(R.id.task_catagery);
                    task_content=(TextView)holder.getView(R.id.task_content);
                    task_time=(TextView)holder.getView(R.id.task_time);
                    banlibutton=(Button)holder.getView(R.id.banlibutton);

                    holder.addOnClickListener(R.id.banlibutton);
                    detail=(Button)holder.getView(R.id.detail);
                    holder.addOnClickListener(R.id.detail);
                    task_catagery.setText(taskBean.getTaskCatagery());
                    task_content.setText(taskBean.getTaskContent());
                    task_time.setText(taskBean.getTaskTime());
                }
                if(taskBean==null){
                   item.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String state, String msg) {

            }
        });



    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_workuser_marched__task;
    }

    @Override
    protected int getViewType(int position, MarchingBean data) {
        return 0;
    }
}
