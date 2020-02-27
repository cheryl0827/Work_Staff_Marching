package com.example.work_staff_marching.cyf.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.TransactionRecordAdapter;
import com.example.work_staff_marching.cyf.entity.RecordBean;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.CustomToast;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class TransactionRecordShowActivity extends BaseActivity {
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    private TransactionRecordAdapter transactionRecordAdapter=null;

    @Override
    protected int getContentView() {
        return R.layout.activity_transaction_record_show;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("办理记录");
        transactionRecordAdapter = new TransactionRecordAdapter(TransactionRecordShowActivity.this);
        recyclerview1.setAdapter(transactionRecordAdapter);
        recyclerview1.setLayoutManager(new LinearLayoutManager(TransactionRecordShowActivity.this, RecyclerView.VERTICAL, false));
        recyclerview1.setItemAnimator(new DefaultItemAnimator());
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swiperereshlayout.setRefreshing(false);
            }
        });
        loadData();
    }
    public void loadData(){
        Intent intent1=getIntent();
        Map<String, String> map = new HashMap<>();
        map.put("taskID",intent1.getStringExtra("taskID"));
        OkHttp.get(TransactionRecordShowActivity.this, Constant.get_showrecord, map,
                new OkCallback<Result<List<RecordBean>>>() {
                    @Override
                    public void onResponse(Result<List<RecordBean>> response) {
                        transactionRecordAdapter.setNewData(response.getData());
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(TransactionRecordShowActivity.this, msg);
                    }
                });

    }
}
