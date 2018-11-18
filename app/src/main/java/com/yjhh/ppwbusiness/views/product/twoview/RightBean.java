package com.yjhh.ppwbusiness.views.product.twoview;

public class RightBean extends BaseMeiTuanBean {
    private String tag;
    private String  text;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String tagStr() {
        return tag;
    }

    @Override
    public long tagInt() {
        return 0;
    }
}

