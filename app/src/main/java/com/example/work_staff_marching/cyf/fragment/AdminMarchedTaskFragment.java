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
import com.example.work_staff_marching.cyf.adapter.AdminMarchedTaskAdapter;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.entity.MarchingBean;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.TransactionRecordShowActivity;
import com.example.work_staff_marching.cyf.ui.WorkUserDetailActivity;
import com.example.work_staff_marching.cyf.ui.WorkUserMarchedDeatailActivity;
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

public class AdminMarchedTaskFragment extends BaseFragment {
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
                CommonDialog commonDialog = new CommonDialog(getContext());
                switch (view.getId()) {
                    case R.id.machedbutton:
                        Intent intent1 = new Intent();
                        intent1.putExtra("taskID", adminMarchedTaskAdapter.getItem(position).getTaskID() + "");
                        //intent1.putExtra("workuserNo", adminMarchedTaskAdapter.getItem(position).getWorkuserNo()+"");
                        intent1.setClass(getContext(), WorkUserDetailActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.jilubutton:
                        Intent intent2 = new Intent();
                        intent2.putExtra("taskID", adminMarchedTaskAdapter.getItem(position).getTaskID() + "");
                        intent2.setClass(getContext(), TransactionRecordShowActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.deleteButton:

                        commonDialog.setTitle("提示").setImageResId(R.mipmap.exit).setMessage("您确定要对这条诉求任务进行重新匹配吗？如果是的话点击确定后进入匹配管理页面进行重新匹配！！").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                Map<String, String> map = new HashMap<>();
                                map.put("taskID",adminMarchedTaskAdapter.getItem(position).getTaskID() + "");
                                OkHttp.get(getContext(), Constant.DeleteMarchedTaskServlet, map,
                                        new OkCallback<Result<String>>() {
                                            @Override
                                            public void onResponse(Result<String> response) {
                                                onloadData();
                                                Toast.makeText(getContext(), "请到匹配管理页面重新匹配！", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(String state, String msg) {
                                                onloadData();
                                                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                            commonDialog.dismiss();
                             }

                            @Override
                            public void onNegtiveClick() {
                                commonDialog.dismiss();
                            }
                        }).show();
                        break;
                    case R.id.march:
                        Intent intent11 = new Intent();
                        intent11.putExtra("taskID", adminMarchedTaskAdapter.getItem(position).getTaskID() + "");
                        //intent1.putExtra("workuserNo", adminMarchedTaskAdapter.getItem(position).getWorkuserNo()+"");
                        intent11.setClass(getContext(), WorkUserMarchedDeatailActivity.class);
                        startActivity(intent11);
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
        //String taskStatus = "2";
        String marchingStatus = "2";
        Map<String, String> map = new HashMap<>();
        //map.put("taskStatus", taskStatus);
        map.put("marchingStatus", marchingStatus);
        OkHttp.get(getContext(), Constant.get_marchedshow, map,
                new OkCallback<Result<List<TaskBean>>>() {
                    @Override
                    public void onResponse(Result<List<TaskBean>> response) {
                        adminMarchedTaskAdapter.setNewData(response.getData());
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
                onloadData();
                editSearchContext.setText("");
                break;
            case R.id.iv_search:
                //String taskStatus = "2";
                String marchingStatus = "2";
                Map<String, String> map = new HashMap<>();
                //map.put("taskStatus", taskStatus);
                map.put("marchingStatus", marchingStatus);
                map.put("context",  editSearchContext.getText().toString());
                OkHttp.get(getContext(), Constant.MmarchedDimShowServlet, map,
                        new OkCallback<Result<List<TaskBean>>>() {
                            @Override
                            public void onResponse(Result<List<TaskBean>> response) {
                                adminMarchedTaskAdapter.setNewData(response.getData());
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
