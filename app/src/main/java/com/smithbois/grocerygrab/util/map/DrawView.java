package com.smithbois.grocerygrab.util.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.smithbois.grocerygrab.R;

public class DrawView extends View {
    Path path = new Path();
    Paint fillPaint = new Paint();
    Paint strokePaint = new Paint();

    public void init() {

        strokePaint.setColor(getResources().getColor(R.color.tertiaryBackground));
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(5);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);

        fillPaint.setColor(getResources().getColor(R.color.secondaryBackground));
        fillPaint.setStyle(Paint.Style.FILL);
    }
    public DrawView(Context context){
        super(context);
        init();
    }
    public DrawView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }
    public DrawView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        float[] radii = new float[]{
                25f, 25f,
                25f, 25f,
                25f, 25f,
                25f, 25f
        };
        path.addRoundRect(100f, 200f, 900f, 900f, radii, Path.Direction.CW);
        //canvas.drawPath(path, fillPaint);
        canvas.drawPath(path, strokePaint);
        strokePaint.setStrokeWidth(10);
        path.reset();
        path.addRoundRect(250f, 300f, 350f, 800f, radii, Path.Direction.CW);
        canvas.drawPath(path, fillPaint);
        path.reset();
        path.addRoundRect(450f, 300f, 550f, 800f, radii, Path.Direction.CW);
        canvas.drawPath(path, fillPaint);
        path.reset();
        path.addRoundRect(650f, 300f, 750f, 800f, radii, Path.Direction.CW);
        canvas.drawPath(path, fillPaint);
        path.reset();

        //canvas.drawLine(0, 0, 100, 100, paint);
        //canvas.drawLine(100, 0, 0, 100, paint);
    }
}
