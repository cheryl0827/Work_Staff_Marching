package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentSureActivity extends BaseActivity {
    @BindView(R.id.et_word)
    EditText etWord;

    @Override
    protected int getContentView() {
        return R.layout.activity_content_sure;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("上访内容预览");
        Intent intent1=getIntent();
        etWord.setText(intent1.getStringExtra("content"));
    }

}
