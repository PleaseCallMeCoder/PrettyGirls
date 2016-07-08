package coder.prettygirls.splash;

import coder.prettygirls.BasePresenter;
import coder.prettygirls.BaseView;

/**
 * Created by oracleen on 2016/6/28.
 */
public interface SplashContract {

    interface View extends BaseView<Presenter> {
        void showGirl(String girlUrl);

        void showGirl();
    }

    interface Presenter extends BasePresenter {

    }

}
