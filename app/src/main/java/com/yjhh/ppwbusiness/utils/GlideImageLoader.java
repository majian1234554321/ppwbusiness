package com.yjhh.ppwbusiness.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.yjhh.ppwbusiness.R;
import com.yjhh.ppwbusiness.views.evaluate.ninegrid.NineGridView;

public class GlideImageLoader implements NineGridView.ImageLoader {
    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {


        RequestOptions options = new RequestOptions()
                // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .placeholder(R.drawable.ic_default_color)
                .error(R.drawable.ic_default_color)
                // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .transform(new CornersTranform(context, 5f));
        // 图片加载库采用Glide框架
        Glide.with(context).load(url)
                 .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);


    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
