package com.example.work_staff_marching.cyf.fragment;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.TaskRecycleViewAdapter;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.ChangeOnlinePetitionActivity;
import com.example.work_staff_marching.cyf.ui.TaskEstimateActivity;
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
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class TaskFragment extends BaseFragment {
    @BindView(R.id.recyclerview1)
    RecyclerView mRecyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    @BindView(R.id.go)
    Button go;
    @BindView(R.id.ed)
    Button ed;
    @BindView(R.id.no)
    Button no;
    @BindView(R.id.end)
    Button end;
    String taskStatus="1";
    private List<TaskBean> mTaskBeans = new ArrayList<>();
    private TaskRecycleViewAdapter mRecyclerViewFragmentAdapter = null;

    @Override
    protected int initLayout() {
        return R.layout.activity_taskrecycle_view;
    }

    @Override
    protected void initView(View view) {
        mRecyclerViewFragmentAdapter = new TaskRecycleViewAdapter(getContext());
        mRecyclerview1.setAdapter(mRecyclerViewFragmentAdapter);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRecyclerview1.setItemAnimator(new DefaultItemAnimator());
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swiperereshlayout.setRefreshing(false);
            }
        });
        mRecyclerViewFragmentAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                switch(view.getId()){
                    case R.id.updateButton:
                        Intent intent = new Intent();
                        intent.putExtra("taskID",mRecyclerViewFragmentAdapter.getItem(position).getTaskID()+"");
                        intent.putExtra("taskAdress",mRecyclerViewFragmentAdapter.getItem(position).getTaskAdress());
                        intent.putExtra("taskCatagery",mRecyclerViewFragmentAdapter.getItem(position).getTaskCatagery());
                        intent.putExtra("taskContent",mRecyclerViewFragmentAdapter.getItem(position).getTaskContent());
                        intent.putExtra("taskDetailAdress",mRecyclerViewFragmentAdapter.getItem(position).getTaskDetaiAdress());
                        intent.setClass(getContext(), ChangeOnlinePetitionActivity.class);
                        startActivityForResult(intent,1);
                        break;
                    case R.id.pingjiabutton:
                        Intent intent1 = new Intent();
                        intent1.putExtra("taskID",mRecyclerViewFragmentAdapter.getItem(position).getTaskID()+"");
                        intent1.setClass(getContext(), TaskEstimateActivity.class);
                        startActivityForResult(intent1,1);
                        break;
                    case R.id.detailEndButton:
                        break;
                }
            }
        });
        loadData();
    }

    @Override
    protected void initData(Context mContext) {

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
                        mRecyclerViewFragmentAdapter.setNewData(response.getData());
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(getContext(), msg);
                    }
                });
    }

    @OnClick({R.id.go, R.id.ed, R.id.no,R.id.end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go:
                taskStatus="1";
                loadData();
                setEnable(go);
                break;
            case R.id.ed:
                taskStatus="2";
                loadData();
                setEnable(ed);
                break;
            case R.id.end:
                taskStatus="3";
                setEnable(end);
                loadData();
                break;
            case R.id.no:
                setEnable(no);
                taskStatus="4";
                loadData();
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                 loadData();
            }
        }
    }
    private void setEnable(Button btn) {
        List<Button> buttonList=new ArrayList<>();
        if (buttonList.size()==0){
            buttonList.add(go);
            buttonList.add(ed);
            buttonList.add(no);
            buttonList.add(end);
        }
        for (int i = 0; i <buttonList.size() ; i++) {
            buttonList.get(i).setEnabled(true);
        }
        btn.setEnabled(false);
    }
}
