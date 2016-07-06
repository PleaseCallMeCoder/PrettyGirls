package coder.prettygirls.girl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import coder.mylibrary.base.BaseFragment;
import coder.prettygirls.R;
import coder.prettygirls.data.bean.GirlsBean;
import coder.prettygirls.util.BitmapUtil;
import coder.prettygirls.widget.PinchImageView;

/**
 * Created by oracleen on 2016/7/4.
 */
public class GirlFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private GirlAdapter mAdapter;

    private ArrayList<GirlsBean.ResultsEntity> datas;
    private int current;

    private Unbinder unbinder;

    private OnGirlChange mListener;

    public interface OnGirlChange {
        void change(int color);
    }

    public static GirlFragment newInstance(ArrayList<Parcelable> datas, int current) {
        Bundle bundle = new Bundle();
        GirlFragment fragment = new GirlFragment();
        bundle.putParcelableArrayList("girls", datas);
        bundle.putInt("current", current);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girl;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnGirlChange) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            datas = bundle.getParcelableArrayList("girls");
            current = bundle.getInt("current");
        }

        mAdapter = new GirlAdapter(mActivity, datas);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(current);
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getColor();
    }

    /**
     * 根据图片获得主题色
     */
    private void getColor() {
        PinchImageView imageView = getCurrentImageView();
        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
//                Palette.Swatch vir = palette.getLightMutedSwatch();
                Palette.Swatch vir = palette.getVibrantSwatch();
                if (vir == null)
                    return;
                mListener.change(vir.getRgb());
            }
        });
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private PinchImageView getCurrentImageView() {
        View currentItem = mAdapter.getPrimaryItem();
        if (currentItem == null) {
            return null;
        }
        PinchImageView imageView = (PinchImageView) currentItem.findViewById(R.id.img);
        if (imageView == null) {
            return null;
        }
        return imageView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
