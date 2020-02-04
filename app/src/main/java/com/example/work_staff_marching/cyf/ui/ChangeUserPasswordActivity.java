package com.example.work_staff_marching.cyf.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;
import com.example.work_staff_marching.cyf.utils.SMSTextView;
import com.example.work_staff_marching.cyf.utils.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeUserPasswordActivity extends BaseActivity {


    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.phoneText)
    EditText phoneText;
    @BindView(R.id.phoneLinear)
    LinearLayout phoneLinear;
    @BindView(R.id.identifyCode)
    TextView identifyCode;
    @BindView(R.id.identityCodeText)
    EditText identityCodeText;
    @BindView(R.id.indentifyCodeGet)
    SMSTextView indentifyCodeGet;
    @BindView(R.id.identifyCodeLinear)
    LinearLayout identifyCodeLinear;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.passwordText)
    EditText passwordText;
    @BindView(R.id.passwordLinear)
    LinearLayout passwordLinear;
    @BindView(R.id.passwordSure)
    TextView passwordSure;
    @BindView(R.id.passwordSureText)
    EditText passwordSureText;
    @BindView(R.id.passwordSureLinear)
    LinearLayout passwordSureLinear;
    @BindView(R.id.register)
    Button register;
    String indentifycode;

    @Override
    protected int getContentView() {
        return R.layout.activity_change_userpassword;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        phoneText.setText(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getPhone() + "");
        setTitle("修改密码");

    }


    @OnClick({R.id.indentifyCodeGet, R.id.register})
    public void onViewClicked(View view) {
        CommonDialog commonDialog = new CommonDialog(this);
        switch (view.getId()) {
            case R.id.indentifyCodeGet:
                Map<String, String> map = new HashMap<>();
                map.put("phone", phoneText.getText().toString());
//                map.put("password",passwordText.getText().toString());
                OkHttp.post(ChangeUserPasswordActivity.this, Constant.get_code, map, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result response) {
                        indentifyCodeGet.start();//开启验证码倒计时
                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification.Builder builder1 = new Notification.Builder(ChangeUserPasswordActivity.this);
                        builder1.setSmallIcon(R.drawable.ic_preview_radio_on); //设置图标
                        builder1.setTicker("收到验证码");
                        builder1.setContentTitle("通知，获取到的验证码为："); //设置标题
                        indentifycode = response.getData() + "";
                        builder1.setContentText(indentifycode); //消息内容
                        builder1.setWhen(System.currentTimeMillis()); //发送时间
                        builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
                        builder1.setAutoCancel(true);//打开程序后图标消失
                        Notification notification1 = builder1.build();
                        notificationManager.notify(124, notification1); // 通过通知管理器发送通知
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        Toast.makeText(ChangeUserPasswordActivity.this, "该用户不存在，请重新输入！", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.register:
                Map<String, String> map1 = new HashMap<>();
               // map1.put("phone", phoneText.getText().toString());
                map1.put("password",passwordText.getText().toString());
                map1.put("userID",SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getUserID() + "");
                    if(identityCodeText.getText().toString().equals("")||passwordText.getText().toString().equals("")||passwordSureText.getText().toString().equals(""))
                        Toast.makeText(ChangeUserPasswordActivity.this, "请输入以上内容，不能为空！", Toast.LENGTH_SHORT).show();
                    if (!(identityCodeText.getText().toString().equals(indentifycode))) {
                    Toast.makeText(ChangeUserPasswordActivity.this, "验证码不正确，请重新输入！", Toast.LENGTH_SHORT).show();
                }
                if (!(passwordText.getText().toString().equals(passwordSureText.getText().toString()))) {
                    Toast.makeText(ChangeUserPasswordActivity.this, "两次输入的密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
                }
                if(identityCodeText.getText().toString().equals(indentifycode)&&passwordText.getText().toString().equals(passwordSureText.getText().toString())){
                    OkHttp.post(ChangeUserPasswordActivity.this, Constant.get_changepassword, map1, new OkCallback<Result<String>>() {
                        @Override
                        public void onResponse(Result response) {
                            commonDialog.isSingle=true;
                            commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("修改密码成功,返回登陆页面重新登陆").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                                @Override
                                public void onPositiveClick() {
                                    startActivity(new Intent(ChangeUserPasswordActivity.this,MainActivity.class));
                                    commonDialog.dismiss();
                                }
                                @Override
                                public void onNegtiveClick() {
                                    commonDialog.dismiss();
                                }
                            }).show();
                        }
                        @Override
                        public void onFailure(String state, String msg) {
                        }
                    });
                }
                break;
        }

    }
}
