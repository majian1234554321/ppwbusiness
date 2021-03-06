package com.yjhh.ppwbusiness.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils;
import io.reactivex.disposables.CompositeDisposable;
import me.jessyan.autosize.internal.CustomAdapt;
import me.yokeyword.fragmentation.SupportFragment;

import java.util.List;

public abstract class BaseFragment extends SupportFragment implements CustomAdapt {


    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName()); //统计页面("MainScreen"为页面名称，可自定义)
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }

    @Override
    public boolean isBaseOnWidth() {
        return true;
    }

    @Override
    public float getSizeInDp() {
        return 375;
    }


    public BaseFragment() {
    }


    public void loginOut() {


        SharedPreferencesUtils.setParam(context, "mobile", "");
        SharedPreferencesUtils.setParam(context, "nickName", "");
        SharedPreferencesUtils.setParam(context, "sessionId", "");
        SharedPreferencesUtils.setParam(context, "type", "");

        removeCookie(mActivity);


    }


    private void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }


    public CompositeDisposable compositeDisposable = new CompositeDisposable();


    protected Activity mActivity;
    public Context context;


    /**
     * 标记已加载完成，保证懒加载只能加载一次
     */
    private boolean hasLoaded = true;

    public boolean isViewCreated;

    public boolean isUIVisible;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        this.context = context;
    }


    View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null)
            mRootView = inflater.inflate(getLayoutRes(), container, false);

        return mRootView;
    }

    protected abstract int getLayoutRes();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

        isViewCreated = true;
        lazyLoad();
    }

    private void lazyLoad() {


        if (isViewCreated && isUIVisible && hasLoaded) {
            loadData();
            isUIVisible = false;
            isViewCreated = false;
            hasLoaded = false;
        }
    }


    protected void loadData() {

    }

    ;

    protected void initView() {

    }

    ;

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();

        isViewCreated = false;


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();

        } else {
            isUIVisible = false;
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }


}
