package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.PeopleAuditRecycleViewAdapter;
import com.example.work_staff_marching.cyf.adapter.TaskRecycleViewAdapter;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.WorkuserAddEvaluatingIndicatorActivity;
import com.example.work_staff_marching.cyf.ui.WorkuserUpdateEvaluatingIndicatorActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
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
        mPeopleAuditRecycleViewAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                switch(view.getId()){
                    case R.id.audit:
                        CommonDialog commonDialog = new CommonDialog(getContext());
                        commonDialog.setPositive("审核通过");
                        commonDialog.setNegtive("审核不通过");
                        commonDialog.setTitle("提示").setImageResId(R.mipmap.me1).setMessage("请对该人员信息进行审核是否通过？").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                Map<String, String> map = new HashMap<>();
                                map.put("userID", mPeopleAuditRecycleViewAdapter.getItem(position).getUserID()+"");
                               // map.put("registerStatus", mPeopleAuditRecycleViewAdapter.getItem(position).getRegisterStatus()+"");
                                OkHttp.post(getContext(), Constant.get_useraudit,map, new OkCallback<Result<String>>() {
                                    @Override
                                    public void onResponse(Result response) {
                                        loadData();

                                    }
                                    @Override
                                    public void onFailure(String state, String msg) {
                                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

                                    }
                                });
                                commonDialog.dismiss();
                            }
                            @Override
                            public void onNegtiveClick() {
                                Map<String, String> map1 = new HashMap<>();
                                map1.put("userID", mPeopleAuditRecycleViewAdapter.getItem(position).getUserID()+"");
                                // map.put("registerStatus", mPeopleAuditRecycleViewAdapter.getItem(position).getRegisterStatus()+"");
                                OkHttp.post(getContext(), Constant.get_userauditfailure,map1, new OkCallback<Result<String>>() {
                                    @Override
                                    public void onResponse(Result response) {
                                        loadData();

                                    }
                                    @Override
                                    public void onFailure(String state, String msg) {
                                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

                                    }
                                });
                                commonDialog.dismiss();
                            }
                        }).show();
                        break;
                    case  R.id.audit1:
                        CommonDialog commonDialog1 = new CommonDialog(getContext());
                        commonDialog1.setPositive("审核通过");
                        commonDialog1.setNegtive("审核不通过");
                        commonDialog1.setTitle("提示").setImageResId(R.mipmap.me1).setMessage("请对该人员信息进行审核是否通过？").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                Map<String, String> map2 = new HashMap<>();
                                map2.put("userID", mPeopleAuditRecycleViewAdapter.getItem(position).getUserID()+"");
                                // map.put("registerStatus", mPeopleAuditRecycleViewAdapter.getItem(position).getRegisterStatus()+"");
                                OkHttp.post(getContext(), Constant.get_useraudit,map2, new OkCallback<Result<String>>() {
                                    @Override
                                    public void onResponse(Result response) {
                                        loadData();

                                    }
                                    @Override
                                    public void onFailure(String state, String msg) {
                                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

                                    }
                                });
                                commonDialog1.dismiss();
                            }
                            @Override
                            public void onNegtiveClick() {
                                Map<String, String> map4 = new HashMap<>();
                                map4.put("userID", mPeopleAuditRecycleViewAdapter.getItem(position).getUserID()+"");
                                // map.put("registerStatus", mPeopleAuditRecycleViewAdapter.getItem(position).getRegisterStatus()+"");
                                OkHttp.post(getContext(), Constant.get_userauditfailure,map4, new OkCallback<Result<String>>() {
                                    @Override
                                    public void onResponse(Result response) {
                                        loadData();

                                    }
                                    @Override
                                    public void onFailure(String state, String msg) {
                                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

                                    }
                                });
                                commonDialog1.dismiss();
                            }
                        }).show();
                        break;
                    case R.id.addworkEvaluatingIndicator:
                        Map<String, String> map5 = new HashMap<>();
                        map5.put("userID",mPeopleAuditRecycleViewAdapter.getItem(position).getUserID()+"");
                        OkHttp.get(getContext(), Constant.get_showworkuserevaluatingindicator, map5, new OkCallback<Result<WorkuserEvaluatingIndicatorBean>>() {
                            @Override
                            public void onResponse(Result<WorkuserEvaluatingIndicatorBean> response) {
                                if(response.getData()!=null){
                                    Toast.makeText(getContext(),"该人员已经有指标，不能进行添加操作",Toast.LENGTH_SHORT).show();
                                }
                                if(response.getData()==null){
                                    Intent intent1 = new Intent();
                                    intent1.putExtra("userID",mPeopleAuditRecycleViewAdapter.getItem(position).getUserID()+"");
                                    intent1.setClass(getContext(), WorkuserAddEvaluatingIndicatorActivity.class);
                                    startActivityForResult(intent1,1);
                                }
                            }
                            @Override
                            public void onFailure(String state, String msg) {

                            }
                        });
                        break;
                    case R.id.updateworkEvaluatingIndicator:
                        Map<String, String> map6 = new HashMap<>();
                        map6.put("userID",mPeopleAuditRecycleViewAdapter.getItem(position).getUserID()+"");
                        OkHttp.post(getContext(), Constant.get_showworkuserevaluatingindicator,map6,new OkCallback<Result<WorkuserEvaluatingIndicatorBean>>() {
                            @Override
                            public void onResponse(Result response) {
                                if(response.getData()!=null){
                                Intent intent1 = new Intent();
                                intent1.putExtra("userID",mPeopleAuditRecycleViewAdapter.getItem(position).getUserID()+"");
                                intent1.setClass(getContext(), WorkuserUpdateEvaluatingIndicatorActivity.class);
                                startActivityForResult(intent1,1);}
                                if(response.getData()==null)
                                    Toast.makeText(getContext(), "该人员不存在指标，不能进行修改操作", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(String state, String msg) {
                            }
                        });
                        break;
                    case R.id.deleteworkEvaluatingIndicator:
                                CommonDialog commonDialog11 = new CommonDialog(getContext());
                                Map<String, String> map = new HashMap<>();
                                map.put("userID",mPeopleAuditRecycleViewAdapter.getItem(position).getUserID()+"");
                                OkHttp.post(getContext(), Constant.get_deleteworkuserevaluatingindicator,map, new OkCallback<Result<String>>() {
                                    @Override
                                    public void onResponse(Result response) {
                                        commonDialog11.isSingle = true;
                                        commonDialog11.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("删除人员指标成功！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                                            @Override
                                            public void onPositiveClick() {
                                                commonDialog11.dismiss();
                                            }

                                            @Override
                                            public void onNegtiveClick() {
                                                commonDialog11.dismiss();
                                            }
                                        }).show();

                                    }
                                    @Override
                                    public void onFailure(String state, String msg) {
                                        Toast.makeText(getContext(), "该人员不存在指标，不能进行删除操作", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        break;
                }
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


