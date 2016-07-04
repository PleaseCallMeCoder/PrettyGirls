package coder.prettygirls.about;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import coder.mylibrary.base.BaseFragment;
import coder.mylibrary.base.GestureActivity;
import coder.prettygirls.R;

/**
 * Created by oracleen on 2016/7/4.
 */
public class AboutActivity extends GestureActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {

    }
}
