package com.xcd.pinyin;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CandidatesDrag extends RelativeLayout implements OnTouchListener{

    private Context mContext;
    
    private PinyinIME mPinyinIME;
    
    private Environment mEnvironment;
    
    private ImageView mDragImageView;
    private AnimationDrawable animationDrawable;
    
//    private ImageView mDragPressImageView;
    
    public CandidatesDrag(Context context) {
        super(context);
        this.mContext = context;
        this.mEnvironment = Environment.getInstance();
    }
    
    public CandidatesDrag(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mEnvironment = Environment.getInstance();
    }
    
    public void initialize()
    {
        mDragImageView = (ImageView) findViewById(R.id.test_image);
//        mDragPressImageView = (ImageView) findViewById(R.id.test_image_press);
        mDragImageView.setVisibility(View.VISIBLE);
//        mDragPressImageView.setVisibility(View.GONE);
//        mDragImageView.setOnTouchListener(this);
//        mDragImageView.setImageResource(R.drawable.float_mode_move_button_normal);
        
        animationDrawable = (AnimationDrawable) mDragImageView
				.getBackground();
    }
    
    public void updateImage(boolean flag)
    {
        if(flag)
        {
            mDragImageView.setVisibility(View.GONE);
//            mDragPressImageView.setVisibility(View.VISIBLE);
        }
        else
        {
            mDragImageView.setVisibility(View.VISIBLE);
//            mDragPressImageView.setVisibility(View.GONE);
        }
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Environment env = Environment.getInstance();
        int measuredWidth = env.getCandidateDragViewWidht();
        int measuredHeight = getPaddingTop();
        measuredHeight += env.getCandidateDragViewHight();
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth,
                MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(measuredHeight,
                MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
    
    public void setPinyinIME(PinyinIME pinyinIME)
    {
        this.mPinyinIME = pinyinIME;
    }
    /**
     * 记录手指按下时在小悬浮窗的View上的横坐标的值
     */
    private float xInView;

    /**
     * 记录手指按下时在小悬浮窗的View上的纵坐标的值
     */
    private float yInView;
    /**
     * 记录当前手指位置在屏幕上的横坐标值
     */
    private float xInScreen;

    /**
     * 记录当前手指位置在屏幕上的纵坐标值
     */
    private float yInScreen;
    int XmoveRow = 0,YmoveRow = 0,XDownRow = 0,YDownRow = 0,yMoveRowold = 0,xMoveRowold = 0,xDown = 0,yDown = 0;
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("wanghui======= CandidatesDrag onTouchEvent ACTION_MOVE MotionEvent====111=CandidateView==");
//        event.offsetLocation(-(CandidatesContainer.xOffsetForFlipper), 0);
//        CandidateView cv = (CandidateView) mFlipper.getCurrentView();
//        onTouchEventReal(event);
//        if(mCandidatesContainer.mLeftArrowBtn.getVisibility() == View.VISIBLE || mCandidatesContainer.mRightArrowBtn.getVisibility() == View.VISIBLE)
//        {
//            return true;
//        }
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            xInView = event.getX();
            yInView = event.getY();
//            mDragImageView.setImageResource(R.drawable.float_mode_move_button_pressed);
            XDownRow = (int) event.getRawX();
            YDownRow = (int) event.getRawY();
            xDown = (int) event.getX();
            yDown = (int) event.getY();
            yMoveRowold = 0;
            xMoveRowold = 0;
            mDragImageView.setBackgroundResource(R.drawable.move_anim);
			// 获取AnimationDrawable对象 AnimationDrawable
			 animationDrawable = (AnimationDrawable) mDragImageView
			 .getBackground(); // 开始或者继续动画播放
			 animationDrawable.start();
			 
            break;
        case MotionEvent.ACTION_MOVE:
            xInScreen = event.getRawX();
            yInScreen = event.getRawY();
            int y1 = 0;
            int y2 = 0;
            int x1 = 0;
            int x2 = 0;
            XmoveRow = (int) event.getRawX();
            YmoveRow = (int) event.getRawY();
            int x = XmoveRow - XDownRow;
            int y = YmoveRow - YDownRow;
            if(y < 0)
            {
                y1 = Math.abs(y);
            }
            if(y > 0)
            {
                y1 = -y;
            }
            System.out.println("wanghui=======XmoveRow:="+XmoveRow+"  XDownRow:="+XDownRow+" xDown:="+xDown);
//            if(mEnvironment.mScreenHeight > mEnvironment.mScreenWidth)
//            {
                int skbWidth = (int)(mEnvironment.getSkbWidth()/2);
                if(XmoveRow > XDownRow)
                {
                    if((XmoveRow + skbWidth + 5) < mEnvironment.mScreenWidth)
                    {
                        y2 = y1 - yMoveRowold;
                        yMoveRowold = y1;
                        x2 = x - xMoveRowold;
                        xMoveRowold = x;
                        if(mPinyinIME != null)
                        {
                            mPinyinIME.updateKeyBoardView(x2,y2);
                        }
                    }
                }
                else
                {
                    if(((XmoveRow+5) - skbWidth) > 0)
                    {
                        y2 = y1 - yMoveRowold;
                        yMoveRowold = y1;
                        x2 = x - xMoveRowold;
                        xMoveRowold = x;
                        if(mPinyinIME != null)
                        {
                            mPinyinIME.updateKeyBoardView(x2,y2);
                        }
                    }
                }
//            }
            break;
        case MotionEvent.ACTION_UP:
        	animationDrawable.stop();
            break;

        default:
            break;
        }
        
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            System.out.println("wanghui========="+event.getX());
        }
        return true;
    }

}
