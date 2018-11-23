package com.yjhh.ppwbusiness.utils;

import android.app.Activity;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import com.yjhh.ppwbusiness.R;
import android.text.style.AbsoluteSizeSpan;

public class TextStyleUtils {

    public static  SpannableString changeTextAa( String values , int start, int end, int size){

        SpannableString spannableString = new  SpannableString(values);
        spannableString.setSpan(new AbsoluteSizeSpan(size, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }


    public static  SpannableString changeTextColor( String values , int start, int end, int Color){

        SpannableString spannableString = new  SpannableString(values);
        spannableString.setSpan(new ForegroundColorSpan(Color), start, start, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }

}
