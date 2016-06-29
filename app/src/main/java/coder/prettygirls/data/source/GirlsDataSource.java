package coder.prettygirls.data.source;

import java.util.List;

import coder.prettygirls.data.bean.GirlsBean;

/**
 * Created by oracleen on 2016/6/29.
 */
public interface GirlsDataSource {

    interface LoadGirlsCallback {

        void onGirlsLoaded(List<GirlsBean> tasks);

        void onDataNotAvailable();
    }

    interface GetGirlCallback {

        void onGirlLoaded(GirlsBean task);

        void onDataNotAvailable();
    }

    void getGirls(LoadGirlsCallback callback);

    void getGirl(GetGirlCallback callback);
}
