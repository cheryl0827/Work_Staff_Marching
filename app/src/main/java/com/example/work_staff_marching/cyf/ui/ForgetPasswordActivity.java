package com.example.work_staff_marching.cyf.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

public class ForgetPasswordActivity extends BaseActivity {


    @Override
    protected int getContentView() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
    setTitle("找回密码");
    }
}
