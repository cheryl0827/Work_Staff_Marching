package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.EstimateBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkUserDetailActivity extends BaseActivity {


    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.useraddress)
    TextView useraddress;

    @BindView(R.id.dailphone)
    Button dailphone;
    String phone1;

    @Override
    protected int getContentView() {
        return R.layout.activity_work_user_detail;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("匹配工作人员的基本信息");
        Intent intent1 = getIntent();
        Map<String, String> map = new HashMap<>();
        map.put("workuserNo", intent1.getStringExtra("taskWorknumber"));
        OkHttp.get(WorkUserDetailActivity.this, Constant.get_workuserinformationshow, map, new OkCallback<Result<UserBean>>() {
            @Override
            public void onResponse(Result<UserBean> response) {
                username.setText(response.getData().getUserName());
                sex.setText(response.getData().getSex());
                phone.setText(response.getData().getPhone());
                useraddress.setText(response.getData().getWorkuserNo());

                phone1=response.getData().getPhone();
            }

            @Override
            public void onFailure(String state, String msg) {
            }
        });
    }
    @OnClick(R.id.dailphone)
    public void onViewClicked(View view) {
        switch(view.getId()) {
            case R.id.dailphone:
            Intent intent1 = new Intent();
            intent1.putExtra("phone", phone1);
            intent1.setClass(WorkUserDetailActivity.this, PhoneCallActivity.class);
            startActivity(intent1);
            break;
        }
    }
}
