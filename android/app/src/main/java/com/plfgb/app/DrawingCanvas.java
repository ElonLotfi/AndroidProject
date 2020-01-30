package com.plfgb.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.json.JSONException;

import donnees.*;
public class DrawingCanvas extends View {

    private Paint paint;
    private Path path;
    private Bitmap bitmap;
    private Canvas canvas;
    private Points shapePoint;

    public DrawingCanvas(Context context, AttributeSet attributeSet) throws JSONException {
        super(context, attributeSet);
        paint = new Paint(Paint.DITHER_FLAG);
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        this.shapePoint = new Points();


    }

    public Points getShapePoint() {
        return shapePoint;
    }

    public void setShapePoint(Points shapePoint) {
        this.shapePoint = shapePoint;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void redrawWhenOrientationChange(){
        for (int i=0;i<=this.shapePoint.getPoints().size()-1;i++){
            path.lineTo(shapePoint.getPoints().get(i).getX(),shapePoint.getPoints().get(i).getY());
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawPath(path, paint);


    }

    public Bitmap getBitmap() {
        return bitmap;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPos = event.getX();
        float yPos = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos, yPos);
                return true;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos, yPos);
                this.shapePoint.getPoints().add(new Point((int)xPos,(int)yPos));
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(xPos, yPos);
                canvas.drawPath(path, paint);
                path = new Path();
                break;
            default:
                return false;
        }

        invalidate();
        return true;

    }
}