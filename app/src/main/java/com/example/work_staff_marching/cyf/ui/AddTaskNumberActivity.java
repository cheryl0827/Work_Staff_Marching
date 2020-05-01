package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.UserBean;
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

public class AddTaskNumberActivity extends BaseActivity {


    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.useraddress)
    TextView useraddress;
    @BindView(R.id.max)
    EditText max;
    @BindView(R.id.yes)
    Button yes;
    @BindView(R.id.add_linearLayout)
    LinearLayout addLinearLayout;
    UserBean userBean;

    @Override
    protected int getContentView() {
        return R.layout.activity_maxtask_number;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("填写用户的处理最大任务数");
        Intent intent1 = getIntent();
        Map<String, String> map1 = new HashMap<>();
        map1.put("workuserNo", intent1.getStringExtra("workuserNo"));
        OkHttp.get(AddTaskNumberActivity.this, Constant.get_showuserinformation, map1, new OkCallback<Result<UserBean>>() {
            @Override
            public void onResponse(Result<UserBean> response) {
                userBean=response.getData();
                username.setText(userBean.getUserName());
                useraddress.setText(userBean.getWorkuserNo());
                max.setText(userBean.getMaxTaskNumber()+"");
            }
            @Override
            public void onFailure(String state, String msg) {
            }
        });


    }


    @OnClick(R.id.yes)
    public void onViewClicked(View view) {
        CommonDialog commonDialog = new CommonDialog(this);
       switch(view.getId()){
           case R.id.yes:
               Intent intent1 = getIntent();
               Map<String, String> map = new HashMap<>();
               map.put("maxTaskNumber",max.getText().toString());
               map.put("workuserNo", intent1.getStringExtra("workuserNo"));
               OkHttp.get(AddTaskNumberActivity.this, Constant.AddWorkUserTaskNtmberServlet, map, new OkCallback<Result<String>>() {
                   @Override
                   public void onResponse(Result<String> response) {
                       commonDialog.isSingle = true;
                       commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("信访人员处理的最大任务数填写成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                           @Override
                           public void onPositiveClick() {
                               commonDialog.dismiss();
                               setResult(RESULT_OK);
                               finish();

                           }

                           @Override
                           public void onNegtiveClick() {
                               commonDialog.dismiss();
                           }
                       }).show();

                   }
                   @Override
                   public void onFailure(String state, String msg) {
                   }
               });

               break;
       }
    }
}
