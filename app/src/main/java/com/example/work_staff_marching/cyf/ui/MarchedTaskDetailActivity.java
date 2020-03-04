package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.TaskBean;
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

public class MarchedTaskDetailActivity extends BaseActivity {

    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.catagery)
    TextView catagery;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.adressdetail)
    TextView adressdetail;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.useraddress)
    TextView useraddress;
    @BindView(R.id.useraddressdetail)
    TextView useraddressdetail;
    @BindView(R.id.dailphone)
    Button dailphone;
    @BindView(R.id.peopleInformation)
    LinearLayout peopleInformation;
    @BindView(R.id.contentLineaeLayout)
    LinearLayout contentLineaeLayout;
    String userID;
    String contenttext;
    String phone1;
    @Override
    protected int getContentView() {
        return R.layout.activity_workuser_task_detail;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("诉求任务详情");
        Intent intent1 = getIntent();
        peopleInformation.setVisibility(View.GONE);
        Map<String, String> map = new HashMap<>();
        map.put("taskID", intent1.getStringExtra("taskID"));
        OkHttp.get(MarchedTaskDetailActivity.this, Constant.get_showtask, map, new OkCallback<Result<TaskBean>>() {
            @Override
            public void onResponse(Result<TaskBean> response) {
                catagery.setText(response.getData().getTaskCatagery());
                content.setText(response.getData().getTaskContent());
                adressdetail.setText(response.getData().getTaskDetaiAdress());
                time.setText(response.getData().getTaskTime());
                address.setText(response.getData().getTaskAdress());
                userID=response.getData().getUserID()+"";
                contenttext=response.getData().getTaskContent();
            }
            @Override
            public void onFailure(String state, String msg) {
            }
        });
        contentLineaeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("content",contenttext);
                intent.setClass(MarchedTaskDetailActivity.this, ContentSureActivity.class);
                startActivity(intent);
            }
        });

    }
    @OnClick({R.id.button2, R.id.dailphone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                peopleInformation.setVisibility(View.VISIBLE);
                Map<String, String> map1 = new HashMap<>();
                map1.put("userID", userID);
                OkHttp.get(MarchedTaskDetailActivity.this, Constant.get_userinformationshow, map1, new OkCallback<Result<UserBean>>() {
                    @Override
                    public void onResponse(Result<UserBean> response) {
                        username.setText(response.getData().getUserName());
                        sex.setText(response.getData().getSex());
                        phone.setText(response.getData().getProvince());
                        useraddress.setText(response.getData().getProvince()+"-"+response.getData().getCity()+"-"+response.getData().getCountry());
                        useraddressdetail.setText(response.getData().getAddress());
                        phone1=response.getData().getPhone();
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                    }
                });
                break;
            case R.id.dailphone:
                Intent intent1 = new Intent();
                intent1.putExtra("phone",phone1);
                intent1.setClass(MarchedTaskDetailActivity.this,PhoneCallActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
