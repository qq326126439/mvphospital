package com.example.gongshihao.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gongshihao.myapplication.ImageUtil.GlideUtil;
import com.example.gongshihao.myapplication.R;
import com.example.gongshihao.myapplication.bean.MainMenuBean;

import java.util.List;

/**
 * Created by gongshihao on 2018/4/19.
 */

public class MainAdapter extends BaseQuickAdapter<MainMenuBean,BaseViewHolder>{
    public Context mContext;
    public MainAdapter(Context context, @LayoutRes int LayoutRes, List<MainMenuBean> list){
        super(LayoutRes,list);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MainMenuBean item) {
        helper.setText(R.id.Name,item.getNameBtn());
        GlideUtil.LoadCommonImage(mContext,item.getImagePath(),(ImageView) helper.getView(R.id.logo));
    }
}
