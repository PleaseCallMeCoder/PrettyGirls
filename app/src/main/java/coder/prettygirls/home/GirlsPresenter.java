package coder.prettygirls.home;

import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.data.source.GirlsDataSource;
import coder.prettygirls.data.source.GirlsResponsitory;

/**
 * Created by oracleen on 2016/6/29.
 */
public class GirlsPresenter implements GirlsContract.Presenter {

    private GirlsContract.View mView;
    private GirlsResponsitory mGirlsResponsitory;

    private boolean isFirst = true;

    public GirlsPresenter(GirlsContract.View view) {
        mView = view;
        mGirlsResponsitory = new GirlsResponsitory();
    }

    @Override
    public void start() {

    }

    @Override
    public void getGirls(int page, int size) {
        mGirlsResponsitory.getGirls(page, size, new GirlsDataSource.LoadGirlsCallback() {
            @Override
            public void onGirlsLoaded(GirlsBean girlsBean) {
                mView.showGirls(isFirst, girlsBean.getResults());
                if (isFirst)
                    isFirst = !isFirst;
            }

            @Override
            public void onDataNotAvailable() {
                if (isFirst) {
                    mView.showNull();
                }
            }
        });
    }

    @Override
    public void refresh(int size) {
        mGirlsResponsitory.getGirls(1, 20, new GirlsDataSource.LoadGirlsCallback() {
            @Override
            public void onGirlsLoaded(GirlsBean girlsBean) {
                mView.stopRefresh(true, girlsBean.getResults());
            }

            @Override
            public void onDataNotAvailable() {
                mView.stopRefresh(false, null);
            }
        });
    }
}
