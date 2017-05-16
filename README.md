# 日历视图
该视图通过继承LinearLayout，然后组合一些基本控件组合而成。使用很简单，只需要引用下列代码就可以：<br/>
`<com.codeteenager.library.CalendarView
        android:id="@+id/cv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"></com.codeteenager.library.CalendarView>`
是不是很简单呢？界面如下：<br/>
![](http://ww3.sinaimg.cn/large/006HJ39wgy1ffnlbermccj30bg0awwew.jpg)
在activity可以为其设置监听事件然后获取点击的日期，代码如下：<br/>
`CalendarView calendarView= (CalendarView) findViewById(R.id.cv);
        calendarView.setListener(new CalendarView.CalendarViewListener() {
            @Override
            public void onItemPress(Date date) {

            }
        });`
