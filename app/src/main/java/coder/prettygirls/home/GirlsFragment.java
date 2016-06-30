package coder.prettygirls.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;

/**
 * Created by oracleen on 2016/6/21.
 */
public class GirlsFragment extends BaseFragment implements GirlsContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.girls_list)
    RecyclerView mGirlsList;
    @BindView(R.id.girls_swiperefreshlayout)
    SwipeRefreshLayout mSwiperefreshlayout;

    private MyAdapter mAdapter;
    private StaggeredGridLayoutManager mLayoutManager;

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

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mGirlsList.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mGirlsList.setLayoutManager(mLayoutManager);

        //set divider

        //自动加载
        mGirlsList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems = mLayoutManager.findLastVisibleItemPositions(null);
                int lastitem = Math.max(visibleItems[0], visibleItems[1]);
                int totalItemCount = mLayoutManager.getItemCount();
                Log.d("girsl", "visibleItems =" + visibleItems);
                Log.d("girsl", "lastitem =" + lastitem);
                Log.d("girsl", "adapter.getItemCount() =" + totalItemCount);
                if (dy > 0 && lastitem > totalItemCount - 5 && !false) {
                    Log.d("girsl", "will loadNewFeeds");
                }
            }
        });

        // specify an adapter (see also next example)
        String[] myDataset = {"http://ww3.sinaimg.cn/large/610dc034jw1f5d36vpqyuj20zk0qo7fc.jpg", "http://ww3.sinaimg.cn/large/610dc034jw1f5byokn81tj20dw0hiwfe.jpg", "http://ww2.sinaimg.cn/large/610dc034jw1f5aqgzu2oej20rt15owo7.jpg", "http://ww3.sinaimg.cn/large/610dc034jw1f5d36vpqyuj20zk0qo7fc.jpg", "http://ww3.sinaimg.cn/large/610dc034jw1f5byokn81tj20dw0hiwfe.jpg", "http://ww2.sinaimg.cn/large/610dc034jw1f5aqgzu2oej20rt15owo7.jpg", "http://ww3.sinaimg.cn/large/610dc034jw1f5d36vpqyuj20zk0qo7fc.jpg", "http://ww3.sinaimg.cn/large/610dc034jw1f5byokn81tj20dw0hiwfe.jpg", "http://ww2.sinaimg.cn/large/610dc034jw1f5aqgzu2oej20rt15owo7.jpg"};
        mAdapter = new MyAdapter(mActivity, myDataset);
        mGirlsList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mActivity, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
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
}
