package coder.prettygirls.splash;

import coder.prettygirls.app.MyApplication;
import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.data.source.GirlsDataSource;
import coder.prettygirls.data.source.GirlsRepository;

/**
 * Created by oracleen on 2016/6/28.
 */
public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mView;
    private GirlsRepository mResponsitory;

    public SplashPresenter(GirlsRepository girlsRepository, SplashContract.View view) {
        mView = view;
        mResponsitory = girlsRepository;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mResponsitory.getGirl(new GirlsDataSource.LoadGirlsCallback() {

            @Override
            public void onGirlsLoaded(GirlsBean girlsBean) {
                String url = girlsBean.getResults().get(0).getUrl();
                mView.showGirl(url);
                MyApplication.currentGirl = url;
            }

            @Override
            public void onDataNotAvailable() {
                mView.showGirl();
            }

        });
    }
}
