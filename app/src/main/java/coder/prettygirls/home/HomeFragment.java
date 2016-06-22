package coder.prettygirls.home;

import android.os.Bundle;
import android.view.View;

import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;

/**
 * Created by oracleen on 2016/6/21.
 */
public class HomeFragment extends BaseFragment {

    public static HomeFragment getInstance() {
        HomeFragment mainFragment = new HomeFragment();
        return mainFragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
