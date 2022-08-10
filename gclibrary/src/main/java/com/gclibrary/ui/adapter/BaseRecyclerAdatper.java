package com.gclibrary.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 12985 on 2016/11/15.
 */
public abstract class BaseRecyclerAdatper<T> extends RecyclerView.Adapter<BaseRecyclerAdatper.ViewHolder> {
    private List<T> datas;
    public Context context;
    public boolean isLoad = true;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;

    public BaseRecyclerAdatper(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void addHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                context).inflate(getLayoutResId(), parent,
                false));
        return holder;
    }

    protected abstract int getLayoutResId();

    protected abstract BaseRecyclerHolder<T> getiBaseRecyclerHolder(View view);

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(holder);
        T data = datas.get(pos);
        holder.iBaseRecyclerHolder.setData(data, pos, datas, isLoad);

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? datas.size() : datas.size() + 1;
    }

    class ViewHolder<T> extends RecyclerView.ViewHolder {
        public BaseRecyclerHolder iBaseRecyclerHolder;

        public ViewHolder(View view) {
            super(view);
            if (itemView == mHeaderView) return;
            iBaseRecyclerHolder = getiBaseRecyclerHolder(view);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {//GridLayoutManager合并第一行
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {//StaggeredGridLayoutManager第一行合并
        super.onViewAttachedToWindow(holder);
        if (mHeaderView != null) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(holder.getLayoutPosition() == 0);
            }
        }
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
}
