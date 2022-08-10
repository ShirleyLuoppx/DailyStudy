package com.gclibrary.http.converter;

import android.text.TextUtils;


import com.gclibrary.GcHelper;
import com.nanchen.compresshelper.CompressHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class RequestImageConverter implements Converter<File, RequestBody> {
    @Override
    public RequestBody convert(File file) throws IOException {
        return RequestBody.create(MediaType.parse("multipart/form-data"), file);
    }

    public MultipartBody getMultipartBody(String strfile) throws IOException {
        if (strfile == null) return null;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        File file = CompressHelper.getDefault(GcHelper.getInstance().getContext()).compressToFile(new File(strfile));
        RequestBody requestBody = convert(file);
        builder.addFormDataPart("photo", file.getName(), requestBody);
        return builder.build();
    }
}
