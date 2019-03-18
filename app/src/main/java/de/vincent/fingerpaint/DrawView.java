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
import android.widget.Toast;

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
            color = Color.rgb(gen.nextInt(0xFF), gen.nextInt(0xFF), gen.nextInt(0xFF));
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            points.push(new Point(event.getX(), event.getY(), color, width, points.peek()));
        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            points.push(new Point(event.getX(), event.getY(), color, width, null));
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            StringBuilder sb = new StringBuilder();
            for (Point p : points) {
                if (p.neighbour != null) {
                    continue;
                }
                byte pos = (byte) Math.ceil(p.x / (float) (v.getWidth() / 3));
                pos += (3 * Math.floor(p.y / (float) (v.getHeight() / 4)));
                sb.append(pos).append(",");
            }
            if (sb.toString().equals("1,2,3,4,5,6,")) {
                Toast.makeText(getContext(), "Gotcha", Toast.LENGTH_SHORT).show();
            }
            Log.d("MATCH", sb.toString() + "?" + sb.toString().equals("1,2,3,4,5,6,"));
        } else {
            return false;
        }

        // Redraw
        invalidate();
        return true;
    }
}
