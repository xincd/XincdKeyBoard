package com.xcd.pinyin;

import android.content.Context;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SettingKeyboardView extends RelativeLayout
{
    private Context mContext;
    
    private Environment mEnvironment;
    
    private ImageView mHideKeyBoardImage;
    
    private PinyinIME mPinyinIME;
    
    public SettingKeyboardView(Context context) {
        super(context);
        this.mContext = context;
        this.mEnvironment = Environment.getInstance();
    }
    
    public SettingKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mEnvironment = Environment.getInstance();
    }
    
    public void setPinyinIME(PinyinIME pinyinIME)
    {
        this.mPinyinIME = pinyinIME;
    }
    
    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        mHideKeyBoardImage = (ImageView) findViewById(R.id.hide_keyboard);
        mHideKeyBoardImage.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                System.out.println("wanghui===========mHideButton");
                if(mPinyinIME != null)
                {
                    mPinyinIME.hideWindow();
                }
            }
        });
        super.onFinishInflate();
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Environment env = Environment.getInstance();
        int measuredWidth = env.getSkbWidth();
        int measuredHeight = getPaddingTop();
        measuredHeight += env.getHeightForCandidates();
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth,
                MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(measuredHeight,
                MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
