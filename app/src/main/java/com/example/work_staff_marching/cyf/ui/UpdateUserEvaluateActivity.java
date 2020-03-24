package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.EstimateBean;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateUserEvaluateActivity extends BaseActivity {


    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.community)
    EditText community;
    @BindView(R.id.textView1016)
    TextView textView1016;
    @BindView(R.id.urgent)
    EditText urgent;
    @BindView(R.id.psychology)
    EditText psychology;
    @BindView(R.id.organization)
    EditText organization;
    @BindView(R.id.analyse)
    EditText analyse;
    @BindView(R.id.analyse1)
    TextView analyse1;
    @BindView(R.id.law)
    EditText law;


    @Override
    protected int getContentView() {
        return R.layout.activity_update_user_evaluate;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("工作人员能力评价详情");
        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        Map<String, String> map = new HashMap<>();
        map.put("workuserNo", intent.getStringExtra("workuserNo"));
        map.put("taskID", intent.getStringExtra("taskID"));
        //Log.v("taskID", intent.getStringExtra("taskID"));
        OkHttp.get(UpdateUserEvaluateActivity.this, Constant.ShowWorkUserEstimate, map, new OkCallback<Result<EstimateBean>>() {
            @Override
            public void onResponse(Result<EstimateBean> response) {
                if(response.getData()!=null){
                  community.setText(response.getData().getCommunity()+" ");
                  urgent.setText(response.getData().getUrgent()+" ");
                  organization.setText(response.getData().getOrganization()+" ");
                  law.setText(response.getData().getLaw()+" ");
                  analyse.setText(response.getData().getAnalyse()+" ");
                  psychology.setText(response.getData().getPsychology()+" ");}
            }

            @Override
            public void onFailure(String state, String msg) {

            }
        });


    }

}
