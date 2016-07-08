package coder.prettygirls.about;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import coder.mylibrary.base.BaseFragment;
import coder.mylibrary.base.GestureActivity;
import coder.prettygirls.R;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by oracleen on 2016/7/4.
 */
public class AboutActivity extends GestureActivity {

    @BindView(R.id.backdrop)
    ImageView mBackdrop;
    @BindView(R.id.about_toolbar)
    Toolbar mAboutToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

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

    private void initView() {
        mAboutToolbar.setNavigationIcon(R.drawable.ic_back);
        mAboutToolbar.setTitle("关于我");
        setSupportActionBar(mAboutToolbar);

        Glide.with(this)
                .load(R.drawable.about_backdrop)
                .bitmapTransform(new BlurTransformation(this, 15))
                .into(mBackdrop);
    }

    @Override
    public void onClick(View v) {

    }
}
