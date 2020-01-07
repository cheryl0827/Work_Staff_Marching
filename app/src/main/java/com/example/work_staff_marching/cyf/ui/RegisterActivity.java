package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.dao.UserDao;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.passwordSure)
    EditText passwordSure;
    @BindView(R.id.user)
    RadioButton user;
    @BindView(R.id.workuser)
    RadioButton workuser;
    @BindView(R.id.radio)
    RadioGroup radio;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.tishi)
    TextView tishi;
    String usertype;

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }
    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("注册页面");
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=(RadioButton)RegisterActivity.this.findViewById(radio.getCheckedRadioButtonId());
                usertype=rb.getText().toString();
            }
        });

    }
    @OnClick({ R.id.next, R.id.cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next:
                //                   if(usertype.equals("普通用户")&&UserDao.add_user(userName.getText().toString(),phone.getText().toString(), password.getText().toString(),usertype))
//                   {
//                       new Intent(RegisterActivity.this,RegisterUserActivity.class);;
//                   }
                MyThread myThread = new MyThread();
                new Thread(myThread).start();
                break;
            case R.id.cancel:
                userName.setText("");
                phone.setText("");
                password.setText("");
                passwordSure.setText("");
                break;
           }
        }
//    /**
//     * 1.使用实现 Runnable接 口的方式来定义一个线程
//     */
    class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                boolean add_user = UserDao.add_user(userName.getText().toString(), phone.getText().toString(), password.getText().toString(), usertype);
                Log.v("re",add_user+"");
                if (usertype.equals("普通用户")&& add_user) {
                    RegisterActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(RegisterActivity.this,RegisterUserActivity.class));
                        }
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //验证各个控件
    public boolean check(){
        if(userName.getText().toString().equals("")){
            tishi.setText("请输入姓名！");
            return false;
        }
        else if(phone.getText().toString().equals("")) {
            tishi.setText("请输入手机号码！");
            return false;
        }
        else if(!(isMobileNo(phone.getText().toString()))){
            tishi.setText("请正确格式的手机号码！");
            return false;
        }
        else if(password.getText().toString().equals("")) {
            tishi.setText("请输入密码！");
            return false;
        }
        else if(passwordSure.getText().toString().equals("")) {
            tishi.setText("请输入确认密码！");
            return false;
        }

        else if(!(passwordSure.getText().toString().equals("passwordSure.getText().toString()"))){
            tishi.setText("请检查密码，两次输入的密码不一致！");
            return false;
        }
        else {
            tishi.setText("");
            return true;
        }
    }
    //验证手机号码
    public static boolean isMobileNo(String mobiles) {
        /*
         * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、184、187、188、147
         * 联通号码段:130、131、132、185、186、145、171/176/175
         * 电信号码段:133、153、180、181、189、173、177
         */
        String telRegex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([1-3]|[5-9]))|(18[0-9]))\\d{8}$";
        /**
         * (13[0-9])代表13号段 130-139
         * (14[5|7])代表14号段 145、147
         * (15([0-3]|[5-9]))代表15号段 150-153 155-159
         * (17([1-3][5-8]))代表17号段 171-173 175-179 虚拟运营商170屏蔽
         * (18[0-9]))代表18号段 180-189
         * d{8}代表后面可以是0-9的数字，有8位
         */
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }



    }

