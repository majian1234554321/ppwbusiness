package com.yjhh.ppwbusiness.views.cui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.internal.InternalClassics;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yjhh.ppwbusiness.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


public class PPWHeader extends InternalClassics<PPWHeader> implements RefreshHeader {

    private IImageView imageView;
    private AnimatorSet animSet;

    private View view;
    private SportView sview;
    private RelativeLayout rl;


    public PPWHeader(Context context) {
        this(context, null);
    }

    public PPWHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PPWHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final ViewGroup thisGroup = this;
        view = View.inflate(context, R.layout.ppwheader1, null);


        thisGroup.addView(view, MATCH_PARENT, WRAP_CONTENT);

        mSpinnerStyle = SpinnerStyle.FixedBehind;
        sview = view.findViewById(R.id.sview);

        imageView = view.findViewById(R.id.iv_image);
        rl = findViewById(R.id.rl);


        // imageView.setImageResource(R.drawable.logo);
        // centerLayout.addView(view,60,60);


    }


    protected boolean isRefreshing;
    protected Animation mAnimation;
    protected RefreshState mState;

    public float s = 0f;
    public float e = 0f;

    @Override
    public void onReleased(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
        isRefreshing = true;
        // final View thisView = this;
        // thisView.startAnimation(mAnimation);
        // Log.i("PPWHeader", "onReleased");
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
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {


        Log.i("PPWHeader", "percent" + percent);


        if (offset > height) {
            RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) view.getLayoutParams();
            Params.height = offset;
            view.setLayoutParams(Params);

            RelativeLayout.LayoutParams Params2 = (RelativeLayout.LayoutParams) sview.getLayoutParams();
            Params2.height = offset;
            sview.setLayoutParams(Params2);
        } else if (offset == height) {
            RelativeLayout.LayoutParams Params2 = (RelativeLayout.LayoutParams) sview.getLayoutParams();
            Params2.height = offset;
            Params2.width = offset;
            sview.setLayoutParams(Params2);
        }


//        if (percent > 1f) {
//            ObjectAnimator animatorY = ObjectAnimator.ofFloat(
//                    sview, "scaleX", percent );
//
//            animatorY.getValues()
//            animatorY.start();
//        }


//        if (percent > 1f) {
//
//
//
//            ObjectAnimator animatorX = ObjectAnimator.ofFloat(
//                    sview, "scaleX", height);
//
//            animatorX.start();
//
//            animSet = new AnimatorSet();
//            animSet.play(animatorX);
//            animSet.setDuration(500);
//            animSet.start();
//
//
//
//
//            ObjectAnimator animatory = ObjectAnimator.ofFloat(
//                    sview, "TranslationY", s, offset);
//
//            s = height - sview.getHeight();
//            animatory.start();
//
//        }


//        if (mState == RefreshState.PullDownToRefresh || mState == RefreshState.RefreshReleased) {
//
//            ObjectAnimator animatory = ObjectAnimator.ofFloat(
//                    sview, "TranslationY", s, offset);
//            s = height - sview.getHeight();
//            animatory.start();
//
//
//        }


        if (mState == RefreshState.Refreshing) {

//
//            ObjectAnimator animatorY = ObjectAnimator.ofFloat(
//                    imageView, "scaleY", percent);
//
//            animSet = new AnimatorSet();
//            animSet.play(animatorX).with(animatorY);
//            animSet.setDuration(500);
//            animSet.start();
        }


        if (mState == RefreshState.RefreshReleased) {

        }

    }

    public float sY = 0f;
    public float eY = 0f;


    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

        mState = newState;
        switch (newState) {
            case None:
                // Log.i("PPWHeader", "None");
                s = 0;
                break;
            case PullDownToRefresh:

                //  Log.i("PPWHeader", "PullDownToRefresh");
                // imageView.setImageResource(R.drawable.icon_ppwwz);
                break;
            case PullDownCanceled:
                // Log.i("PPWHeader", "PullDownCanceled");
                break;
            case ReleaseToRefresh:
                //  Log.i("PPWHeader", "ReleaseToRefresh");
                // animSet.cancel();


                break;
            case Refreshing:
                // Log.i("PPWHeader", "Refreshing");
                // imageView.setImageResource(R.drawable.icon_ppwwz);
                break;
            case RefreshFinish:
                // Log.i("PPWHeader", "RefreshFinish");
                break;
        }
    }


}
