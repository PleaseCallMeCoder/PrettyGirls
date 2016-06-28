package coder.prettygirls.splash;

import android.os.Bundle;
import android.view.View;

import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;

/**
 * Created by oracleen on 2016/6/28.
 */
public class SplashFragment extends BaseFragment implements SplashContract.View {

    public static SplashFragment getInstance() {
        SplashFragment splashFragment = new SplashFragment();
        return splashFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {

    }
}
