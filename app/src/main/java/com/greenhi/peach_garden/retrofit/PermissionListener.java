package com.greenhi.peach_garden.retrofit;

import java.util.List;

public interface PermissionListener {
    void granted();
    void denied(List<String> deniedList);
}
