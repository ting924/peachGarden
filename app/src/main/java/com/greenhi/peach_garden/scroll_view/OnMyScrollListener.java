package com.greenhi.peach_garden.scroll_view;

public interface OnMyScrollListener {
    int SCROLL_STATE_FLING = 2;   //手指滑动后松开，自动滑动
    int SCROLL_STATE_IDLE = 0;   //不滑动
    int SCROLL_STATE_TOUCH_SCROLL = 1;   //手指按着屏幕滑动

    void onScrollStateChanged(MyScrollView view, int state);

    void onScroll(MyScrollView view, int y); //滑动距离

    void onScrollToTop();   //滑到顶部

    void onScrollToBottom(); //滑到底部
}