package com.example.baitap5_c1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class GraphicsView extends View {

    public GraphicsView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = new Rect(40, 40, 400, 200);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(rect, paint);
        invalidate();
    }
}
