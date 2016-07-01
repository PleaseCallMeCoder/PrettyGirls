package coder.prettygirls.home;

import coder.prettygirls.BasePresenter;
import coder.prettygirls.BaseView;

/**
 * Created by oracleen on 2016/6/29.
 */
public interface GirlsContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter {
        void getGirls(int page, int size);
    }
}
