package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.UserBean;
import com.example.work_staff_marching.cyf.entity.WorkuserEvaluatingIndicatorBean;
import com.example.work_staff_marching.cyf.utils.Constant;
import com.example.work_staff_marching.cyf.utils.OkCallback;
import com.example.work_staff_marching.cyf.utils.OkHttp;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;
import com.example.work_staff_marching.cyf.utils.Result;

import java.util.HashMap;
import java.util.Map;

public class PeopleAuditRecycleViewAdapter extends BaseRecyclerViewAdapter<UserBean, RecyclerViewHolder> {
    public TextView userName,phone,workuserNoText,workuserNo,sex,address,identification,sexuser;
    public Button audit,audit1,addworkEvaluatingIndicator,updateworkEvaluatingIndicator,deleteworkEvaluatingIndicator;
    public LinearLayout addressLinearLayout,user,work,opetation,opetation1,addworkLinearLayout,workEvaluatingIndicator;
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
        user=(LinearLayout)holder.getView(R.id.user);
        work=(LinearLayout)holder.getView(R.id.work);
        workEvaluatingIndicator=(LinearLayout)holder.getView(R.id.workEvaluatingIndicator);
        opetation=(LinearLayout)holder.getView(R.id.opetation);
        opetation1=(LinearLayout)holder.getView(R.id.opetation1);
        addworkLinearLayout=(LinearLayout)holder.getView(R.id.addworkLinearLayout);
        sex=(TextView)holder.getView(R.id.sex);
        address=(TextView)holder.getView(R.id.address);
        identification=(TextView)holder.getView(R.id.identification);
        sexuser=(TextView)holder.getView(R.id.sexuser);
        audit=(Button)holder.getView(R.id.audit);
        audit1=(Button)holder.getView(R.id.audit1);
        addworkEvaluatingIndicator=(Button)holder.getView(R.id.addworkEvaluatingIndicator);
        updateworkEvaluatingIndicator=(Button)holder.getView(R.id.updateworkEvaluatingIndicator);
        deleteworkEvaluatingIndicator=(Button)holder.getView(R.id.deleteworkEvaluatingIndicator);
        userName.setText(data.getUserName());
        phone.setText(data.getPhone());
        sex.setText(data.getSex());
        workuserNo.setText(data.getWorkuserNo());
        identification.setText(data.getIndentificationCard());
        sexuser.setText(data.getSex());
        holder.addOnClickListener(R.id.audit);
        holder.addOnClickListener(R.id.audit1);
        holder.addOnClickListener(R.id.addworkEvaluatingIndicator);
        holder.addOnClickListener(R.id.updateworkEvaluatingIndicator);
        holder.addOnClickListener(R.id.deleteworkEvaluatingIndicator);
       if(data.getRoleName().equals("工作用户")) {
            addressLinearLayout.setVisibility(View.GONE);
            user.setVisibility(View.GONE);
            work.setVisibility(View.VISIBLE);
            if(data.getRegisterStatus()==1){
                opetation.setVisibility(View.VISIBLE);
                opetation1.setVisibility(View.GONE);
                workEvaluatingIndicator.setVisibility(View.GONE);
                addworkLinearLayout.setVisibility(View.GONE);
            }
            if(data.getRegisterStatus()==3){
                opetation1.setVisibility(View.VISIBLE);
                opetation.setVisibility(View.GONE);
                workEvaluatingIndicator.setVisibility(View.GONE);
                addworkLinearLayout.setVisibility(View.GONE);
            }
           if(data.getRegisterStatus()==2){
               opetation1.setVisibility(View.GONE);
               opetation.setVisibility(View.GONE);
               Map<String, String> map = new HashMap<>();
               map.put("userID",data.getUserID()+"");
               OkHttp.get(mContext, Constant.get_showworkuserevaluatingindicator, map, new OkCallback<Result<WorkuserEvaluatingIndicatorBean>>() {
                   @Override
                   public void onResponse(Result<WorkuserEvaluatingIndicatorBean> response) {
                       if(response.getData()!=null){
                           addworkLinearLayout.setVisibility(View.GONE);
                           workEvaluatingIndicator.setVisibility(View.VISIBLE);
                       }
                       else {
                           addworkLinearLayout.setVisibility(View.VISIBLE);
                           workEvaluatingIndicator.setVisibility(View.GONE);
                       }
                   }
                   @Override
                   public void onFailure(String state, String msg) {

                   }
               });
           }
        }
        if(data.getRoleName().equals("普通用户")) {
            addressLinearLayout.setVisibility(View.VISIBLE);
            user.setVisibility(View.VISIBLE);
            work.setVisibility(View.GONE);
            workEvaluatingIndicator.setVisibility(View.GONE);
            addworkLinearLayout.setVisibility(View.GONE);
            if(data.getRegisterStatus()==1){
                opetation.setVisibility(View.VISIBLE);
                opetation1.setVisibility(View.GONE);
            }
            if(data.getRegisterStatus()==2){
                opetation1.setVisibility(View.GONE);
                opetation.setVisibility(View.GONE);

            }
            if(data.getRegisterStatus()==3){
                opetation1.setVisibility(View.VISIBLE);
                opetation.setVisibility(View.GONE);
            }

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