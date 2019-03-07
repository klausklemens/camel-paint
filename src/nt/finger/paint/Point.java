package nt.finger.paint;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Point {
	final float x, y;
	final int col;
	final int width;
	
	Point(final float x, final float y, final int col, final int width) {
		this.x = x;
		this.y = y;
		this.col = col;
		this.width = width;
	}
	
	public void draw(final Canvas canvas, final Paint paint) {
		paint.setColor(col);
		canvas.drawCircle(x, y, (float) width/2, paint);
	}
	
	@Override
	public String toString() {
		return x + ", " + y + ", " + col;
	}
}
