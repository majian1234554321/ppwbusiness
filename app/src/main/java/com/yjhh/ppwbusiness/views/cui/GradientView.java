package com.yjhh.ppwbusiness.views.cui;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.yjhh.ppwbusiness.R;

public class GradientView extends View {
    public GradientView(Context context) {
        super(context);
    }

    public GradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取View的宽高
        int width = getWidth();
        int height = getHeight();


        Paint paint = new Paint();
        LinearGradient backGradient = new LinearGradient(width, 0, 0, 0,
                new int[]{Color.parseColor("#FFE6BC"),
                        Color.parseColor("#FEE5BA"),
                        Color.parseColor("#D7B781"),
                        Color.parseColor("#D4B47D")
                }, new float[]{0,0.1f,0.66f, 1f}, Shader.TileMode.CLAMP);
        paint.setShader(backGradient);
        canvas.drawRect(0, 0, width, height, paint);
    }


}
