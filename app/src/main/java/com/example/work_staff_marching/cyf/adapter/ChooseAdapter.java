package com.example.work_staff_marching.cyf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.entity.ChooseBean;
import com.example.work_staff_marching.cyf.utils.RecyclerViewHolder;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

public class ChooseAdapter extends BaseRecyclerViewAdapter<ChooseBean, RecyclerViewHolder> {
    private Context mContext;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public ChooseAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    protected void convert(RecyclerViewHolder holder, ChooseBean data, int position, int viewType) {

        QMUIRoundButton ivIsSelect = (QMUIRoundButton) holder.getView(R.id.iv_is_select);
        TextView tvText = (TextView) holder.getView(R.id.tv_text);
        if (isSelect()) {//判断复选框的出现
            ivIsSelect.setVisibility(View.VISIBLE);
        } else {
            ivIsSelect.setVisibility(View.GONE);
        }
        if (data.isSelect()) {//判断数据是否选择
            ivIsSelect.setBackgroundColor(mContext.getResources().getColor(R.color.red));
        } else {
            ivIsSelect.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
        }

    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_choose;
    }

    @Override
    protected int getViewType(int position, ChooseBean data) {
        return 0;
    }
}
