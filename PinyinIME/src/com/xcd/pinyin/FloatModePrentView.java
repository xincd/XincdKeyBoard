package com.xcd.pinyin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class FloatModePrentView extends RelativeLayout{
    private Context mContext;
    
    private Environment mEnvironment;
    
    public FloatModePrentView(Context context) {
        super(context);
        this.mContext = context;
        this.mEnvironment = Environment.getInstance();
    }
    
    public FloatModePrentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mEnvironment = Environment.getInstance();
    }
    
    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        super.onFinishInflate();
    }
    
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Environment env = Environment.getInstance();
        int measuredWidth = env.getSkbWidth();
        int measuredHeight = getPaddingTop();
        System.out.println("wanghui=============== 00 CandidatesDrag measuredHeight:="+measuredHeight);
        measuredHeight += env.getmFloatModePrentHeight();
        System.out.println("wanghui=============== 11 CandidatesDrag measuredHeight:="+measuredHeight+" env.getHeightForCandidates():="+env.getmFloatModePrentHeight());
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth,
                MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(measuredHeight,
                MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
