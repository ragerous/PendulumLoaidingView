package com.rage.pendulumball;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by nzq on 17/01/10.
 */

public class PendulumLoaidingView extends View {
    int mWidth;
    int mHeight;
    Paint paint;

    Context mContext;
    int mNum = 4;
    int mStage = 0;
    int mBetween = mWidth / mNum;
    float moveValue;
    int mDuration = 1200;
    int mRound = 60;

    //try another  by using round&sin cos
    public PendulumLoaidingView(Context context) {
        super(context);

        init(context);
    }

    public PendulumLoaidingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PendulumLoaidingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void init(Context context) {
        mContext = context;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        setEnabled(true);
        setClickable(true);
        setOnClickListener(onClickListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mStage) {
            case 0:
                paint.setColor(Color.BLUE);
                canvas.drawCircle(mWidth * (1) / (mNum * 2), mHeight / 2, mRound, paint);//draw dy before-One
                paint.setColor(Color.RED);
                canvas.drawCircle(mWidth * (2 + 1) / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One

                canvas.drawCircle(mWidth * (2 + 2 + 1) / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One
                canvas.drawCircle(mWidth * (4 + 2 + 1) / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One

                break;
            case 1:
                paint.setColor(Color.BLUE);
                canvas.drawCircle(mWidth * 2 / (mNum * 2) + moveValue, mHeight / 2 - (float) Math.sqrt((mBetween / 2 * (mBetween / 2) - moveValue * moveValue)), mRound, paint);//draw dy before-One
                paint.setColor(Color.RED);
                canvas.drawCircle(mWidth * 2 / (mNum * 2) - moveValue, mHeight / 2 + (float) Math.sqrt((mBetween / 2 * (mBetween / 2) - moveValue * moveValue)), mRound, paint);//draw dy After-One

                canvas.drawCircle(mWidth * (2 + 2 + 1) / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One
                canvas.drawCircle(mWidth * (4 + 2 + 1) / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One

                break;
            case 2:
                paint.setColor(Color.BLUE);
                canvas.drawCircle(mWidth * (2 + 2) / (mNum * 2) + moveValue, mHeight / 2 + (float) Math.sqrt((mBetween / 2 * (mBetween / 2) - moveValue * moveValue)), mRound, paint);//draw dy before-One
                paint.setColor(Color.RED);
                canvas.drawCircle(mWidth * (2 + 2) / (mNum * 2) - moveValue, mHeight / 2 - (float) Math.sqrt((mBetween / 2 * (mBetween / 2) - moveValue * moveValue)), mRound, paint);//draw dy After-One

                canvas.drawCircle(mWidth / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One
                canvas.drawCircle(mWidth * (4 + 2 + 1) / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One

                break;
            case 3:
                paint.setColor(Color.BLUE);
                canvas.drawCircle(mWidth * (2 + 2 + 2) / (mNum * 2) + moveValue, mHeight / 2 - (float) Math.sqrt((mBetween / 2 * (mBetween / 2) - moveValue * moveValue)), mRound, paint);//draw dy before-One
                paint.setColor(Color.RED);
                canvas.drawCircle(mWidth * (2 + 2 + 2) / (mNum * 2) - moveValue, mHeight / 2 + (float) Math.sqrt((mBetween / 2 * (mBetween / 2) - moveValue * moveValue)), mRound, paint);//draw dy After-One

                canvas.drawCircle(mWidth / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One
                canvas.drawCircle(mWidth * (2 + 1) / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One

                break;
            case 4:

                paint.setColor(Color.BLUE);
                canvas.drawCircle(mWidth * 2 / (mNum) - moveValue, mHeight / 2 + (float) Math.sqrt((mBetween * 3 / 2 * (mBetween * 3 / 2) - moveValue * moveValue)), mRound, paint);//draw dy before-One
                paint.setColor(Color.RED);
                canvas.drawCircle(mWidth * 2 / (mNum) + moveValue, mHeight / 2 - (float) Math.sqrt((mBetween * 3 / 2 * (mBetween * 3 / 2) - moveValue * moveValue)), mRound, paint);//draw dy After-One

                canvas.drawCircle(mWidth * (2 + 1) / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One
                canvas.drawCircle(mWidth * (2 + 2 + 1) / (mNum * 2), mHeight / 2, mRound, paint);//draw dy After-One
                break;
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
        mBetween = mWidth / mNum;
    }

    public void onStartAnimation() {
        animationStatge1();

    }

    public void cancelAnimaiton() {
        mStage = 0;
        invalidate();
    }

    void LogE(Object o) {
        Log.e("pendulum", "" + o.toString());
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            onStartAnimation();
        }
    };

    void animationStatge0() {
        mStage = 0;
        invalidate();
    }

    void animationStatge1() {
        mStage = 1;
        ValueAnimator stage0Animator = ValueAnimator.ofFloat(-mBetween / 2, mBetween / 2);//should be r and culate the x & y
        stage0Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                moveValue = (float) animation.getAnimatedValue();
                Log.e("move:", "" + moveValue);
                invalidate();
            }
        });
        stage0Animator.addListener(new mAnimator() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationStatge2();

            }
        });
        stage0Animator.setDuration(mDuration);
        stage0Animator.start();
    }

    void animationStatge2() {
        mStage = 2;
        ValueAnimator stage2Animator = ValueAnimator.ofFloat(-mBetween / 2, mBetween / 2);//should be r and culate the x & y
        stage2Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                moveValue = (float) animation.getAnimatedValue();
                Log.e("move:", "" + moveValue);
                invalidate();
            }
        });
        stage2Animator.addListener(new mAnimator() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationStatg3();
            }
        });
        stage2Animator.setDuration(mDuration);
        stage2Animator.start();
    }

    void animationStatg3() {
        mStage = 3;
        ValueAnimator stage3Animator = ValueAnimator.ofFloat(-mBetween / 2, mBetween / 2);//should be r and culate the x & y
        stage3Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                moveValue = (float) animation.getAnimatedValue();
                Log.e("move:", "" + moveValue);
                invalidate();
            }
        });
        stage3Animator.addListener(new mAnimator() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationStatg4();
            }
        });
        stage3Animator.setDuration(mDuration);
        stage3Animator.start();
    }

    void animationStatg4() {
        mStage = 4;
        ValueAnimator stage4Animator = ValueAnimator.ofFloat(-mBetween * 3 / 2, mBetween * 3 / 2);//should be r and culate the x & y
        stage4Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                moveValue = (float) animation.getAnimatedValue();
                Log.e("move:", "" + moveValue);
                invalidate();
            }
        });
        stage4Animator.addListener(new mAnimator() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                LogE("end");
                Toast.makeText(mContext, "end ", Toast.LENGTH_SHORT).show();
            }
        });
        stage4Animator.setDuration(mDuration);
        stage4Animator.start();
    }
}
