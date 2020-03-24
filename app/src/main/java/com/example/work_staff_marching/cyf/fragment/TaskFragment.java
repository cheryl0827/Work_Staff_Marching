package com.example.work_staff_marching.cyf.fragment;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
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
import com.example.work_staff_marching.cyf.adapter.TaskRecycleViewAdapter;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.ChangeOnlinePetitionActivity;
import com.example.work_staff_marching.cyf.ui.EstimateActivity;
import com.example.work_staff_marching.cyf.ui.TaskEstimateActivity;
import com.example.work_staff_marching.cyf.ui.WorkUserDetailActivity;
import com.example.work_staff_marching.cyf.ui.WorkUserEvaluateActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.CustomToast;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;
import com.example.work_staff_marching.cyf.utils.SharePrefrenceUtil;

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
    String taskStatus = "1";
    String pingjiaStatus = "1";
    String marchingStatus = "1";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.edit_search_context)
    EditText editSearchContext;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.go)
    TextView go;
    @BindView(R.id.ed)
    TextView ed;
    @BindView(R.id.marching)
    TextView marching;
    @BindView(R.id.end)
    TextView end;
    @BindView(R.id.no)
    TextView no;

    private List<TaskBean> mTaskBeans = new ArrayList<>();
    private TaskRecycleViewAdapter mRecyclerViewFragmentAdapter = null;

    @Override
    protected int initLayout() {
        return R.layout.activity_taskrecycle_view;
    }

    @Override
    protected void initView(View view) {
        go.setTextColor(getResources().getColor(R.color.red));
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
                CommonDialog commonDialog = new CommonDialog(getContext());
                switch (view.getId()) {
                    case R.id.updateButton:
                        Intent intent = new Intent();
                        intent.putExtra("taskID", mRecyclerViewFragmentAdapter.getItem(position).getTaskID() + "");
                        intent.putExtra("taskAdress", mRecyclerViewFragmentAdapter.getItem(position).getTaskAdress());
                        intent.putExtra("taskCatagery", mRecyclerViewFragmentAdapter.getItem(position).getTaskCatagery());
                        intent.putExtra("taskContent", mRecyclerViewFragmentAdapter.getItem(position).getTaskContent());
                        intent.putExtra("taskDetailAdress", mRecyclerViewFragmentAdapter.getItem(position).getTaskDetaiAdress());
                        intent.setClass(getContext(), ChangeOnlinePetitionActivity.class);
                        startActivityForResult(intent, 1);
                        break;
//                    case R.id.marched:
//                        Intent intent33 = new Intent();
//                        intent33.putExtra("taskID", mRecyclerViewFragmentAdapter.getItem(position).getTaskID() + "");
//                        intent33.setClass(getContext(), WorkUserDetailActivity.class);
//                        startActivity(intent33);
//                        break;
                    case R.id.detailEndButton:
                        Intent intent2 = new Intent();
                        intent2.putExtra("taskID", mRecyclerViewFragmentAdapter.getItem(position).getTaskID() + "");
                        intent2.setClass(getContext(), WorkUserDetailActivity.class);
                        startActivity(intent2);
                        break;
//                    case R.id.marchig:
//                        Intent intent3 = new Intent();
//                        intent3.putExtra("taskID", mRecyclerViewFragmentAdapter.getItem(position).getTaskID() + "");
//                        intent3.setClass(getContext(), WorkUserDetailActivity.class);
//                        startActivity(intent3);
//                        break;
                    case R.id.wanchengbutton:
                        Intent intent1 = new Intent();
                        intent1.putExtra("taskID", mRecyclerViewFragmentAdapter.getItem(position).getTaskID() + "");
                        intent1.setClass(getContext(), WorkUserEvaluateActivity.class);
                        startActivityForResult(intent1, 2);
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
        map.put("taskStatus", taskStatus);
        map.put("marchingStatus", marchingStatus);
        map.put("pingjiaStatus", pingjiaStatus);
        map.put("userID", SharePrefrenceUtil.getObject(getContext(), UserBean.class).getUserID() + "");
        OkHttp.get(getContext(), Constant.get_usertaskshow, map,
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                loadData();
            }
            if (requestCode == 2) {
                taskStatus = "2";
                pingjiaStatus = "1";
                marchingStatus = "2";
                loadData();
            }
        }
    }

    @OnClick({R.id.back, R.id.iv_search, R.id.go, R.id.ed, R.id.marching, R.id.end, R.id.no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                editSearchContext.setText("");
                loadData();
                break;
            case R.id.iv_search:
                Map<String, String> map = new HashMap<>();
                map.put("userID", SharePrefrenceUtil.getObject(getContext(), UserBean.class).getUserID() + "");
                map.put("catagery",editSearchContext.getText().toString());
                OkHttp.get(getContext(), Constant.UserTaskDimShowServlet, map,
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
                break;
            case R.id.go://未审核
                go.setTextColor(getResources().getColor(R.color.red));
                end.setTextColor(getResources().getColor(R.color.black));
                ed.setTextColor(getResources().getColor(R.color.black));
                no.setTextColor(getResources().getColor(R.color.black));
                marching.setTextColor(getResources().getColor(R.color.black));
                taskStatus = "1";
                pingjiaStatus = "1";
                marchingStatus = "1";
                loadData();
                break;
            case R.id.ed://未匹配
                ed.setTextColor(getResources().getColor(R.color.red));
                end.setTextColor(getResources().getColor(R.color.black));
                go.setTextColor(getResources().getColor(R.color.black));
                no.setTextColor(getResources().getColor(R.color.black));
                marching.setTextColor(getResources().getColor(R.color.black));
                taskStatus = "2";
                pingjiaStatus = "1";
                marchingStatus = "1";
                loadData();
                break;
            case R.id.end://办理结束
                end.setTextColor(getResources().getColor(R.color.red));
                go.setTextColor(getResources().getColor(R.color.black));
                ed.setTextColor(getResources().getColor(R.color.black));
                no.setTextColor(getResources().getColor(R.color.black));
                marching.setTextColor(getResources().getColor(R.color.black));
                taskStatus = "3";
                pingjiaStatus = "2";
                marchingStatus = "2";
                loadData();
                break;
            case R.id.marching://匹配成功
                marching.setTextColor(getResources().getColor(R.color.red));
                go.setTextColor(getResources().getColor(R.color.black));
                ed.setTextColor(getResources().getColor(R.color.black));
                no.setTextColor(getResources().getColor(R.color.black));
                end.setTextColor(getResources().getColor(R.color.black));
                taskStatus = "2";
                pingjiaStatus = "1";
                marchingStatus = "2";
                loadData();
                break;
            case R.id.no://审核失败
                no.setTextColor(getResources().getColor(R.color.red));
                go.setTextColor(getResources().getColor(R.color.black));
                ed.setTextColor(getResources().getColor(R.color.black));
                marching.setTextColor(getResources().getColor(R.color.black));
                end.setTextColor(getResources().getColor(R.color.black));
                taskStatus = "4";
                pingjiaStatus = "1";
                marchingStatus = "1";
                loadData();
                break;
        }
    }
}
