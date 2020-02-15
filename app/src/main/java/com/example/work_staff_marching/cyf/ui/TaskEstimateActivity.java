package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
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

public class TaskEstimateActivity extends BaseActivity {

    @BindView(R.id.community)
    EditText community;
    @BindView(R.id.urgent)
    EditText urgent;
    @BindView(R.id.psychology)
    EditText psychology;
    @BindView(R.id.organization)
    EditText organization;
    @BindView(R.id.analyse)
    EditText analyse;
    @BindView(R.id.law)
    EditText law;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.community1)
    TextView community1;
    @BindView(R.id.urgent1)
    TextView urgent1;
    @BindView(R.id.psychology1)
    TextView psychology1;
    @BindView(R.id.organization1)
    TextView organization1;
    @BindView(R.id.analyse1)
    TextView analyse1;
    @BindView(R.id.law1)
    TextView law1;
    String taskStatus="3";


    @Override
    protected int getContentView() {
        return R.layout.activity_task_estimate;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("诉求任务完成度评价");
        community.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String stringport = community.getText().toString().trim();
                int port = Integer.parseInt(stringport);   //把port转换成int整形
                if(port<0||port>100){
                    Toast.makeText(TaskEstimateActivity.this,"输入的值只能在0-100之间",Toast.LENGTH_SHORT).show();
                }
            }
        });
        urgent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                int port = Integer.parseInt(s.toString());
                if(port<0||port>100){
                    Toast.makeText(TaskEstimateActivity.this,"输入的值只能在0-100之间",Toast.LENGTH_SHORT).show();
                }
            }
        });
        psychology.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                int port = Integer.parseInt(s.toString());
                if(port<0||port>100){
                    Toast.makeText(TaskEstimateActivity.this,"输入的值只能在0-100之间",Toast.LENGTH_SHORT).show();
                }
            }
        });
        organization.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                int port = Integer.parseInt(s.toString());
                if(port<0||port>100){
                    Toast.makeText(TaskEstimateActivity.this,"输入的值只能在0-100之间",Toast.LENGTH_SHORT).show();
                }
            }
        });
        analyse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                int port = Integer.parseInt(s.toString());
                if(port<0||port>100){
                    Toast.makeText(TaskEstimateActivity.this,"输入的值只能在0-100之间",Toast.LENGTH_SHORT).show();
                }
            }
        });
        law.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                int port = Integer.parseInt(s.toString());
                if(port<0||port>100){
                    Toast.makeText(TaskEstimateActivity.this,"输入的值只能在0-100之间",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick({R.id.ok, R.id.cancel})
    public void onViewClicked(View view) {
        int community1 = Integer.parseInt(community.getText().toString());
        int urgent1 = Integer.parseInt(urgent.getText().toString());
        int psychology1 = Integer.parseInt(psychology.getText().toString());
        int organization1 = Integer.parseInt(organization.getText().toString());
        int analyse1 = Integer.parseInt(analyse.getText().toString());
        int law1 = Integer.parseInt(law.getText().toString());
        int count=community1+urgent1+psychology1+organization1+analyse1+law1;
        CommonDialog commonDialog = new CommonDialog(this);
        switch (view.getId()) {
            case R.id.ok:
                Intent intent1=getIntent();
                Map<String, String> map = new HashMap<>();
                map.put("community",community.getText().toString());
                map.put("urgent",urgent.getText().toString());
                map.put("psychology",psychology.getText().toString());
                map.put("organization",organization.getText().toString());
                map.put("analyse",analyse.getText().toString());
                map.put("law",law.getText().toString());
                map.put("taskID",intent1.getStringExtra("taskID"));
                map.put("taskStatus",taskStatus);
                if(count!=100)
                    Toast.makeText(TaskEstimateActivity.this,"输入的总值只能是100，请重新输入",Toast.LENGTH_SHORT).show();
                else {
                    OkHttp.post(TaskEstimateActivity.this, Constant.get_estimateadd, map, new OkCallback<Result<String>>() {
                        @Override
                        public void onResponse(Result<String> response) {
                            commonDialog.isSingle = true;
                            commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("诉求任务评价填写成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
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
                            Toast.makeText(TaskEstimateActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.cancel:
                community.setText("");
                urgent.setText("");
                psychology.setText("");
                organization.setText("");
                analyse.setText("");
                law.setText("");
                break;
        }
    }

}
