package com.example.work_staff_marching.cyf.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterWorkActivity extends BaseActivity {
    @BindView(R.id.workuserno)
    Spinner workuserno;
    @BindView(R.id.man)
    RadioButton man;
    @BindView(R.id.woman)
    RadioButton woman;
    @BindView(R.id.sex)
    RadioGroup sex;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.cancel)
    Button cancel;
    String str[]=new String[]{"110011","110012","110013","110014","110015","110016","110017","110018","110019","110020","110021","110022","110023","110024","110025","110026","110027","110028","110029"};
    @Override
    protected int getContentView() {
        return R.layout.activity_register_work;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("工作人员注册");
        ArrayAdapter<String> sp1da=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str);
        workuserno.setAdapter(sp1da);
    }


    @OnClick({R.id.register, R.id.cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                break;
            case R.id.cancel:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
