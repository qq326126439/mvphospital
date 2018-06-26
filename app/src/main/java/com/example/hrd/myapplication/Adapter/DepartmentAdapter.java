package com.example.hrd.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hrd.myapplication.bean.DepartmentBean;

import java.util.List;

public class DepartmentAdapter extends BaseQuickAdapter<DepartmentBean,BaseViewHolder>{

    public DepartmentAdapter(Context context, @LayoutRes int LayouRes, List<DepartmentBean> list){
        super(LayouRes,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, DepartmentBean item) {

    }
}
