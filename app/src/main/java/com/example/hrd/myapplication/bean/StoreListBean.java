package com.example.hrd.myapplication.bean;

public class StoreListBean <T> {

    /**
     * data : [{"storageroomcode":"00000011","storageroomname":"八里湖卫生材料库"},{"storageroomcode":"00000012","storageroomname":"总务处"},{"storageroomcode":"00000014","storageroomname":"卫生材料库"},{"storageroomcode":"00000042","storageroomname":"信息设备仓库"},{"storageroomcode":"00000093","storageroomname":"物资采供科"},{"storageroomcode":"90000001","storageroomname":"设备库房"}]
     * state : 1
     * msc : 成功
     */

    private T data;
    private String state;
    private String msc;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsc() {
        return msc;
    }

    public void setMsc(String msc) {
        this.msc = msc;
    }
}
