package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkuserInformationRecycleViewAdapter extends BaseRecyclerViewAdapter<WorkuserEvaluatingIndicatorBean, RecyclerViewHolder> {
    private Context mContext;
    private UserBean userBean;

    public WorkuserInformationRecycleViewAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, WorkuserEvaluatingIndicatorBean data, int position, int viewType) {
        TextView userName,sex,workuserNo,count,community,urgent,psychology,organization,analyse,law;
        userName=(TextView)holder.getView(R.id.userName);
        sex=(TextView)holder.getView(R.id.sex);
        workuserNo=(TextView)holder.getView(R.id.workuserNo);
        count=(TextView)holder.getView(R.id.count);
        community=(TextView)holder.getView(R.id.community);
        urgent=(TextView)holder.getView(R.id.urgent);
        psychology=(TextView)holder.getView(R.id.psychology);
        organization=(TextView)holder.getView(R.id.organization);
        analyse=(TextView)holder.getView(R.id.analyse);
        law=(TextView)holder.getView(R.id.law);
        community.setText(data.getCommunity()+"");
        urgent.setText(data.getUrgent()+"");
        psychology.setText(data.getPsychology()+"");
        organization.setText(data.getOrganization()+"");
        law.setText(data.getLaw()+"");
        analyse.setText(data.getAnalyse()+"");
        Map<String, String> map = new HashMap<>();
        map.put("workuserNo",data.getWorkuserNo());
        OkHttp.get(mContext, Constant.get_showuserinformation, map, new OkCallback<Result<UserBean>>() {
            @Override
            public void onResponse(Result<UserBean> response) {
                userBean=response.getData();
                userName.setText(userBean.getUserName());
                sex.setText(userBean.getSex());
                workuserNo.setText(userBean.getWorkuserNo());
            }
            @Override
            public void onFailure(String state, String msg) {
            }
        });
        OkHttp.get(mContext, Constant.get_calculatetasks, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
              count.setText(response.getData()+"件");
            }
            @Override
            public void onFailure(String state, String msg) {
            }
        });



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
