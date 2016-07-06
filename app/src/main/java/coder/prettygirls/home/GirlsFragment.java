package coder.prettygirls.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

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
import coder.prettygirls.girl.GirlActivity;
import coder.prettygirls.util.LogUtil;

/**
 * Created by oracleen on 2016/6/21.
 */
public class GirlsFragment extends BaseFragment implements GirlsContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    public static final String TAG = "GirlsFragment";

    @BindView(R.id.girls_recycler_view)
    EasyRecyclerView mGirlsRecyclerView;

    private ArrayList<GirlsBean.ResultsEntity> data;
    private GirlsAdapter mAdapter;

    private GirlsPresenter mPresenter;
    private int page = 1;
    private int size = 20;

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

        mPresenter = new GirlsPresenter(this);

        initRecyclerView();

        //初始化数据
        mPresenter.start();
    }

    private void initRecyclerView() {
        data = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mGirlsRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new GirlsAdapter(getContext());

        mGirlsRecyclerView.setAdapterWithProgress(mAdapter);

        mAdapter.setMore(R.layout.load_more_layout, this);
        mAdapter.setNoMore(R.layout.no_more_layout);
        mAdapter.setError(R.layout.error_layout);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mActivity, GirlActivity.class);
                intent.putParcelableArrayListExtra("girls", data);
                intent.putExtra("current", position);
                startActivity(intent);
                mActivity.overridePendingTransition(R.anim.slide_left_out, R.anim.slide_right_in);
            }
        });

        mGirlsRecyclerView.setRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mPresenter.getGirls(1, size, true);
        page = 1;
    }

    @Override
    public void onLoadMore() {
        if (data.size() % 20 == 0) {
            LogUtil.d(TAG, "onloadmore");
            page++;
            mPresenter.getGirls(page, size, false);
        }
    }

    @Override
    public void refresh(List<GirlsBean.ResultsEntity> datas) {
        data.clear();
        data.addAll(datas);
        mAdapter.clear();
        mAdapter.addAll(datas);
    }

    @Override
    public void load(List<GirlsBean.ResultsEntity> datas) {
        data.addAll(datas);
        mAdapter.addAll(datas);
    }

    @Override
    public void showError() {
        mGirlsRecyclerView.showError();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
