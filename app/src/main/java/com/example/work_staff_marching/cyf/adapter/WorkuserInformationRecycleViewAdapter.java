package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.Map;

public class WorkuserInformationRecycleViewAdapter extends BaseRecyclerViewAdapter<WorkuserEvaluatingIndicatorBean, RecyclerViewHolder> {
    private Context mContext;
    String userID;
    public TextView userName,sex,workuserNo,count,community,urgent,psychology,organization,analyse,law;
    public WorkuserInformationRecycleViewAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, WorkuserEvaluatingIndicatorBean data, int position, int viewType) {
        userID=data.getUserID()+"";
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
        //userName.setText(data.getUserID()+"");
        community.setText(data.getCommunity()+"");
        urgent.setText(data.getUrgent()+"");
        psychology.setText(data.getPsychology()+"");
        organization.setText(data.getOrganization()+"");
        law.setText(data.getLaw()+"");
        analyse.setText(data.getAnalyse()+"");
        Map<String, String> map = new HashMap<>();
        map.put("userID",userID);
        OkHttp.get(mContext, Constant.get_showuserinformation, map, new OkCallback<Result<UserBean>>() {
            @Override
            public void onResponse(Result<UserBean> response) {

                userName.setText(response.getData().getUserName());
                sex.setText(response.getData().getSex());
                workuserNo.setText(response.getData().getWorkuserNo());
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
