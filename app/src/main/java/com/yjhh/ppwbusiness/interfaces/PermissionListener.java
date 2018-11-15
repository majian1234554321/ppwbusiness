package com.yjhh.ppwbusiness.interfaces;

import java.util.List;

public interface PermissionListener {
    void onGranted();
    void onDenied(List<String> deniedPermission);
}
