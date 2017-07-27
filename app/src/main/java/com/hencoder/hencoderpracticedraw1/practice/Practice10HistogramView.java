package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {
  Paint mLinePaint;
  Paint mRetanglePaint;
  Paint mTextPaint;

  Path mLinePath;

  String[] mTitles = new String[] {
      "Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"
  };

  public Practice10HistogramView(Context context) {
    this(context, null);
  }

  public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mLinePaint = new Paint();
    mRetanglePaint = new Paint();
    mTextPaint = new Paint();
    mLinePath = new Path();

    mLinePaint.setColor(Color.WHITE);
    mLinePaint.setStrokeWidth(3);
    mLinePaint.setStyle(Paint.Style.STROKE);

    mRetanglePaint.setColor(Color.CYAN);
    mRetanglePaint.setStyle(Paint.Style.FILL);

    mTextPaint.setColor(Color.WHITE);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    //        综合练习
    //        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

    // 绘制坐标
    mLinePath.moveTo(100, 100);
    mLinePath.rLineTo(0, 500);
    mLinePath.rLineTo(500, 0);

    canvas.drawPath(mLinePath, mLinePaint);

    // 绘制文字
    int xLength = 500;
    int colum = mTitles.length + 1;
    int step = xLength / colum;
    for (int i = 0; i < colum - 1; i++) {
      int i1 = 100 + step * (i + 1);
      canvas.drawText(mTitles[i], i1, 620, mTextPaint);
    }

    //绘制柱状图
    for (int i = 0; i < colum-1; i++) {

      int lHeight = 100;

      Rect rect = new Rect();

      rect.left = 100 + step * (i + 1) - 10;
      rect.right = 100 + step * (i + 1) + 10;

      // 当前的相对高度：
      int rh = (lHeight * i) / 8;

      rect.top = 500-rh;
      rect.bottom = 600;

      canvas.drawRect(rect, mRetanglePaint);
    }
  }
}
