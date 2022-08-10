package com.gclibrary.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by guangchuan on 2016/6/16.
 */
public abstract class BAdapter<T> extends BaseAdapter {
    private List<T> datas;
    protected Context context;

    public BAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public int getCount() {
        if(datas==null) return 0;
        else return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder<T> holder;
        if (convertView == null) {
            holder = getHolder();
        } else {
            holder = (BaseHolder<T>) convertView.getTag();
        }
        T data = datas.get(position);
        holder.setData(data, position, datas,this);
        return holder.getContentView();
    }

    public void add(T outData)
    {
        if(outData==null) return;
        datas.add(outData);
        notifyDataSetChanged();
    }

    public void addAll(List<T> outDatas)
    {
        if(outDatas==null) return;
        datas.addAll(outDatas);
        notifyDataSetChanged();
    }

    public void remove(T outData){
        if(outData==null) return;
        datas.remove(outData);
        notifyDataSetChanged();
    }

    public void remove(int position){
        if(position<0) return;
        datas.remove(position);
        notifyDataSetChanged();
    }

    public void clear(){
        if(datas.size()==0) return;
        datas.clear();
        notifyDataSetChanged();
    }

    protected abstract BaseHolder<T> getHolder();

}
