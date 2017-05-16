# 日历视图
该视图通过继承LinearLayout，然后组合一些基本控件组合而成。使用很简单，只需要引用下列代码就可以：<br/>
<pre>&lt;com.codeteenager.library.CalendarView
        android:id="@+id/cv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
&lt;/com.codeteenager.library.CalendarView></pre>
是不是很简单呢？界面如下：<br/>
![](http://ww3.sinaimg.cn/large/006HJ39wgy1ffnlbermccj30bg0awwew.jpg)<br/>
在activity可以为其设置监听事件然后获取点击的日期，代码如下：<br/>
<pre>CalendarView calendarView= (CalendarView) findViewById(R.id.cv);
        calendarView.setListener(new CalendarView.CalendarViewListener() {
            @Override
            public void onItemPress(Date date) {
            }
        });</pre>
是不是很简单，喜欢的话点个star吧。
