package com.yjhh.ppwbusiness.views.cui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.Nullable;

import static com.yjhh.ppwbusiness.views.cui.WaterDropView.BACK_ANIM_DURATION;

public class IImageView extends ImageView {
    public IImageView(Context context) {
        super(context);
    }

    public IImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void aa(float s, float e) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(s, e).setDuration(BACK_ANIM_DURATION);
        valueAnimator.start();
    }

}
