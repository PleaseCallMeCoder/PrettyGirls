package coder.mylibrary.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器的基类
 * Created by renlei on 2016/5/23.
 */
public abstract class SimpleBaseAdapter<T> extends BaseAdapter {

    protected Context c;

    protected LayoutInflater layoutInflater;

    protected List<T> datas = new ArrayList<T>();

    public SimpleBaseAdapter(Context c, List<T> datas) {
        this.c = c;
        this.datas = datas;
        layoutInflater = LayoutInflater.from(c);
    }

    /**
     * 刷新适配器数据
     *
     * @param datas
     */
    public void refereshDatas(List<T> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return datas != null ? datas.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public abstract View getView(int i, View view, ViewGroup viewGroup);
}
