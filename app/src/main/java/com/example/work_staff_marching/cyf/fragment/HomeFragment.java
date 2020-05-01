package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.ui.AdressActivity;
import com.example.work_staff_marching.cyf.ui.CatageryActivity;
import com.example.work_staff_marching.cyf.ui.ContentActivity;
import com.example.work_staff_marching.cyf.ui.MainActivity;
import com.example.work_staff_marching.cyf.ui.OnlinePetitionSureActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

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
    public static String contentString="";
    public static String catagery1String;
    public static String catagery2String;
    public static String catagery3String;
    public static String catageryString="";
    public static String address1String;
    public static String address2String;
    public static String address3String;
    public static String addressString="";

    @Override
    protected int initLayout() {
        return R.layout.activity_online_petition;
    }

    @Override
    protected void initView(View view) {
//        content.setText(contentString);
//        catagery.setText(catageryString);
//        address.setText(addressString);
        contentLineaeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("content",content.getText().toString());
                intent.setClass(getContext(), ContentActivity.class);
                startActivityForResult(intent,2);
            }
        });
        catageryLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), CatageryActivity.class);
                startActivityForResult(intent,3);
            }
        });
        addressLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), AdressActivity.class);
                startActivityForResult(intent,4);
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
        intent.putExtra("address",address.getText().toString());
        intent.putExtra("catagery",catagery.getText().toString());
        intent.putExtra("detailaddress",detailAdress.getText().toString());
        if(view.getId()==R.id.button){
            if(catagery.getText().toString().equals(""))
                Toast.makeText(getContext(), "请选择问题类别！", Toast.LENGTH_SHORT).show();
            else if(content.getText().toString().equals(""))
                Toast.makeText(getContext(), "请输入内容！", Toast.LENGTH_SHORT).show();
            else if(address.getText().toString().equals(""))
                Toast.makeText(getContext(), "请选择事发地！", Toast.LENGTH_SHORT).show();
            else if(detailAdress.getText().toString().equals(""))
                Toast.makeText(getContext(), "请输入详细地址！", Toast.LENGTH_SHORT).show();
            else {
                intent.setClass(getContext(), OnlinePetitionSureActivity.class);
                this.startActivityForResult(intent,1);
            }
        }
    }
//回到保存页面
//    @Override
//    public void onResume() {
//        super.onResume();
//        initView(getView());
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
if (resultCode==RESULT_OK){
    if (requestCode==1){
        //置空代码
        content.setText("");
        catagery.setText("");
        address.setText("");
        detailAdress.setText("");
    }
    if (requestCode==2)
        content.setText(contentString);
    if (requestCode==3)
        catagery.setText(catageryString);
    if (requestCode==4)
        address.setText(addressString);

}
    }
}
