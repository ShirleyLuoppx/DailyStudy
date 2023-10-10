package com.ppx.dailystudy.test.vrandgif.stlrender.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.test.vrandgif.stlrender.base.BaseFragment;
import com.ppx.dailystudy.test.vrandgif.stlrender.stl.StlRenderFragment;
import com.ppx.dailystudy.test.vrandgif.stlrender.utils.FileUtils;
import com.ppx.dailystudy.test.vrandgif.stlrender.utils.FragmentUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gongw on 2018/10/15.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.btn_choose_stl)
    Button chooseStlBtn;
    public static final int REQUEST_STL_FILE = 0X01;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.btn_choose_stl).setOnClickListener(v -> chooseFIle());
    }

    public void chooseFIle() {
        Log.d("TAG", "chooseFIle: okin----");
        //调用方法
        String dirPath = ContextCompat.getExternalFilesDirs(getContext(), Environment.DIRECTORY_DCIM)[0].getAbsolutePath() + File.separator + "PrintFile";
        try {
            copyAssetToFile("NISSAN-GTR.stl", dirPath, "/NISSAN-GTR.stl");
        } catch (IOException e) {
            Log.e("tyl", "IOException=" + e.getMessage());
            e.printStackTrace();
        }
        Log.d("TAG", "chooseFIle: dirPath = "+dirPath);
        //读取到本地file文件
        File file1 = new File(dirPath + "/NISSAN-GTR.stl");
        FragmentUtils.addFragment(getFragmentManager(), StlRenderFragment.getInstance(file1), R.id.fl_container, true);
    }

    /**
     * 复制assets下的文件到本地文件
     * assetName assets内的文件名称
     * savepath 本地文件夹路径
     * savename 保存的文件名称需带后缀文件类型 如.pdf
     *
     * @throws IOException
     */
    public void copyAssetToFile(String assetName, String savepath, String savename) throws IOException {
        InputStream myInput;
        File dir = new File(savepath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dbf = new File(savepath + savename);
        if (dbf.exists()) {
            dbf.delete();
        }
        String outFileName = savepath + savename;
        OutputStream myOutput = new FileOutputStream(outFileName);
        myInput = getContext().getAssets().open(assetName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

}
