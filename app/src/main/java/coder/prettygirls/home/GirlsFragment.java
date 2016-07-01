package coder.prettygirls.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;
import coder.prettygirls.data.bean.GirlsBean;

/**
 * Created by oracleen on 2016/6/21.
 */
public class GirlsFragment extends BaseFragment implements GirlsContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "GirlsFragment";

    @BindView(R.id.girls_list)
    RecyclerView mGirlsRecyclerView;
    @BindView(R.id.girls_swiperefreshlayout)
    SwipeRefreshLayout mSwiperefreshlayout;
    @BindView(R.id.network_error_layout)
    ViewStub mNetworkErrorLayout;

    private View networkErrorView;

    private List<GirlsBean.ResultsEntity> datas;
    private AdapterOfGirls mAdapter;
    private StaggeredGridLayoutManager mLayoutManager;

    private GirlsPresenter mPresenter;
    private int page = 0;
    private int size = 10;
    private int latestSize = 0;

    private Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    public static GirlsFragment getInstance() {
        GirlsFragment mainFragment = new GirlsFragment();
        return mainFragment;
    }

    private void showNetError() {
        // not repeated infalte
        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.VISIBLE);
            return;
        }

        networkErrorView = mNetworkErrorLayout.inflate();
    }

    private void showNormal() {
        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        mPresenter = new GirlsPresenter(this);

        //swiperefreshlayout
        mSwiperefreshlayout.setColorSchemeResources(R.color.swipe_color_1, R.color.swipe_color_2, R.color.swipe_color_3, R.color.swipe_color_4);
        mSwiperefreshlayout.setProgressViewEndTarget(true, 200);
        mSwiperefreshlayout.setOnRefreshListener(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mGirlsRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mGirlsRecyclerView.setLayoutManager(mLayoutManager);

        mGirlsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //自动加载
        mGirlsRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems = mLayoutManager.findLastVisibleItemPositions(null);
                int lastitem = Math.max(visibleItems[0], visibleItems[1]);
                int totalItemCount = mLayoutManager.getItemCount();
                if (dy > 0 && lastitem > totalItemCount - 5 && !false) {
                    if (latestSize >= 10) {
                        mPresenter.getGirls(page++, 10);
                        Log.e(TAG, "page:  " + page);
                    } else {
                        Log.e(TAG, "没有数据了");
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getGirls(page++, 10);
        Log.e(TAG, "page:  " + page);
    }

    @Override
    public void showNull() {
        showNetError();
    }

    @Override
    public void showGirls(boolean isFirst, List<GirlsBean.ResultsEntity> results) {
        latestSize = results.size();
        if (isFirst) {
            Log.e(TAG, "size:  " + results.size());
            // specify an adapter
            datas = new ArrayList<>();
            datas.addAll(results);
            mAdapter = new AdapterOfGirls(mActivity, results);
            mGirlsRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new AdapterOfGirls.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(mActivity, "" + position, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            datas.addAll(results);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(size);
        page = 0;
    }

    @Override
    public void stopRefresh(boolean hasData, List<GirlsBean.ResultsEntity> results) {
        mSwiperefreshlayout.setRefreshing(false);
        if (hasData) {
            latestSize = results.size();
            datas.clear();
            datas.addAll(results);
            mAdapter.notifyDataSetChanged();
        }
    }

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
