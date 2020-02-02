package com.example.work_staff_marching.cyf.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    //获取TAG的fragment名称
    protected final String TAG = this.getClass().getSimpleName();
    public Context context;
    protected Fragment mCurrentFragment;//当前显示着的fragment

    /**
     * 封装Toast对象
     */
    private static Toast toast;

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        context = ctx;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(initLayout(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        initData(context);
        return rootView;
    }

    /**
     * 初始化布局
     *
     * @return 布局id
     */
    protected abstract int initLayout();

    /**
     * 初始化控件
     *
     * @param view 布局View
     */
    protected abstract void initView(final View view);

    /**
     * 初始化、绑定数据
     *
     * @param mContext 上下文
     */
    protected abstract void initData(Context mContext);

    /**
     * 显示提示  toast
     *
     * @param msg 提示信息
     */
    @SuppressLint("ShowToast")
    public void showToast(String msg) {
        try {
            if (null == toast) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void showFragment(int id, Fragment fragment) {
        showFragment(id, fragment, null);
    }

    protected void showFragment(int id, Fragment fragment, @Nullable String tag) {
        if (mCurrentFragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().hide(mCurrentFragment).commit();
        }
        mCurrentFragment = fragment;
        if (fragment == null) return;
        if (!fragment.isAdded()) {
            getActivity().getSupportFragmentManager().beginTransaction().add(id, fragment, tag).commit();
        } else {
            getActivity().getSupportFragmentManager().beginTransaction().show(fragment).commit();
        }
    }
}