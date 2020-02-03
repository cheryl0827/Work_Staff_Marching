package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.fragment.HomeFragment;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentActivity extends BaseActivity {

    @BindView(R.id.et_word)
    EditText etWord;
    @BindView(R.id.tv_word_count)
    TextView tvWordCount;
    @BindView(R.id.button)
    Button button;

    @Override
    protected int getContentView() {
        return R.layout.activity_content;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("上访内容");
        Intent intent = new Intent();
        //对内容字数的计数
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
        Intent intent1=getIntent();
        etWord.setText(intent1.getStringExtra("content"));
    }
    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        HomeFragment.contentString=etWord.getText().toString();
        setResult(RESULT_OK);
        finish();
    }
}
