package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.EstimateBean;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EstimateActivity extends BaseActivity {


    @BindView(R.id.community)
    TextView community;
    @BindView(R.id.urgent)
    TextView urgent;
    @BindView(R.id.psychology)
    TextView psychology;
    @BindView(R.id.organization)
    TextView organization;
    @BindView(R.id.analyse)
    TextView analyse;
    @BindView(R.id.law)
    TextView law;

    @Override
    protected int getContentView() {
        return R.layout.activity_estimate;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("诉求任务评价详情");
        Intent intent1 = getIntent();
        Map<String, String> map = new HashMap<>();
        map.put("taskID", intent1.getStringExtra("taskID"));
        OkHttp.get(EstimateActivity.this, Constant.get_estimateshow, map, new OkCallback<Result<EstimateBean>>() {
            @Override
            public void onResponse(Result<EstimateBean> response) {
                    community.setText(response.getData().getCommunity()+"");
                    urgent.setText(response.getData().getUrgent()+"");
                    psychology.setText(response.getData().getPsychology()+"");
                    organization.setText(response.getData().getOrganization()+"");
                    analyse.setText(response.getData().getAnalyse()+"");
                    law.setText(response.getData().getLaw()+"");
            }

            @Override
            public void onFailure(String state, String msg) {
            }
        });
    }


}
