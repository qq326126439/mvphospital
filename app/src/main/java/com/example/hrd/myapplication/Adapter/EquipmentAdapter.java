package com.example.hrd.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.bean.EquipmentBean;

import java.util.List;

public class EquipmentAdapter extends BaseQuickAdapter<EquipmentBean,BaseViewHolder>{

    public EquipmentAdapter(Context context, @LayoutRes int LayoutRes, List<EquipmentBean> mlist){
        super(LayoutRes,mlist);
    }

    @Override
    protected void convert(BaseViewHolder helper, EquipmentBean item) {
        helper.setText(R.id.EquipId,item.getEquipmentcode());
        helper.setText(R.id.EquipName,item.getEquipmentname());
        helper.setText(R.id.model,item.getModel());
        helper.setText(R.id.Department,item.getDeptname());
    }
}

