package com.yjhh.ppwbusiness.views.product.twoview;

import java.util.List;

public abstract class BindData<T,E> {
    public  abstract int getLeftLayoutId();
    public  abstract List<T> getLeftData();
    public  abstract void bindLeftView(BaseViewAdapter.BaseHolder holder, int position, T bean);
    public abstract void bindDefaultStatus(BaseViewAdapter.BaseHolder holder, int position,T bean);
    public abstract void bindSelectStatus(BaseViewAdapter.BaseHolder holder, int position, T bean);
    public   abstract int getRightLayoutId();
    public abstract void bindRightView(BaseViewAdapter.BaseHolder holder, int position, E bean);
    public  abstract List<E> getRightData();
    public abstract void rightItemClickListener(BaseViewAdapter.BaseHolder holder, int position,E bean);

//    public abstract void rightadd();
//    public abstract void rightred();

}
