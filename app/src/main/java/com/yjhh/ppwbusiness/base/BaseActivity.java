package com.yjhh.ppwbusiness.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Toast;
import com.yjhh.ppwbusiness.interfaces.PermissionListener;
import com.yjhh.ppwbusiness.utils.ActivityCollector;
import com.yjhh.ppwbusiness.utils.BDLocationUtils;
import io.reactivex.disposables.CompositeDisposable;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.internal.CustomAdapt;
import me.yokeyword.fragmentation.SupportActivity;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends SupportActivity implements CustomAdapt {

    public static PermissionListener mListener;
    public static final int REQUEST_CODE = 1;

    public CompositeDisposable compositeDisposable = new CompositeDisposable();
    private BDLocationUtils bdLocationUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //transparentAndCoverStatusBar(this);

        // SystemBarUtil.tintStatusBar(this, ContextCompat.getColor(this,R.color.colorPrimary),0.0f);
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);


        initBaiDuMap();


    }

    public void initBaiDuMap() {

        bdLocationUtils = new BDLocationUtils(this);
        bdLocationUtils.doLocation();//开启定位
        bdLocationUtils.mLocationClient.start();//开始定位

    }


    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 667;
    }


    public void stop(View view) {
        Toast.makeText(getApplicationContext(), "AndroidAutoSize stops working!", Toast.LENGTH_SHORT).show();
        AutoSizeConfig.getInstance().stop(this);
    }

    /**
     * 需要注意的是重新启动 AndroidAutoSize 后, AndroidAutoSize 只是重新开始了对后续还没有启动的 {@link Activity} 进行适配的工作
     * 但对已经启动且在 stop 期间未适配的 {@link Activity} 不会有任何影响
     *
     * @param view {@link View}
     */
    public void restart(View view) {
        Toast.makeText(getApplicationContext(), "AndroidAutoSize continues to work", Toast.LENGTH_SHORT).show();
        AutoSizeConfig.getInstance().restart();
    }



    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        bdLocationUtils.mLocationClient.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        // Disposable
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public static void requestRuntimePermission(String[] permissions,
                                                PermissionListener listener) {
        // 获取栈顶 Activity
        Activity topActivity = ActivityCollector.getTopActivity();
        if (topActivity == null)
            return;
        mListener = listener;
        // 需要请求的权限列表
        List<String> requestPermisssionList = new ArrayList<>();
        // 检查权限  是否已被授权
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(topActivity, permission)
                    != PackageManager.PERMISSION_GRANTED)
                // 未授权时添加该权限
                requestPermisssionList.add(permission);
        }

        if (requestPermisssionList.isEmpty())
            // 所有权限已经被授权过 回调 Listener onGranted 方法 已授权
            listener.onGranted();
        else
            // 进行请求权限操作
            ActivityCompat.requestPermissions(topActivity,
                    requestPermisssionList.toArray(new String[requestPermisssionList.size()]),
                    REQUEST_CODE);

    }

    // 请求权限的回调
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE: {

                List<String> deniedPermissionList = new ArrayList<>();
                // 检查返回授权结果不为空
                if (grantResults.length > 0) {
                    // 判断授权结果
                    for (int i = 0; i < grantResults.length; i++) {
                        int result = grantResults[i];
                        if (result != PackageManager.PERMISSION_GRANTED)
                            // 保存被用户拒绝的权限
                            deniedPermissionList.add(permissions[i]);
                    }
                    if (deniedPermissionList.isEmpty())
                        // 都被授权  回调 Listener onGranted 方法 已授权
                        mListener.onGranted();
                    else
                        // 有权限被拒绝 回调 Listner onDeynied 方法
                        mListener.onDenied(deniedPermissionList);
                }
                break;
            }
            default:
                break;
        }
    }


    /**
     * 使状态栏透明,并覆盖状态栏，对API大于19的显示正常，但小于的界面扩充到状态栏，但状态栏不为透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void transparentAndCoverStatusBar(Activity activity) {
        //FLAG_LAYOUT_NO_LIMITS这个千万别用，带虚拟按键的机型会有特别多问题

//        //FLAG_TRANSLUCENT_STATUS要求API大于19
//        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        //FLAG_LAYOUT_NO_LIMITS对API没有要求
//        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Resources.getSystem().getColor(android.R.color.background_dark));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }


}
