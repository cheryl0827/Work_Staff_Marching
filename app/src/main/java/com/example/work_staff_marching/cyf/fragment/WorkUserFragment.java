package com.example.work_staff_marching.cyf.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.work_staff_marching.BuildConfig;
import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.ui.ChangePhoneActivity;
import com.example.work_staff_marching.cyf.ui.ChangeUserPasswordActivity;
import com.example.work_staff_marching.cyf.ui.MainActivity;
import com.example.work_staff_marching.cyf.utils.BaseFragment;
import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.CustomToast;
import com.example.work_staff_marching.cyf.utils.MyGlideEngine;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.Result;
import com.example.work_staff_marching.cyf.utils.SharePrefrenceUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class WorkUserFragment extends BaseFragment {
    private static final int REQUEST_CODE_CHOOSE = 1;//定义请求码常量
    @BindView(R.id.iv_upload_image)
    QMUIRadiusImageView ivUploadImage;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_upload_image)
    QMUIRoundButton tvUploadImage;
    @BindView(R.id.PeopleInformatinChange)
    LinearLayout PeopleInformatinChange;
    @BindView(R.id.PasswordChange)
    LinearLayout PasswordChange;
    @BindView(R.id.exit)
    Button exit;
    private List<String> mSelected = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.activity_workuser_me;
    }

    @Override
    protected void initView(View view) {
        username.setText(SharePrefrenceUtil.getObject(getContext(), UserBean.class).getUserName() + "");
        Glide.with(ivUploadImage.getContext()).load(SharePrefrenceUtil.getObject(getContext(),UserBean.class).getUserPicture()).into(ivUploadImage);
        PasswordChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangeUserPasswordActivity.class));
            }
        });
        PeopleInformatinChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangePhoneActivity.class));
            }
        });
    }

    @Override
    protected void initData(Context mContext) {

    }

    @OnClick({R.id.iv_upload_image, R.id.tv_upload_image,R.id.exit})
    public void onViewClicked(View view) {
        CommonDialog commonDialog = new CommonDialog(getContext());
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
            case R.id.exit:
                commonDialog.setTitle("提示").setImageResId(R.mipmap.exit).setMessage("你确定要退出该程序吗？").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        startActivity(new Intent(getContext(), MainActivity.class));
                        commonDialog.dismiss();
                    }
                    @Override
                    public void onNegtiveClick() {

                        commonDialog.dismiss();
                    }
                }).show();
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected.clear();
            mSelected.addAll(Matisse.obtainPathResult(data));


        }
        Map<String, String> map = new HashMap<>();
        map.put("userID",SharePrefrenceUtil.getObject(getContext(), UserBean.class).getUserID() + "");

        OkHttp.upload(getContext(), Constant.UploadImageServlet, map, mSelected, new OkCallback<Result<String>>() {

            @Override
            public void onResponse(Result<String> response) {
                CustomToast.showToast(getContext(),response.getMessage());
                Glide.with(ivUploadImage.getContext()).load(mSelected.get(0)).into(ivUploadImage);
            }

            @Override
            public void onFailure(String state, String msg) {

            }
        });
    }
}
