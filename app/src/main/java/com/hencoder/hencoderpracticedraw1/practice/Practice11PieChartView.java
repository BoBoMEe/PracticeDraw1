package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

  private int mTotalWidth;
  private int mTotalHeight;
  private float mRadius;

  private Paint mPaint, mLinePaint, mTextPaint;

  private RectF mRectF;
  private RectF mRectFTouch;

  private List<PieData> mPieDatas = new ArrayList<>();

  private float mTotalValue;

  private float[] mStartAngle;
  private int position = -1;

  private int[] mColors = {
      0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
      0xFFE6B800, 0xFF7CFC00
  };

  public Practice11PieChartView(Context context) {
    this(context, null);
  }

  public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mRectF = new RectF();
    mRectFTouch = new RectF();

    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setAntiAlias(true);

    mLinePaint = new Paint();
    mLinePaint.setAntiAlias(true);
    mLinePaint.setStyle(Paint.Style.FILL);
    mLinePaint.setStrokeWidth(2);
    mLinePaint.setColor(Color.WHITE);

    mTextPaint = new Paint();
    mTextPaint.setAntiAlias(true);
    mTextPaint.setStyle(Paint.Style.FILL);
    mTextPaint.setTextSize(24);
    mTextPaint.setColor(Color.WHITE);

    List<PieData> dataEntities = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      PieData entity = new PieData("name" + i, i + 1, mColors[i]);
      dataEntities.add(entity);
    }
    setDataList(dataEntities);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mTotalWidth = w - getPaddingLeft() - getPaddingRight();
    mTotalHeight = h - getPaddingTop() - getPaddingBottom();

    mRadius = (float) (Math.min(mTotalWidth, mTotalHeight) / 2 * 0.7);

    mRectF.left = -mRadius;
    mRectF.top = -mRadius;
    mRectF.right = mRadius;
    mRectF.bottom = mRadius;

    mRectFTouch.left = -mRadius - 16;
    mRectFTouch.top = -mRadius - 16;
    mRectFTouch.right = mRadius + 16;
    mRectFTouch.bottom = mRadius + 16;
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    //        综合练习
    //        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

    if (null == mPieDatas || mPieDatas.size() == 0) return;

    canvas.translate(mTotalWidth / 2, mTotalHeight / 2);

    drawPiePath(canvas);
  }

  private void drawPiePath(Canvas canvas) {
    float startAngle = 0;
    for (int i = 0; i < mPieDatas.size(); i++) {
      PieData pieData = mPieDatas.get(i);
      float sweepAngle = pieData.getValue() / mTotalValue * 360 - 1;
      mPaint.setColor(pieData.getColor());
      if (position - 1 == i) {
        canvas.drawArc(mRectFTouch, startAngle, sweepAngle, true, mPaint);
      } else {
        canvas.drawArc(mRectF, startAngle, sweepAngle, true, mPaint);
      }
      float pxs = (float) (mRadius * Math.cos(Math.toRadians(startAngle + sweepAngle / 2)));
      float pys = (float) (mRadius * Math.sin(Math.toRadians(startAngle + sweepAngle / 2)));
      float pxt = (float) ((mRadius + 30) * Math.cos(Math.toRadians(startAngle + sweepAngle / 2)));
      float pyt = (float) ((mRadius + 30) * Math.sin(Math.toRadians(startAngle + sweepAngle / 2)));
      mStartAngle[i] = startAngle;
      startAngle += sweepAngle + 1;

      canvas.drawLine(pxs, pys, pxt, pyt, mLinePaint);

      float res = mPieDatas.get(i).getValue() / mTotalValue * 100;
      double resToRound = CalculateUtil.round(res, 2);

      if (startAngle % 360.0 >= 90.0 && startAngle % 360.0 <= 270.0) {//2 3 象限
        canvas.drawLine(pxt, pyt, pxt - 30, pyt, mLinePaint);
        canvas.drawText(resToRound + "%", pxt - mTextPaint.measureText(resToRound + "%") - 30, pyt,
            mTextPaint);
      } else {
        canvas.drawLine(pxt, pyt, pxt + 30, pyt, mLinePaint);
        canvas.drawText(resToRound + "%", pxt + 30, pyt, mTextPaint);
      }
    }
  }

  public void setDataList(List<PieData> dataList) {
    this.mPieDatas = dataList;
    mTotalValue = 0;
    for (PieData pieData : mPieDatas) {
      mTotalValue += pieData.getValue();
    }
    mStartAngle = new float[dataList.size()];
    invalidate();
  }
}
