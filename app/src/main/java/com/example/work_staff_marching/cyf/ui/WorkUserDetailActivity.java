package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.TaskRecycleViewAdapter;
import com.example.work_staff_marching.cyf.adapter.TaskWorkUserInformationAdapter;
import com.example.work_staff_marching.cyf.entity.EstimateBean;
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
    private List<UserBean> mTaskBeans = new ArrayList<>();
    private TaskWorkUserInformationAdapter taskWorkUserInformationAdapter=null;
    String taskID;

    @Override
    protected int getContentView() {
        return R.layout.activity_taskworkuser_information;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        Intent intent1 = getIntent();
        taskID=intent1.getStringExtra("taskID");
        setTitle("信访人员匹配评价详情");
        taskWorkUserInformationAdapter = new TaskWorkUserInformationAdapter(WorkUserDetailActivity.this);
        mRecyclerview1.setAdapter(taskWorkUserInformationAdapter);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(WorkUserDetailActivity.this, RecyclerView.VERTICAL, false));
        mRecyclerview1.setItemAnimator(new DefaultItemAnimator());
        onlodata();
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
                    case R.id.show:
                        Map<String, String> map = new HashMap<>();
                        map.put("taskID",taskID);
                        map.put("workuserNo", taskWorkUserInformationAdapter.getItem(position).getWorkuserNo() + "");
                        OkHttp.get(WorkUserDetailActivity.this, Constant.ShowWorkUserEstimate, map, new OkCallback<Result<EstimateBean>>() {
                            @Override
                            public void onResponse(Result<EstimateBean> response) {
                                if (response.getData() == null) {
                                    Toast.makeText(WorkUserDetailActivity.this, "上访人员未对该信访人员能力进行评价，不能进行查看操作", Toast.LENGTH_SHORT).show();
                                }
                                if (response.getData() != null) {
                                    Intent intent = new Intent();
                                    intent.putExtra("workuserNo", taskWorkUserInformationAdapter.getItem(position).getWorkuserNo() + "");
                                    intent.putExtra("username", taskWorkUserInformationAdapter.getItem(position).getUserName() + "");
                                    intent.putExtra("taskID", taskID);
                                    intent.setClass(WorkUserDetailActivity.this, UpdateUserEvaluateActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(String state, String msg) {

                            }
                        });
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
