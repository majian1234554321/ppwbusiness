package com.yjhh.ppwbusiness.views.cui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.internal.InternalClassics;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yjhh.ppwbusiness.R;


public class PPWHeader2 extends InternalClassics<PPWHeader2> implements RefreshHeader {

    private ImageView imageView;
    private TextView tv_tips;

    public PPWHeader2(Context context) {
        this(context, null);
    }

    public PPWHeader2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PPWHeader2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = View.inflate(context, R.layout.ppwheader, this);

        final View thisView = this;
        final View arrowView = mArrowView;
        //  final View updateView = mLastUpdateText;
        final View progressView = mProgressView;
        final ViewGroup centerLayout = mCenterLayout;
        final DensityUtil density = new DensityUtil();


        imageView = view.findViewById(R.id.iv_image);

        tv_tips = view.findViewById(R.id.tv_tips);

    }


    protected boolean isRefreshing;
    protected Animation mAnimation;
    protected RefreshState mState;


    @Override
    public void onReleased(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
        isRefreshing = true;
        // final View thisView = this;
        // thisView.startAnimation(mAnimation);


        Log.i("PPWHeader", "height:" + height);
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        //  final View thisView = this;
        isRefreshing = false;
        //thisView.clearAnimation();
        //  Log.i("PPWHeader", "onFinish");
        return 0;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        Log.i("PPWHeader", "offset:" + l);
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        super.onMoving(isDragging, percent, offset, height, maxDragHeight);

        //沿x轴放大
        //  ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 2f, 1f);
        //沿y轴放大
        //  ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 2f, 1f);


        imageView.scrollTo(0, 30);


        // AnimatorSet set = new AnimatorSet();
        // set.play(scaleXAnimator);
        // set.start();


    }


    String valueStatus = "下拉刷新";

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

        mState = newState;
        switch (newState) {
            case None:

                break;
            case PullDownToRefresh:
                Log.i("PPWHeader", "PullDownToRefresh");
                imageView.setImageResource(R.drawable.icon_ppwwz);
                valueStatus = "下拉刷新";
                break;
            case PullDownCanceled:
                Log.i("PPWHeader", "PullDownCanceled");
                break;
            case ReleaseToRefresh:
                Log.i("PPWHeader", "ReleaseToRefresh");

                valueStatus = "释放刷新";

                break;
            case Refreshing:
                Log.i("PPWHeader", "Refreshing");
                imageView.setImageResource(R.drawable.icon_ppwwz);

                valueStatus = "正在刷新";
                break;
            case RefreshFinish:
                Log.i("PPWHeader", "RefreshFinish");
                break;
        }


        tv_tips.setText(valueStatus);
    }


}
