package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.work_staff_marching.cyf.utils.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeUserInformationActivity extends BaseActivity {


    @BindView(R.id.usernameText)
    TextView usernameText;
    @BindView(R.id.indentificationText)
    TextView indentificationText;
    @BindView(R.id.phoneText)
    TextView phoneText;
    @BindView(R.id.phonechangeText)
    EditText phonechangeText;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.addressLinearLayout)
    LinearLayout addressLinearLayout;
    @BindView(R.id.detaiaddress)
    EditText detaiaddress;
    @BindView(R.id.register)
    Button register;
    public static String address1String=null;
    public static String address2String=null;
    public static String address3String=null;
    public static String addressString=null;
    @Override
    protected int getContentView() {
        return R.layout.activity_people_information;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("修改个人信息");
        usernameText.setText(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getUserName() + "");
        indentificationText.setText(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getIndentificationCard() + "");
        phoneText.setText(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getPhone() + "");
        address.setText(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getProvince() +"-"+ SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getCity()+"-"+SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getCountry()+"");
        addressLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), InformationAddressActivity.class);
                startActivityForResult(intent,3);
            }
        });

    }


    @OnClick(R.id.register)
    public void onViewClicked() {
        CommonDialog commonDialog = new CommonDialog(this);
        if((!phonechangeText.getText().toString().equals("")&&!detaiaddress.getText().toString().equals("")&&address.getText().toString().equals(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getProvince() +"-"+ SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getCity()+"-"+SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getCountry()+""))
            ||(phonechangeText.getText().toString().equals("")&&!detaiaddress.getText().toString().equals("")&&address.getText().toString().equals(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getProvince() +"-"+ SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getCity()+"-"+SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getCountry()+"")))
            Toast.makeText(ChangeUserInformationActivity.this, "请选择所在地区！", Toast.LENGTH_LONG).show();
            if (!(isMobileNo(phonechangeText.getText().toString()))&&!phonechangeText.getText().toString().equals("")) {
            Toast.makeText(ChangeUserInformationActivity.this, "您输入的手机号码不正确，请重新输入！", Toast.LENGTH_LONG).show();
        }
        if(phonechangeText.getText().toString().equals("")&&detaiaddress.getText().toString().equals(""))
            Toast.makeText(ChangeUserInformationActivity.this,"修改的个人信息不能为空",Toast.LENGTH_SHORT).show();
        if((isMobileNo(phonechangeText.getText().toString()))&&detaiaddress.getText().toString().equals("")&&!phonechangeText.getText().toString().equals("")) {
            Map<String, String> map1 = new HashMap<>();
            map1.put("userID",SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getUserID() + "");
            map1.put("phone",phonechangeText.getText().toString());
            OkHttp.post(ChangeUserInformationActivity.this, Constant.get_changephone,map1, new OkCallback<Result<String>>() {
                @Override
                public void onResponse(Result response) {
                    commonDialog.isSingle = true;
                    commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("修改手机号码成功，返回登录页面重新登录！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            startActivity(new Intent(ChangeUserInformationActivity.this, MainActivity.class));
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
        if(!detaiaddress.getText().toString().equals("")&&phonechangeText.getText().toString().equals("")&&!address.getText().toString().equals(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getProvince() +"-"+ SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getCity()+"-"+SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getCountry()+"")) {
            Map<String, String> map2 = new HashMap<>();
            map2.put("userID",SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getUserID() + "");
            map2.put("province",address1String);
            map2.put("city",address2String);
            map2.put("country",address3String);
            map2.put("address",detaiaddress.getText().toString());
            OkHttp.post(ChangeUserInformationActivity.this, Constant.get_changeaddress,map2, new OkCallback<Result<String>>() {
                @Override
                public void onResponse(Result response) {
                    commonDialog.isSingle = true;
                    commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("修改地址成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            finish();
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
        if(!phonechangeText.getText().toString().equals("")&&!detaiaddress.getText().toString().equals("")&&!address.getText().toString().equals(SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getProvince() +"-"+ SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getCity()+"-"+SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getCountry()+"")) {
            Map<String, String> map = new HashMap<>();
            map.put("userID",SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getUserID() + "");
            map.put("province",address1String);
            map.put("city",address2String);
            map.put("country",address3String);
            map.put("address",detaiaddress.getText().toString());
            map.put("phone",phonechangeText.getText().toString());
            OkHttp.post(ChangeUserInformationActivity.this, Constant.get_changeinformation, map, new OkCallback<Result<String>>() {
                @Override
                public void onResponse(Result response) {
                    commonDialog.isSingle = true;
                    commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("修改个人信息成功，返回登录页面重新登录！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            startActivity(new Intent(ChangeUserInformationActivity.this, MainActivity.class));
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

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK){
            if (requestCode==3) {
                address.setText(addressString);
            }}
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
