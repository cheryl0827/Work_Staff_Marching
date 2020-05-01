package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.Date;
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
    @BindView(R.id.community)
    EditText community;
    @BindView(R.id.urgent)
    EditText urgent;
    @BindView(R.id.psychology)
    EditText psychology;
    @BindView(R.id.organization)
    EditText organization;
    @BindView(R.id.analyse)
    EditText analyse;
    @BindView(R.id.law)
    EditText law;

    @Override
    protected int getContentView() {
        return R.layout.activity_change_online_petitition;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("信访诉求任务修改");
        Intent intent1 = getIntent();
        content.setText(intent1.getStringExtra("taskContent"));
        catagery.setText(intent1.getStringExtra("taskCatagery"));
        address.setText(intent1.getStringExtra("taskAdress"));
        detailAdress.setText(intent1.getStringExtra("taskDetailAdress"));
        community.setText(intent1.getStringExtra("community"));
        analyse.setText(intent1.getStringExtra("analyse"));
        organization.setText(intent1.getStringExtra("organization"));
        law.setText(intent1.getStringExtra("law"));
        urgent.setText(intent1.getStringExtra("urgent"));
        psychology.setText(intent1.getStringExtra("psychology"));
        taskID = intent1.getStringExtra("taskID");
        contentLineaeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("content", content.getText().toString());
                intent.setClass(ChangeOnlinePetitionActivity.this, ChangeContentActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        catageryLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChangeOnlinePetitionActivity.this, ChangeCatageryActivity.class);
                startActivityForResult(intent, 3);
            }
        });
        addressLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChangeOnlinePetitionActivity.this, ChangeAddressActivity.class);
                startActivityForResult(intent, 2);
            }
        });
        community.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(ChangeOnlinePetitionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        urgent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(ChangeOnlinePetitionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        psychology.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(ChangeOnlinePetitionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        organization.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(ChangeOnlinePetitionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        analyse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(ChangeOnlinePetitionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        law.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    int port = Integer.parseInt(s.toString());   //把port转换成int整形
                    if (port < 0 || port > 100) {
                        Toast.makeText(ChangeOnlinePetitionActivity.this, "输入的值只能在0-100之间", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        int community1 = Integer.parseInt(community.getText().toString());
        int urgent1 = Integer.parseInt(urgent.getText().toString());
        int psychology1 = Integer.parseInt(psychology.getText().toString());
        int organization1 = Integer.parseInt(organization.getText().toString());
        int analyse1 = Integer.parseInt(analyse.getText().toString());
        int law1 = Integer.parseInt(law.getText().toString());
        int count = community1 + urgent1 + psychology1 + organization1 + analyse1 + law1;
        if (view.getId() == R.id.button) {
            CommonDialog commonDialog = new CommonDialog(this);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            taskTime = sdf.format(new Date());
            Map<String, String> map1 = new HashMap<>();
            map1.put("taskAdress", address.getText().toString());
            map1.put("taskCatagery", catagery.getText().toString());
            map1.put("taskDetaiAdress", detailAdress.getText().toString());
            map1.put("taskContent", content.getText().toString());
            map1.put("taskTime", taskTime);
            map1.put("taskID", taskID);
            map1.put("community", community.getText().toString());
            map1.put("urgent", urgent.getText().toString());
            map1.put("psychology", psychology.getText().toString());
            map1.put("organization", organization.getText().toString());
            map1.put("analyse", analyse.getText().toString());
            map1.put("law", law.getText().toString());
            if (count != 100)
                Toast.makeText(ChangeOnlinePetitionActivity.this, "输入的权重总值只能是100，请重新输入", Toast.LENGTH_SHORT).show();
            else {
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                content.setText(contentString);
            }
            if (requestCode == 2) {
                address.setText(addressString);
            }
            if (requestCode == 3) {
                catagery.setText(catageryString);
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
