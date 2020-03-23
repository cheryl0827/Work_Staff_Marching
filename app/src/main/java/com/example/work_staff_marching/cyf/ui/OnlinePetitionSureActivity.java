package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;
import com.example.work_staff_marching.cyf.utils.SharePrefrenceUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnlinePetitionSureActivity extends BaseActivity {

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.detailAdress)
    TextView detailAdress;
    @BindView(R.id.catagery)
    TextView catagery;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.contentLineaeLayout)
    LinearLayout contentLineaeLayout;
    String taskTime;
    @Override
    protected int getContentView() {
        return R.layout.activity_online_pititition_sure;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("信访诉求任务预览");
        Intent intent1=getIntent();
        content.setText(intent1.getStringExtra("content"));
        catagery.setText(intent1.getStringExtra("catagery"));
        address.setText(intent1.getStringExtra("address"));
        detailAdress.setText(intent1.getStringExtra("detailaddress"));
        contentLineaeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("content",content.getText().toString());
                intent.setClass(OnlinePetitionSureActivity.this, ContentSureActivity.class);
                OnlinePetitionSureActivity.this.startActivity(intent);
            }
        });
    }


    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        if(view.getId()==R.id.button) {
            CommonDialog commonDialog = new CommonDialog(this);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            taskTime = sdf.format(new java.util.Date());
            Map<String, String> map = new HashMap<>();
            map.put("taskAdress", address.getText().toString());
            map.put("taskCatagery", catagery.getText().toString());
            map.put("taskDetaiAdress", detailAdress.getText().toString());
            map.put("taskContent", content.getText().toString());
            map.put("taskTime",taskTime);
            map.put("userID",SharePrefrenceUtil.getObject(getApplicationContext(), UserBean.class).getUserID() + "");
            OkHttp.post(OnlinePetitionSureActivity.this, Constant.get_taskadd, map, new OkCallback<Result<String>>() {
                @Override
                public void onResponse(Result<String> response) {
                    commonDialog.setPositive("下一步");
                    commonDialog.isSingle=true;
                    commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("诉求任务填写成功,请完成下一步操作！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            Intent intent = new Intent();
                            intent.putExtra("content",content.getText().toString());
                            intent.putExtra("address",address.getText().toString());
                            intent.putExtra("catagery",catagery.getText().toString());
                            intent.putExtra("detailaddress",detailAdress.getText().toString());
                            intent.setClass(OnlinePetitionSureActivity.this, TaskProportionActivity.class);
                            startActivityForResult(intent,3);
                            commonDialog.dismiss();
                        }
                        @Override
                        public void onNegtiveClick() {
                            commonDialog.dismiss();
//                            setResult(RESULT_OK);
//                            finish();
                        }
                    }).show();
                }

                @Override
                public void onFailure(String state, String msg) {
                    Toast.makeText(OnlinePetitionSureActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        finish();
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode == 3) {
                setResult(RESULT_OK);
                finish();
        }

    }
}
