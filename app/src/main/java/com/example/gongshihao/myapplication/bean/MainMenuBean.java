package com.example.gongshihao.myapplication.bean;

/**
 * Created by gongshihao on 2018/4/19.
 */

public class MainMenuBean <T>{
    public T ImagePath;
    public String NameBtn;

    public T getImagePath() {
        return ImagePath;
    }

    public void setImagePath(T imagePath) {
        ImagePath = imagePath;
    }

    public String getNameBtn() {
        return NameBtn;
    }

    public void setNameBtn(String nameBtn) {
        NameBtn = nameBtn;
    }
}
