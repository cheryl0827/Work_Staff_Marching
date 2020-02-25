package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransactionRecordActivity extends BaseActivity {
    final private static String TAG = "Toby_Test";
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.et_word)
    EditText etWord;
    @BindView(R.id.tv_word_count)
    TextView tvWordCount;
    @BindView(R.id.record)
    Button record;
    @BindView(R.id.end)
    Button end;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;


    @Override
    protected int getContentView() {
        return R.layout.activity_transaction_record;
    }

    @Override
    protected void init(Bundle saveInstanceState) {

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        final Calendar c = Calendar.getInstance();
        date.setText(
                String.valueOf(c.get(Calendar.YEAR)) + "/" +
                String.valueOf(c.get(Calendar.MONTH) + 1) + "/" +
                String.valueOf(c.get(Calendar.DATE)));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView,
                                            int year, int month, int dayOfMonth) {
                date.setText( String.valueOf(year) + "/" +
                        String.valueOf(month + 1) + "/" + String.valueOf(dayOfMonth));
            }
        });
        etWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvWordCount.setText(String.valueOf(s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.record, R.id.end})
    public void onViewClicked(View view) {
        final Calendar c = Calendar.getInstance();
        Intent intent1=getIntent();
        CommonDialog commonDialog = new CommonDialog(this);
        switch (view.getId()) {
            case R.id.record:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                String time=sdf.format(new java.util.Date());
                Map<String, String> map = new HashMap<>();
                map.put("taskID",intent1.getStringExtra("taskID"));
                map.put("nextVisitTime",date.getText().toString());
                map.put("recordContent",etWord.getText().toString());
                map.put("recordTime",time);
                OkHttp.post(TransactionRecordActivity.this, Constant.get_addrecord, map, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result<String> response) {
                            commonDialog.isSingle=true;
                            commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("办理记录成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                                @Override
                                public void onPositiveClick() {
                                    date.setText(
                                            String.valueOf(c.get(Calendar.YEAR)) + "/" +
                                            String.valueOf(c.get(Calendar.MONTH) + 1) + "/" +
                                            String.valueOf(c.get(Calendar.DATE)));
                                    etWord.setText("");
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
                        Toast.makeText(TransactionRecordActivity.this,msg, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.end:
                break;
        }
    }
}
