package com.shj.draw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

public class MainActivity extends AppCompatActivity {
    class MyView extends View{
        private Paint paint1 = new Paint();
        private Paint paint2 = new Paint();
        private Paint paint3 = new Paint();
        private boolean useCenter = true;
        private  float[] textSizeArray = new float[]{15,18,21,24,27};

        /**
         * Implement this method to handle touch screen motion events.
         * <p>
         * If this method is used to detect click actions, it is recommended that
         * the actions be performed by implementing and calling
         * {@link #performClick()}. This will ensure consistent system behavior,
         * including:
         * <ul>
         * <li>obeying click sound preferences
         * <li>dispatching OnClickListener calls
         * <li>handling  when
         * accessibility features are enabled
         * </ul>
         *
         * @param event The motion event.
         * @return True if the event was handled, false otherwise.
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (useCenter)
            {
                useCenter = false;
                paint1.setColor(Color.RED);
                paint2.setColor(Color.BLACK);
                paint3.setColor(Color.GREEN);

                paint1.setStrokeWidth(6);
                paint2.setStrokeWidth(4);
                paint3.setStrokeWidth(2);
            }
            else
            {
                useCenter = true;
                paint1.setColor(Color.BLACK);
                paint2.setColor(Color.RED);
                paint3.setColor(Color.BLUE);

                paint1.setStrokeWidth(2);
                paint2.setStrokeWidth(4);
                paint3.setStrokeWidth(6);
            }
            for (int i = 0;i<textSizeArray.length/2;i++)
            {
                float textSize = textSizeArray[i];
                textSizeArray[i] =textSizeArray[textSizeArray.length-i-1];
                textSizeArray[textSizeArray.length-i-1] = textSize;
            }
            invalidate();

            return super.onTouchEvent(event);
        }

        public MyView (Context context)
        {
            super(context);
            setBackgroundColor(Color.WHITE);

            paint1.setColor(Color.BLACK);
            paint1.setStrokeWidth(2);
            paint2.setColor(Color.RED);
            paint2.setStrokeWidth(4);
            paint3.setColor(Color.BLUE);
            paint3.setStrokeWidth(6);
        }
       private void drawLineExt(Canvas canvas,float[] pts,Paint paint)
       {
           float[] points = new float[pts.length * 2 -4];
           for (int i = 0,j = 0;i < pts.length;i = i+2)
           {
               points[i++] = pts[i];
               points[j++] = pts[i+1];

               if (i > 1 && i < pts.length - 2)
               {
                   points[i++] = pts[i];
                   points[j++] = pts[i+1];
               }
           }
           canvas.drawLines(points,paint);
       }

        /**
         * Implement this to do your drawing.
         *
         * @param canvas the canvas on which the background will be drawn
         */
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPoint(60,120,paint3);
            canvas.drawPoint(70,130,paint3);
            canvas.drawPoints(new float[]{70,140,75,145,75,160},paint2);

            canvas.drawLine(10,10,300,10,paint1);
            canvas.drawLine(10,30,300,30,paint2);
            canvas.drawLine(10,50,300,50,paint3);

            drawLineExt(canvas,new float[]{10,70,120,70,120,170,10,170,10,70},paint2);
            drawLineExt(canvas,new  float[]{25,85,105,105,155,25,155,25,85},paint3);
            drawLineExt(canvas,new float[]{160,70,230,150,170,155,160,70},paint2);
            paint2.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(260,110,40,paint2);
            super.onDraw(canvas);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}