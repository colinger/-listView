YlistView
==============

YlistView 主是对StaggeredGridView对行扩展，简化分页功能的实现


使用方法 - Library Module
============

1. Clone the git repo
2. Import the "YlistView" module into your Android-gradle project.
3. Add "YlistView" module in your settings.gradle
4. DONE



如何使用
================

创建Adapter(extends YcodePagingBaseAdapter<T>) 然后将之增加到 cn.ycode.view.YcodeListView.<br>
需要实现 OnLoadNextListener 接口的 loadNextPage() 方法. 如:<br>
``` java
listView.setHasMoreItems(true);
listView.setLoadNextListener(new YcodeListView.OnLoadNextListener() {
     @Override
     public void loadNextPage() {
           loadData(mPage);
     }
});
```
当数据加载完成后使用 onFinishLoading(boolean hasMoreItems, List newItems) 更新listView.
``` java
listView.onFinishLoading(true, newItems);
```
在你的布局文件加入:

```xml
<cn.ycode.view.YcodeListView  xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/list_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:column_count="1">
</cn.ycode.view.YcodeListView>
```
根据自身需求修改app:column_count="1"其中"1"表示一列.