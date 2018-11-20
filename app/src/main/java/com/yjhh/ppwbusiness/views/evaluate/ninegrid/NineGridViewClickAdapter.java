package com.yjhh.ppwbusiness.views.evaluate.ninegrid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;


public class NineGridViewClickAdapter extends NineGridViewAdapter {

    private int statusHeight;

    public NineGridViewClickAdapter(Context context, List<String> imageInfo) {
        super(context, imageInfo);
        statusHeight = getStatusHeight(context);
    }

    @Override
    protected void onImageItemClick(Context context, NineGridView nineGridView, int index, List<String> imageInfo) {


//        Intent intent = new Intent(context, ImagePreviewActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfo);
//        bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, index);
//        intent.putExtras(bundle);
//        context.startActivity(intent);
        Toast.makeText(context, "index" + index, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获得状态栏的高度
     */
    public int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
