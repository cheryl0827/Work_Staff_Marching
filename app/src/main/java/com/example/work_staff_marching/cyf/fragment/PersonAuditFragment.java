package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.PeopleAuditRecycleViewAdapter;
import com.example.work_staff_marching.cyf.adapter.TaskRecycleViewAdapter;
import com.example.work_staff_marching.cyf.entity.UserBean;
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

public class PersonAuditFragment extends BaseFragment {


    @BindView(R.id.go)
    Button go;
    @BindView(R.id.ed)
    Button ed;
    @BindView(R.id.no)
    Button no;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    String[] spinnerItems = {"工作用户","普通用户"};
    private List<UserBean> mUserBeans = new ArrayList<>();
    private PeopleAuditRecycleViewAdapter mPeopleAuditRecycleViewAdapter = null;
    String registerStatus="1";
    String roleName="工作用户";

    @Override
    protected int initLayout() {
        return R.layout.activity_people_audit_recycle_view;
    }

    @Override
    protected void initView(View view) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                R.layout.item_select, spinnerItems);
        spinnerAdapter.setDropDownViewResource(R.layout.item_drop);
        spinner.setAdapter(spinnerAdapter);

        mPeopleAuditRecycleViewAdapter = new PeopleAuditRecycleViewAdapter(getContext());
        recyclerview1.setAdapter(mPeopleAuditRecycleViewAdapter);
        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerview1.setItemAnimator(new DefaultItemAnimator());
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swiperereshlayout.setRefreshing(false);
            }
        });
spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        roleName=spinner.getSelectedItem().toString();
        loadData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
        loadData();
    }

    @Override
    protected void initData(Context mContext) {

    }

    @OnClick({R.id.go, R.id.ed, R.id.no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go:
                registerStatus="1";
                setEnable(go);
                loadData();
                break;
            case R.id.ed:
                registerStatus="2";
                setEnable(ed);
                loadData();
                break;
            case R.id.no:
                registerStatus="3";
                setEnable(no);
                loadData();
                break;
        }
    }
    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("registerStatus",registerStatus);
        map.put("roleName",roleName);
        OkHttp.get(getContext(), Constant.get_user, map,
                new OkCallback<Result<List<UserBean>>>() {
                    @Override
                    public void onResponse(Result<List<UserBean>> response) {
                        mPeopleAuditRecycleViewAdapter.setNewData(response.getData());
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(getContext(), msg);
                    }
                });
    }
    private void setEnable(Button btn) {
        List<Button> buttonList=new ArrayList<>();
        if (buttonList.size()==0){
            buttonList.add(go);
            buttonList.add(ed);
            buttonList.add(no);
        }
        for (int i = 0; i <buttonList.size() ; i++) {
            buttonList.get(i).setEnabled(true);
        }
        btn.setEnabled(false);
    }
}


