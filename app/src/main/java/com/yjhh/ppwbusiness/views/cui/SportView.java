package com.yjhh.ppwbusiness.views.cui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.yjhh.ppwbusiness.R;

public class SportView extends View {
    float progress = 100;

    final float radius = 45f;
    RectF arcRectF = new RectF();

    public SportView(Context context) {
        super(context);
    }

    Paint paint = new Paint();


    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SportView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }


    // 创建 getter 方法
    public float getProgress() {
        return progress;
    }

    // 创建 setter 方法
    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;


        paint.setColor(getResources().getColor(R.color.zthj));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15f);

        arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(arcRectF, 135, progress * 3.6f, false, paint);

    }
}
