package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.TaskAuditRecycleViewAdapter;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.AuditYesTaskDetailActivity;
import com.example.work_staff_marching.cyf.ui.TaskDetailActivity;
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

public class TaskAuditFragment extends BaseFragment {

    @BindView(R.id.recyclerview1)
    RecyclerView mRecyclerview1;
    @BindView(R.id.swiperereshlayout)

    SwipeRefreshLayout swiperereshlayout;

    @BindView(R.id.textView71)
    TextView textView71;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.edit_search_context)
    EditText editSearchContext;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.ing)
    TextView ing;
    @BindView(R.id.end)
    TextView end;
    @BindView(R.id.no)
    TextView no;
    private List<TaskBean> mTaskBean = new ArrayList<>();
    private TaskAuditRecycleViewAdapter mTaskAuditRecycleViewAdapter = null;
    String taskStatus = "1";
    String spinnercontent = "未审核";

    @Override
    protected int initLayout() {
        return R.layout.activity_task_audit_recycleview;
    }

    @Override
    protected void initView(View view) {
        ing.setTextColor(getResources().getColor(R.color.red));
        mTaskAuditRecycleViewAdapter = new TaskAuditRecycleViewAdapter(getContext());
        mRecyclerview1.setAdapter(mTaskAuditRecycleViewAdapter);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRecyclerview1.setItemAnimator(new DefaultItemAnimator());
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swiperereshlayout.setRefreshing(false);
            }
        });

        mTaskAuditRecycleViewAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.detail:
                        Intent intent1 = new Intent();
                        intent1.putExtra("taskID", mTaskAuditRecycleViewAdapter.getItem(position).getTaskID() + "");
                        intent1.putExtra("userID", mTaskAuditRecycleViewAdapter.getItem(position).getUserID() + "");
                        Log.v("re", mTaskAuditRecycleViewAdapter.getItem(position).getTaskID() + "");
                        if (mTaskAuditRecycleViewAdapter.getItem(position).getTaskStatus() == 2) {
                            intent1.setClass(getContext(), AuditYesTaskDetailActivity.class);
                            startActivityForResult(intent1, 1);
                        }
                        if (mTaskAuditRecycleViewAdapter.getItem(position).getTaskStatus() == 1) {
                            intent1.setClass(getContext(), TaskDetailActivity.class);
                            startActivityForResult(intent1, 1);
                        }
                        if (mTaskAuditRecycleViewAdapter.getItem(position).getTaskStatus() == 4) {
                            intent1.setClass(getContext(), TaskDetailActivity.class);
                            startActivityForResult(intent1, 1);
                        }
                        break;
                }
            }
        });
        loadData();
    }

    /**
     * 加载诉求任务列表
     */
    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("taskStatus", taskStatus);
        OkHttp.get(getContext(), Constant.get_taskreviewing, map,
                new OkCallback<Result<List<TaskBean>>>() {
                    @Override
                    public void onResponse(Result<List<TaskBean>> response) {
                        mTaskAuditRecycleViewAdapter.setNewData(response.getData());
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(getContext(), msg);
                    }
                });
    }

    @Override
    protected void initData(Context mContext) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                loadData();
            }
        }
    }

    @OnClick({R.id.back, R.id.iv_search, R.id.ing, R.id.end, R.id.no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                taskStatus = "1";
                editSearchContext.setText("");
                ing.setTextColor(getResources().getColor(R.color.red));
                end.setTextColor(getResources().getColor(R.color.black));
                no.setTextColor(getResources().getColor(R.color.black));
                loadData();
                break;
            case R.id.iv_search:
                Map<String, String> map = new HashMap<>();
                map.put("catagery", editSearchContext.getText().toString());
                OkHttp.get(getContext(), Constant.TaskAuditDimShowServlet, map,
                        new OkCallback<Result<List<TaskBean>>>() {
                            @Override
                            public void onResponse(Result<List<TaskBean>> response) {
                                mTaskAuditRecycleViewAdapter.setNewData(response.getData());
                            }

                            @Override
                            public void onFailure(String state, String msg) {
                                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.ing:
                taskStatus = "1";
                ing.setTextColor(getResources().getColor(R.color.red));
                end.setTextColor(getResources().getColor(R.color.black));
                no.setTextColor(getResources().getColor(R.color.black));
                loadData();
                break;
            case R.id.end:
                taskStatus = "2";
                end.setTextColor(getResources().getColor(R.color.red));
                no.setTextColor(getResources().getColor(R.color.black));
                ing.setTextColor(getResources().getColor(R.color.black));
                loadData();
                break;
            case R.id.no:
                taskStatus = "4";
                no.setTextColor(getResources().getColor(R.color.red));
                end.setTextColor(getResources().getColor(R.color.black));
                ing.setTextColor(getResources().getColor(R.color.black));
                loadData();
                break;
        }
    }
}
