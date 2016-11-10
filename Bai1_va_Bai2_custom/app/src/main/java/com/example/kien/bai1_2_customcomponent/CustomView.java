package com.example.kien.bai1_2_customcomponent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Kien on 11/10/2016.
 */

public class CustomView extends View {
    private int mCount = 0; // as flag used to change the color of view had 200 pixels in both directions
    private int mCheck; // to detect view had 200 pixels in both directions
    public CustomView(Context context) {
        super(context);
    }
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mCheck = widthMeasureSpec;
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
    private int measureWidth(int measureSpec) {
        return(measure(measureSpec));
    }
    private int measureHeight(int measureSpec) {
        return(measure(measureSpec));
    }
    private int measure(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = 200;
            }
        }
        return(result);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int)event.getX();
            int y = (int)event.getY();
            if (MeasureSpec.getMode(mCheck) == MeasureSpec.AT_MOST) {
                if (x <= 200 && y <= 200) {
                    if (mCount == 0) {
                        this.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                        mCount = 1;
                    } else {
                        this.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        mCount = 0;
                    }
                }
            }
            invalidate();
            return (true);
        } else {
            return (false);
        }
    }
}
