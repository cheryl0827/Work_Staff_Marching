package com.example.work_staff_marching.cyf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.WorkUserInformationAdapter;
import com.example.work_staff_marching.cyf.entity.EstimateBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
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

public class WorkUserEvaluateActivity extends BaseActivity {
    @BindView(R.id.recyclerview1)
    RecyclerView mRecyclerview1;
    @BindView(R.id.ok)
    Button ok;
    private List<UserBean> mTaskBeans = new ArrayList<>();
    private WorkUserInformationAdapter taskWorkUserInformationAdapter = null;
    String taskID;
    String taskStatus="3";

    @Override
    protected int getContentView() {
        return R.layout.activity_task_workuser_information;
    }



    @Override
    protected void init(Bundle saveInstanceState) {
        Intent intent1 = getIntent();
        taskID = intent1.getStringExtra("taskID");
        setTitle("匹配信访人员信息");
        taskWorkUserInformationAdapter = new WorkUserInformationAdapter(WorkUserEvaluateActivity.this);
        mRecyclerview1.setAdapter(taskWorkUserInformationAdapter);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(WorkUserEvaluateActivity.this, RecyclerView.VERTICAL, false));
        mRecyclerview1.setItemAnimator(new DefaultItemAnimator());
        onlodata();

        taskWorkUserInformationAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.pingjia:
                        Map<String, String> map5 = new HashMap<>();
                        map5.put("workuserNo", taskWorkUserInformationAdapter.getItem(position).getWorkuserNo() + "");
                        map5.put("taskID",taskID);
                        OkHttp.get(WorkUserEvaluateActivity.this, Constant.ShowWorkUserEstimate, map5, new OkCallback<Result<EstimateBean>>() {
                            @Override
                            public void onResponse(Result<EstimateBean> response) {
                                if (response.getData() != null) {
                                    Toast.makeText(WorkUserEvaluateActivity.this, "上访人员已经对该信访人员能力进行评价，不能再次进行添加操作", Toast.LENGTH_SHORT).show();
                                }
                                if (response.getData() == null) {
                                    Intent intent = new Intent();
                                    intent.putExtra("workuserNo", taskWorkUserInformationAdapter.getItem(position).getWorkuserNo() + "");
                                    intent.putExtra("username", taskWorkUserInformationAdapter.getItem(position).getUserName() + "");
                                    intent.putExtra("taskID", taskID);
                                    //intent.putExtra("taskID", taskWorkUserInformationAdapter.getItem(position).getUserName() + "");
                                    intent.setClass(WorkUserEvaluateActivity.this, TaskEstimateActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(String state, String msg) {

                            }
                        });

                        break;
                    case R.id.update:
                        Map<String, String> map = new HashMap<>();
                        map.put("taskID",taskID);
                        map.put("workuserNo", taskWorkUserInformationAdapter.getItem(position).getWorkuserNo() + "");
                        OkHttp.get(WorkUserEvaluateActivity.this, Constant.ShowWorkUserEstimate, map, new OkCallback<Result<EstimateBean>>() {
                            @Override
                            public void onResponse(Result<EstimateBean> response) {
                                if (response.getData() == null) {
                                    Toast.makeText(WorkUserEvaluateActivity.this, "上访人员未对该信访人员能力进行评价，不能进行查看操作", Toast.LENGTH_SHORT).show();
                                }
                                if (response.getData() != null) {
                                    Intent intent = new Intent();
                                    intent.putExtra("workuserNo", taskWorkUserInformationAdapter.getItem(position).getWorkuserNo() + "");
                                    intent.putExtra("username", taskWorkUserInformationAdapter.getItem(position).getUserName() + "");
                                    intent.putExtra("taskID", taskID);
                                    intent.setClass(WorkUserEvaluateActivity.this, UpdateUserEvaluateActivity.class);
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
        map.put("taskID", taskID);
        OkHttp.get(WorkUserEvaluateActivity.this, Constant.ShowTaskWorkUserServlet, map, new OkCallback<Result<List<UserBean>>>() {
            @Override
            public void onResponse(Result<List<UserBean>> response) {
                taskWorkUserInformationAdapter.setNewData(response.getData());
            }

            @Override
            public void onFailure(String state, String msg) {
            }
        });
    }


    @OnClick(R.id.ok)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ok:
                CommonDialog commonDialog = new CommonDialog(WorkUserEvaluateActivity.this);
                Map<String, String> map1 = new HashMap<>();
                map1.put("taskID",taskID);
                map1.put("taskStatus",taskStatus);
                map1.put("pingjiaStatus", "2");
                OkHttp.post(WorkUserEvaluateActivity.this, Constant.UpdateTasklStatusServlet, map1, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result<String> response) {
                        commonDialog.isSingle = true;
                        commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("办理和评价成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                commonDialog.dismiss();
                                setResult(RESULT_OK);
                                finish();

                            }

                            @Override
                            public void onNegtiveClick() {
                                commonDialog.dismiss();
                            }
                        }).show();
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        Toast.makeText(WorkUserEvaluateActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
