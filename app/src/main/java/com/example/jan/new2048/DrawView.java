package com.example.jan.new2048;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import Model.AnimatedRectF;

public class DrawView extends View {
    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    int lx = 10;
    int ly = 10;

    int width;
    int height;

    int score = 0;

    int screenWidth = 0;
    int screenHeight = 0;

    Paint animPaint = new Paint();


    AnimatedRectF mCurrentRect = new AnimatedRectF(0, 0, 200, 200);;

    int canvasX, canvasY, canvasWidth, canvasHeight;

    boolean victory = false;

    int mWidth, mHeight;

    boolean init = true;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        screenWidth = w;
        screenHeight = h;

        canvasX = 0;
        canvasY = (screenHeight/2) - (screenWidth/2);
        canvasWidth = screenWidth;
        canvasHeight = (screenHeight/2) + (screenWidth/2);

        width = w / ly;
        height = h / lx;

        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        //mCurrentRect = new AnimatedRectF(0, 0, 200, 200);
    }


    public void animateRect(){
        float translateX = 50.0f;
        float translateY = 50.0f;
        ObjectAnimator animateLeft = ObjectAnimator.ofFloat(mCurrentRect, "left", mCurrentRect.left, mCurrentRect.left+translateX);
        ObjectAnimator animateRight = ObjectAnimator.ofFloat(mCurrentRect, "right", mCurrentRect.right, mCurrentRect.right+translateX);
        ObjectAnimator animateTop = ObjectAnimator.ofFloat(mCurrentRect, "top", mCurrentRect.top, mCurrentRect.top+translateY);
        ObjectAnimator animateBottom = ObjectAnimator.ofFloat(mCurrentRect, "bottom", mCurrentRect.bottom, mCurrentRect.bottom+translateY);
        animateBottom.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                postInvalidate();
            }
        });
        AnimatorSet rectAnimation = new AnimatorSet();
        rectAnimation.playTogether(animateLeft, animateRight, animateTop, animateBottom);
        rectAnimation.setDuration(1000).start();
    }

    public void animateRectRight(float translateX){
            //float translateX = 200.0f;

            ObjectAnimator animateRight = ObjectAnimator.ofFloat(mCurrentRect, "right", mCurrentRect.right, mCurrentRect.right+translateX);
            ObjectAnimator animateLeft = ObjectAnimator.ofFloat(mCurrentRect, "left", mCurrentRect.left, mCurrentRect.left+translateX);

            animateRight.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    invalidate();
                }
            });
            //animateRight.start();
            //animateLeft.start();

            AnimatorSet rectAnimation = new AnimatorSet();
            rectAnimation.playTogether(animateLeft, animateRight);
            rectAnimation.setDuration(1000).start();
    }


    public void animateRectLeft(float translateX){
        //float translateX = 200.0f;

        ObjectAnimator animateRight = ObjectAnimator.ofFloat(mCurrentRect, "right", mCurrentRect.right, mCurrentRect.right-translateX);
        ObjectAnimator animateLeft = ObjectAnimator.ofFloat(mCurrentRect, "left", mCurrentRect.left, mCurrentRect.left-translateX);

        animateLeft.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        //animateRight.start();
        //animateLeft.start();

        AnimatorSet rectAnimation = new AnimatorSet();
        rectAnimation.playTogether(animateLeft, animateRight);
        rectAnimation.setDuration(1000).start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
//                animateRect();
                animateRectRight(200);
                //invalidate();
                break;
        }

        return super.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        animPaint.setColor(Color.RED);

        RectF rectF = new RectF(0,0,200,200);

        //mCurrentRect = new AnimatedRectF(0,0,200,200);


        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRoundRect(mCurrentRect,10,10,animPaint);
        //canvas.drawRoundRect(0,0,200,200,10,10,paint);




        super.onDraw(canvas);
    }
}
