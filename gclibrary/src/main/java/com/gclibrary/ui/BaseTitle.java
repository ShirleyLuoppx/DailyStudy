package com.gclibrary.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.ViewUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gclibrary.R;
import com.gclibrary.R2;
import com.gclibrary.base.BaseActivity;
import com.gclibrary.util.ScrUtils;
import com.gclibrary.util.Tools;
import com.gclibrary.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/3/11 0011.
 */

public class BaseTitle extends RelativeLayout implements View.OnClickListener {
    @BindView(R2.id.ll_title_bg)
    LinearLayout llTitleBg;
    @BindView(R2.id.iv_left)
    ImageView ivLeft;
    @BindView(R2.id.tv_left)
    TextView tvLeft;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.et_titile_search)
    EditText etSearch;
    @BindView(R2.id.iv_right)
    ImageView ivRight;
    @BindView(R2.id.iv_right2)
    ImageView ivRight2;
    @BindView(R2.id.tv_right)
    TextView tvRight;
    @BindView(R2.id.v_line)
    View vLine;
    @BindView(R2.id.v_status_bar)
    View vStatusBar;
    private Context context;

    public BaseTitle(Context context) {
        this(context, null);
    }

    public BaseTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        initView();
        setvStatusBarHeight();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.item_common_base_title, this);
        ButterKnife.bind(this, view);
        ivLeft.setOnClickListener(this);
    }

    public void setIvLeft(@DrawableRes int icon) {
        ivLeft.setImageResource(icon);
        ivLeft.setVisibility(VISIBLE);
    }

    public void setTvLeft(String str) {
        tvLeft.setText(str);
        tvLeft.setVisibility(VISIBLE);
    }

    public void setTvTitle(String str) {
        tvTitle.setText(str);
        tvTitle.setVisibility(VISIBLE);
        etSearch.setVisibility(GONE);
        setVisibility(VISIBLE);
    }

    public void setSearchVisable(boolean flag) {
        tvTitle.setVisibility(flag ? GONE : VISIBLE);
        etSearch.setVisibility(flag ? VISIBLE : GONE);
    }

    public void setIvRight(@DrawableRes int icon) {
        ivRight.setImageResource(icon);
        ivRight.setVisibility(VISIBLE);
    }

    public void setIvRight2(@DrawableRes int icon) {
        ivRight2.setImageResource(icon);
        ivRight2.setVisibility(VISIBLE);
    }

    public void setTvRight(String str) {
        tvRight.setText(str);
        tvRight.setVisibility(VISIBLE);
    }


    public void setvStatusBarHeight() {
        int barHeight = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//大于19
            barHeight = ScrUtils.getStatusHeight(context);
        }
        vStatusBar.getLayoutParams().height = barHeight;
//        ViewUtil.setViewSizeh(llTitleBg, barHeight + ScrUtils.dpToPx(context, 50));
    }

    public View getvStatusBar() {
        return vStatusBar;
    }

    public ImageView getIvLeft() {
        return ivLeft;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvLeft() {
        return tvLeft;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    public ImageView getIvRight() {
        return ivRight;
    }

    public ImageView getIvRight2() {
        return ivRight2;
    }

    public EditText getEtSearch() {
        return etSearch;
    }

    public LinearLayout getLlTitleBg() {
        return llTitleBg;
    }

    public void setTitleBgTransparent() {
        llTitleBg.setBackgroundResource(R.color.transparent);
    }

    public void setTitleBgColorRes(int ids) {
        llTitleBg.setBackgroundResource(ids);
    }

    public void setLlTitleBg(@DrawableRes int icon) {
        llTitleBg.setBackgroundResource(icon);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_left) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).finish();
                ((BaseActivity) context).showKeyboard(false);
            }
        }
    }
}
