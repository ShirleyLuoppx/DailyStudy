package com.ppx.dailystudy.wutong;

import android.content.ContentValues;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;

import com.openos.statistics.IStatisticsInterface;

/**
 * @Author: LuoXia
 * @Date: 2022/3/10 17:03
 * @Description:
 */
public class StatisticsImpl extends IStatisticsInterface.Stub {
    @Override
    public Uri insert(Uri uri, ContentValues values) throws RemoteException {
        if (values == null) {
            return uri;
        }
        Log.i("StatisticsImpl", "provider insert values = " + values);

        return uri;

    }
}
