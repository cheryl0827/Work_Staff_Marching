package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class WorkuserAddEvaluatingIndicatorActivity extends BaseActivity {


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

    @Override
    protected int getContentView() {
        return R.layout.activity_task_estimate;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("工作人员指标填写");

    }

    @OnClick({R.id.ok, R.id.cancel})
    public void onViewClicked(View view) {
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
                map.put("userID",intent1.getStringExtra("userID"));
                OkHttp.post(WorkuserAddEvaluatingIndicatorActivity.this, Constant.get_addworkuserevaluatingindicator, map, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result<String> response) {
                        commonDialog.isSingle=true;
                        commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("工作人员指标填写成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
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
                        Toast.makeText(WorkuserAddEvaluatingIndicatorActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
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
