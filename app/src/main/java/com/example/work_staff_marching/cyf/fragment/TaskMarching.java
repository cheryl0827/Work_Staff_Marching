package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.MineRadioAdapter;
import com.example.work_staff_marching.cyf.entity.ChooseBean;
import com.example.work_staff_marching.cyf.entity.TaskBean;
import com.example.work_staff_marching.cyf.inteface.OnItemClickListener;
import com.example.work_staff_marching.cyf.ui.ChooseActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.CustomToast;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskMarching extends BaseFragment {
    @BindView(R.id.recyclerview1)
    RecyclerView mRecyclerview;
    @BindView(R.id.tv_choose)
    TextView mTvChoose;
    @BindView(R.id.tv_all_choose)
    TextView mTvAllChoose;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    @BindView(R.id.tv_quit_choose)
    TextView mTvQuitChoose;
    @BindView(R.id.tv_quit_all_choose)
    TextView mTvQuitAllChoose;


    private MineRadioAdapter mRadioAdapter = null;
    List<TaskBean> mList = new ArrayList<>();
    private String mTotalId;
    private Map mMap = new HashMap();
    private List<TaskBean> select = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.activity_task_marching;
    }

    @Override
    protected void initView(View view) {
        mRadioAdapter = new MineRadioAdapter(getContext());
        mRecyclerview.setAdapter(mRadioAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mRadioAdapter.setOnItemClickListener(new OnItemClickListener<TaskBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, TaskBean data, int position) {
                if (!mRadioAdapter.isSelect()) {
                    return;
                } else {
                    data.setSelect(!data.isSelect());//判断是否选择
                    mRadioAdapter.notifyDataSetChanged();
                    if (data.isSelect()) {
                        select.add(data);//将选择的数据存放
                        CustomToast.showToast(getContext(), select.size() + "");
                    } else {
                        select.remove(data);//将选择的数据去除
                    }
                }
            }
        });
        loadData();
        //initListener();

    }

    @Override
    protected void initData(Context mContext) {

    }

    /**
     * 加载未匹配的诉求任务列表
     */
    private void loadData() {
        String taskStatus = "2";
        String marchingStatus = "1";
        Map<String, String> map = new HashMap<>();
        map.put("taskStatus", taskStatus);
        map.put("marchingStatus", marchingStatus);
        OkHttp.get(getContext(), Constant.get_showmarchingtask, map,
                new OkCallback<Result<List<TaskBean>>>() {
                    @Override
                    public void onResponse(Result<List<TaskBean>> response) {
                        mList = response.getData();
                        mRadioAdapter.setNewData(response.getData());
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(getContext(), msg);
                    }
                });


    }

    @OnClick({R.id.tv_choose, R.id.tv_all_choose, R.id.tv_commit, R.id.tv_quit_choose, R.id.tv_quit_all_choose})
    public void onViewClicked(View view) {
        CommonDialog commonDialog = new CommonDialog(getContext());
        switch (view.getId()) {
            case R.id.tv_all_choose:
                for (TaskBean taskBean : mList) {
                    taskBean.setSelect(true);
                }//for循环
                mRadioAdapter.setNewData(mList);
                select.clear();//清空之前的数据
                select.addAll(mList);
                mTvAllChoose.setVisibility(View.GONE);
                mTvQuitAllChoose.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_choose:
                mTvChoose.setVisibility(View.GONE);
                mTvAllChoose.setVisibility(View.VISIBLE);
                mTvQuitChoose.setVisibility(View.VISIBLE);
                mTvCommit.setVisibility(View.VISIBLE);
                mTvQuitAllChoose.setVisibility(View.GONE);
                for (TaskBean taskBean : mList) {
                    taskBean.setSelect(false);//针对数据被选择与否
                }
                mRadioAdapter.setNewData(mList);
                mRadioAdapter.setSelect(true);//z针对复选框的出现与否
                mRadioAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_commit:
                if (select.size() == 0) {
                    CustomToast.showToast(getContext(), "请选择");
                    return;
                } else {
                    mTotalId = "";
                    for (TaskBean taskBean : select) {
                        mTotalId = mTotalId + taskBean.getTaskID() + ",";
                    }
                    commonDialog.setTitle("提示").setImageResId(R.mipmap.exit).setMessage("你确定要对这些诉求任务进行匹配吗？").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            commonDialog.dismiss();
                            mMap.put("all_id", mTotalId);
                            OkHttp.post(getContext(), Constant. CalculatePorprotionServlet, mMap, new OkCallback<Result<String>>() {
                                @Override
                                public void onResponse(Result<String> response) {

                                }
                                @Override
                                public void onFailure(String state, String msg) {

                                }
                            });
                           // CustomToast.showToast(getContext(), mMap.get("all_id").toString());
                        }
                        @Override
                        public void onNegtiveClick() {

                            commonDialog.dismiss();
                        }
                    }).show();
                }
                break;
            case R.id.tv_quit_choose:
                mTvCommit.setVisibility(View.GONE);
                mTvChoose.setVisibility(View.VISIBLE);
                mTvAllChoose.setVisibility(View.GONE);
                mTvQuitAllChoose.setVisibility(View.GONE);
                mTvQuitChoose.setVisibility(View.GONE);
                mRadioAdapter.setSelect(false);
                mRadioAdapter.notifyDataSetChanged();
                select.clear();
                break;
            case R.id.tv_quit_all_choose:
                mTvCommit.setVisibility(View.VISIBLE);
                mTvChoose.setVisibility(View.GONE);
                mTvAllChoose.setVisibility(View.VISIBLE);
                mTvQuitAllChoose.setVisibility(View.GONE);
                mTvQuitChoose.setVisibility(View.VISIBLE);
                for (TaskBean taskBean : mList) {
                    taskBean.setSelect(false);
                }
                mRadioAdapter.setNewData(mList);
                select.clear();
                break;
        }
    }
}