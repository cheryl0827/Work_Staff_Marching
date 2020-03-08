package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.AdminMarchedTaskAdapter;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.WorkTaskMarchedAdapter;
import com.example.work_staff_marching.cyf.entity.MarchingBean;
import com.example.work_staff_marching.cyf.entity.RecordBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.ChooseActivity;
import com.example.work_staff_marching.cyf.ui.EstimateActivity;
import com.example.work_staff_marching.cyf.ui.MarchedDetailActivity;
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

public class AdminMarchedTaskFragment extends BaseFragment {
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    private List<MarchingBean> mMarchingBean = new ArrayList<>();
    private AdminMarchedTaskAdapter adminMarchedTaskAdapter = null;

    @Override
    protected int initLayout() {
        return R.layout.activity_admin_marched_task;
    }

    @Override
    protected void initView(View view) {
        adminMarchedTaskAdapter = new AdminMarchedTaskAdapter(getContext());
        recyclerview1.setAdapter(adminMarchedTaskAdapter);
        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerview1.setItemAnimator(new DefaultItemAnimator());
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onloadData();
                swiperereshlayout.setRefreshing(false);
            }
        });
        adminMarchedTaskAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.machedbutton:
                        Intent intent1 = new Intent();
                        intent1.putExtra("taskID", adminMarchedTaskAdapter.getItem(position).getTaskID()+"");
                        intent1.putExtra("workuserNo", adminMarchedTaskAdapter.getItem(position).getWorkuserNo()+"");
                        intent1.setClass(getContext(), MarchedDetailActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.pingjiabutton:
                        Map<String, String> map1 = new HashMap<>();
                        map1.put("taskID",adminMarchedTaskAdapter.getItem(position).getTaskID()+"");
                        OkHttp.get(getContext(), Constant.get_estimateshow,map1,new OkCallback<Result<WorkuserEvaluatingIndicatorBean>>() {
                            @Override
                            public void onResponse(Result response) {
                                if(response.getData()!=null){
                                    Intent intent = new Intent();
                                    intent.putExtra("taskID", adminMarchedTaskAdapter.getItem(position).getTaskID() + "");
                                    intent.setClass(getContext(), EstimateActivity.class);
                                    startActivity(intent);
                                }
                                if(response.getData()==null)
                                    Toast.makeText(getContext(), "该诉求任务未进行评价，请等待普通用户进行评价！", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(String state, String msg) {
                            }
                        });
                        break;
                    case R.id.jilubutton:
                        Intent intent2 = new Intent();
                        intent2.putExtra("taskID", adminMarchedTaskAdapter.getItem(position).getTaskID()+"");
                        intent2.setClass(getContext(), TransactionRecordShowActivity.class);
                        startActivity(intent2);
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
        OkHttp.get(getContext(), Constant.get_marchedshow, null,
                new OkCallback<Result<List<MarchingBean>>>() {
                    @Override
                    public void onResponse(Result<List<MarchingBean>> response) {
                        adminMarchedTaskAdapter.setNewData(response.getData());
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(getContext(), msg);
                    }
                });
    }
}
