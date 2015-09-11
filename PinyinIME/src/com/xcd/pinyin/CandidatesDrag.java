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
        System.out.println("wanghui=============== 00 CandidatesDrag measuredHeight:="+measuredHeight);
        measuredHeight += env.getCandidateDragViewHight();
        System.out.println("wanghui=============== 11 CandidatesDrag measuredHeight:="+measuredHeight+" env.getHeightForCandidates():="+env.getmFloatModePrentHeight());
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth,
                MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(measuredHeight,
                MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
    
    public void setPinyinIME(PinyinIME pinyinIME)
    {
        this.mPinyinIME = pinyinIME;
        System.out.println("wanghui=============3333333333:mPinyinIME:="+mPinyinIME);
    }
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
//            mDragImageView.setImageResource(R.drawable.float_mode_move_button_pressed);
            XDownRow = (int) event.getRawX();
            YDownRow = (int) event.getRawY();
            xDown = (int) event.getX();
            yDown = (int) event.getRawY();
            yMoveRowold = 0;
            xMoveRowold = 0;
            
            mDragImageView.setBackgroundResource(R.drawable.move_anim);
			
			// 获取AnimationDrawable对象 AnimationDrawable
			 animationDrawable = (AnimationDrawable) mDragImageView
			 .getBackground(); // 开始或者继续动画播放
			 animationDrawable.start();
			 
            break;
        case MotionEvent.ACTION_MOVE:
//            mDragImageView.setImageResource(R.drawable.float_mode_move_button_pressed);
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
            y2 = y1 - yMoveRowold;
            yMoveRowold = y1;
            x2 = x - xMoveRowold;
            xMoveRowold = x;
            System.out.println("wanghui======= CandidatesDrag onTouchEvent ACTION_MOVE MotionEvent======222=CandidateView=y2:="+y2+" y:="+y+"y1:="+y1+" x2:="+x2+" mPinyinIME:="+mPinyinIME);
            if(mPinyinIME != null)
            {
                mPinyinIME.updateKeyBoardView(x2,y2);
//                updateImage(true);
            }
                break;
        case MotionEvent.ACTION_UP:
        	animationDrawable.stop();
//            updateImage(false);
//            mDragImageView.setImageResource(R.drawable.float_mode_move_button_normal);
            break;

        default:
            break;
        }
        
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v == mDragImageView) {
            }
        }
        return true;
    }

}
