package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

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

    @Override
    protected int getContentView() {
        return R.layout.activity_online_pititition_sure;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("信访诉求任务预览");
        Intent intent1=getIntent();
        content.setText(intent1.getStringExtra("content"));
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
    public void onViewClicked() {
    }
}
