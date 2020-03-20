package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.WorkTaskHandledAdapter;
import com.example.work_staff_marching.cyf.entity.MarchingBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.EstimateActivity;
import com.example.work_staff_marching.cyf.ui.TaskHandledDetailActivity;
import com.example.work_staff_marching.cyf.ui.TransactionRecordShowActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
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

public class WorkTaskHandledFragment extends BaseFragment {
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.edit_search_context)
    EditText editSearchContext;
    @BindView(R.id.iv_search)
    ImageView ivSearch;

    private List<MarchingBean> mMarchingBean = new ArrayList<>();
    private WorkTaskHandledAdapter workTaskHandledAdapter = null;

    @Override
    protected int initLayout() {
        return R.layout.activity_worktask_handled;
    }

    @Override
    protected void initView(View view) {
        workTaskHandledAdapter = new WorkTaskHandledAdapter(getContext());
        recyclerview1.setAdapter(workTaskHandledAdapter);
        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerview1.setItemAnimator(new DefaultItemAnimator());
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onloadData();
                //recyclerview1.setAdapter(workTaskMarchedAdapter);
                swiperereshlayout.setRefreshing(false);
            }
        });
        workTaskHandledAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.detail:
                        Intent intent1 = new Intent();
                        intent1.putExtra("taskID", workTaskHandledAdapter.getItem(position).getTaskID() + "");
                        intent1.setClass(getContext(), TaskHandledDetailActivity.class);
                        startActivity(intent1);

                        break;
                    case R.id.banlibutton:
                        Intent intent = new Intent();
                        intent.putExtra("taskID", workTaskHandledAdapter.getItem(position).getTaskID() + "");
                        intent.setClass(getContext(), TransactionRecordShowActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.pingjiabutton:
                        Map<String, String> map1 = new HashMap<>();
                        map1.put("taskID", workTaskHandledAdapter.getItem(position).getTaskID() + "");
                        OkHttp.post(getContext(), Constant.get_estimateshow, map1, new OkCallback<Result<WorkuserEvaluatingIndicatorBean>>() {
                            @Override
                            public void onResponse(Result response) {
                                if (response.getData() != null) {
                                    Intent intent = new Intent();
                                    intent.putExtra("taskID", workTaskHandledAdapter.getItem(position).getTaskID() + "");
                                    intent.setClass(getContext(), EstimateActivity.class);
                                    startActivity(intent);
                                }
                                if (response.getData() == null)
                                    Toast.makeText(getContext(), "该诉求任务未进行评价，请等待普通用户进行评价！", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(String state, String msg) {
                            }
                        });
                        break;

                }
            }
        });
        onloadData();

    }

    @Override
    protected void initData(Context mContext) {

    }

    public void onloadData() {
        Map<String, String> map = new HashMap<>();
        map.put("workuserNo", SharePrefrenceUtil.getObject(getContext(), UserBean.class).getWorkuserNo() + "");
        OkHttp.get(getContext(), Constant.get_showmarching, map,
                new OkCallback<Result<List<MarchingBean>>>() {
                    @Override
                    public void onResponse(Result<List<MarchingBean>> response) {
                        workTaskHandledAdapter.setNewData(response.getData());
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(getContext(), msg);
                    }
                });
    }

    @OnClick({R.id.back, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                editSearchContext.setText("");
                onloadData();
                break;
            case R.id.iv_search:
                Map<String, String> map = new HashMap<>();
                map.put("workuserNo", SharePrefrenceUtil.getObject(getContext(), UserBean.class).getWorkuserNo() + "");
                map.put("catagery",editSearchContext.getText().toString());
                OkHttp.get(getContext(), Constant.MarchingTaskDimShowServlet, map,
                        new OkCallback<Result<List<MarchingBean>>>() {
                            @Override
                            public void onResponse(Result<List<MarchingBean>> response) {
                                workTaskHandledAdapter.setNewData(response.getData());
                            }

                            @Override
                            public void onFailure(String state, String msg) {
                                Toast.makeText(getContext(), "查询诉求任务的匹配失败,没有相关的诉求任务", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }
}
