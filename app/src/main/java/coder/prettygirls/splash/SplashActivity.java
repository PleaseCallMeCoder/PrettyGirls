package coder.prettygirls.splash;

import android.view.View;

import coder.mylibrary.base.AppActivity;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;
import coder.prettygirls.data.source.GirlsRepository;

public class SplashActivity extends AppActivity {

    private SplashPresenter mSplashPresenter;

    @Override
    protected BaseFragment getFirstFragment() {
        SplashFragment mSplashFragment = SplashFragment.newInstance();
        initPresenter(new GirlsRepository(), mSplashFragment);
        return mSplashFragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.splash_fragment;
    }

   private void initPresenter(GirlsRepository repository, SplashContract.View view) {
       mSplashPresenter = new SplashPresenter(repository, view);
   }

    @Override
    public void onClick(View v) {

    }
}
