package jp.ac.gifu_u.daikikano.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyView extends View {
    private ArrayList<Integer> array_x;
    private ArrayList<Integer> array_y;
    private ArrayList<Boolean> array_status;

    public MyView(Context context){
        super(context);
        array_x = new ArrayList<>();
        array_y = new ArrayList<>();
        array_status = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        // 背景白塗り
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), p);

        // 線用ペイント
        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        p.setStrokeWidth(5); // 線を見やすく

        // 線の描画
        for (int i = 1; i < array_status.size(); i++) {
            if (array_status.get(i)) {
                int x1 = array_x.get(i - 1);
                int x2 = array_x.get(i);
                int y1 = array_y.get(i - 1);
                int y2 = array_y.get(i);
                canvas.drawLine(x1, y1, x2, y2, p);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                array_x.add(x);
                array_y.add(y);
                array_status.add(false);  // 始点
                break;

            case MotionEvent.ACTION_MOVE:
                array_x.add(x);
                array_y.add(y);
                array_status.add(true);  // 線を引く
                break;

            case MotionEvent.ACTION_UP:
                array_x.add(x);
                array_y.add(y);
                array_status.add(true);  // 最後の線
                break;
        }

        invalidate();  // 再描画
        return true;
    }
}
