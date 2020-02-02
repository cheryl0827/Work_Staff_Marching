package com.example.work_staff_marching.cyf.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
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
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;
import com.example.work_staff_marching.cyf.utils.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText phone,password;
    TextView forgetpassword,register;
    Button login;
    RadioGroup radio;
    String usertype="普通用户";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);
        forgetpassword=(TextView)findViewById(R.id.forgetpassword);
        register=(TextView)findViewById(R.id.register);
        login=(Button)findViewById(R.id.login);
        radio=(RadioGroup)findViewById(R.id.radio);
        login.setOnClickListener(new Onbuttonlistener());
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ForgetPasswordActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) MainActivity.this.findViewById(radio.getCheckedRadioButtonId());
                usertype = rb.getText().toString();
            }
        });
    }
    class Onbuttonlistener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String pphone = phone.getText().toString();
            String ppassword = password.getText().toString();
            if (!(isMobileNo(pphone))) {
                Toast.makeText(MainActivity.this, "您输入的手机号码不正确，请重新输入！", Toast.LENGTH_LONG).show();
            }
            if (ppassword.equals(""))
                Toast.makeText(MainActivity.this, "密码不能为空！", Toast.LENGTH_LONG).show();
//            else
//            startActivity(new Intent(MainActivity.this,UserIndexActivity.class));
           // if(v.getId()==R.id.register){
                Map<String, String> map = new HashMap<>();
                map.put("phone",pphone);
                map.put("password",ppassword);
                map.put("usertype",usertype);
                OkHttp.post(MainActivity.this, Constant.get_userlogin, map, new OkCallback<Result<UserBean>>() {
                    @Override
                    public void onResponse(Result response){
                       SharePrefrenceUtil.saveObject(MainActivity.this,response.getData());
                        if(usertype.equals("普通用户"))
                            startActivity(new Intent(MainActivity.this,OnlinePetitionActivity.class));
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        Toast.makeText(MainActivity.this, "用户信息不正确，请重新输入！", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
   // }
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

