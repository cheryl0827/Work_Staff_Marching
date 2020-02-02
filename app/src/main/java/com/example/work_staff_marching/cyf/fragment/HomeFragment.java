package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.ui.CatageryActivity;
import com.example.work_staff_marching.cyf.ui.ContentActivity;
import com.example.work_staff_marching.cyf.ui.OnlinePetitionSureActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.catagery)
    TextView catagery;
    @BindView(R.id.catageryLinearLayout)
    LinearLayout catageryLinearLayout;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.contentLineaeLayout)
    LinearLayout contentLineaeLayout;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.addressLinearLayout)
    LinearLayout addressLinearLayout;
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

    @Override
    protected int initLayout() {
        return R.layout.activity_online_petition;
    }

    @Override
    protected void initView(View view) {
        content.setText(contentString);
        catagery.setText(catagery1String);
        contentLineaeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("content",content.getText().toString());
                intent.setClass(getContext(), ContentActivity.class);
                getContext().startActivity(intent);
            }
        });
        catageryLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), CatageryActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(Context mContext) {

    }

    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        intent.putExtra("content",content.getText().toString());
        intent.putExtra("detailaddress",detailAdress.getText().toString());
        if(view.getId()==R.id.button){
            intent.setClass(getContext(), OnlinePetitionSureActivity.class);
            getContext().startActivity(intent);
        }
    }
//回到保存页面
    @Override
    public void onResume() {
        super.onResume();
        initView(getView());
    }
}
