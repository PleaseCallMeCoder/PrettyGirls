package coder.mylibrary.base;

import android.content.Intent;
import android.os.Bundle;


/**
 * Created by renlei on 2016/5/23.
 */
public abstract class AppActivity extends BaseActivity {

    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    //获取Intent
    protected void handleIntent(Intent intent) {

    }

    protected void setTheme() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        if (getIntent() == null) {
            handleIntent(getIntent());
        }
        //避免重复添加Fragment
        if (getSupportFragmentManager().getFragments() == null
                || getSupportFragmentManager().getFragments().size() == 0) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }

        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().finishActivity(this);
    }
}
