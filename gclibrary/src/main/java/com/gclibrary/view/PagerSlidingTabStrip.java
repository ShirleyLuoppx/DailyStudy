/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gclibrary.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gclibrary.R;
import com.gclibrary.util.ScrUtils;

import java.util.List;
import java.util.Locale;

public class PagerSlidingTabStrip extends HorizontalScrollView implements OnPageChangeListener {

    public interface IconTabProvider {

        int getPageIconResId(int position);
    }


    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private LinearLayout.LayoutParams expandedTabLayoutParams;

    private LinearLayout tabsContainer;
    private ViewPager pager;

    private int tabCount;

    private int currentPosition = 0;
    private float currentPositionOffset = 0f;

    private Paint rectPaint;
    private Paint dividerPaint;
    private Paint dotPaint;

    private int indicatorColor = 0xbd6aff;
    private int underlineColor = 0xbd6aff;
    private int dividerColor = 0x00000000;

    private boolean shouldExpand = true;
    private boolean textAllCaps = true;

    private int scrollOffset = 52;
    private int indicatorHeight = 4;
    private int underlineHeight = 1;
    private int dividerPadding = 12;
    private int tabPadding = 20;
    private int dividerWidth = 1;
    private int indicatorMarginLeft = 20;

    private int tabTextSize = 36;
    private int tabTextColor = 0xFFFFFFFF;
    private Typeface tabTypeface = null;
    private int tabTypefaceStyle = Typeface.NORMAL;

    private int lastScrollX = 0;

    private int tabBackgroundResId = R.color.transparent;

    private int checkedTextColor = R.color.text_gray_3d4552;

    private int unCheckedTextColor = R.color.text_gray_afb5c2;

    private Locale locale;
    private boolean[] dots;
    private Context context;

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

        setFillViewport(true);
        setWillNotDraw(false);

        tabsContainer = new LinearLayout(context);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(tabsContainer);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PagerSlidingTabStrip);

        indicatorColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, getResources().getColor(R.color.title_bg));
        underlineColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsUnderlineColor, getResources().getColor(R.color.view_bg));
        dividerColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsDividerColor, dividerColor);
        tabTextColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsTextColor, getResources().getColor(checkedTextColor));
        indicatorHeight = ScrUtils.getRealWidth(context, a.getInt(R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight, indicatorHeight));
        underlineHeight = ScrUtils.getRealWidth(context, a.getInt(R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight, underlineHeight));
        dividerPadding = ScrUtils.getRealWidth(context, a.getInt(R.styleable.PagerSlidingTabStrip_pstsDividerPadding, dividerPadding));
        tabPadding = ScrUtils.getRealWidth(context, a.getInt(R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight, tabPadding));
        tabTextSize = ScrUtils.getRealWidth(context, a.getInt(R.styleable.PagerSlidingTabStrip_pstsTabTextSize, tabTextSize));
        indicatorMarginLeft = ScrUtils.getRealWidth(context, a.getInt(R.styleable.PagerSlidingTabStrip_pstsTabIndicatorMarginLeft, indicatorMarginLeft));
        tabBackgroundResId = a.getResourceId(R.styleable.PagerSlidingTabStrip_pstsTabBackground, tabBackgroundResId);
        shouldExpand = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldExpand, shouldExpand);
        scrollOffset = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsScrollOffset, scrollOffset);
        textAllCaps = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsTextAllCaps, textAllCaps);

        a.recycle();

        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStyle(Style.FILL);

        dividerPaint = new Paint();
        dividerPaint.setAntiAlias(true);
        dividerPaint.setStrokeWidth(dividerWidth);

        dotPaint = new Paint();
        dotPaint.setColor(Color.RED);
        dotPaint.setAntiAlias(true);
        dotPaint.setStyle(Style.FILL);

        defaultTabLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        expandedTabLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);

        if (locale == null) {
            locale = getResources().getConfiguration().locale;
        }
    }

    public void setViewPager(ViewPager pager, List<String> titles) {
        this.pager = pager;

        if (pager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        notifyDataSetChanged(titles);
    }

    public void notifyDataSetChanged(List<String> titles) {

        tabsContainer.removeAllViews();

        tabCount = pager.getAdapter().getCount();

        for (int i = 0; i < tabCount; i++) {

//			if (pager.getAdapter() instanceof IconTabProvider) {
//				addIconTab(i, ((IconTabProvider) pager.getAdapter()).getPageIconResId(i));
//			} else {
            addTextTab(i, titles.get(i));
//			}

        }

        updateTabStyles();

        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                currentPosition = pager.getCurrentItem();
                setChooseTabViewTextColor(currentPosition);
                scrollToChild(currentPosition, 0);
            }
        });

    }

    private void addTextTab(final int position, String title) {

        TextView tab = new TextView(getContext());
        tab.setText(title);
        tab.setGravity(Gravity.CENTER);
        tab.setSingleLine();
        tab.getPaint().setFakeBoldText(true);
        addTab(position, tab);
    }

    private void setChooseTabViewTextColor(int position) {
        int childCount = tabsContainer.getChildCount();
        TextView textView;
        if (minTextSize == 0) minTextSize = tabTextSize;
        for (int i = 0; i < childCount; ++i) {
            textView = (TextView) tabsContainer.getChildAt(i);
            textView.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
            if (i == position) {
                textView.setTextColor(getResources().getColor(checkedTextColor));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,tabTextSize);
            } else {
                textView.setTextColor(getResources().getColor(unCheckedTextColor));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, minTextSize);
            }
        }
    }


    private void addTab(final int position, TextView tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pager.setCurrentItem(position, false);
            }
        });
