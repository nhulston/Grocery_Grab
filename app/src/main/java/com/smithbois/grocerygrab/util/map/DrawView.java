package com.smithbois.grocerygrab.util.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.smithbois.grocerygrab.R;
import com.smithbois.grocerygrab.demo.DemoProductList;
import com.smithbois.grocerygrab.demo.DemoStoreMap;
import com.smithbois.grocerygrab.util.api.NCRRequests;
import com.smithbois.grocerygrab.util.pathfinding.Driver;
import com.smithbois.grocerygrab.util.pathfinding.Product;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

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
        Product[] productsArray = NCRRequests.getProducts();
        System.out.println("products array: " + productsArray);
        List<Product> productList = Arrays.asList(productsArray);

        List<Point> bestPoints = Driver.optimizeRoute(DemoStoreMap.map(), productList);
        System.out.println("best points: " + bestPoints);

        float startx = 100f;
        float starty = 200f;
        float width = 800f;
        float height = 800f;

        path.reset();
        float[] radii = new float[]{
                25f, 25f,
                25f, 25f,
                0f, 0f,
                0f, 0f
        };
        path.addRoundRect(startx, starty, startx+width, starty+height, radii, Path.Direction.CW);
        //canvas.drawPath(path, fillPaint);
        canvas.drawPath(path, strokePaint);
        strokePaint.setStrokeWidth(10);
        path.reset();
        path.addRoundRect(startx+width*1/5-35, 350f, startx+width*1/5+35, 1000f, radii, Path.Direction.CW);
        canvas.drawPath(path, fillPaint);
        path.reset();
        path.addRoundRect(startx+width*2/5-35, 350f, startx+width*2/5+35, 1000f, radii, Path.Direction.CW);
        canvas.drawPath(path, fillPaint);
        path.reset();
        path.addRoundRect(startx+width*3/5-35, 350f, startx+width*3/5+35, 1000f, radii, Path.Direction.CW);
        canvas.drawPath(path, fillPaint);
        path.reset();
        path.addRoundRect(startx+width*4/5-35, 350f, startx+width*4/5+35, 1000f, radii, Path.Direction.CW);
        canvas.drawPath(path, fillPaint);
        path.reset();

        strokePaint.setColor(Color.RED);
        float sizeAdjustmentFactor = 1.25f;
        for(int i = 0; i < bestPoints.size()-1; i++){
            canvas.drawLine(((float) bestPoints.get(i).y-0) * width/(DemoStoreMap.map()[0].length-1) + startx,
                    ((float) bestPoints.get(i).x-0) * height/(DemoStoreMap.map().length-1) + starty,
                    ((float) bestPoints.get(i+1).y-0) * width/(DemoStoreMap.map()[0].length-1) + startx,
                    ((float) bestPoints.get(i+1).x-0) * height/(DemoStoreMap.map().length-1) + starty,
                    strokePaint
                    );
        }

        fillPaint.setColor(Color.BLUE);
        for(Product p : productList){
            canvas.drawCircle(p.getY() * width/(DemoStoreMap.map()[0].length-1) + startx, p.getX() * height/(DemoStoreMap.map().length-1) + starty, 10, fillPaint);
        }

        //canvas.drawLine(0, 0, 100, 100, paint);
        //canvas.drawLine(100, 0, 0, 100, paint);
    }
}
