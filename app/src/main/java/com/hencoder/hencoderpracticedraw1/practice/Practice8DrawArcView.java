package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

  Paint mPaint = new Paint();

  public Practice8DrawArcView(Context context) {
    super(context);
  }

  public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    // 练习内容：使用 canvas.drawArc() 方法画弧形和扇形
    mPaint.setStyle(Paint.Style.FILL); // 填充模式
    RectF rectF1 = new RectF(200, 100, 800, 500);
    canvas.drawArc(rectF1, -110, 100, true, mPaint); // 绘制扇形

    RectF rectF2 = new RectF(200, 100, 800, 500);
    canvas.drawArc(rectF2, 20, 140, false, mPaint); // 绘制弧形

    mPaint.setStyle(Paint.Style.STROKE); // 画线模式
    RectF rectF3 = new RectF(200, 100, 800, 500);
    canvas.drawArc(rectF3, 180, 60, false, mPaint); // 绘制不封口的弧形
  }
}
