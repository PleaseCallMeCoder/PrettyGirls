package coder.prettygirls.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;

/**
 * Created by oracleen on 2016/6/21.
 */
public class GirlsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.girls_swiperefreshlayout)
    SwipeRefreshLayout mSwiperefreshlayout;
    private Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    public static GirlsFragment getInstance() {
        GirlsFragment mainFragment = new GirlsFragment();
        return mainFragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        mSwiperefreshlayout.setColorSchemeResources(R.color.swipe_color_1, R.color.swipe_color_2, R.color.swipe_color_3, R.color.swipe_color_4);
        mSwiperefreshlayout.setProgressViewEndTarget(true, 200);
        mSwiperefreshlayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

    //handler
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mSwiperefreshlayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
