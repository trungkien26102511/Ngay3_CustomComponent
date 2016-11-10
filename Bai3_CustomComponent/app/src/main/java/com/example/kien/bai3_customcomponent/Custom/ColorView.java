package com.example.kien.bai3_customcomponent.Custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.kien.bai3_customcomponent.R;

/**
 * Created by Kien on 11/10/2016.
 */

public class ColorView extends View {
    private int mColorOriginal;
    private int mColorAfterPress;
    private int mDimension;
    private int mCount = 0;//as flag used to change the color of view
    private int mTextSize;
    private static final String TEST = "Test attrs";
    private String mTest = TEST;

    private int mTextColor;
    private Paint mPaint;
    public ColorView(Context context) {
        super(context);
        mPaint = makePaint(mTextColor);
    }
    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCustomAttrs(attrs);
        this.setBackgroundColor(mColorOriginal);
        mPaint = makePaint(mTextColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2 - 100,getHeight()/2);
        canvas.drawText(mTest,0,0 ,mPaint);

    }

    private void initCustomAttrs(AttributeSet attrs) {
        TypedArray attributeArray = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.ColorView);
        mColorOriginal = attributeArray.getInt(R.styleable.ColorView_background_color_original, Color.BLUE);
        mColorAfterPress = attributeArray.getInt(R.styleable.ColorView_background_color_after_press, Color.GRAY);
        mDimension = attributeArray.getInt(R.styleable.ColorView_dimension,500);
        mTextColor = attributeArray.getInt(R.styleable.ColorView_text_color,Color.WHITE);
        mTextSize = attributeArray.getInt(R.styleable.ColorView_text_size,50);
        mTest = attributeArray.getString(R.styleable.ColorView_text_test);
        if (mTest == null) {
            mTest = TEST;
        }
    }

    private Paint makePaint(int color) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(color);
        p.setTextSize(mTextSize);
        return(p);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (mCount == 0) {
                this.setBackgroundColor(mColorOriginal);
                mCount = 1;
            } else {
                this.setBackgroundColor(mColorAfterPress);
                mCount = 0;
            }
            invalidate();
            return (true);
        } else {
            return (false);
        }
    }
}
