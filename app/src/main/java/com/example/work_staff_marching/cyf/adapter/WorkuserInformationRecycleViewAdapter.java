package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.EstimateBean;
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

public class WorkuserInformationRecycleViewAdapter extends BaseRecyclerViewAdapter<UserBean, RecyclerViewHolder> {
    private Context mContext;
    private UserBean userBean;

    public WorkuserInformationRecycleViewAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, UserBean data, int position, int viewType) {
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
        userName.setText(data.getUserName());
        sex.setText(data.getSex());
        workuserNo.setText(data.getWorkuserNo());
        Map<String, String> map = new HashMap<>();
        map.put("workuserNo",data.getWorkuserNo());
        OkHttp.get(mContext, Constant.get_calculatetasks, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                count.setText(response.getData()+"件");
            }
            @Override
            public void onFailure(String state, String msg) {
            }
        });
        OkHttp.get(mContext, Constant.EstimateAvgServlet, map, new OkCallback<Result<EstimateBean>>() {
            @Override
            public void onResponse(Result<EstimateBean> response) {
               if(response.getData()!=null){
                urgent.setText(response.getData().getUrgent()+"");
                psychology.setText(response.getData().getPsychology()+"");
                organization.setText(response.getData().getOrganization()+"");
                law.setText(response.getData().getLaw()+"");
                analyse.setText(response.getData().getAnalyse()+"");
                community.setText(response.getData().getCommunity()+"");
               }
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
    protected int getViewType(int position, UserBean data) {
        return 0;
    }
}
