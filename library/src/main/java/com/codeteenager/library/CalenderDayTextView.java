package com.codeteenager.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by shuai on 2017/5/4.
 */

public class CalenderDayTextView extends TextView {
    public boolean isToday = false;
    private Paint paint = new Paint();

    public CalenderDayTextView(Context context) {
        super(context);
    }

    public CalenderDayTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalenderDayTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ff0000"));
        paint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isToday) {
            canvas.translate(getWidth() / 2, getHeight() / 2);
            canvas.drawCircle(0, 0, getWidth() / 2-5, paint);
        }
    }
}
