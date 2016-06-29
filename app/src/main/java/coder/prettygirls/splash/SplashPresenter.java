package coder.prettygirls.splash;

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
        mResponsitory.getGirl(new GirlsDataSource.GetGirlCallback() {
            @Override
            public void onGirlLoaded(GirlsBean task) {
                mView.showGirl(task.getResults().get(0).getUrl());
                mView.animaImg();
            }

            @Override
            public void onDataNotAvailable() {
                mView.showGirl();
                mView.animaImg();
            }
        });
    }
}