//        if (!shouldExpand) {
//            defaultTabLayoutParams = news LinearLayout.LayoutParams((int) tab.getPaint().measureText(tab.getText().toString()) + tabPadding * 2, LayoutParams.MATCH_PARENT);
//        }
        tab.setPadding(tabPadding, 0, tabPadding, 0);
        tabsContainer.addView(tab, position, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
    }

    private void updateTabStyles() {
        for (int i = 0; i < tabCount; i++) {
            View v = tabsContainer.getChildAt(i);
            v.setBackgroundResource(tabBackgroundResId == 0 ? R.color.transparent : tabBackgroundResId);
            if (v instanceof TextView) {
                TextView tab = (TextView) v;
                tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                tab.setTypeface(tabTypeface, tabTypefaceStyle);
                tab.setTextColor(tabTextColor);
//                if (textAllCaps) {//控制大小写
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//                        tab.setAllCaps(true);
//                    } else {
//                        tab.setText(tab.getText().toString().toUpperCase(locale));
//                    }
//                }
            }
        }

    }

    private void scrollToChild(int position, int offset) {

        if (tabCount == 0) {
            return;
        }

        int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;

        if (position > 0 || offset > 0) {
            newScrollX -= scrollOffset;
        }

        if (newScrollX != lastScrollX) {
            lastScrollX = newScrollX;
            scrollTo(newScrollX, 0);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInEditMode() || tabCount == 0) {
            return;
        }

        final int height = getHeight();

        // draw indicator line

        rectPaint.setColor(indicatorColor);


        // default: line below current tab
        View currentTab = tabsContainer.getChildAt(currentPosition);
        float lineLeft = currentTab.getLeft();
        float lineRight = currentTab.getRight();

        // if there is an offset, start interpolating left and right coordinates
        // between current and next tab
        if (currentPositionOffset > 0f && currentPosition < tabCount - 1) {

            View nextTab = tabsContainer.getChildAt(currentPosition + 1);
            final float nextTabLeft = nextTab.getLeft();
            final float nextTabRight = nextTab.getRight();

            lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * lineLeft);
            lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * lineRight);
        }
        canvas.drawRoundRect(lineLeft + indicatorMarginLeft, height - indicatorHeight, lineRight - indicatorMarginLeft, height, indicatorHeight / 2, indicatorHeight / 2, rectPaint);

        //画圆角背景
        if (isRound) {
//            rectPaint.setColor(Color.parseColor("#eef1f4"));
            RectF rectF = new RectF(0, 0, getWidth(), height);
//            canvas.drawRoundRect(rectF, height / 2, height / 2, rectPaint);

            rectPaint.setColor(indicatorColor);
            rectF = new RectF(lineLeft, 0, lineRight, height);
            canvas.drawRoundRect(rectF, height / 2, height / 2, rectPaint);
        }
        // draw underline

        rectPaint.setColor(underlineColor);
        canvas.drawRect(0, height - underlineHeight, tabsContainer.getWidth(), height, rectPaint);

        // draw divider

        dividerPaint.setColor(dividerColor);
        for (int i = 0; i < tabCount - 1; i++) {
            View tab = tabsContainer.getChildAt(i);
            canvas.drawLine(tab.getRight(), dividerPadding, tab.getRight(), height - dividerPadding, dividerPaint);
        }

        dividerPaint.setColor(dividerColor);
        for (int i = 0; i < tabCount; i++) {
            if (dots != null && dots.length > i) {
                View tab = tabsContainer.getChildAt(i);
                if (dots[i])
                    canvas.drawCircle(tab.getRight() - ScrUtils.getRealWidth(context, 20), ScrUtils.getRealWidth(context, 25), ScrUtils.getRealWidth(context, 6), dotPaint);
            }
        }

    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        invalidate();
    }

    public void setIndicatorColorResource(int resId) {
        if (resId == 0) return;
        this.indicatorColor = getResources().getColor(resId);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public void setIndicatorHeight(int indicatorLineHeightPx) {
        this.indicatorHeight = indicatorLineHeightPx;
        invalidate();
    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }

    public void setUnderlineColor(int underlineColor) {
        this.underlineColor = underlineColor;
        invalidate();
    }

    public void setUnderlineColorResource(int resId) {
        this.underlineColor = getResources().getColor(resId);
        invalidate();
    }

    public int getUnderlineColor() {
        return underlineColor;
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        invalidate();
    }

    public void setDividerColorResource(int resId) {
        this.dividerColor = getResources().getColor(resId);
        invalidate();
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public void setUnderlineHeight(int underlineHeightPx) {
        this.underlineHeight = underlineHeightPx;
        invalidate();
    }

    public int getUnderlineHeight() {
        return underlineHeight;
    }

    public void setDividerPadding(int dividerPaddingPx) {
        this.dividerPadding = dividerPaddingPx;
        invalidate();
    }

    public void setDividerWidth(int dividerWidth) {
        this.dividerWidth = dividerWidth;
        invalidate();
    }

    public int getDividerPadding() {
        return dividerPadding;
    }

    public void setScrollOffset(int scrollOffsetPx) {
        this.scrollOffset = scrollOffsetPx;
        invalidate();
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    public void setShouldExpand(boolean shouldExpand) {
        this.shouldExpand = shouldExpand;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return shouldExpand;
    }

    public boolean isTextAllCaps() {
        return textAllCaps;
    }

    public void setAllCaps(boolean textAllCaps) {
        this.textAllCaps = textAllCaps;
    }

    public void setTextSize(int textSizePx) {
        this.tabTextSize = textSizePx;
        updateTabStyles();
    }

    public void setCheckedTextColor(int checkedTextColor) {
        this.checkedTextColor = checkedTextColor;
        invalidate();
    }

    public void setUnCheckedTextColor(int unCheckedTextColor) {
        this.unCheckedTextColor = unCheckedTextColor;
        invalidate();
    }

    public int getTextSize() {
        return tabTextSize;
    }

    public void setTextColor(int textColor) {
        this.tabTextColor = textColor;
        updateTabStyles();
    }

    public void setTextColorResource(int resId) {
        this.tabTextColor = getResources().getColor(resId);
        updateTabStyles();
    }

    public int getTextColor() {
        return tabTextColor;
    }

    public void setTypeface(Typeface typeface, int style) {
        this.tabTypeface = typeface;
        this.tabTypefaceStyle = style;
        updateTabStyles();
    }

    public void setTabBackground(int resId) {
        this.tabBackgroundResId = resId;
    }

    public int getTabBackground() {
        return tabBackgroundResId;
    }

    public void setTabPaddingLeftRight(int paddingPx) {
        this.tabPadding = paddingPx;
        updateTabStyles();
    }

    public int getTabPaddingLeftRight() {
        return tabPadding;
    }

    public void setIndicatorMarginLeft(int marginLeft) {
        this.indicatorMarginLeft = marginLeft;
        updateTabStyles();
    }

    public int getIndicatorMarginLeft() {
        return indicatorMarginLeft;
    }

    public void setUnReadDot(boolean[] dots) {
        this.dots = dots;
        invalidate();
    }

    private boolean isRound;

    public void setIndicatorRound(boolean isRound) {
        this.isRound = isRound;
        invalidate();
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        currentPosition = savedState.currentPosition;
        requestLayout();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.currentPosition = currentPosition;
        return savedState;
    }

    static class SavedState extends BaseSavedState {

        int currentPosition;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            currentPosition = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(currentPosition);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {

            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        currentPosition = position;
        currentPositionOffset = positionOffset;
        scrollToChild(position, (int) (positionOffset * tabsContainer.getChildAt(position).getWidth()));

        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            scrollToChild(pager.getCurrentItem(), 0);
        }
    }

    @Override
    public void onPageSelected(int position) {
        setChooseTabViewTextColor(position);
    }


    private int minTextSize;

    public void setTextSize(int maxTextSize, int minTextSize) {
        tabTextSize = maxTextSize;
        this.minTextSize = minTextSize;
        invalidate();
    }

}
