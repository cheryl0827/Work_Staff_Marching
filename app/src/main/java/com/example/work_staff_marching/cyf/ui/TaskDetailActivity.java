package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.TaskBean;
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

public class TaskDetailActivity extends BaseActivity {


    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.indentification)
    TextView indentification;
    @BindView(R.id.catagery)
    TextView catagery;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.contentLineaeLayout)
    LinearLayout contentLineaeLayout;
    @BindView(R.id.aadress)
    TextView aadress;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.nobutton)
    Button nobutton;
    String contenttext;
    String taskID;
    String taskStatus;
    String community;



    @Override
    protected int getContentView() {
        return R.layout.activity_task_detail;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("诉求任务的详情");
        Intent intent1 = getIntent();
        Map<String, String> map1 = new HashMap<>();
        map1.put("userID", intent1.getStringExtra("userID"));
        OkHttp.get(TaskDetailActivity.this, Constant.get_userinformationshow, map1, new OkCallback<Result<UserBean>>() {
            @Override
            public void onResponse(Result<UserBean> response) {
                username.setText(response.getData().getUserName());
                indentification.setText(response.getData().getIndentificationCard());
            }

            @Override
            public void onFailure(String state, String msg) {
            }
        });
        Map<String, String> map = new HashMap<>();
        taskID=intent1.getStringExtra("taskID");
        map.put("taskID",taskID );
        OkHttp.get(TaskDetailActivity.this, Constant.get_showtask, map, new OkCallback<Result<TaskBean>>() {
            @Override
            public void onResponse(Result<TaskBean> response) {
                contenttext=response.getData().getTaskContent();
                community=response.getData().getCommunity()+"";
                catagery.setText(response.getData().getTaskCatagery());
                content.setText(response.getData().getTaskContent());
                aadress.setText(response.getData().getTaskDetaiAdress());
                time.setText(response.getData().getTaskTime());
                address.setText(response.getData().getTaskAdress());
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
                intent.setClass(TaskDetailActivity.this, ContentSureActivity.class);
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.button, R.id.nobutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                taskStatus="2";
                CommonDialog commonDialog = new CommonDialog(this);
                Map<String, String> map = new HashMap<>();
                map.put("taskID",taskID);
                map.put("taskStatus",taskStatus);
                OkHttp.get(TaskDetailActivity.this, Constant.get_taskaudit, map, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result<String> response) {
                        commonDialog.isSingle=true;
                        commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("诉求任务审核成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                    Intent intent = new Intent();
                                    intent.putExtra("taskID",taskID);
                                    intent.setClass(TaskDetailActivity.this, AdminAddTaskProportionActivity.class);
                                    startActivityForResult(intent,1);
                            }
                            @Override
                            public void onNegtiveClick() {
                                commonDialog.dismiss();
                            }
                        }).show();
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        Toast.makeText(TaskDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.nobutton:
                taskStatus="4";
                CommonDialog commonDialog1 = new CommonDialog(this);
                Map<String, String> map1 = new HashMap<>();
                map1.put("taskID",taskID );
                map1.put("taskStatus",taskStatus );
                OkHttp.post(TaskDetailActivity.this, Constant.get_taskaudit, map1, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result<String> response) {
                        commonDialog1.isSingle=true;
                        commonDialog1.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("诉求任务审核成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                commonDialog1.dismiss();
                                setResult(RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onNegtiveClick() {
                                commonDialog1.dismiss();
                            }
                        }).show();
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        Toast.makeText(TaskDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                setResult(RESULT_OK);
                finish();
            }
        }
    }

}
