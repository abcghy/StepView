package me.daolema.sakura.stepviewlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sakura on 2016/11/28.
 */

public class StepView extends View {

    public enum State {
        POSTED,
        RECEIVED,
//        CANCELLING,
        CANCELLED,
        DONE
    }

    private State currentState = State.POSTED;

    public void setState(State state) {
        currentState = state;
        invalidate();
    }

    Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setColor(Color.parseColor("#999999"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(dp2px(2.0f));
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(dp2px(10));
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        setMeasuredDimension(widthSize, heightSize);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2 + dp2px(9));

        mPaint.setStrokeWidth(dp2px(2));
        mPaint.setColor(Color.parseColor("#EFEFEF"));
        canvas.drawLine(dp2px(-100), 0, dp2px(100), 0, mPaint);

        switch (currentState) {
            case POSTED: {
                drawColorNode(canvas, "已发单", -100, Color.parseColor("#2196F3"));
                drawColorNode(canvas, "已接单", 0, Color.parseColor("#999999"));
                drawColorNode(canvas, "已完成", 100, Color.parseColor("#999999"));
                break;
            }
            case RECEIVED: {
                drawColorNode(canvas, "已发单", -100, Color.parseColor("#999999"));
                drawColorNode(canvas, "已接单", 0, Color.parseColor("#2196F3"));
                drawColorNode(canvas, "已完成", 100, Color.parseColor("#999999"));
                break;
            }
//            case CANCELLING: {
//                drawColorNode(canvas, "已发单", -100, Color.parseColor("#999999"));
//                drawColorNode(canvas, "已接单", -33, Color.parseColor("#999999"));
//                drawColorNode(canvas, "取消中", 33, Color.parseColor("#FF9900"));
//                drawColorNode(canvas, "已取消", 100, Color.parseColor("#999999"));
//                break;
//            }
            case CANCELLED: {
                drawColorNode(canvas, "已发单", -100, Color.parseColor("#999999"));
                drawColorNode(canvas, "已接单", 0, Color.parseColor("#999999"));
//                drawColorNode(canvas, "取消中", 33, Color.parseColor("#999999"));
                drawColorNode(canvas, "已取消", 100, Color.parseColor("#FF6B6B"));
                break;
            }
            case DONE: {
                drawColorNode(canvas, "已发单", -100, Color.parseColor("#999999"));
                drawColorNode(canvas, "已接单", 0, Color.parseColor("#999999"));
                drawColorNode(canvas, "已完成", 100, Color.parseColor("#00D51E"));
                break;
            }
            default: {
                break;
            }
        }
    }

    private void drawColorNode(Canvas canvas, String title, int position, int color) {
        mPaint.setColor(color);
        canvas.drawText(title, dp2px(position), dp2px(-13), mPaint);
        RectF rectF = new RectF(dp2px(position - 4), dp2px(-4), dp2px(position + 4), dp2px(4));
        canvas.drawOval(rectF, mPaint);

        if (color != Color.parseColor("#999999")) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(dp2px(1));

            RectF rectF1 = new RectF(dp2px(position - 6), dp2px(-6), dp2px(position + 6), dp2px(6));
            canvas.drawOval(rectF1, mPaint);

            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(dp2px(2));
        }
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
