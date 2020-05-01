package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.TaskWorkUserInformationAdapter;
import com.example.work_staff_marching.cyf.adapter.WorkUserMarchedDetailAdapter;
import com.example.work_staff_marching.cyf.entity.EstimateBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class WorkUserMarchedDeatailActivity extends BaseActivity {
    @BindView(R.id.recyclerview1)
    RecyclerView mRecyclerview1;
    private List<UserBean> mTaskBeans = new ArrayList<>();
    private WorkUserMarchedDetailAdapter taskWorkUserInformationAdapter = null;
    String taskID;

    @Override
    protected int getContentView() {
        return R.layout.activity_taskworkuser_information;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        Intent intent1 = getIntent();
        taskID = intent1.getStringExtra("taskID");
        setTitle("信访人员匹配评价详情");
        taskWorkUserInformationAdapter = new WorkUserMarchedDetailAdapter(WorkUserMarchedDeatailActivity.this);
        mRecyclerview1.setAdapter(taskWorkUserInformationAdapter);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(WorkUserMarchedDeatailActivity.this, RecyclerView.VERTICAL, false));
        mRecyclerview1.setItemAnimator(new DefaultItemAnimator());
        onlodata();
    }

    public void onlodata() {
        Map<String, String> map = new HashMap<>();
        map.put("taskID", taskID);
        OkHttp.get(WorkUserMarchedDeatailActivity.this, Constant.ShowTaskWorkUserServlet, map, new OkCallback<Result<List<UserBean>>>() {
            @Override
            public void onResponse(Result<List<UserBean>> response) {
                taskWorkUserInformationAdapter.setNewData(response.getData());
            }

            @Override
            public void onFailure(String state, String msg) {
            }
        });
    }
}

