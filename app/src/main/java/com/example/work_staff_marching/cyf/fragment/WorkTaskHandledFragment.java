package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.WorkTaskHandledAdapter;
import com.example.work_staff_marching.cyf.adapter.WorkTaskMarchedAdapter;
import com.example.work_staff_marching.cyf.entity.MarchingBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.MarchedTaskDetailActivity;
import com.example.work_staff_marching.cyf.ui.TaskHandledDetailActivity;
import com.example.work_staff_marching.cyf.ui.TransactionRecordActivity;
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

public class WorkTaskHandledFragment extends BaseFragment {
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;

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
                        intent1.putExtra("taskID", workTaskHandledAdapter.getItem(position).getTaskID()+"");
                        intent1.setClass(getContext(), TaskHandledDetailActivity.class);
                        startActivity(intent1);

                        break;
                    case R.id.banlibutton:
                        Intent intent = new Intent();
                        intent.putExtra("taskID", workTaskHandledAdapter.getItem(position).getTaskID() + "");
                        intent.setClass(getContext(), TransactionRecordShowActivity.class);
                        startActivity(intent);
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
}
