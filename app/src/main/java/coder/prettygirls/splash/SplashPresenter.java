package coder.prettygirls.splash;

import coder.prettygirls.app.MyApplication;
import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.data.source.GirlsDataSource;
import coder.prettygirls.data.source.GirlsResponsitory;

/**
 * Created by oracleen on 2016/6/28.
 */
public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mView;
    private GirlsResponsitory mResponsitory;

    public SplashPresenter(SplashContract.View view) {
        mView = view;
        mResponsitory = new GirlsResponsitory();
    }

    @Override
    public void start() {
        mResponsitory.getGirl(new GirlsDataSource.LoadGirlsCallback() {

            @Override
            public void onGirlsLoaded(GirlsBean girlsBean) {
                mView.showGirl(girlsBean.getResults().get(0).getUrl());
                MyApplication.currentGirl = girlsBean.getResults().get(0).getUrl();
            }

            @Override
            public void onDataNotAvailable() {
                mView.showGirl();
            }

        });
    }
}
