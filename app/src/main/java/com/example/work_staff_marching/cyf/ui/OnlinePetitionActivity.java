package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnlinePetitionActivity extends BaseActivity {


    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.addressLinearLayout)
    LinearLayout addressLinearLayout;
    @BindView(R.id.detailAdress)
    EditText detailAdress;
    @BindView(R.id.detailAdressLinearLayout)
    LinearLayout detailAdressLinearLayout;
    @BindView(R.id.catagery)
    TextView catagery;
    @BindView(R.id.catageryLinearLayout)
    LinearLayout catageryLinearLayout;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.contentLineaeLayout)
    LinearLayout contentLineaeLayout;
    @BindView(R.id.button)
    Button button;

    @Override
    protected int getContentView() {
        return R.layout.activity_online_petition;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("信访诉求任务填写");
        Intent intent=getIntent();
        content.setText(intent.getStringExtra("content"));
        contentLineaeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("content",content.getText().toString());
                intent.setClass(OnlinePetitionActivity.this, ContentActivity.class);
                OnlinePetitionActivity.this.startActivity(intent);
        }
        });

    }

    @Override
    protected boolean hasBack() {
        return false;
    }


    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        intent.putExtra("content",content.getText().toString());
        intent.putExtra("detailaddress",detailAdress.getText().toString());
        if(view.getId()==R.id.button){
            intent.setClass(OnlinePetitionActivity.this, OnlinePetitionSureActivity.class);
            OnlinePetitionActivity.this.startActivity(intent);
        }

    }
}
