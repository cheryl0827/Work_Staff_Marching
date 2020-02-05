package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneCallActivity extends BaseActivity {


    @BindView(R.id.tv_text1)
    TextView tvText1;
    @BindView(R.id.tv_text2)
    TextView tvText2;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.bt_one)
    Button btOne;
    @BindView(R.id.bt_two)
    Button btTwo;
    @BindView(R.id.bt_three)
    Button btThree;
    @BindView(R.id.bt_four)
    Button btFour;
    @BindView(R.id.bt_five)
    Button btFive;
    @BindView(R.id.bt_six)
    Button btSix;
    @BindView(R.id.bt_seven)
    Button btSeven;
    @BindView(R.id.bt_eight)
    Button btEight;
    @BindView(R.id.bt_nine)
    Button btNine;
    @BindView(R.id.bt_star)
    Button btStar;
    @BindView(R.id.bt_zero)
    Button btZero;
    @BindView(R.id.bt_bottom)
    Button btBottom;
    @BindView(R.id.bt_call)
    Button btCall;
    private TextView tempText;
    private String all = "";


    @Override
    protected int getContentView() {
        return R.layout.activity_phone_call;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("拨打电话");
        tvText2.setText("13164820880");
        tvText1.setText("13164820880");
        btZero.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_zero);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btOne.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_one);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btTwo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_two);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btThree.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_three);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btFour.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_four);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btFive.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_five);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btSix.setOnClickListener(new View.OnClickListener()  {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_six);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btSeven.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_seven);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btEight.setOnClickListener(new View.OnClickListener()  {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_eight);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btNine.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_nine);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btStar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_star);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btBottom.setOnClickListener(new View.OnClickListener()  {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_bottom);
                all += tempText.getText().toString();
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

        btCall.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tempText = (TextView) findViewById(R.id.bt_call);
                if(PhoneNumberUtils.isGlobalPhoneNumber(tvText2.getText().toString())){
                Intent dial=new Intent();
                dial.setAction(Intent.ACTION_CALL);
                dial.setData(Uri.parse("tel://"+tvText2.getText().toString()));
                startActivity(dial);}
                else
                    Toast.makeText(PhoneCallActivity.this,"电话号码格式不符合要求",Toast.LENGTH_SHORT).show();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                all = all.substring(0, all.length() - 1);
                tvText1.setText(all);
                tvText2.setText(all);
            }
        });

    }

}
