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
import com.example.work_staff_marching.cyf.adapter.WorkuserInformationRecycleViewAdapter;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.WorkUserTaskActivity;
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

public class MessageFragment extends BaseFragment {

    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    @BindView(R.id.edit_search_context)
    EditText editSearchContext;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.back)
    ImageView back;
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
        mWorkuserInformationRecycleViewAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.countimage:
                        Intent intent1 = new Intent();
                        intent1.putExtra("workuserNo", mWorkuserInformationRecycleViewAdapter.getItem(position).getWorkuserNo() + "");
                        intent1.putExtra("username",mWorkuserInformationRecycleViewAdapter.getItem(position).getUserName());
                        intent1.setClass(getContext(), WorkUserTaskActivity.class);
                        startActivity(intent1);

                        break;
                }
            }
            });
        loadData();
    }

    private void loadData() {
        String registerStatus = "2";
        String roleName = "工作用户";
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

    @OnClick({R.id.iv_search,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                String registerStatus = "2";
                String roleName = "工作用户";
                Map<String, String> map = new HashMap<>();
                map.put("registerStatus", registerStatus);
                map.put("roleName", roleName);
                map.put("name", editSearchContext.getText().toString());
                OkHttp.get(getContext(), Constant.UserShowDimServlet, map,
                        new OkCallback<Result<List<UserBean>>>() {
                            @Override
                            public void onResponse(Result<List<UserBean>> response) {
                                mWorkuserInformationRecycleViewAdapter.setNewData(response.getData());
                            }

                            @Override
                            public void onFailure(String state, String msg) {
                                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.back:
                loadData();
                editSearchContext.setText("");
                break;

        }
    }

}

