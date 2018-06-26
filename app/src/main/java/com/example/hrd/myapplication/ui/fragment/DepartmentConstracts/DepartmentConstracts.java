package com.example.hrd.myapplication.ui.fragment.DepartmentConstracts;

import com.example.hrd.myapplication.mvp.BaseModel;
import com.example.hrd.myapplication.mvp.BasePresenter;
import com.example.hrd.myapplication.mvp.BaseView;

public interface DepartmentConstracts {
     abstract class DepartmentPresenter extends BasePresenter<DepartmentModel,DepartmentMview>{

    }
    interface DepartmentModel extends BaseModel{

    }

    interface DepartmentMview extends BaseView{

    }

}
