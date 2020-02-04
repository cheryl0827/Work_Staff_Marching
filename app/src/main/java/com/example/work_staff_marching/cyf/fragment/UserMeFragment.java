package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.work_staff_marching.BuildConfig;
import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.ui.ChangeUserPasswordActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
import com.example.work_staff_marching.cyf.utils.MyGlideEngine;
import com.example.work_staff_marching.cyf.utils.SharePrefrenceUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class UserMeFragment extends BaseFragment {
    private static final int REQUEST_CODE_CHOOSE = 1;//定义请求码常量
    @BindView(R.id.photochange)
    LinearLayout photochange;
    @BindView(R.id.MyMessage)
    LinearLayout MyMessage;
    @BindView(R.id.PeopleInformatinChange)
    LinearLayout PeopleInformatinChange;
    @BindView(R.id.PasswordChange)
    LinearLayout PasswordChange;
    @BindView(R.id.phone)
    LinearLayout phone;
    @BindView(R.id.version)
    LinearLayout version;
    @BindView(R.id.exit)
    Button exit;
    @BindView(R.id.username)
    TextView username;
    private List<String> mSelected = new ArrayList<>();
    @BindView(R.id.iv_upload_image)
    QMUIRadiusImageView ivUploadImage;
    @BindView(R.id.tv_upload_image)
    QMUIRoundButton tvUploadImage;

    @Override
    protected int initLayout() {
        return R.layout.activity_user_me;
    }

    @Override
    protected void initView(View view) {
        username.setText(SharePrefrenceUtil.getObject(getContext(), UserBean.class).getUserName() + "");
        PasswordChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangeUserPasswordActivity.class));
            }
        });
    }

    @Override
    protected void initData(Context mContext) {

    }

    @OnClick({R.id.iv_upload_image, R.id.tv_upload_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_upload_image:
                Matisse.from(this)
                        .choose(MimeType.ofAll())//资源的类型，比如现在这个设置是照片视频全部显示
                        .countable(true)//显示选择图片的数量
                        .capture(true)//使用拍照
                        .maxSelectable(1)//最多选择几张图片
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 图像选择和预览活动所需的方向。
                        .captureStrategy(new CaptureStrategy(true, BuildConfig.APPLICATION_ID + ".file_provider"))
                        .thumbnailScale(0.85f)//缩放比例
                        .imageEngine(new MyGlideEngine())//图片加载类，需要重写框架自带的不然会报错
                        .forResult(REQUEST_CODE_CHOOSE);//请求码
                break;
            case R.id.iv_upload_image:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected.addAll(Matisse.obtainPathResult(data));
            Glide.with(ivUploadImage.getContext()).load(mSelected.get(0)).into(ivUploadImage);

        }
    }

    @OnClick(R.id.exit)
    public void onViewClicked() {
    }
}
