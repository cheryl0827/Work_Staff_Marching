package com.example.work_staff_marching.cyf.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.ui.ForgetPasswordActivity;
import com.example.work_staff_marching.cyf.ui.MainActivity;

public class MessageFragment extends Fragment

    {

        public Activity mActivity;
        public LayoutInflater mInflater;


        @Override
        public void onAttach(Context context) {

        super.onAttach(context);

    }

        @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity创建时回掉，在这里获去依赖的activity对象
            mActivity = getActivity();
            //startActivity(new Intent(mActivity, ForgetPasswordActivity.class));

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mInflater = inflater;//用来将xml文件实例化成View的类实例
        //在HomeFragment的xml文件渲染成view。
        View rootView = mInflater.inflate(R.layout.activity_register,null);
        //返回的View即为fragment要显示的View
        return rootView;
    }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //fragment依赖的activity创建完成时回掉，一般在这里做fragment页面数据的初始化
    }

        @Override
        public void onStart() {
        super.onStart();
    }

        @Override
        public void onResume() {
        super.onResume();
    }

        @Override
        public void onPause() {
        super.onPause();
    }

        @Override
        public void onStop() {
        super.onStop();
    }

        @Override
        public void onDestroyView() {
        super.onDestroyView();
    }

        @Override
        public void onDestroy() {
        super.onDestroy();
    }

        @Override
        public void onDetach() {

        super.onDetach();
    }

    }

