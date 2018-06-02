package com.example.gongshihao.myapplication.ui.activity.MainActivityConstracts;
import com.example.gongshihao.myapplication.R;
import com.example.gongshihao.myapplication.bean.MainMenuBean;
import com.example.gongshihao.myapplication.http.RxSchedulers;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * Created by gongshihao on 2018/4/17.
 */

public class MainModel implements MainConstracts.MainModel {
    @Override
    public Observable<List<MainMenuBean>> getMainMenu() {
        return Observable.create(new ObservableOnSubscribe<List<MainMenuBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<MainMenuBean>> e) throws Exception {
                String[] titles={"Test1","Test2","Test3"};
                int[] Imgs={R.mipmap.home,R.mipmap.home,R.mipmap.home};
                List<MainMenuBean> list=new ArrayList<>();
                for(int i=0;i<titles.length;i++){
                    MainMenuBean mainMenuBean=new MainMenuBean();
                    mainMenuBean.setNameBtn(titles[i]);
                    mainMenuBean.setImagePath(Imgs[i]);
                    list.add(mainMenuBean);
                }
                e.onNext(list);
                e.onComplete();
            }
        }).compose(RxSchedulers.<List<MainMenuBean>>applySchedulers());
    }
}
