package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.adapter.BaseRecyclerViewAdapter;
import com.example.work_staff_marching.cyf.adapter.WorkTaskMarchedAdapter;
import com.example.work_staff_marching.cyf.entity.MarchingBean;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.inteface.OnItemChildClickListener;
import com.example.work_staff_marching.cyf.ui.MarchedTaskDetailActivity;
import com.example.work_staff_marching.cyf.ui.TransactionRecordActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.CustomToast;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;
import com.example.work_staff_marching.cyf.utils.SharePrefrenceUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class WorkTaskMarchedFragment extends BaseFragment {


    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    private List<MarchingBean> mMarchingBean = new ArrayList<>();
    private WorkTaskMarchedAdapter workTaskMarchedAdapter=null;

    @Override
    protected int initLayout() {
        return R.layout.activity_workuser_marchedtask;
    }

    @Override
    protected void initView(View view) {
        workTaskMarchedAdapter = new WorkTaskMarchedAdapter(getContext());
        recyclerview1.setAdapter(workTaskMarchedAdapter);
        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerview1.setItemAnimator(new DefaultItemAnimator());
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onloadData();
                swiperereshlayout.setRefreshing(false);
            }
        });
        onloadData();
        workTaskMarchedAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
               switch (view.getId()){
                   case R.id.detail:
                       Intent intent1 = new Intent();
                       intent1.putExtra("taskID",workTaskMarchedAdapter.getItem(position).getTaskID()+"");
                       intent1.setClass(getContext(), MarchedTaskDetailActivity.class);
                       startActivity(intent1);
                       break;
                   case  R.id.banlibutton:
                       Intent intent = new Intent();
                       intent.putExtra("taskID",workTaskMarchedAdapter.getItem(position).getTaskID()+"");
                       intent.setClass(getContext(), TransactionRecordActivity.class);
                       startActivity(intent);
                       break;

               }
            }
        });
        int[] imageResourceID = new int[]{R.mipmap.as, R.mipmap.as1, R.mipmap.as2};
        List<Integer> imgeList = new ArrayList<>();
        //轮播标题
        String[] mtitle = new String[]{"图片1", "图片2", "图片3"};
        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < imageResourceID.length; i++) {
            imgeList.add(imageResourceID[i]);//把图片资源循环放入list里面
            titleList.add(mtitle[i]);//把标题循环设置进列表里面
            //设置图片加载器，通过Glide加载图片
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getContext()).load(path).into(imageView);
                }
            });
            //设置轮播的动画效果,里面有很多种特效,可以到GitHub上查看文档。
            mBanner.setBannerAnimation(Transformer.Accordion);
            mBanner.setImages(imgeList);//设置图片资源
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置banner显示样式（带标题的样式）
            mBanner.setBannerTitles(titleList); //设置标题集合（当banner样式有显示title时）
            //设置指示器位置（即图片下面的那个小圆点）
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.setDelayTime(1000);//设置轮播时间3秒切换下一图
            mBanner.start();//开始进行banner渲染
        }


    }

    @Override
    protected void initData(Context mContext) {

    }
    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();//开始轮播
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();//结束轮播
    }
    public void onloadData(){
        Map<String, String> map = new HashMap<>();
        map.put("workuserNo",SharePrefrenceUtil.getObject(getContext(),UserBean.class).getWorkuserNo()+"");
        OkHttp.get(getContext(), Constant.get_showmarching, map,
                new OkCallback<Result<List<MarchingBean>>>() {
                    @Override
                    public void onResponse(Result<List<MarchingBean>> response) {
                        workTaskMarchedAdapter.setNewData(response.getData());
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        CustomToast.showToast(getContext(), msg);
                    }
                });
    }
}
