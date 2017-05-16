# 日历视图
### 该视图通过继承LinearLayout，然后组合一些基本控件组合而成。使用很简单，只需要引用下列代码就可以：
<pre>&lt;com.codeteenager.library.CalendarView
        android:id="@+id/cv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
&lt;/com.codeteenager.library.CalendarView></pre>
### 是不是很简单呢？界面如下：
![](http://ww3.sinaimg.cn/large/006HJ39wgy1ffnlbermccj30bg0awwew.jpg)<br/>
### 在activity可以为其设置监听事件然后获取点击的日期，代码如下：
<pre>CalendarView calendarView= (CalendarView) findViewById(R.id.cv);
        calendarView.setListener(new CalendarView.CalendarViewListener() {
            @Override
            public void onItemPress(Date date) {
            }
        });</pre>
### 那么如何引用这个类库呢？教程如下：
### 将以下代码加入到你的项目根下的build.gradle中
<pre>allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}</pre>
### 然后再添加依赖
<pre>dependencies {
	        compile 'com.github.codeteenager:CalendarLib:v1.1'
	}</pre>
接下来你就可以使用了。
是不是很简单，喜欢的话点个star吧。
