package com.example.gongshihao.myapplication.ui.activity.MainActivityConstracts;



import com.example.gongshihao.myapplication.bean.MainMenuBean;
import com.example.gongshihao.myapplication.mvp.BaseModel;
import com.example.gongshihao.myapplication.mvp.BasePresenter;
import com.example.gongshihao.myapplication.mvp.BaseView;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by gongshihao on 2018/4/17.
 */

public interface MainConstracts {

    public abstract class MainPresenter extends BasePresenter<MainModel,MainMview>{
        public abstract void getMainMenu();
    }

    interface MainMview extends BaseView{
        void EquipAdapter(List<MainMenuBean> mList);
    }
    interface MainModel extends BaseModel{
        Observable<List<MainMenuBean>> getMainMenu();
    }
}
