package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.TaskBean;

import com.example.work_staff_marching.cyf.utils.CommonDialog;
import com.example.work_staff_marching.cyf.utils.Constant;

import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRecycleViewAdapter extends BaseRecyclerViewAdapter<TaskBean,RecyclerViewHolder>{

    private Context mContext;

    public TaskRecycleViewAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, TaskBean data, int position, int viewType) {
        TextView task_catagery,task_content,task_time,address,detailaddress;
        Button updateButton,deleteButton,pingjiabutton,detailEndButton,marchig,wanchengbutton;
        LinearLayout pingjia,opetation,detailEndLinearLayout,adressLinearLayout;
       // String workuserno=data.getTaskWorknumber();
        task_catagery=(TextView)holder.getView(R.id.task_catagery);
        task_content=(TextView)holder.getView(R.id.task_content);
        task_time=(TextView)holder.getView(R.id.task_time);
        detailaddress=(TextView)holder.getView(R.id.detailaddress);
        address=(TextView)holder.getView(R.id.address);
        //username=(TextView)holder.getView(R.id.username);
        //workuserNo=(TextView)holder.getView(R.id.workuserNo);
        task_catagery.setText(data.getTaskCatagery());
        task_content.setText(data.getTaskContent());
        task_time.setText(data.getTaskTime());
        address.setText(data.getTaskAdress());
        //workuserNo.setText(data.getTaskWorknumber());
        detailaddress.setText(data.getTaskDetaiAdress());
        pingjia=(LinearLayout)holder.getView(R.id.pingjia);
        opetation=(LinearLayout)holder.getView(R.id.opetation);
        detailEndLinearLayout=(LinearLayout)holder.getView(R.id.detailEndLinearLayout);
        adressLinearLayout=(LinearLayout)holder.getView(R.id.adressLinearLayout);
        updateButton=(Button)holder.getView(R.id.updateButton);
        deleteButton=(Button)holder.getView(R.id.deleteButton);
        pingjiabutton=(Button)holder.getView(R.id.pingjiabutton);
        detailEndButton=(Button)holder.getView(R.id.detailEndButton);
        marchig=(Button)holder.getView(R.id.marchig);
        wanchengbutton=(Button)holder.getView(R.id.wanchengbutton);
        holder.addOnClickListener(R.id.deleteButton);
        holder.addOnClickListener(R.id.updateButton);
        holder.addOnClickListener(R.id.pingjiabutton);
        holder.addOnClickListener(R.id.detailEndButton);
        holder.addOnClickListener(R.id.marchig);
        holder.addOnClickListener(R.id.wanchengbutton);
        switch(data.getTaskStatus()){
            case 1:
                opetation.setVisibility(View.VISIBLE);
                pingjia.setVisibility(View.GONE);
                detailEndLinearLayout.setVisibility(View.GONE);
                break;
            case 2:
                opetation.setVisibility(View.GONE);
                detailEndLinearLayout.setVisibility(View.GONE);
                if(data.getMarchingStatus()==2&&data.getPingjiaStatus()==1)
                {
                pingjia.setVisibility(View.VISIBLE);
                }
                else pingjia.setVisibility(View.GONE);
                break;
            case 3:
                detailEndLinearLayout.setVisibility(View.VISIBLE);
                pingjia.setVisibility(View.GONE);
                opetation.setVisibility(View.GONE);
                break;
            case 4:
                pingjia.setVisibility(View.GONE);
                detailEndLinearLayout.setVisibility(View.GONE);
                opetation.setVisibility(View.GONE);
                break;
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonDialog commonDialog = new CommonDialog(mContext);
                commonDialog.setTitle("提示").setImageResId(R.mipmap.registersuccess).setMessage("你确定要删除这条诉求任务吗？").setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        Map<String, String> map = new HashMap<>();
                        map.put("taskID",data.getTaskID()+"");
                        OkHttp.post(mContext, Constant.get_taskdelete,map, new OkCallback<Result<String>>() {
                            @Override
                            public void onResponse(Result response) {

                            }
                            @Override
                            public void onFailure(String state, String msg) {
                                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                        commonDialog.dismiss();
                        notifyItemRemoved(position);
                    }
                    @Override
                    public void onNegtiveClick() {
                        commonDialog.dismiss();
                    }
                }).show();
            }
        });
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_taskrecycle_view;
    }

    @Override
    protected int getViewType(int position, TaskBean data) {
        return 0;
    }


}
