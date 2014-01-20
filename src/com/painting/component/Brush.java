package com.painting.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.painting.R;

/**
 * Created by kai.wang on 1/20/14.
 */
public class Brush {

	public int width;
	public int height;
	private Bitmap pointBitmap;

	private Context context;

	private Path pathA, pathB;

	public Brush(Context context) {
		this.context = context;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;

		pointBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.point_white);
		pathA = new Path();
		pathB = new Path();
	}

	public void quadTo(float prvX, float prvY, float x2, float y2) {
		pathA.quadTo(prvX, prvY, (prvX + x2) / 2, (prvY + y2) / 2);
		pathB.quadTo(width - prvX, prvY, (2*width - x2 - prvX)/2, (prvY + y2) / 2);
	}

	public void reset() {
		pathA.reset();
		pathB.reset();
	}

	public void moveTo(float x, float y) {
		pathA.moveTo(x, y);
		pathB.moveTo(width - x, y);
	}

	public void lineTo(float x, float y) {
		pathA.lineTo(x, y);
		pathB.lineTo(width - x, y);
	}

	/**
	 * 画光标（两个点）
	 *
	 * @param canvas
	 */
	public void drawCursor(Canvas canvas, float x, float y) {
		canvas.drawBitmap(pointBitmap, x, y, null);
		canvas.drawBitmap(pointBitmap, width - x, y, null);
	}

	public void drawPath(Canvas canvas, Paint paint) {
		canvas.drawPath(pathA, paint);
		canvas.drawPath(pathB, paint);
	}

}
