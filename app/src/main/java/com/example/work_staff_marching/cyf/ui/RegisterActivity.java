package com.example.work_staff_marching.cyf.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.dao.UserDao;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.textView0)
    TextView textView0;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.textView01)
    TextView textView01;
    @BindView(R.id.passwordSure)
    EditText passwordSure;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.indentificationCard)
    EditText indentificationCard;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.editText)
    TextView editText;
    @BindView(R.id.editText1)
    TextView editText1;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.user)
    RadioButton user;
    @BindView(R.id.workuser)
    RadioButton workuser;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.cancel)
    Button cancel;

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("注册");
    }


    @OnClick({R.id.register, R.id.cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                MyThread myThread = new MyThread();
                new Thread(myThread).start();
                break;
            case R.id.cancel:
                break;
        }
    }

    /**
     * 1.使用实现 Runnable接 口的方式来定义一个线程
     */
    class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                if (UserDao.add_user(userName.getText().toString(), indentificationCard.getText().toString(), phone.getText().toString(), 0, address.getText().toString(), 0, password.getText().toString(), 0)) {
                    RegisterActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
