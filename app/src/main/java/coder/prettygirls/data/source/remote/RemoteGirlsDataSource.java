package coder.prettygirls.data.source.remote;

import coder.prettygirls.data.source.GirlsDataSource;
import coder.prettygirls.http.RetrofitUtil;
import coder.prettygirls.http.GirlsService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oracleen on 2016/6/29.
 */
public class RemoteGirlsDataSource implements GirlsDataSource {

    @Override
    public void getGirls(int page, int size, final LoadGirlsCallback callback) {
        RetrofitUtil.getInstance()
                .create(GirlsService.class)
                .getGirls("福利", size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        callback::onGirlsLoaded,
                        throwable -> callback.onDataNotAvailable()
                );
    }

    @Override
    public void getGirl(final LoadGirlsCallback callback) {
        getGirls(1, 1, callback);
    }
}
