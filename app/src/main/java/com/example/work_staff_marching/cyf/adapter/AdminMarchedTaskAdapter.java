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

public class AdminMarchedTaskAdapter extends  BaseRecyclerViewAdapter<MarchingBean, RecyclerViewHolder> {
    private Context mContext;
    private TaskBean taskBean;
    private UserBean userBean;
    public AdminMarchedTaskAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    protected void convert(RecyclerViewHolder holder, MarchingBean data, int position, int viewType) {
        TextView task_catagery,task_content,task_time,address,detailaddress,username,phone,marching_time;
        Button machedbutton,pingjiabutton,jilubutton;
        task_catagery=(TextView)holder.getView(R.id.task_catagery);
        task_content=(TextView)holder.getView(R.id.task_content);
        task_time=(TextView)holder.getView(R.id.task_time);
        detailaddress=(TextView)holder.getView(R.id.detailaddress);
        address=(TextView)holder.getView(R.id.address);
        username=(TextView)holder.getView(R.id.username);
        phone=(TextView)holder.getView(R.id.phone);
        marching_time=(TextView)holder.getView(R.id.marching_time);

        machedbutton=(Button) holder.getView(R.id.machedbutton);
        pingjiabutton=(Button) holder.getView(R.id.pingjiabutton);
        jilubutton=(Button) holder.getView(R.id.jilubutton);
        holder.addOnClickListener(R.id.machedbutton);
        holder.addOnClickListener(R.id.pingjiabutton);
        holder.addOnClickListener(R.id.jilubutton);

        marching_time.setText(data.getMarchingTime());
        Map<String, String> map = new HashMap<>();
        map.put("taskID",data.getTaskID()+"");
        OkHttp.get(mContext, Constant.get_showtask, map, new OkCallback<Result<TaskBean>>() {
            @Override
            public void onResponse(Result<TaskBean> response) {
                taskBean=response.getData();
                if(taskBean!=null){
                    task_catagery.setText(taskBean.getTaskCatagery());
                    task_content.setText(taskBean.getTaskContent());
                    task_time.setText(taskBean.getTaskTime());
                    address.setText(taskBean.getTaskAdress());
                    detailaddress.setText(taskBean.getTaskDetaiAdress());
                }
            }
            @Override
            public void onFailure(String state, String msg) {

            }
        });
        Map<String, String> map1 = new HashMap<>();
        map1.put("adminID",data.getAdminID()+"");
        OkHttp.get(mContext, Constant.get_adminshow, map1, new OkCallback<Result<UserBean>>() {
            @Override
            public void onResponse(Result<UserBean> response) {
                userBean=response.getData();
                if(userBean!=null){
                   username.setText(userBean.getUserName());
                   phone.setText(userBean.getPhone());
                }
            }
            @Override
            public void onFailure(String state, String msg) {

            }
        });
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_admin_marched_task;
    }

    @Override
    protected int getViewType(int position, MarchingBean data) {
        return 0;
    }
}
