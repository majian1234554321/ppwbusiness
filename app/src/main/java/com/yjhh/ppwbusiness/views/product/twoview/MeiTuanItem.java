package com.yjhh.ppwbusiness.views.product.twoview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class MeiTuanItem extends RecyclerView.ItemDecoration {
    private int mTitleHeight;
    private BindData data;
    private Paint mPaint;
    private Rect mBounds;
    private int backgroundColor;
    private int textColor;
    private int textSize;

    public MeiTuanItem(Context context, int mTitleHeight, BindData data, int backgroundColor, int textColor, int textSize) {
        this.mTitleHeight = dip2px(context, mTitleHeight);
        this.data = data;
        mPaint = new Paint();
        mBounds = new Rect();
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.textSize = sp2px(context, textSize);

    }

    public MeiTuanItem(Context context, BindData data) {
        this(context, dip2px(context, 10), data, Color.LTGRAY, Color.DKGRAY, sp2px(context, 9));
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
            View itemView = parent.findViewHolderForAdapterPosition(position).itemView;
            boolean flag = false;
            if (position + 1 < data.getRightData().size()) {
                if (!isTopNotEqualsNext(position)) {
                    if (itemView.getTop() + itemView.getHeight() < mTitleHeight) {
                        c.save();
                        c.translate(0, itemView.getTop() + itemView.getHeight() - mTitleHeight);
                    }
                }
            }
            mPaint.setColor(backgroundColor);
            c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getTop() + mTitleHeight, mPaint);
            mPaint.setTextSize(textSize);
            mPaint.setColor(textColor);
            mPaint.getTextBounds(getPositionText(position), 0, getPositionText(position).length(), mBounds);
            c.drawText(getPositionText(position), parent.getPaddingLeft(), parent.getPaddingTop() + mTitleHeight - (mTitleHeight / 2 - mBounds.height() / 2), mPaint);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int paddingLeft = parent.getPaddingLeft();
        int paddingRight = parent.getRight() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = lp.getViewLayoutPosition();
            if (position == 0) {
                mPaint.setColor(backgroundColor);
                c.drawRect(paddingLeft, child.getTop() - lp.topMargin - mTitleHeight, paddingRight, child.getTop() - lp.topMargin, mPaint);
                mPaint.setColor(textColor);
                mPaint.setTextSize(textSize);
                mPaint.getTextBounds(getPositionText(position), 0, getPositionText(position).length(), mBounds);
                c.drawText(getPositionText(position), child.getPaddingLeft(), child.getTop() - lp.topMargin - (mTitleHeight / 2 - mBounds.height() / 2), mPaint);

            } else {
                if (!isTopNotEqualsBefore(position)) {
                    mPaint.setColor(backgroundColor);
                    c.drawRect(paddingLeft, child.getTop() - lp.topMargin - mTitleHeight, paddingRight, child.getTop() - lp.topMargin, mPaint);
                    mPaint.setColor(textColor);
                    mPaint.setTextSize(textSize);
                    mPaint.getTextBounds(getPositionText(position), 0, getPositionText(position).length(), mBounds);
                    c.drawText(getPositionText(position), child.getPaddingLeft(), child.getTop() - lp.topMargin - (mTitleHeight / 2 - mBounds.height() / 2), mPaint);
                } else {

                }
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position == 0) {
            outRect.set(0, mTitleHeight, 0, 0);
        } else {
            if (!isTopNotEqualsBefore(position)) {
                outRect.set(0, mTitleHeight, 0, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }

    public String getPositionText(int position) {
        if (data.getRightData().get(position) instanceof BaseMeiTuanBean) {
            return ((BaseMeiTuanBean) data.getRightData().get(position)).tagStr();
        }
        return "";
    }

    public boolean isTopNotEqualsBefore(int position) {
        if (data.getRightData().get(position) instanceof BaseMeiTuanBean && data.getRightData().get(position - 1) instanceof BaseMeiTuanBean) {
            return ((BaseMeiTuanBean) data.getRightData().get(position)).tagStr().equals(((BaseMeiTuanBean) data.getRightData().get(position - 1)).tagStr());
        }
        return true;
    }

    public boolean isTopNotEqualsNext(int position) {
        if (data.getRightData().get(position) instanceof BaseMeiTuanBean && data.getRightData().get(position + 1) instanceof BaseMeiTuanBean) {
            return ((BaseMeiTuanBean) data.getRightData().get(position)).tagStr().equals(((BaseMeiTuanBean) data.getRightData().get(position + 1)).tagStr());
        }
        return true;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}