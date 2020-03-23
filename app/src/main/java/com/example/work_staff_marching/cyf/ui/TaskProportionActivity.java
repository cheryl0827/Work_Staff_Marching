package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class TaskProportionActivity extends BaseActivity {


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
        return R.layout.activity_task_proportion;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("诉求任务权重值填写");
        community.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(TaskProportionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
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
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(TaskProportionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
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
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(TaskProportionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
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
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(TaskProportionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
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
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(TaskProportionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
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
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(TaskProportionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
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
        Intent intent1=getIntent();
        CommonDialog commonDialog = new CommonDialog(this);
        Map<String, String> map = new HashMap<>();
        map.put("taskContent",intent1.getStringExtra("content"));
        map.put("taskCatagery",intent1.getStringExtra("catagery"));
        map.put("taskAdress",intent1.getStringExtra("address"));
        map.put("taskDetaiAdress",intent1.getStringExtra("detailaddress"));
        map.put("community",community.getText().toString());
        map.put("urgent",urgent.getText().toString());
        map.put("psychology",psychology.getText().toString());
        map.put("organization",organization.getText().toString());
        map.put("analyse",analyse.getText().toString());
        map.put("law",law.getText().toString());
            if(view.getId()==R.id.ok) {
                if(count!=100)
                    Toast.makeText(TaskProportionActivity.this,"输入的总值只能是100，请重新输入",Toast.LENGTH_SHORT).show();
                else{
                    OkHttp.get(TaskProportionActivity.this, Constant.get_taskproportion, map, new OkCallback<Result<String>>() {
                        @Override
                        public void onResponse(Result<String> response) {
                            commonDialog.isSingle=true;
                            commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("诉求任务权重值填写成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                                @Override
                                public void onPositiveClick() {
                                    commonDialog.dismiss();
                                    setResult(RESULT_OK);
                                    finish();
                                }

                                @Override
                                public void onNegtiveClick() {
                                    commonDialog.dismiss();
                                    //finish();
                                }
                            }).show();
                        }

                        @Override
                        public void onFailure(String state, String msg) {
                            Toast.makeText(TaskProportionActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
             if(view.getId()==R.id.cancel) {
                 community.setText("");
                 urgent.setText("");
                 psychology.setText("");
                 organization.setText("");
                 analyse.setText("");
                 law.setText("");
             }
    }
}
