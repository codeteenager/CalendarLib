package com.codeteenager.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shuai on 2017/5/3.
 */

public class CalendarView extends LinearLayout {
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView currentMonth;
    private GridView gridView;
    private Calendar curDate = Calendar.getInstance();
    private String displayFormat;

    public void setListener(CalendarViewListener listener) {
        this.listener = listener;
    }

    public CalendarViewListener listener;

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.calendar_view, this);
        btnPrev = (ImageView) findViewById(R.id.btnPrev);
        btnNext = (ImageView) findViewById(R.id.btnNext);
        currentMonth = (TextView) findViewById(R.id.tvDate);
        gridView = (GridView) findViewById(R.id.calendar_grid);
        initEvent();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CalendarView);
        try {
            String format = ta.getString(R.styleable.CalendarView_dateFormat);
            displayFormat = format;
            if (displayFormat == null) {
                displayFormat = "MMM yyyy";
            }
        } finally {
            ta.recycle();
        }
        renderCalendar();
    }

    private void initEvent() {
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                curDate.add(Calendar.MONTH, -1);
                renderCalendar();
            }
        });
        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                curDate.add(Calendar.MONTH, 1);
                renderCalendar();
            }
        });
    }

    private void renderCalendar() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(displayFormat);
        currentMonth.setText(simpleDateFormat.format(curDate.getTime()));
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar) curDate.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int prevDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.add(Calendar.DAY_OF_MONTH, -prevDays);
        int maxCellCount = 6 * 7;
        while (cells.size() < maxCellCount) {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        gridView.setAdapter(new CalendarAdapter(getContext(), cells));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onItemPress((Date) parent.getItemAtPosition(position));
                }
            }
        });
    }

    private class CalendarAdapter extends ArrayAdapter<Date> {
        LayoutInflater layoutInflater;

        public CalendarAdapter(@NonNull Context context, ArrayList<Date> days) {
            super(context, R.layout.calendar_view_item, days);
            layoutInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Date date = getItem(position);
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.calendar_view_item, parent, false);
            }
            int day = date.getDate();
            CalenderDayTextView tvDay = (CalenderDayTextView) convertView.findViewById(R.id.tv_day);
            tvDay.setText(String.valueOf(day));
            Date now = new Date();
            boolean isTheSameMonth = false;
            if (date.getMonth() == now.getMonth()) {
                isTheSameMonth = true;
            }
            if (isTheSameMonth) {
                tvDay.setTextColor(Color.parseColor("#000000"));
            } else {
                tvDay.setTextColor(Color.parseColor("#666666"));
            }

            if (now.getDate() == date.getDate() && now.getMonth() == date.getMonth() && now.getYear() == date.getYear()) {
                tvDay.setTextColor(Color.parseColor("#ff0000"));
                tvDay.isToday = true;
            }
            return convertView;
        }
    }

    public interface CalendarViewListener {
        void onItemPress(Date date);
    }
}
