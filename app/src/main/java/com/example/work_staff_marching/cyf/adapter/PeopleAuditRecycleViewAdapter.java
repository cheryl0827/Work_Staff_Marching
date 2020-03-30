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
    public TextView max,textView130,userName,phone,workuserNoText,workuserNo,sex,address,identification,sexuser;
    public Button audit,audit1,audit2,audit3,updateworkEvaluatingIndicator,add;
    public LinearLayout addressLinearLayout,ability,user,work,opetation,opetation1,opetation2,opetation3,workEvaluatingIndicator;
    private Context mContext;
    public PeopleAuditRecycleViewAdapter(Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected void convert(RecyclerViewHolder holder, UserBean data, int position, int viewType) {
        userName=(TextView)holder.getView(R.id.userName);
        textView130=(TextView)holder.getView(R.id.textView130);
        max=(TextView)holder.getView(R.id.max);
        phone=(TextView)holder.getView(R.id.phone);
        workuserNoText=(TextView)holder.getView(R.id.workuserNoText);
        workuserNo=(TextView)holder.getView(R.id.workuserNo);
        addressLinearLayout=(LinearLayout)holder.getView(R.id.addressLinearLayout);
        user=(LinearLayout)holder.getView(R.id.user);
        work=(LinearLayout)holder.getView(R.id.work);
        workEvaluatingIndicator=(LinearLayout)holder.getView(R.id.workEvaluatingIndicator);
        opetation=(LinearLayout)holder.getView(R.id.opetation);
        opetation2=(LinearLayout)holder.getView(R.id.opetation2);
        opetation3=(LinearLayout)holder.getView(R.id.opetation3);
        ability=(LinearLayout)holder.getView(R.id.ability);
        opetation1=(LinearLayout)holder.getView(R.id.opetation1);
        sex=(TextView)holder.getView(R.id.sex);
        address=(TextView)holder.getView(R.id.address);
        identification=(TextView)holder.getView(R.id.identification);
        sexuser=(TextView)holder.getView(R.id.sexuser);
        audit=(Button)holder.getView(R.id.audit);
        audit1=(Button)holder.getView(R.id.audit1);
        audit2=(Button)holder.getView(R.id.audit2);
        audit3=(Button)holder.getView(R.id.audit3);
        add=(Button)holder.getView(R.id.add);
        updateworkEvaluatingIndicator=(Button)holder.getView(R.id.updateworkEvaluatingIndicator);
        userName.setText(data.getUserName());
        phone.setText(data.getPhone());
        sex.setText(data.getSex());
        max.setText(data.getMaxTaskNumber()+"");
        workuserNo.setText(data.getWorkuserNo());
        identification.setText(data.getIndentificationCard());
        sexuser.setText(data.getSex());
        holder.addOnClickListener(R.id.audit);
        holder.addOnClickListener(R.id.audit1);
        holder.addOnClickListener(R.id.audit2);
        holder.addOnClickListener(R.id.audit3);
        holder.addOnClickListener(R.id.updateworkEvaluatingIndicator);
        holder.addOnClickListener(R.id.add);
       //switch ()
       if(data.getRoleName().equals("工作用户")) {
            addressLinearLayout.setVisibility(View.GONE);
            user.setVisibility(View.GONE);
            opetation.setVisibility(View.GONE);
            opetation1.setVisibility(View.GONE);
            work.setVisibility(View.VISIBLE);
            if(data.getRegisterStatus()==1){
                textView130.setVisibility(View.GONE);
                max.setVisibility(View.GONE);
                ability.setVisibility(View.GONE);
                opetation2.setVisibility(View.VISIBLE);
                opetation3.setVisibility(View.GONE);
                workEvaluatingIndicator.setVisibility(View.GONE);


            }
            if(data.getRegisterStatus()==3){
                textView130.setVisibility(View.GONE);
                max.setVisibility(View.GONE);
                ability.setVisibility(View.GONE);
                opetation3.setVisibility(View.VISIBLE);
                opetation2.setVisibility(View.GONE);
                workEvaluatingIndicator.setVisibility(View.GONE);


            }
           if(data.getRegisterStatus()==2){
               textView130.setVisibility(View.VISIBLE);
               max.setVisibility(View.VISIBLE);
               ability.setVisibility(View.VISIBLE);
               opetation3.setVisibility(View.GONE);
               opetation2.setVisibility(View.GONE);
               workEvaluatingIndicator.setVisibility(View.VISIBLE);

           }
        }
        if(data.getRoleName().equals("上访用户")) {
            addressLinearLayout.setVisibility(View.VISIBLE);
            user.setVisibility(View.VISIBLE);
            work.setVisibility(View.GONE);
            opetation2.setVisibility(View.GONE);
            opetation3.setVisibility(View.GONE);
            workEvaluatingIndicator.setVisibility(View.GONE);
            textView130.setVisibility(View.GONE);
            max.setVisibility(View.GONE);
            ability.setVisibility(View.GONE);
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

        Map<String, String> map = new HashMap<>();
        map.put("workuserNo",data.getWorkuserNo());
        OkHttp.get(mContext, Constant.get_showworkuserevaluatingindicator,map,new OkCallback<Result<WorkuserEvaluatingIndicatorBean>>() {
            @Override
            public void onResponse(Result<WorkuserEvaluatingIndicatorBean> response) {
                if(response.getData()!=null) {
                    TextView community,urgent,psychology,organization,analyse,law;
                    community=(TextView)holder.getView(R.id.community);
                    urgent=(TextView)holder.getView(R.id.urgent);
                    psychology=(TextView)holder.getView(R.id.psychology);
                    organization=(TextView)holder.getView(R.id.organization);
                    analyse=(TextView)holder.getView(R.id.analyse);
                    law=(TextView)holder.getView(R.id.law);
                    community.setText(response.getData().getCommunity() + " ");
                    urgent.setText(response.getData().getUrgent() + " ");
                    psychology.setText(response.getData().getPsychology() + " ");
                    organization.setText(response.getData().getOrganization() + " ");
                    analyse.setText(response.getData().getAnalyse() + " ");
                    law.setText(response.getData().getLaw() + " ");
                }
            }

            @Override
            public void onFailure(String state, String msg) {

            }
        });


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
