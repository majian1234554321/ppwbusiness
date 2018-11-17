package com.yjhh.ppwbusiness.views.product.twoview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import com.yjhh.ppwbusiness.R;

import java.util.List;

public class MeiTuanFoodView extends LinearLayout {


    private RecyclerView leftView;
    public RecyclerView rightView;
    private BaseViewAdapter leftAdapter;
    public BaseViewAdapter rightAdapter;
    private List<LeftBean> leftData;
    private List<RightBean> rightData;

    public MeiTuanFoodView(Context context) {
        this(context, null);
    }

    public MeiTuanFoodView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public Context context;

    public MeiTuanFoodView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setOrientation(HORIZONTAL);
        inflate(context, R.layout.layout_meituan, this);
        leftView = (RecyclerView) findViewById(R.id.left_view);
        rightView = (RecyclerView) findViewById(R.id.right_view);
        leftView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rightView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MeiTuanFoodView, defStyleAttr, 0);
        int leftSum = ta.getInt(R.styleable.MeiTuanFoodView_leftSum, 1);
        int rightSum = ta.getInt(R.styleable.MeiTuanFoodView_rightSum, 2);
        int itemTopHeight = ta.getDimensionPixelSize(R.styleable.MeiTuanFoodView_topItemHeight, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics()));
        ta.recycle();

        LayoutParams leftLp = (LayoutParams) leftView.getLayoutParams();
        LayoutParams rightLp = (LayoutParams) rightView.getLayoutParams();
        if (leftSum != leftLp.weight) {
            leftLp.weight = leftSum;
            leftView.setLayoutParams(leftLp);
        }
        if (rightSum != rightLp.weight) {
            rightLp.weight = rightSum;
            rightView.setLayoutParams(rightLp);
        }


        rightView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                //Log.e("dy:","dy:"+dy);
                Log.e("position:", "position:" + position);
                int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                Log.e("lastPosition:", "lastPosition:" + lastPosition);
                if (dy > 0) {
                    if (position + 1 < data.getRightData().size()) {
                        if (position == 0) {
                            leftAdapter.setSelectPosition(0);
                        } else {

                            if (isTopNotEqualsNext(position)) {

                                leftAdapter.setSelectPosition(rightBoundLeftPosition(position));
                                leftView.scrollToPosition(rightBoundLeftPosition(position));
                                Log.e("rightBoundLeftPosition:", "rightBoundLeftPosition:" + rightBoundLeftPosition(position));
                                // ((LinearLayoutManager) leftView.getLayoutManager()).smoothScrollToPosition(leftView,null,data.rightBoundLeftPosition(data.getRightData().get(position),data.getLeftData()));
                            }
                        }
                    }

                } else {
                    if (position + 1 < data.getRightData().size()) {
                        if (position == 0) {
                            leftAdapter.setSelectPosition(0);
                        } else {
                            if (isTopNotEqualsBefore(position)) {
                                leftAdapter.setSelectPosition(rightBoundLeftPosition(position));
                                leftView.scrollToPosition(rightBoundLeftPosition(position));

                                //   ((LinearLayoutManager) leftView.getLayoutManager()).smoothScrollToPosition(leftView,null,data.rightBoundLeftPosition(data.getRightData().get(position),data.getLeftData()));
                            }
                        }
                    }

                }
            }
        });


    }


    public BindData data;

    public void setData(final BindData data) {
        this.data = data;
        rightView.addItemDecoration(new MeiTuanItem(context, data));
        leftAdapter = new BaseViewAdapter(context, data.getLeftData()) {
            @Override
            protected void bind(final BaseHolder holder, final int position) {
                data.bindLeftView(holder, position, data.getLeftData().get(position));
                if (position == getSelectPosition()) {
                    data.bindSelectStatus(holder, position, data.getLeftData().get(position));
                } else {
                    data.bindDefaultStatus(holder, position, data.getLeftData().get(position));
                }
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setSelectPosition(position);
                        ((LinearLayoutManager) rightView.getLayoutManager()).scrollToPositionWithOffset(leftBoundRightPosition(position), 0);
                        //rightView.scrollToPosition(leftBoundRightPosition(position));
                        //((LinearLayoutManager) rightView.getLayoutManager()).smoothScrollToPosition(rightView,null,position);
                    }
                });

            }

            @Override
            public int getLayoutId() {
                return data.getLeftLayoutId();
            }
        };
        leftView.setAdapter(leftAdapter);
        leftView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (lastPosition == data.getLeftData().size() - 1) {
                    leftView.smoothScrollBy(50, 50);
                }
            }
        });
        rightAdapter = new BaseViewAdapter(context, data.getRightData()) {
            @Override
            protected void bind(final BaseHolder holder, final int position) {
                data.bindRightView(holder, position, data.getRightData().get(position));
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        data.rightItemClickListener(holder, position, data.getRightData().get(position));
                    }
                });

               // holder.itemView.findViewById(R.id.tv_right_dish_add);


            }

            @Override
            public int getLayoutId() {
                return data.getRightLayoutId();
            }
        };
        rightView.setAdapter(rightAdapter);

    }


    public int leftBoundRightPosition(int position) {
        rightData = data.getRightData();
        for (int i = 0; i < rightData.size(); i++) {
            if (data.getRightData().get(i) instanceof BaseMeiTuanBean && data.getLeftData().get(position) instanceof BaseMeiTuanBean) {
                if (rightData.get(i).tagStr().equals(((BaseMeiTuanBean) data.getLeftData().get(position)).tagStr())) {
                    return i;
                } else {
                    continue;
                }
            } else {
                continue;
            }

        }
        return 0;
    }


    public boolean isTopNotEqualsBefore(int position) {
        if (data.getRightData().get(position) instanceof BaseMeiTuanBean && data.getRightData().get(position - 1) instanceof BaseMeiTuanBean) {
            return ((BaseMeiTuanBean) data.getRightData().get(position)).tagStr().equals(((BaseMeiTuanBean) data.getRightData().get(position - 1)).tagStr());
        }
        return true;
    }

    public boolean isTopNotEqualsNext(int position) {
        if (data.getRightData().get(position) instanceof BaseMeiTuanBean && data.getRightData().get(position + 1) instanceof BaseMeiTuanBean) {
            return ((BaseMeiTuanBean) data.getRightData().get(position)).tagStr().equals(((BaseMeiTuanBean) data.getRightData().get(position + 1)).tagStr());
        }
        return true;
    }

    public int rightBoundLeftPosition(int position) {
        leftData = data.getLeftData();
        for (int i = 0; i < leftData.size(); i++) {
            if (data.getRightData().get(position) instanceof BaseMeiTuanBean && data.getLeftData().get(i) instanceof BaseMeiTuanBean) {
                if (((BaseMeiTuanBean) data.getRightData().get(position)).tagStr().equals(leftData.get(i).tagStr())) {
                    return i;
                } else {
                    continue;
                }
            } else {
                continue;
            }

        }
        return 0;
    }





}
