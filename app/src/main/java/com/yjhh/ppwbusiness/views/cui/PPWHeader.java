package com.yjhh.ppwbusiness.views.cui;

import android.content.Context;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.internal.InternalClassics;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yjhh.ppwbusiness.R;


public class PPWHeader extends InternalClassics<PPWHeader> implements RefreshHeader {

    private ImageView imageView;

    public PPWHeader(Context context) {
        this(context, null);
    }

    public PPWHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PPWHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view  = View.inflate(context, R.layout.ppwheader, this);

        final View thisView = this;
        final View arrowView = mArrowView;
      //  final View updateView = mLastUpdateText;
        final View progressView = mProgressView;
        final ViewGroup centerLayout = mCenterLayout;
        final DensityUtil density = new DensityUtil();


        imageView = view.findViewById(R.id.iv_image);

    }


    protected boolean isRefreshing;
    protected Animation mAnimation;
    protected RefreshState mState;

    @Override
    public void onReleased(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
        isRefreshing = true;
        // final View thisView = this;
        // thisView.startAnimation(mAnimation);
        Log.i("PPWHeader","onReleased");
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        //  final View thisView = this;
        isRefreshing = false;
        //thisView.clearAnimation();
        Log.i("PPWHeader","onFinish");
        return 0;
    }


    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

        mState = newState;
        switch (newState) {
            case None:

                break;
            case PullDownToRefresh:
                Log.i("PPWHeader","PullDownToRefresh");
                imageView.setImageResource(R.drawable.icon_ppw);
                break;
            case PullDownCanceled:
                Log.i("PPWHeader","PullDownCanceled");
                break;
            case ReleaseToRefresh:
                Log.i("PPWHeader","ReleaseToRefresh");
                break;
            case Refreshing:
                Log.i("PPWHeader","Refreshing");
                imageView.setImageResource(R.drawable.icon_ppwwz);
                break;
            case RefreshFinish:
                Log.i("PPWHeader","RefreshFinish");
                break;
        }
    }


}
