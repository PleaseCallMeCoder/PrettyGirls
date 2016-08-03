package coder.prettygirls.home;

import android.util.Log;

import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.data.source.GirlsDataSource;
import coder.prettygirls.data.source.GirlsRepository;

/**
 * Created by oracleen on 2016/6/29.
 */
public class GirlsPresenter implements GirlsContract.Presenter {

    public static final String TAG = "GirlsPresenter";

    private GirlsContract.View mView;
    private GirlsRepository mGirlsRepository;

    public GirlsPresenter(GirlsRepository girlsRepository, GirlsContract.View view) {
        mView = view;
        mGirlsRepository = girlsRepository;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        getGirls(1, 20, true);
    }

    @Override
    public void getGirls(int page, int size, final boolean isRefresh) {
        Log.d(TAG, "getGirls");
        mGirlsRepository.getGirls(page, size, new GirlsDataSource.LoadGirlsCallback() {
            @Override
            public void onGirlsLoaded(GirlsBean girlsBean) {
                if (isRefresh) {
                    mView.refresh(girlsBean.getResults());
                } else {
                    mView.load(girlsBean.getResults());
                }
                mView.showNormal();
            }

            @Override
            public void onDataNotAvailable() {
                if (isRefresh) {
                    mView.showError();
                }
            }
        });
    }

}
