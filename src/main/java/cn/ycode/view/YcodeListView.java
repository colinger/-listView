package cn.ycode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListAdapter;

import com.etsy.android.grid.StaggeredGridView;

import java.util.List;

/**
 * 云极客ListView
 * <p/>
 * Created by colin on 8/30/15.
 */
public class YcodeListView extends StaggeredGridView {

    private boolean isLoading;
    private boolean hasMoreItems;
    private OnLoadNextListener loadNextListener;
    private OnScrollListener onScrollListener;

    public YcodeListView(Context context) {
        super(context);
        init();
    }

    public YcodeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public YcodeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     *
     */
    private void init() {
        super.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (YcodeListView.this.onScrollListener != null) {
                    YcodeListView.this.onScrollListener.onScrollStateChanged(view, scrollState);
                }
            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (YcodeListView.this.onScrollListener != null) {
                    YcodeListView.this.onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }
                int lastVisibleItem = firstVisibleItem + visibleItemCount;
                if (!YcodeListView.this.isLoading && YcodeListView.this.hasMoreItems && lastVisibleItem == totalItemCount && YcodeListView.this.loadNextListener != null) {
                    YcodeListView.this.isLoading = true;
                    YcodeListView.this.loadNextListener.loadNextPage();
                }

            }
        });
    }

    /**
     * @param loadNextListener
     */
    public void setLoadNextListener(OnLoadNextListener loadNextListener) {
        this.loadNextListener = loadNextListener;
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }


    public void setHasMoreItems(boolean hasMoreItems) {
        this.hasMoreItems = hasMoreItems;
//        if(!this.hasMoreItems) {
//            this.removeFooterView(this.loadingView);
//        } else if(this.findViewById(id.loading_view) == null) {
//            this.addFooterView(this.loadingView);
//            ListAdapter adapter = ((HeaderViewListAdapter)this.getAdapter()).getWrappedAdapter();
//            this.setAdapter(adapter);
//        }
    }

    /**
     * 加载是否完成
     *
     * @param hasMoreItems
     * @param newItems
     */
    public void onFinishLoading(boolean hasMoreItems, List<? extends Object> newItems) {
        this.setHasMoreItems(hasMoreItems);
        this.setIsLoading(false);
        if (newItems != null && newItems.size() > 0) {
//            ListAdapter adapter = ((HeaderViewListAdapter) this.getAdapter()).getWrappedAdapter();
            ListAdapter adapter = this.getAdapter();
            if (adapter instanceof YcodePagingBaseAdapter) {
                ((YcodePagingBaseAdapter) adapter).addMoreItems(newItems);
            }
        }

    }

    public boolean hasMoreItems() {
        return this.hasMoreItems;
    }

    @Override
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**
     * 下一页接口
     */
    public interface OnLoadNextListener {
        /**
         * 加载下一页
         */
        void loadNextPage();
    }
}
