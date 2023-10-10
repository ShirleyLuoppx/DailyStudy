package com.ppx.dailystudy.test.vrandgif.stlrender.stl;

import com.ppx.dailystudy.test.vrandgif.stlrender.base.BasePresenter;
import com.ppx.dailystudy.test.vrandgif.stlrender.base.BaseView;

import java.io.File;

/**
 * Created by gw on 2017/7/11.
 */

public interface StlRenderContract {

    interface View extends BaseView {
        void showToastMsg(String msg);
        void showModel(STLObject stlObject);
        void showFetchProgressDialog(int progress);
        void hideFetchProgressDialog();
    }

    interface Presenter extends BasePresenter {
        void loadModel(File file);
    }

}
