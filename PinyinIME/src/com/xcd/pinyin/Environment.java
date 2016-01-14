/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xcd.pinyin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Display;
import android.view.WindowManager;

/**
 * Global environment configurations for showing soft keyboard and candidate
 * view. All original dimension values are defined in float, and the real size
 * is calculated from the float values of and screen size. In this way, this
 * input method can work even when screen size is changed.
 */
@SuppressLint("NewApi")
public class Environment {
    /**
     * The key height for portrait mode. It is relative to the screen height.
     */
    private static final float KEY_HEIGHT_RATIO_PORTRAIT = 0.04f;

    private static final float KEY_WIDTH_RATIO_PORTRAIT = 0.45f;
    
    private static final float KEY_WIDTH_RATIO_LANDSCAPE = 0.25f;
    
    private static final float KEY_WIDTH_CANDIDATEDRAG = 0.1f;

    /**
     * The key height for landscape mode. It is relative to the screen height.
     */
    private static final float KEY_HEIGHT_RATIO_LANDSCAPE = 0.05f;

    /**
     * The height of the candidates area for portrait mode. It is relative to
     * screen height.
     */
    private static final float CANDIDATES_AREA_HEIGHT_RATIO_PORTRAIT = 0.05f;//0.070f;
    
    private static final float CANDIDATES_VIEW_HEIGHT_RATIO_PORTRAIT = 0.10f;//0.13f;

    private static final float CANDIDATES_VIEW_HEIGHT_RATIO_LANDSCAPE = 0.15f;
    /**
     * The height of the candidates area for portrait mode. It is relative to
     * screen height.
     */
    private static final float CANDIDATES_AREA_HEIGHT_RATIO_LANDSCAPE = 0.07f;

    /**
     * How much should the balloon width be larger than width of the real key.
     * It is relative to the smaller one of screen width and height.
     */
    private static final float KEY_BALLOON_WIDTH_PLUS_RATIO = 0.6f;

    /**
     * How much should the balloon height be larger than that of the real key.
     * It is relative to the smaller one of screen width and height.
     */
    private static final float KEY_BALLOON_HEIGHT_PLUS_RATIO = 0.08f;

    /**
     * The text size for normal keys. It is relative to the smaller one of
     * screen width and height.
     */
    private static final float NORMAL_KEY_TEXT_SIZE_RATIO = 0.020f;

    /**
     * The text size for function keys. It is relative to the smaller one of
     * screen width and height.
     */
    private static final float FUNCTION_KEY_TEXT_SIZE_RATIO = 0.020f;

    /**
     * The text size balloons of normal keys. It is relative to the smaller one
     * of screen width and height.
     */
    private static final float NORMAL_BALLOON_TEXT_SIZE_RATIO = 0.020f;

    /**
     * The text size balloons of function keys. It is relative to the smaller
     * one of screen width and height.
     */
    private static final float FUNCTION_BALLOON_TEXT_SIZE_RATIO = 0.020f;

    /**
     * The configurations are managed in a singleton.
     */
    private static Environment mInstance;

    public int mScreenWidth;
    public int mScreenHeight;
    private int mKeyHeight;
    private int mSkbWidth;
    private int mCandidatesAreaHeight;
    private int mKeyBalloonWidthPlus;
    private int mKeyBalloonHeightPlus;
    private int mNormalKeyTextSize;
    private int mFunctionKeyTextSize;
    private int mNormalBalloonTextSize;
    private int mFunctionBalloonTextSize;
    private int mFloatModePrentHeight;
    private int mCandidateDragViewHight;
    private int mCandidateDragViewWidht;
    private Configuration mConfig = new Configuration();
    private boolean mDebug = false;

    private Environment() {
    }

    public static Environment getInstance() {
        if (null == mInstance) {
            mInstance = new Environment();
        }
        return mInstance;
    }

