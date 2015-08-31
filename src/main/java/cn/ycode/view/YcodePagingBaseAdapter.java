package cn.ycode.view;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin on 8/30/15.
 */
public abstract class YcodePagingBaseAdapter<T> extends BaseAdapter {
    protected List<T> items;

    public YcodePagingBaseAdapter() {
        this.items = new ArrayList();
    }

    public YcodePagingBaseAdapter(List<T> items) {
        this.items = items;
    }

    public void addMoreItems(List<T> newItems) {
        this.items.addAll(newItems);
        this.notifyDataSetChanged();
    }

    public void removeAllItems() {
        this.items.clear();
        this.notifyDataSetChanged();
    }
}