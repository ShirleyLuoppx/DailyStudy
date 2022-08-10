package com.gclibrary.ui;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gclibrary.R;
import com.gclibrary.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/3/11 0011.
 */

public class EmptyView extends FrameLayout implements View.OnClickListener {
    @BindView(R2.id.pb)
    ProgressBar pb;
    @BindView(R2.id.ll_loading)
    LinearLayout llLoading;
    @BindView(R2.id.ll_error)
    LinearLayout llError;
    @BindView(R2.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R2.id.tv_empty)
    TextView tvEmpty;
    private Context context;

    public EmptyView(@NonNull Context context) {
        this(context, null);
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.item_error_empty, this);
        ButterKnife.bind(this, view);
        setShowContent();
        llError.setOnClickListener(this);
        llEmpty.setOnClickListener(this);
    }

    public void setLoading() {
        if (!loadEnable) return;
        setVisibility(VISIBLE);
        llLoading.setVisibility(VISIBLE);
        llError.setVisibility(GONE);
        llEmpty.setVisibility(GONE);
    }

    public void setError() {
        setVisibility(VISIBLE);
        llLoading.setVisibility(GONE);
        llError.setVisibility(VISIBLE);
        llEmpty.setVisibility(GONE);
    }

    public void setEmpty() {
        setVisibility(VISIBLE);
        llLoading.setVisibility(GONE);
        llError.setVisibility(GONE);
        llEmpty.setVisibility(VISIBLE);
    }

    public void setEmptyTxt(String str){
        tvEmpty.setText(str);
    }

    public void setShowContent() {
        setVisibility(GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_error || i == R.id.ll_empty) {
            if (onClick != null) {
                onClick.setRestartData();
            }
        }
    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick {
        void setRestartData();
    }

    private boolean loadEnable = true;

    public void setLoadEnable(boolean loadEnable) {
        this.loadEnable = loadEnable;
    }
}
