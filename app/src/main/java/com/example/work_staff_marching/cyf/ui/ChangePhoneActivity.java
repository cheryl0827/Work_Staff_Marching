package com.example.work_staff_marching.cyf.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ChangePhoneActivity extends BaseActivity {

    @BindView(R.id.usernameText)
    TextView usernameText;
    @BindView(R.id.phoneText)
    TextView phoneText;
    @BindView(R.id.phonechangeText)
    EditText phonechangeText;
    @BindView(R.id.identityCodeText)
    EditText identityCodeText;
    @BindView(R.id.indentifyCodeGet)
    SMSTextView indentifyCodeGet;
    @BindView(R.id.register)
    Button register;
    String indentifycode;

    @Override
    protected int getContentView() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("修改手机号码");
        usernameText.setText(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getUserName() + "");
        phoneText.setText(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getPhone() + "");

    }

    @OnClick({R.id.indentifyCodeGet, R.id.register})
    public void onViewClicked(View view) {
        CommonDialog commonDialog = new CommonDialog(this);
        switch (view.getId()) {
            case R.id.register:
                Map<String, String> map1 = new HashMap<>();
                map1.put("userID", SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getUserID() + "");
                map1.put("phone", phonechangeText.getText().toString());
                if (!(isMobileNo(phonechangeText.getText().toString())) && !phonechangeText.getText().toString().equals("")) {
                    Toast.makeText(ChangePhoneActivity.this, "您输入的手机号码不正确，请重新输入！", Toast.LENGTH_SHORT).show();
                }
                if (!(identityCodeText.getText().toString().equals(indentifycode)))
                    Toast.makeText(ChangePhoneActivity.this, "验证码不正确，请重新输入！", Toast.LENGTH_SHORT).show();
                if ((isMobileNo(phonechangeText.getText().toString()))&&identityCodeText.getText().toString().equals(indentifycode)) {
                    OkHttp.post(ChangePhoneActivity.this, Constant.get_changephone, map1, new OkCallback<Result<String>>() {
                        @Override
                        public void onResponse(Result<String> response) {
                            commonDialog.isSingle = true;
                            commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("修改手机号码成功，返回登录页面重新登录！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                                @Override
                                public void onPositiveClick() {
                                    startActivity(new Intent(ChangePhoneActivity.this, MainActivity.class));
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
            case R.id.indentifyCodeGet:
                Map<String, String> map = new HashMap<>();
                map.put("phone", phoneText.getText().toString());
                OkHttp.post(ChangePhoneActivity.this, Constant.get_code1, map, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result response) {
                        indentifyCodeGet.start();//开启验证码倒计时
                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification.Builder builder1 = new Notification.Builder(ChangePhoneActivity.this);
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
                        Toast.makeText(ChangePhoneActivity.this,msg, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
      }
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
