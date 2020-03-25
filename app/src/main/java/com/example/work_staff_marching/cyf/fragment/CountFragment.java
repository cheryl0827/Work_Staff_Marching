package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.view.View;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.EstimateBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;
import com.example.work_staff_marching.cyf.utils.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.Map;

public class CountFragment  extends BaseFragment {
    @Override
    protected int initLayout() {
        return R.layout.activity_work_user_evaluate;
    }

    @Override
    protected void initView(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("workuserNo", SharePrefrenceUtil.getObject(getContext(),UserBean.class).getWorkuserNo()+"");
        OkHttp.get(getContext(), Constant.get_calculatetasks, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
//                count.setText(response.getData()+"件");
            }
            @Override
            public void onFailure(String state, String msg) {
            }
        });
        OkHttp.get(getContext(), Constant.EstimateAvgServlet, map, new OkCallback<Result<EstimateBean>>() {
            @Override
            public void onResponse(Result<EstimateBean> response) {
                if(response.getData()!=null){
//                    urgent.setText(response.getData().getUrgent()+"");
//                    psychology.setText(response.getData().getPsychology()+"");
//                    organization.setText(response.getData().getOrganization()+"");
//                    law.setText(response.getData().getLaw()+"");
//                    analyse.setText(response.getData().getAnalyse()+"");
//                    community.setText(response.getData().getCommunity()+"");
                }
            }
            @Override
            public void onFailure(String state, String msg) {
            }
        });


    }

    @Override
    protected void initData(Context mContext) {

    }
}
