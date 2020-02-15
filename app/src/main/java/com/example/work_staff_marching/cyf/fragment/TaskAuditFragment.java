package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.TaskAuditRecycleViewAdapter;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.AuditYesTaskDetailActivity;
import com.example.work_staff_marching.cyf.ui.TaskDetailActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.CustomToast;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

public class TaskAuditFragment extends BaseFragment {
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.recyclerview1)
    RecyclerView mRecyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    String[] spinnerItems = {"未审核","审核通过","审核失败"};
    private List<TaskBean> mTaskBean = new ArrayList<>();
    private TaskAuditRecycleViewAdapter mTaskAuditRecycleViewAdapter = null;
    String taskStatus="1";
    String spinnercontent="未审核";
    @Override
    protected int initLayout() {
        return R.layout.activity_task_audit_recycleview;
    }

    @Override
    protected void initView(View view) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                R.layout.item_select, spinnerItems);
        spinnerAdapter.setDropDownViewResource(R.layout.item_drop);
        spinner.setAdapter(spinnerAdapter);
        mTaskAuditRecycleViewAdapter = new TaskAuditRecycleViewAdapter(getContext());
        mRecyclerview1.setAdapter(mTaskAuditRecycleViewAdapter);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRecyclerview1.setItemAnimator(new DefaultItemAnimator());
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               // loadData();
                swiperereshlayout.setRefreshing(false);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnercontent=spinner.getSelectedItem().toString();
                if(spinnercontent.equals("未审核")) taskStatus="1";
                if(spinnercontent.equals("审核通过")) taskStatus="2";
                if(spinnercontent.equals("审核失败")) taskStatus="4";

                loadData();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mTaskAuditRecycleViewAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.detail:
                        Intent intent1 = new Intent();
                        intent1.putExtra("taskID",mTaskAuditRecycleViewAdapter.getItem(position).getTaskID()+"");
                        intent1.putExtra("userID",mTaskAuditRecycleViewAdapter.getItem(position).getUserID()+"");
                        if(mTaskAuditRecycleViewAdapter.getItem(position).getTaskStatus()==2){
                            intent1.setClass(getContext(), AuditYesTaskDetailActivity.class);
                            startActivityForResult(intent1,1);
                        }
                        else{
                        intent1.setClass(getContext(), TaskDetailActivity.class);
                        startActivityForResult(intent1,1);}
                        break;
                }
            }
        });
        loadData();
    }
    /**
     * 加载诉求任务列表
     */
    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("taskStatus",taskStatus);
        OkHttp.get(getContext(), Constant.get_taskreviewing, map,
                new OkCallback<Result<List<TaskBean>>>() {
                    @Override
                    public void onResponse(Result<List<TaskBean>> response) {
                        mTaskAuditRecycleViewAdapter.setNewData(response.getData());
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(getContext(), msg);
                    }
                });
    }
    @Override
    protected void initData(Context mContext) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                loadData();
            }
        }
    }
}
