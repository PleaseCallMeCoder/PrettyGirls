package coder.prettygirls.home;

import java.util.List;

import coder.prettygirls.BasePresenter;
import coder.prettygirls.BaseView;
import coder.prettygirls.data.bean.GirlsBean;

/**
 * Created by oracleen on 2016/6/29.
 */
public interface GirlsContract {

    interface View extends BaseView {
        void showNull();

        void showGirls(boolean isFirst, List<GirlsBean.ResultsEntity> results);

        void stopRefresh(boolean hasData, List<GirlsBean.ResultsEntity> results);
    }

    interface Presenter extends BasePresenter {
        void getGirls(int page, int size);

        void refresh(int size);
    }
}
