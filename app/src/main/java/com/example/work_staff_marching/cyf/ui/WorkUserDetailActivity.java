package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.TaskRecycleViewAdapter;
import com.example.work_staff_marching.cyf.adapter.TaskWorkUserInformationAdapter;
import com.example.work_staff_marching.cyf.entity.TaskBean;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkUserDetailActivity extends BaseActivity {
    @BindView(R.id.recyclerview1)
    RecyclerView mRecyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    private List<UserBean> mTaskBeans = new ArrayList<>();
    private TaskWorkUserInformationAdapter taskWorkUserInformationAdapter=null;
    String taskID;

    @Override
    protected int getContentView() {
        return R.layout.activity_task_workuser_information;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        Intent intent1 = getIntent();
        taskID=intent1.getStringExtra("taskID");
        setTitle("匹配工作人员的基本信息");
        taskWorkUserInformationAdapter = new TaskWorkUserInformationAdapter(WorkUserDetailActivity.this);
        mRecyclerview1.setAdapter(taskWorkUserInformationAdapter);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(WorkUserDetailActivity.this, RecyclerView.VERTICAL, false));
        mRecyclerview1.setItemAnimator(new DefaultItemAnimator());
        onlodata();
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onlodata();
                swiperereshlayout.setRefreshing(false);
            }
        });

        taskWorkUserInformationAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.dailphone:
                        Intent intent1 = new Intent();
                        intent1.putExtra("phone", taskWorkUserInformationAdapter.getItem(position).getPhone());
                        intent1.setClass(WorkUserDetailActivity.this, PhoneCallActivity.class);
                        startActivity(intent1);
                        break;
                }
            }
        });


    }

    public void onlodata() {
        Map<String, String> map = new HashMap<>();
        map.put("taskID",taskID);
        OkHttp.get(WorkUserDetailActivity.this, Constant.ShowTaskWorkUserServlet, map, new OkCallback<Result<List<UserBean>>>() {
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
