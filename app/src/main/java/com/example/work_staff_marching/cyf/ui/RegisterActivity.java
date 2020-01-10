package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.userName1)
    TextView userName1;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.phone1)
    TextView phone1;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.password1)
    TextView password1;
    @BindView(R.id.passwordSure)
    EditText passwordSure;
    @BindView(R.id.passwordSure1)
    TextView passwordSure1;
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
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!("").equals(userName.getText().toString()))
                    userName1.setText("");
                else
                    userName1.setText("*");
            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!("").equals(phone.getText().toString())&&(isMobileNo(phone.getText().toString())))
                    phone1.setText("");
                else
                    phone1.setText("*");
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!("").equals(password.getText().toString()))
                    password1.setText("");
                else
                    password1.setText("*");
            }
        });
        passwordSure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!("").equals(passwordSure.getText().toString())&&password.getText().toString().equals(passwordSure.getText().toString()))
                    passwordSure1.setText("");
                else
                    passwordSure1.setText("*");
            }
        });
    }
    @OnClick({ R.id.next, R.id.cancel})
    public void onViewClicked(View view) {
        if (userName1.getText().toString().equals("") && password1.getText().toString().equals("") && passwordSure1.getText().toString().equals("") && phone1.getText().toString().equals(""))
          {
                    Map<String, String> map = new HashMap<>();
                    map.put("userName", userName.getText().toString());
                    map.put("phone", phone.getText().toString());
                    map.put("password", password.getText().toString());
                    map.put("roleName", usertype);
                //    Intent intent = new Intent();
//                    intent.putExtra("userName",userName.getText().toString());
//                    intent.putExtra("phone",phone.getText().toString());
//                    intent.putExtra("password",password.getText().toString());
                    OkHttp.post(RegisterActivity.this, Constant.get_register, map, new OkCallback<Result<String>>() {
                        @Override
                        public void onResponse(Result<String> response)
                           {
                           if (view.getId() == R.id.next && usertype.equals("普通用户")){
//////                                   intent.setClass(RegisterActivity.this,RegisterUserActivity.class);
//////                                   RegisterActivity.this.startActivity(intent);
                                 startActivity(new Intent(RegisterActivity.this, RegisterUserActivity.class));
                              }
                             if (view.getId() == R.id.next && usertype.equals("工作用户")){
//////                                   intent.setClass(RegisterActivity.this,RegisterWorkActivity.class);
//////                                   RegisterActivity.this.startActivity(intent);
                                 startActivity(new Intent(RegisterActivity.this, RegisterWorkActivity.class));
                               }
                         }
                        @Override
                        public void onFailure(String state, String msg)
                             {
                            Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                    });
        }
        if (view.getId() == R.id.cancel) {
            userName.setText("");
            phone.setText("");
            password.setText("");
            passwordSure.setText("");
           }
        if (!(userName1.getText().toString().equals("") && password1.getText().toString().equals("") && passwordSure1.getText().toString().equals("") && phone1.getText().toString().equals("")))
            Toast.makeText(RegisterActivity.this, "请正确填写以上信息！", Toast.LENGTH_SHORT).show();
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

