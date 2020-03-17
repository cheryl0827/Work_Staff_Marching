package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.WorkuserInformationRecycleViewAdapter;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.CustomToast;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MessageFragment extends BaseFragment {

       @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    private List<WorkuserEvaluatingIndicatorBean> mWorkuserEvaluatingIndicatorBeans = new ArrayList<>();
    private WorkuserInformationRecycleViewAdapter mWorkuserInformationRecycleViewAdapter = null;



    @Override
    protected void initView(View view) {
        mWorkuserInformationRecycleViewAdapter = new WorkuserInformationRecycleViewAdapter(getContext());
        recyclerview1.setAdapter(mWorkuserInformationRecycleViewAdapter);
        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerview1.setItemAnimator(new DefaultItemAnimator());
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swiperereshlayout.setRefreshing(false);
            }
        });
        loadData();
         }
    private void loadData() {
        String registerStatus="2";
        String roleName="工作用户";
        Map<String, String> map = new HashMap<>();
        map.put("registerStatus", registerStatus);
        map.put("roleName", roleName);
        OkHttp.get(getContext(), Constant.get_user, map,
                new OkCallback<Result<List<UserBean>>>() {
                    @Override
                    public void onResponse(Result<List<UserBean>> response) {
                        mWorkuserInformationRecycleViewAdapter.setNewData(response.getData());
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(getContext(), msg);
                    }
                });
    }





    @Override
    protected int initLayout() {
        return R.layout.activity_recycleview_workuser;
    }

    protected void initData(Context mContext) {

    }
}

