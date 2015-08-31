package cn.ycode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by colin on 8/31/15.
 */
public class LoadingView extends LinearLayout{

    public LoadingView(Context context) {
        super(context);
        this.init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    private void init() {
        inflate(this.getContext(), R.layout.loading_view, this);
    }
}
