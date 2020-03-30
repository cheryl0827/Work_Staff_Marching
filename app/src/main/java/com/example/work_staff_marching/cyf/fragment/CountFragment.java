package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.EstimateBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.ui.WorkuserUpdateEvaluatingIndicatorActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;
import com.example.work_staff_marching.cyf.utils.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


public class CountFragment extends BaseFragment {
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.community)
    TextView community;
    @BindView(R.id.community1)
    TextView community1;
    @BindView(R.id.organization)
    TextView organization;
    @BindView(R.id.organization1)
    TextView organization1;
    @BindView(R.id.psychology)
    TextView psychology;
    @BindView(R.id.psychology1)
    TextView psychology1;
    @BindView(R.id.urgent)
    TextView urgent;
    @BindView(R.id.urgent1)
    TextView urgent1;
    @BindView(R.id.analyse)
    TextView analyse;
    @BindView(R.id.analyse1)
    TextView analyse1;
    @BindView(R.id.law)
    TextView law;
    @BindView(R.id.law1)
    TextView law1;

    @Override
    protected int initLayout() {
        return R.layout.count_workuser;
    }

    @Override
    protected void initView(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("workuserNo", SharePrefrenceUtil.getObject(getContext(), UserBean.class).getWorkuserNo() + "");
        OkHttp.get(getContext(), Constant.get_calculatetasks, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                num.setText(response.getData()+"件");
            }

            @Override
            public void onFailure(String state, String msg) {
            }
        });
        OkHttp.get(getContext(), Constant.EstimateAvgServlet, map, new OkCallback<Result<EstimateBean>>() {
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
        OkHttp.post(getContext(), Constant.get_showworkuserevaluatingindicator,map,new OkCallback<Result<WorkuserEvaluatingIndicatorBean>>() {
            @Override
            public void onResponse(Result<WorkuserEvaluatingIndicatorBean> response) {
                community1.setText(response.getData().getCommunity()+"");
                urgent1.setText(response.getData().getUrgent()+"");
                psychology1.setText(response.getData().getPsychology()+"");
                organization1.setText(response.getData().getOrganization()+"");
                analyse1.setText(response.getData().getAnalyse()+"");
                law1.setText(response.getData().getLaw()+"");
            }

            @Override
            public void onFailure(String state, String msg) {

            }
        });

    }

    @Override
    protected void initData(Context mContext) {

    }

}
