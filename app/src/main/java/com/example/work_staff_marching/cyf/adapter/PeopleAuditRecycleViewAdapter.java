package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;

public class PeopleAuditRecycleViewAdapter extends BaseRecyclerViewAdapter<UserBean, RecyclerViewHolder> {
    public TextView userName,phone,workuserNoText,workuserNo,sex,address;
    public Button audit;
    public LinearLayout addressLinearLayout;
    private Context mContext;

    public PeopleAuditRecycleViewAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, UserBean data, int position, int viewType) {
        userName=(TextView)holder.getView(R.id.userName);
        phone=(TextView)holder.getView(R.id.phone);
        workuserNoText=(TextView)holder.getView(R.id.workuserNoText);
        workuserNo=(TextView)holder.getView(R.id.workuserNo);
        addressLinearLayout=(LinearLayout)holder.getView(R.id.addressLinearLayout);
        sex=(TextView)holder.getView(R.id.sex);
        address=(TextView)holder.getView(R.id.address);
        audit=(Button)holder.getView(R.id.audit);
        userName.setText(data.getUserName());
        phone.setText(data.getPhone());
        sex.setText(data.getSex());
        workuserNo.setText(data.getWorkuserNo());
        address.setText(data.getProvince()+"-"+data.getCity()+"-"+data.getCountry()+"-"+data.getAddress());
        holder.addOnClickListener(R.id.audit);

       if(data.getUserName().equals("工作用户")) {
           addressLinearLayout.setVisibility(View.GONE);
           workuserNoText.setText("身份证号：");
           workuserNo.setText(data.getIndentificationCard());
       }
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_people_audit_recycle_view;
    }

    @Override
    protected int getViewType(int position, UserBean data) {
        return 0;
    }
}
