package com.ppx.dailystudy.view.straggergrid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ppx.dailystudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LuoXia
 * @Date: 2022/11/11 18:19
 * @Description:
 */
public class StaggeredViewAdapter extends RecyclerView.Adapter<StaggeredViewAdapter.MyViewHolder> {


    private List<StaggeredBook> bookList = new ArrayList<>();
    private Context mContext;


    public StaggeredViewAdapter(List<StaggeredBook> list, Context context) {
        bookList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_staggeredgrid, null));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(bookList.get(position).getName());
        holder.tvName.setHeight(bookList.get(position).getHeight());
        holder.tvName.setBackgroundColor(R.color.colorBlue00ADAB);
    }

    @Override
    public int getItemCount() {
        return bookList == null ? 0 : bookList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
