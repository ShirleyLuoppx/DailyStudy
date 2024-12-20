package com.ppx.dailystudy.test.vrandgif.stlrender.stl;

import com.ppx.dailystudy.MyApplication;
import com.ppx.dailystudy.R;

import java.io.File;

/**
 * Created by gw on 2017/7/11.
 */

public class StlRenderPresenter implements StlRenderContract.Presenter {
    StlRenderContract.View view;

    public StlRenderPresenter(StlRenderContract.View view){
        this.view = view;
    }

    @Override
    public void loadModel(File stlFile) {
        if (stlFile !=null && stlFile.exists()) {
            StlFetcher.fetchStlFile(stlFile, new StlFetchCallback() {
                @Override
                public void onBefore() {
                    view.showFetchProgressDialog(0);
                }

                @Override
                public void onProgress(int progress) {
                    view.showFetchProgressDialog(progress);
                }

                @Override
                public void onFinish(STLObject stlObject) {
                    view.hideFetchProgressDialog();
                    view.showModel(stlObject);
                }

                @Override
                public void onError() {
                    view.hideFetchProgressDialog();
                    view.showToastMsg(MyApplication.getContext().getString(R.string.error_fetch_data));
                }
            });
        }else{
            view.showToastMsg(MyApplication.getContext().getString(R.string.model_file_not_found));
        }
    }

}
