package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.WorkUserTaskAdapter;
import com.example.work_staff_marching.cyf.entity.EstimateBean;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkUserTaskActivity extends BaseActivity {
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    @BindView(R.id.username)
    TextView username;
    String workUserNo, username1;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.edit_search_context)
    EditText editSearchContext;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    private WorkUserTaskAdapter workUserTaskAdapter = null;

    @Override
    protected int getContentView() {
        return R.layout.workuser_task_activity;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("办理结束的所有诉求任务");
        Intent intent = getIntent();
        username1 = intent.getStringExtra("username");
        username.setText(username1);
        workUserNo = intent.getStringExtra("workuserNo");
        workUserTaskAdapter = new WorkUserTaskAdapter(WorkUserTaskActivity.this);
        recyclerview1.setAdapter(workUserTaskAdapter);
        recyclerview1.setLayoutManager(new LinearLayoutManager(WorkUserTaskActivity.this, RecyclerView.VERTICAL, false));
        recyclerview1.setItemAnimator(new DefaultItemAnimator());
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swiperereshlayout.setRefreshing(false);
            }
        });
        workUserTaskAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.pingjiabutton:
                        Intent intent11 = getIntent();
                        Map<String, String> map = new HashMap<>();
                        map.put("taskID", workUserTaskAdapter.getItem(position).getTaskID() + "");
                        map.put("workuserNo", workUserNo);
                        OkHttp.get(WorkUserTaskActivity.this, Constant.ShowWorkUserEstimate, map, new OkCallback<Result<EstimateBean>>() {
                            @Override
                            public void onResponse(Result<EstimateBean> response) {
                                if (response.getData() == null) {
                                    Toast.makeText(WorkUserTaskActivity.this, "上访人员未对该诉求任务进行评价，不能进行查看操作", Toast.LENGTH_SHORT).show();
                                }
                                if (response.getData() != null) {
                                    Intent intent = new Intent();
                                    intent.putExtra("workuserNo", workUserNo);
                                    intent.putExtra("username", username1);
                                    intent.putExtra("taskID", workUserTaskAdapter.getItem(position).getTaskID() + "");
                                    intent.setClass(WorkUserTaskActivity.this, UpdateUserEvaluateActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(String state, String msg) {

                            }
                        });
                        break;
                    case R.id.banlibutton:
                        Intent intent2 = new Intent();
                        intent2.putExtra("taskID", workUserTaskAdapter.getItem(position).getTaskID() + "");
                        intent2.setClass(WorkUserTaskActivity.this, TransactionRecordShowActivity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });
        loadData();
    }

    public void loadData() {
        Intent intent1 = getIntent();
        Map<String, String> map = new HashMap<>();
        map.put("workuserNo", intent1.getStringExtra("workuserNo"));
        OkHttp.get(WorkUserTaskActivity.this, Constant.WorkUserTaskShowServlet, map,
                new OkCallback<Result<List<TaskBean>>>() {
                    @Override
                    public void onResponse(Result<List<TaskBean>> response) {
                        workUserTaskAdapter.setNewData(response.getData());
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(WorkUserTaskActivity.this, msg);
                    }
                });

    }
    @OnClick({R.id.back, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                loadData();
                break;
            case R.id.iv_search:
                Map<String, String> map = new HashMap<>();
                map.put("taskCatagery", editSearchContext.getText().toString());
                map.put("workuserNo", workUserNo);
                OkHttp.get(WorkUserTaskActivity.this, Constant.WorkUserTaskDimServlet, map,
                        new OkCallback<Result<List<TaskBean>>>() {
                            @Override
                            public void onResponse(Result<List<TaskBean>> response) {
                                workUserTaskAdapter.setNewData(response.getData());
                            }

                            @Override
                            public void onFailure(String state, String msg) {
                                Toast.makeText(WorkUserTaskActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }
}
