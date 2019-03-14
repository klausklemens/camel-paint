package de.vincent.fingerpaint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;

public class Point {
    private final float x;
    private final float y;
    private final int col;
    private final int width;

    private final Point neighbour;

    Point(final float x, final float y, final int col, final int width, @Nullable final Point neighbour) {
        this.x = x;
        this.y = y;
        this.col = col;
        this.width = width;
        this.neighbour = neighbour;
    }

    public void draw(final Canvas canvas, final Paint paint) {
        paint.setColor(col);
        paint.setStrokeWidth(width);
        if (neighbour != null) {
            canvas.drawLine(x, y, neighbour.x, neighbour.y, paint);
        } else {
            canvas.drawCircle(x, y, (float) width / 2, paint);
        }
    }

    @Override
    public String toString() {
        if (neighbour != null) {
            return x + ", " + y + ", " + col + "; N[" + neighbour.toString() + "]";
        } else {
            return x + ", " + y + ", " + col;
        }
    }
}
