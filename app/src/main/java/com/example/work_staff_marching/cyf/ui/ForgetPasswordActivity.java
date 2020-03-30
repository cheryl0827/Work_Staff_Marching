package com.example.work_staff_marching.cyf.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;
import com.example.work_staff_marching.cyf.utils.SMSTextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity {


    @BindView(R.id.phoneText)
    EditText phoneText;
    @BindView(R.id.identifyCode)
    TextView identifyCode;
    @BindView(R.id.indentifyCodeGet)
    SMSTextView indentifyCodeGet;
    @BindView(R.id.passwordText)
    EditText passwordText;
    @BindView(R.id.register)
    Button register;
    String indentifycode;
    @BindView(R.id.identityCodeText)
    EditText identityCodeText;

    @Override
    protected int getContentView() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("忘记密码");

    }

    @OnClick({R.id.indentifyCodeGet, R.id.register})
    public void onViewClicked(View view) {
        CommonDialog commonDialog = new CommonDialog(this);
        switch (view.getId()) {
            case R.id.indentifyCodeGet:
            Map<String, String> map = new HashMap<>();
            map.put("phone", phoneText.getText().toString());
            OkHttp.post(ForgetPasswordActivity.this, Constant.get_code, map, new OkCallback<Result<String>>() {
                @Override
                public void onResponse(Result response) {
                    indentifyCodeGet.start();//开启验证码倒计时
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification.Builder builder1 = new Notification.Builder(ForgetPasswordActivity.this);
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
                    Toast.makeText(ForgetPasswordActivity.this, "该用户不存在，请重新输入！", Toast.LENGTH_SHORT).show();
                }
            });
          break;
          case R.id.register:
            if (identityCodeText.getText().toString().equals(""))
                Toast.makeText(ForgetPasswordActivity.this, "请输入验证码！", Toast.LENGTH_SHORT).show();
            else if (!(identityCodeText.getText().toString().equals(indentifycode)))
                Toast.makeText(ForgetPasswordActivity.this, "验证码不正确，请重新输入！", Toast.LENGTH_SHORT).show();
            else if (passwordText.getText().toString().equals(""))
                  Toast.makeText(ForgetPasswordActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();

           else {
                Map<String, String> map1 = new HashMap<>();
                map1.put("phone", phoneText.getText().toString());
                map1.put("password", passwordText.getText().toString());
                OkHttp.post(ForgetPasswordActivity.this, Constant.get_forgetpassword, map1, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result response) {
                        commonDialog.isSingle = true;
                        commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("找回密码成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                startActivity(new Intent(ForgetPasswordActivity.this, MainActivity.class));
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
                        Toast.makeText(ForgetPasswordActivity.this,msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            break;
        }
        }
        }




