package com.example.work_staff_marching.cyf.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.ChooseAdapter;
import com.example.work_staff_marching.cyf.entity.ChooseBean;
import com.example.work_staff_marching.cyf.inteface.OnItemClickListener;
import com.example.work_staff_marching.cyf.utils.BaseActivity;
import com.example.work_staff_marching.cyf.utils.CustomToast;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
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

    private ChooseAdapter mChooseAdapter = null;
    List<ChooseBean> mChooseBeanList = new ArrayList<>();
    private String mTotalId;
    private Map mMap = new HashMap();
    private List<ChooseBean> select = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_choose;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("多选");
        mChooseAdapter = new ChooseAdapter(ChooseActivity.this);
        mRecycler.setAdapter(mChooseAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(ChooseActivity.this));
        mChooseAdapter.setOnItemClickListener(new OnItemClickListener<ChooseBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, ChooseBean data, int position) {
                if (!mChooseAdapter.isSelect()) {
                    return;
                } else {
                    data.setSelect(!data.isSelect());//判断是否选择
                    mChooseAdapter.notifyDataSetChanged();
                    if (data.isSelect()) {
                        select.add(data);//将选择的数据存放
                        CustomToast.showToast(ChooseActivity.this, select.size() + "");
                    } else {
                        select.remove(data);//将选择的数据去除
                    }
                }
            }
        });

        initData();

    }

    @OnClick({R.id.tv_choose, R.id.tv_all_choose, R.id.tv_quit_choose, R.id.tv_commit, R.id.tv_quit_all_choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all_choose:

                for (ChooseBean chooseBean : mChooseBeanList) {
                    chooseBean.setSelect(true);
                }//for循环
                mChooseAdapter.setNewData(mChooseBeanList);
                select.clear();//清空之前的数据
                select.addAll(mChooseBeanList);
                mTvAllChoose.setVisibility(View.GONE);
                mTvQuitAllChoose.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_choose:
                mTvChoose.setVisibility(View.GONE);
                mTvAllChoose.setVisibility(View.VISIBLE);
                mTvQuitChoose.setVisibility(View.VISIBLE);
                mTvCommit.setVisibility(View.VISIBLE);
                mTvQuitAllChoose.setVisibility(View.GONE);
                for (ChooseBean chooseBean : mChooseBeanList) {
                    chooseBean.setSelect(false);//针对数据被选择与否
                }
                mChooseAdapter.setNewData(mChooseBeanList);
                mChooseAdapter.setSelect(true);//z针对复选框的出现与否
                mChooseAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_quit_choose:
                mTvCommit.setVisibility(View.GONE);
                mTvChoose.setVisibility(View.VISIBLE);
                mTvAllChoose.setVisibility(View.GONE);
                mTvQuitAllChoose.setVisibility(View.GONE);
                mTvQuitChoose.setVisibility(View.GONE);
                mChooseAdapter.setSelect(false);
                mChooseAdapter.notifyDataSetChanged();
                select.clear();
                break;
            case R.id.tv_commit:
                if (select.size() == 0) {
                    CustomToast.showToast(ChooseActivity.this, "请选择");
                    return;
                } else {
                    mTotalId = "";
                    for (ChooseBean chooseBean : select) {
                        mTotalId = mTotalId + chooseBean.getId() + ",";
                    }
                    mMap.put("all_id", mTotalId);
                    CustomToast.showToast(ChooseActivity.this, mMap.get("all_id").toString());
                }
                break;
            case R.id.tv_quit_all_choose:
                mTvCommit.setVisibility(View.VISIBLE);
                mTvChoose.setVisibility(View.GONE);
                mTvAllChoose.setVisibility(View.VISIBLE);
                mTvQuitAllChoose.setVisibility(View.GONE);
                mTvQuitChoose.setVisibility(View.VISIBLE);
                for (ChooseBean chooseBean : mChooseBeanList) {
                    chooseBean.setSelect(false);
                }
                mChooseAdapter.setNewData(mChooseBeanList);
                select.clear();


        }

    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            ChooseBean chooseBean = new ChooseBean();
            chooseBean.setSelect(false);
            mChooseBeanList.add(chooseBean);
        }
        mChooseAdapter.setNewData(mChooseBeanList);
    }
}
