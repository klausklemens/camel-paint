package de.vincent.fingerpaint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DrawView extends View implements View.OnTouchListener {
    Stack<Point> points = new Stack<>();
    Paint paint = new Paint();
    Random gen = new Random();
    private int color;
    private int width;

    @SuppressLint("ClickableViewAccessibility")
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.paint.setAntiAlias(true);

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

        this.setBackgroundColor(0);
        this.setBrushColor(Color.BLACK);
        this.setBrushWidth(10);
    }

    public void clearPoints() {
        points.clear();
        invalidate();
    }

    public void setBrushColor(int color) {
        this.color = color;
    }

    public void setBrushWidth(int width) {
        this.width = width;
    }

    @Override
    public void onDraw(Canvas canvas) {
        // for each point, draw on canvas
        for (Point point : points) {
            point.draw(canvas, paint);
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (color == 0) {
            Log.d("COLOR", "Farbupdate");
            color = Color.rgb(gen.nextInt(0xFF), gen.nextInt(0xFF), gen.nextInt(0xFF));
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            points.push(new Point(event.getX(), event.getY(), color, width, points.peek()));
        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            points.push(new Point(event.getX(), event.getY(), color, width, null));
        } else {
            return false;
        }

        // Redraw
        invalidate();
        return true;
    }
}
