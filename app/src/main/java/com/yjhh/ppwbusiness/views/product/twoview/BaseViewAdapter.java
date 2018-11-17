package com.yjhh.ppwbusiness.views.product.twoview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseViewAdapter<T> extends RecyclerView.Adapter<BaseViewAdapter.BaseHolder> {
    private int selectPosition = -1;

    private Context context;
    private List<T> mData;

    public BaseViewAdapter(Context context, List<T> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseHolder(LayoutInflater.from(context).inflate(getLayoutId(), parent, false));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        bind(holder, position);
    }


    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected abstract void bind(BaseHolder holder, int position);

    public abstract int getLayoutId();

    public static class BaseHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> mViews = new SparseArray<>();
        private View mConvertView;

        public BaseHolder(View itemView) {
            super(itemView);
            mConvertView = itemView;
        }

        public <T extends View> T findViews(int resId) {
            View view = mViews.get(resId);
            if (null == view) {
                view = mConvertView.findViewById(resId);
                mViews.put(resId, view);
            }
            return (T) view;
        }
    }
}