package coder.prettygirls.girl;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import butterknife.BindView;
import butterknife.ButterKnife;
import coder.mylibrary.base.AppActivity;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;

/**
 * Created by oracleen on 2016/7/4.
 */
public class GirlActivity extends AppActivity implements GirlFragment.OnGirlChange {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_girl;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.girl_fragment;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return GirlFragment.newInstance(getIntent().getParcelableArrayListExtra("girls"), getIntent().getIntExtra("current", 0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setTitle("MEIZHI");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void change(int color) {
        mToolbar.setBackgroundColor(color);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.setStatusBarColor(color);
            window.setNavigationBarColor(color);
        }
    }
}
