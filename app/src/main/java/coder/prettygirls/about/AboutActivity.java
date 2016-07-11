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
import coder.prettygirls.app.MyApplication;
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

    @Override
    protected void doFinish() {
        finishActivity();
    }

    private void initView() {
        mAboutToolbar.setTitle("关于我");
        setSupportActionBar(mAboutToolbar);
        mAboutToolbar.setNavigationIcon(R.drawable.ic_back);
        mAboutToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });

        //毛玻璃效果
        Glide.with(this)
                .load(MyApplication.currentGirl)
                .bitmapTransform(new BlurTransformation(this, 15))
                .into(mBackdrop);
    }

    private void finishActivity() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public void onClick(View v) {

    }
}
