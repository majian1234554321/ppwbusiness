package com.yjhh.ppwbusiness.views.product.twoview;
public class LeftBean extends BaseMeiTuanBean {
    private String tag;
    private int id;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

