package com.example.hrd.myapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.bean.EquipmentBean;

import java.util.List;

import zlc.season.rxdownload2.entity.DownloadType;

public class EquipmentAdapter extends BaseQuickAdapter<EquipmentBean,BaseViewHolder>{

    public EquipmentAdapter(Context context, @LayoutRes int LayoutRes, List<EquipmentBean> mlist){
        super(LayoutRes,mlist);
    }

    @Override
    protected void convert(BaseViewHolder helper, EquipmentBean item) {
        helper.setText(R.id.EquipId,item.getEquipmentnum());
        helper.setText(R.id.EquipName,item.getEquipmentname());
        helper.setText(R.id.model,item.getModel());
        helper.setText(R.id.Value,item.getValue());
        helper.setText(R.id.fault,item.getInventorydet());
        boolean VISIBLE = false;
        if(item.getInventorystate()!=null){
            VISIBLE= item.getInventorystate().equals("2")?true:false;
        }
        helper.getView(R.id.fault).setVisibility(VISIBLE?View.VISIBLE:View.GONE);

        int color=Color.BLACK;
        try {
            if (item.getInventorystate().equals("1")) color = Color.BLUE;
            if (item.getInventorystate().equals("2")) color = Color.RED;
        }catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        helper.setTextColor(R.id.EquipId,color);
        helper.setTextColor(R.id.EquipName,color);
        helper.setTextColor(R.id.model,color);
        helper.setTextColor(R.id.Value,color);
    }
}

