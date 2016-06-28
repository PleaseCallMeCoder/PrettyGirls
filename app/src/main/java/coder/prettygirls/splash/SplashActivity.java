package coder.prettygirls.splash;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import coder.mylibrary.base.AppActivity;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;

public class SplashActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return SplashFragment.getInstance();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.splash_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {

    }
}
