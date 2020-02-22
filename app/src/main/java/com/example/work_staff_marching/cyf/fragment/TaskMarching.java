package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.MineRadioAdapter;
import com.example.work_staff_marching.cyf.entity.TaskBean;
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

public class TaskMarching extends BaseFragment {
    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_select_num)
    TextView mTvSelectNum;
    @BindView(R.id.btn_delete)
    Button mBtnDelete;
    @BindView(R.id.select_all)
    TextView mSelectAll;
    @BindView(R.id.ll_mycollection_bottom_dialog)
    LinearLayout mLlMycollectionBottomDialog;
    @BindView(R.id.recyclerview1)
    RecyclerView mRecyclerview;
    @BindView(R.id.btn_editor)
    TextView mBtnEditor;
    private MineRadioAdapter mRadioAdapter = null;
    private LinearLayoutManager mLinearLayoutManager;
    private List<TaskBean> mList = new ArrayList<>();
    private int mEditMode = MYLIVE_MODE_CHECK;
    private boolean isSelectAll = false;
    private boolean editorStatus = false;
    private int index = 0;
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
        loadData();
        //initListener();

    }

    @Override
    protected void initData(Context mContext) {

    }
    /**
     * 加载诉求任务列表
     */
    private void loadData() {
        String taskStatus="2";
        Map<String, String> map = new HashMap<>();
        map.put("taskStatus",taskStatus);
        OkHttp.get(getContext(), Constant.get_taskreviewing, map,
                new OkCallback<Result<List<TaskBean>>>() {
                    @Override
                    public void onResponse(Result<List<TaskBean>> response) {
                        mRadioAdapter.setNewData(response.getData());
//                        for(int i=0;i<response.getData().size();i++){
//                            TaskBean taskBean=new TaskBean();
//                            taskBean.setTaskID(response.getData().get(i).getTaskID());
//                            taskBean.setTaskTime(response.getData().get(i).getTaskTime());
//                            taskBean.setTaskContent(response.getData().get(i).getTaskContent());
//                            taskBean.setTaskCatagery(response.getData().get(i).getTaskCatagery());
//                            mList.add(taskBean);
//                        }
//                        mRadioAdapter.notifyAdapter(mList,false);
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(getContext(), msg);
                    }
                });


    }

    /**
     * 根据选择的数量是否为0来判断按钮的是否可点击.
     *
     * @param size

    private void setBtnBackground(int size) {
        if (size != 0) {
            mBtnDelete.setBackgroundResource(R.drawable.button_shape);
            mBtnDelete.setEnabled(true);
            mBtnDelete.setTextColor(Color.WHITE);
        } else {
            mBtnDelete.setBackgroundResource(R.drawable.button_noclickable_shape);
            mBtnDelete.setEnabled(false);
            mBtnDelete.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        }
    }

    private void initListener() {
        mRadioAdapter.setOnItemClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mSelectAll.setOnClickListener(this);
        mBtnEditor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                //deleteVideo();
                break;
            case R.id.select_all:
                selectAllMain();
                break;
            case R.id.btn_editor:
                updataEditMode();
                break;
            default:
                break;
        }
    }
    private void updataEditMode() {
        mEditMode = mEditMode == MYLIVE_MODE_CHECK ? MYLIVE_MODE_EDIT : MYLIVE_MODE_CHECK;
        if (mEditMode == MYLIVE_MODE_EDIT) {
            mBtnEditor.setText("取消");
            mLlMycollectionBottomDialog.setVisibility(View.VISIBLE);
            editorStatus = true;
        } else {
            mBtnEditor.setText("编辑");
            mLlMycollectionBottomDialog.setVisibility(View.GONE);
            editorStatus = false;
            clearAll();
        }
        mRadioAdapter.setEditMode(mEditMode);
    }
    private void clearAll() {
        mTvSelectNum.setText(String.valueOf(0));
        isSelectAll = false;
        mSelectAll.setText("全选");
        setBtnBackground(0);
    }
    @Override
    public void onItemClickListener(int pos, List<TaskBean> mTaskBean) {
        if (editorStatus) {
            TaskBean mtaskBean = mTaskBean.get(pos);
            boolean isSelect = mtaskBean.isSelect();
            if (!isSelect) {
                index++;
                mtaskBean.setSelect(true);
                if (index == mTaskBean.size()) {
                    isSelectAll = true;
                    mSelectAll.setText("取消全选");
                }

            } else {
                mtaskBean.setSelect(false);
                index--;
                isSelectAll = false;
                mSelectAll.setText("全选");
            }
            setBtnBackground(index);
            mTvSelectNum.setText(String.valueOf(index));
            mRadioAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 全选和反选

    private void selectAllMain() {
        if (mRadioAdapter == null) return;
        if (!isSelectAll) {
            for (int i = 0, j = mRadioAdapter.getMyLiveList().size(); i < j; i++) {
                mRadioAdapter.getMyLiveList().get(i).setSelect(true);
            }
            index = mRadioAdapter.getMyLiveList().size();
            mBtnDelete.setEnabled(true);
            mSelectAll.setText("取消全选");
            isSelectAll = true;
        } else {
            for (int i = 0, j = mRadioAdapter.getMyLiveList().size(); i < j; i++) {
                mRadioAdapter.getMyLiveList().get(i).setSelect(false);
            }
            index = 0;
            mBtnDelete.setEnabled(false);
            mSelectAll.setText("全选");
            isSelectAll = false;
        }
        mRadioAdapter.notifyDataSetChanged();
        setBtnBackground(index);
        mTvSelectNum.setText(String.valueOf(index));
    }  */

}