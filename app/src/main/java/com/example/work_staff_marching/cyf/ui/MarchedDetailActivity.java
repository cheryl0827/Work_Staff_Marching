package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MarchedDetailActivity extends BaseActivity {
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
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.workusername)
    TextView workusername;
    @BindView(R.id.workusersex)
    TextView workusersex;
    @BindView(R.id.workuserNo)
    TextView workuserNo;
    @BindView(R.id.workuserphone)
    TextView workuserphone;
    @BindView(R.id.usernameText)
    TextView usernameText;
    @BindView(R.id.usernameLinearLayout)
    LinearLayout usernameLinearLayout;
    @BindView(R.id.sexText)
    TextView sexText;
    @BindView(R.id.sexLayout)
    LinearLayout sexLayout;
    @BindView(R.id.edittext11)
    TextView edittext11;
    @BindView(R.id.addressLinearLayout1)
    LinearLayout addressLinearLayout1;
    @BindView(R.id.phon1eText)
    TextView phon1eText;
    @BindView(R.id.phoneLine1arLayout)
    LinearLayout phoneLine1arLayout;
    @BindView(R.id.peopleInformation)
    LinearLayout peopleInformation;
    String userID;

    @Override
    protected int getContentView() {
        return R.layout.activity_marched_detail;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("匹配信息详情");
        peopleInformation.setVisibility(View.GONE);
        Intent intent1 = getIntent();
        Map<String, String> map1 = new HashMap<>();
        map1.put("taskID", intent1.getStringExtra("taskID"));
        OkHttp.get(MarchedDetailActivity.this, Constant.ShowMarchedUserInformationServlet, map1, new OkCallback<Result<UserBean>>() {
            @Override
            public void onResponse(Result<UserBean> response) {
                username.setText(response.getData().getUserName());
                sex.setText(response.getData().getSex());
                phone.setText(response.getData().getPhone());
                useraddress.setText(response.getData().getProvince()+"-"+response.getData().getCity()+"-"+response.getData().getCountry());
                useraddressdetail.setText(response.getData().getAddress());
            }

            @Override
            public void onFailure(String state, String msg) {
            }
        });
    }

    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                peopleInformation.setVisibility(View.GONE);
                Intent intent = getIntent();
                Intent intent33 = new Intent();
                intent33.putExtra("taskID", intent.getStringExtra("taskID"));
                intent33.setClass(MarchedDetailActivity.this, WorkUserDetailActivity.class);
                startActivity(intent33);
            break;
        }
    }
}
