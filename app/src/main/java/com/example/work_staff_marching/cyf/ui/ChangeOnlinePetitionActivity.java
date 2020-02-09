package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeOnlinePetitionActivity extends BaseActivity {


    @BindView(R.id.editText)
    TextView editText;
    @BindView(R.id.catagery)
    TextView catagery;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.catageryLinearLayout)
    LinearLayout catageryLinearLayout;
    @BindView(R.id.edittext1)
    TextView edittext1;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.contentLineaeLayout)
    LinearLayout contentLineaeLayout;
    @BindView(R.id.edittext)
    TextView edittext;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.addressLinearLayout)
    LinearLayout addressLinearLayout;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.detailAdress)
    EditText detailAdress;
    @BindView(R.id.detailAdressLinearLayout)
    LinearLayout detailAdressLinearLayout;
    @BindView(R.id.button)
    Button button;
    public static String contentString;
    public static String catagery1String;
    public static String catagery2String;
    public static String catagery3String;
    public static String catageryString;
    public static String address1String;
    public static String address2String;
    public static String address3String;
    public static String addressString;
    String taskTime;
    String taskID;

    @Override
    protected int getContentView() {
        return R.layout.activity_change_online_petitition;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("信访诉求任务修改");
        Intent intent1=getIntent();
        content.setText(intent1.getStringExtra("taskContent"));
        catagery.setText(intent1.getStringExtra("taskCatagery"));
        address.setText(intent1.getStringExtra("taskAdress"));
        detailAdress.setText(intent1.getStringExtra("taskDetailAdress"));
        taskID=intent1.getStringExtra("taskID");
        contentLineaeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("content",content.getText().toString());
                intent.setClass(ChangeOnlinePetitionActivity.this, ChangeContentActivity.class);
                startActivityForResult(intent,1);
            }
        });
        catageryLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChangeOnlinePetitionActivity.this,ChangeCatageryActivity.class);
                startActivityForResult(intent,3);
            }
        });
        addressLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChangeOnlinePetitionActivity.this,ChangeAddressActivity.class);
                startActivityForResult(intent,2);
            }
        });
    }


    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        if(view.getId()==R.id.button) {
            CommonDialog commonDialog = new CommonDialog(this);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            taskTime = sdf.format(new java.util.Date());
            Map<String, String> map1 = new HashMap<>();
            map1.put("taskAdress", address.getText().toString());
            map1.put("taskCatagery", catagery.getText().toString());
            map1.put("taskDetaiAdress", detailAdress.getText().toString());
            map1.put("taskContent", content.getText().toString());
            map1.put("taskTime", taskTime);
            map1.put("taskID", taskID);
            OkHttp.post(ChangeOnlinePetitionActivity.this, Constant.get_taskupdate, map1, new OkCallback<Result<String>>() {
                @Override
                public void onResponse(Result response) {
                    commonDialog.isSingle = true;
                    commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("修改成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            commonDialog.dismiss();
                            setResult(RESULT_OK);
                            finish();
                        }

                        @Override
                        public void onNegtiveClick() {
                            commonDialog.dismiss();
                        }
                    }).show();
                }

                @Override
                public void onFailure(String state, String msg) {
                    Toast.makeText(ChangeOnlinePetitionActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK){
            if (requestCode==1){
                content.setText(contentString);
            }
            if (requestCode==2){
                address.setText(addressString);
            }
            if (requestCode==3){
                catagery.setText(catageryString);
            }

        }

    }
}
