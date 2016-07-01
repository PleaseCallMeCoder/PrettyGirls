package coder.prettygirls.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

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
public class GirlsFragment extends BaseFragment implements GirlsContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    public static final String TAG = "GirlsFragment";

    @BindView(R.id.network_error_layout)
    ViewStub mNetworkErrorLayout;
    @BindView(R.id.girls_recycler_view)
    EasyRecyclerView mGirlsRecyclerView;

    private View networkErrorView;

    private List<GirlsBean.ResultsEntity> datas;
    private GirlsAdapter mAdapter;
    private StaggeredGridLayoutManager mLayoutManager;

    private GirlsPresenter mPresenter;
    private int page = 0;
    private int size = 20;
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

        page++;
        mPresenter.getGirls(page, size);
    }

    @Override
    public void showNull() {
        showNetError();
        mGirlsRecyclerView.showError();
    }

    @Override
    public void showGirls(boolean isFirst, List<GirlsBean.ResultsEntity> results) {
        latestSize = results.size();
        if (isFirst) {
            datas = new ArrayList<>();
            datas.addAll(results);
            mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mGirlsRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new GirlsAdapter(mActivity);
            mGirlsRecyclerView.setAdapter(mAdapter);
            mGirlsRecyclerView.setRefreshListener(GirlsFragment.this);
            mAdapter.addAll(results);
            dealWithAdapter(mAdapter);
        } else {
            datas.addAll(results);
            mAdapter.clear();
            mAdapter.addAll(datas);
        }
    }

    private void dealWithAdapter(final RecyclerArrayAdapter<GirlsBean.ResultsEntity> adapter) {
        mGirlsRecyclerView.setAdapterWithProgress(adapter);

        adapter.setMore(R.layout.load_more_layout, GirlsFragment.this);
        adapter.setNoMore(R.layout.no_more_layout);
        adapter.setError(R.layout.error_layout);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(size);
        page = 0;
    }

    @Override
    public void stopRefresh(boolean hasData, List<GirlsBean.ResultsEntity> results) {
        if (hasData) {
            latestSize = results.size();
            mAdapter.clear();
            datas.clear();
            datas.addAll(results);
            mAdapter.addAll(datas);
        }
    }

    @Override
    public void onLoadMore() {
        if (latestSize >= size) {
            page++;
            mPresenter.getGirls(page, size);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
