package com.example.work_staff_marching.cyf.ui;

import androidx.appcompat.app.AppCompatActivity;

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

public class AuditYesTaskDetailActivity extends BaseActivity {

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
    String contenttext;
    String taskID;
    String taskStatus;
    String community;
    @Override
    protected int getContentView() {
        return R.layout.activity_audit_yes_task_detail;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("诉求任务的详情");
        Intent intent1 = getIntent();
        Map<String, String> map1 = new HashMap<>();
        map1.put("userID", intent1.getStringExtra("userID"));
        OkHttp.get(AuditYesTaskDetailActivity.this, Constant.get_showuserinformation, map1, new OkCallback<Result<UserBean>>() {
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
        OkHttp.get(AuditYesTaskDetailActivity.this, Constant.get_showtask, map, new OkCallback<Result<TaskBean>>() {
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
                intent.setClass(AuditYesTaskDetailActivity.this, ContentSureActivity.class);
                startActivity(intent);
            }
        });

}
}
