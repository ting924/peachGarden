package com.greenhi.peach_garden.activity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
import com.greenhi.peach_garden.R;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance(
                "桃花源","诗词查询",
                R.drawable.yindao1,Color.WHITE,Color.BLACK,Color.BLACK)
        );
        showStatusBar(true);
        addSlide(AppIntroFragment.newInstance(
                "桃花源","发表动态",
                R.drawable.yindao2,Color.WHITE,Color.BLACK,Color.BLACK)
        );
        showStatusBar(true);
        addSlide(AppIntroFragment.newInstance(
                "桃花源","结交诗友",
                R.drawable.yindao3,Color.WHITE,Color.BLACK,Color.BLACK)
        );
        showStatusBar(true);
        addSlide(AppIntroFragment.newInstance(
                "桃花源","文创商品",
                R.drawable.yindao4,Color.WHITE,Color.BLACK,Color.BLACK)
        );
//        showStatusBar(true);
//        addSlide(AppIntroFragment.newInstance(
//                "桃花源","畅玩游戏",
//                R.drawable.yindao5,Color.WHITE,Color.BLACK,Color.BLACK)
//        );
        setIndicatorColor(getColor(R.color.app_color_red),getColor(R.color.black));
        setProgressIndicator();
        setSkipText("跳过");
        setDoneText("完成");
        showStatusBar(true);
    }



    @Override
    public void onSkipPressed(@Nullable Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        //finish直接返回上一层
        Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDonePressed(@Nullable Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        //finish直接返回上一层
        Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
