package com.example.hrd.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hrd.myapplication.R;
import com.example.hrd.myapplication.bean.StoreBean;

import java.util.List;

public class EquipmentAdapter extends BaseQuickAdapter<StoreBean,BaseViewHolder>{
    public Context context;

    public EquipmentAdapter(Context context, @LayoutRes int LayoutId, List<StoreBean> mlist){
        super(LayoutId,mlist);
        this.context=context;
    }


    @Override
    protected void convert(BaseViewHolder helper, StoreBean item) {
        helper.setText(R.id.Name,item.getStorageroomname());
    }
}
