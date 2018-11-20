package com.yjhh.ppwbusiness.views.cui;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by geyifeng on 2017/6/4.
 */

public class GlideLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)

                .into(imageView);
    }

}
