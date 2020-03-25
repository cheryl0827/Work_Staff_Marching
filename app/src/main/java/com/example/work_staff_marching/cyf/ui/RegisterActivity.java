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
import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.man)
    RadioButton man;
    @BindView(R.id.woman)
    RadioButton woman;
    @BindView(R.id.sex)
    RadioGroup sex;
    String sex1="男";
    String usertype="普通用户";
    String date1;
    String workuserNo;
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
                RadioButton rb = (RadioButton) RegisterActivity.this.findViewById(radio.getCheckedRadioButtonId());
                usertype = rb.getText().toString();
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
                if (!("").equals(userName.getText().toString()))
                    userName1.setText("");
                if (("").equals(userName.getText().toString()))
                    userName1.setText("用户名不能为空");
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
                if (!("").equals(phone.getText().toString()) && (isMobileNo(phone.getText().toString())))
                    phone1.setText("");
                if (("").equals(phone.getText().toString()))
                    phone1.setText("手机号码不能为空");
                if(!isMobileNo(phone.getText().toString()))
                    phone1.setText("手机号码不合法");
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
                if (!("").equals(password.getText().toString()))
                    password1.setText("");
                if (("").equals(password.getText().toString()))
                    password1.setText("密码不能为空");
                if(password.getText().toString().trim().length()<6||password.getText().toString().trim().length()>6)
                    password1.setText("输入6-16位由字母和数字组成的密码");
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
                if (!("").equals(passwordSure.getText().toString()) && password.getText().toString().equals(passwordSure.getText().toString()))
                    passwordSure1.setText("");
                if(("").equals(passwordSure.getText().toString()))
                    passwordSure1.setText("确认密码不能为空");
                if(!password.getText().toString().equals(passwordSure.getText().toString()))
                    passwordSure1.setText("两次密码不一致");
            }
        });
        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1=(RadioButton)RegisterActivity.this.findViewById(sex.getCheckedRadioButtonId());
                sex1=rb1.getText().toString();
            }
        });
    }

    @OnClick({R.id.next, R.id.cancel})
    public void onViewClicked(View view) {

        if (userName1.getText().toString().equals("") && password1.getText().toString().equals("") && passwordSure1.getText().toString().equals("") && phone1.getText().toString().equals("")) {
            CommonDialog commonDialog = new CommonDialog(this);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String time=sdf.format(new java.util.Date());
            if(usertype.equals("工作用户")){
                workuserNo=dataOne(time);
            }
            if(usertype.equals("普通用户")) {
                workuserNo = "";
            }
            Map<String, String> map = new HashMap<>();
            map.put("userName",userName.getText().toString());
            map.put("phone",phone.getText().toString());
            map.put("password",password.getText().toString());
            map.put("roleName",usertype);
            map.put("sex",sex1);
            map.put("workuserNo",workuserNo);
            Intent intent = new Intent();
           intent.putExtra("userName", userName.getText().toString());
           intent.putExtra("phone", phone.getText().toString());
           intent.putExtra("password", password.getText().toString());
           intent.putExtra("roleName", usertype);
           intent.putExtra("sex",sex1);
           intent.putExtra("workuserNo",workuserNo);
            OkHttp.post(RegisterActivity.this, Constant.get_register, map, new OkCallback<Result<String>>() {
                @Override
                public void onResponse(Result<String> response) {
                    if (view.getId() == R.id.next && usertype.equals("普通用户")) {
                       // map.put("workuserNo",workuserNo);
                        //startActivity(new Intent(RegisterActivity.this,RegisterUserActivity.class));
                        // Toast.makeText(RegisterActivity.this, "nisdajkd！", Toast.LENGTH_SHORT).show();
                      intent.setClass(RegisterActivity.this, RegisterUserActivity.class);
                       RegisterActivity.this.startActivity(intent);
                    }
                    if (view.getId() == R.id.next && usertype.equals("工作用户")) {
                        commonDialog.isSingle=true;
                        commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("注册成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                commonDialog.dismiss();
                            }
                            @Override
                            public void onNegtiveClick() {
                                commonDialog.dismiss();
                            }
                        }).show();
                    }
                }

                @Override
                public void onFailure(String state, String msg) {
                    Toast.makeText(RegisterActivity.this, "该用户已经存在，请重新输入！", Toast.LENGTH_SHORT).show();
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

//    public String stampToDate(long timeMillis) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date(timeMillis);
//        return simpleDateFormat.format(date);
//    }
    public static String dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }



}

