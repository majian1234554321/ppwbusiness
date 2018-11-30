package com.yjhh.ppwbusiness.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import com.yjhh.ppwbusiness.R;
import com.yjhh.ppwbusiness.views.cui.BottomBar;
import com.yjhh.ppwbusiness.views.cui.BottomBarTab;
import com.yjhh.ppwbusiness.views.main.main1.Main1Fragment;
import com.yjhh.ppwbusiness.views.main.main2.Main2Fragment;

import com.yjhh.ppwbusiness.views.main.main4.Main4Fragment;
import com.yjhh.ppwbusiness.views.writeoff.WriteOffFragment;
import me.yokeyword.fragmentation.SupportFragment;

public class MainFragment extends SupportFragment {
    private static final int REQ_MSG = 10;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    // public static final int THIRD = 2;
    public static final int FOURTH = 2;

    private SupportFragment[] mFragments = new SupportFragment[3];

    public BottomBar mBottomBar;


    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(Main1Fragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = new Main1Fragment();
            mFragments[SECOND] = new Main2Fragment();
            // mFragments[THIRD] = new Main3Fragment();
            mFragments[FOURTH] = new Main4Fragment();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    //  mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(Main2Fragment.class);
            //  mFragments[THIRD] = findChildFragment(Main3Fragment.class);
            mFragments[FOURTH] = findChildFragment(Main4Fragment.class);
        }
    }

    private void initView(View view) {
        mBottomBar = (BottomBar) view.findViewById(R.id.bottomBar);

        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.selector_main1, "店铺"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.selector_main2, "订单"))
                // .addItem(new BottomBarTab(_mActivity, R.drawable.tab_speech_select, "C"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.selector_main3, "设置"));

        // 模拟未读消息
        // mBottomBar.getItem(FIRST).setUnreadCount(9);

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);

                BottomBarTab tab = mBottomBar.getItem(FIRST);
                if (position == FIRST) {

                } else {
                    // tab.setUnreadCount(tab.getUnreadCount() + 1);
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                BottomBarTab tab = mBottomBar.getItem(FIRST);

                if (position == FIRST) {
                    // tab.s(R.drawable.mian1_unselect);
                }


                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                //EventBusActivityScope.getDefault(_mActivity).post(new TabSelectedEvent(position));
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);


        if (requestCode == 10086 && resultCode == RESULT_OK) {


            String content = data.getString("result_string");

           startBrotherFragment(new WriteOffFragment());
            Toast.makeText(_mActivity, content, Toast.LENGTH_SHORT).show();


        }

    }

    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }
}