    public void onConfigurationChanged(Configuration newConfig, Context context) {
        if (mConfig.orientation != newConfig.orientation) {
            WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            Display d = wm.getDefaultDisplay();
//            mScreenWidth = (int)(d.getWidth() * KEY_WIDTH_RATIO_PORTRAIT);
            mScreenWidth = d.getWidth();
            mScreenHeight = d.getHeight();
            int scale;
            if (mScreenHeight > mScreenWidth) {
                mKeyHeight = (int) (mScreenHeight * KEY_HEIGHT_RATIO_PORTRAIT);
                mSkbWidth = (int)(mScreenWidth * KEY_WIDTH_RATIO_PORTRAIT);
                mCandidatesAreaHeight = (int) (mScreenHeight * CANDIDATES_AREA_HEIGHT_RATIO_PORTRAIT);
                mFloatModePrentHeight = (int) (mScreenHeight * CANDIDATES_VIEW_HEIGHT_RATIO_PORTRAIT);
                mCandidateDragViewWidht = (int)(mScreenWidth * KEY_WIDTH_CANDIDATEDRAG);
                scale = mScreenWidth;
            } else {
                mSkbWidth = (int)(mScreenWidth * KEY_WIDTH_RATIO_LANDSCAPE);
                mKeyHeight = (int) (mScreenHeight * KEY_HEIGHT_RATIO_LANDSCAPE);
                mCandidatesAreaHeight = (int) (mScreenHeight * CANDIDATES_AREA_HEIGHT_RATIO_LANDSCAPE);
                mFloatModePrentHeight = (int) (mScreenHeight * CANDIDATES_VIEW_HEIGHT_RATIO_LANDSCAPE);
                mCandidateDragViewWidht = (int)(mScreenWidth * KEY_WIDTH_CANDIDATEDRAG);
                scale = mScreenHeight;
            }
            System.out.println("wanghui=====================22 onConfigurationChanged mScreenWidth:="+mScreenWidth+"  mSkbWidth:="+mSkbWidth);
            mNormalKeyTextSize = (int) (scale * NORMAL_KEY_TEXT_SIZE_RATIO);
            mFunctionKeyTextSize = (int) (scale * FUNCTION_KEY_TEXT_SIZE_RATIO);
            mNormalBalloonTextSize = (int) (scale * NORMAL_BALLOON_TEXT_SIZE_RATIO);
            mFunctionBalloonTextSize = (int) (scale * FUNCTION_BALLOON_TEXT_SIZE_RATIO);
            mKeyBalloonWidthPlus = (int) (scale * KEY_BALLOON_WIDTH_PLUS_RATIO);
            mKeyBalloonHeightPlus = (int) (scale * KEY_BALLOON_HEIGHT_PLUS_RATIO);
            mCandidateDragViewHight = mFloatModePrentHeight - mCandidatesAreaHeight;
            System.out.println("wanghui===============onConfigurationChanged mScreenWidth:="+mScreenWidth+" mScreenHeight:="+mScreenHeight);
        }

        mConfig.updateFrom(newConfig);
    }

    public Configuration getConfiguration() {
        return mConfig;
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public int getHeightForCandidates() {
        return mCandidatesAreaHeight;
    }
    
    public int getCandidateDragViewHight()
    {
        return mCandidateDragViewHight;
    }
    
    public int getmFloatModePrentHeight()
    {
        return mFloatModePrentHeight;
    }
    
    public int getCandidateDragViewWidht()
    {
        return mCandidateDragViewWidht;
    }

    public float getKeyXMarginFactor() {
        return 1.0f;
    }

    public float getKeyYMarginFactor() {
        if (Configuration.ORIENTATION_LANDSCAPE == mConfig.orientation) {
            return 0.7f;
        }
        return 1.0f;
    }

    public int getKeyHeight() {
        return mKeyHeight;
    }
	
    public int getSkbWidth()
    {
        return mSkbWidth;
    }

    public int getKeyBalloonWidthPlus() {
        return mKeyBalloonWidthPlus;
    }

    public int getKeyBalloonHeightPlus() {
        return mKeyBalloonHeightPlus;
    }

    public int getSkbHeight() {
        if (Configuration.ORIENTATION_PORTRAIT == mConfig.orientation) {
            return mKeyHeight * 4;
        } else if (Configuration.ORIENTATION_LANDSCAPE == mConfig.orientation) {
            return mKeyHeight * 4;
        }
        return 0;
    }

    public int getKeyTextSize(boolean isFunctionKey) {
        if (isFunctionKey) {
            return mFunctionKeyTextSize;
        } else {
            return mNormalKeyTextSize;
        }
    }

    public int getBalloonTextSize(boolean isFunctionKey) {
        if (isFunctionKey) {
            return mFunctionBalloonTextSize;
        } else {
            return mNormalBalloonTextSize;
        }
//    	if (isFunctionKey) {
//            return 50;//78
//        } else {
//            return 80;//120
//        }
    }

    public boolean hasHardKeyboard() {
        if (mConfig.keyboard == Configuration.KEYBOARD_NOKEYS
                || mConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            return false;
        }
        return true;
    }

    public boolean needDebug() {
        return mDebug;
    }
}
