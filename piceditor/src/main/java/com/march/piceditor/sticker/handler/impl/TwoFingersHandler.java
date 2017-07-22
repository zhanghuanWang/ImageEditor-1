package com.march.piceditor.sticker.handler.impl;

import android.view.MotionEvent;

import com.march.dev.utils.LogUtils;
import com.march.piceditor.sticker.handler.StickerBaseTouchHandler;
import com.march.piceditor.utils.CalculateUtils;

/**
 * CreateAt : 7/20/17
 * Describe : 双指缩放旋转贴纸的事件处理
 *
 * @author chendong
 */
public class TwoFingersHandler extends StickerBaseTouchHandler {

    public static final String TAG = TwoFingersHandler.class.getSimpleName();

    private float mLastFingersDistance;
    private float mLastRotation;

    @Override
    public void onTouchDown(MotionEvent event) {
        if (isHadStickerActive() && event.getPointerCount() == 2) {
            mLastFingersDistance = CalculateUtils.calculateDistance(event);
            mLastRotation = CalculateUtils.calculateRotation(event);
        }
    }

    @Override
    public void onTouchMove(MotionEvent event) {

        if (isHadStickerActive()
                && event.getPointerCount() == 2
                && mLastFingersDistance > 0) {

            // 缩放
            float distance = CalculateUtils.calculateDistance(event);
            float scale = distance * 1f / mLastFingersDistance;
            LogUtils.e(TAG, scale + "");
            if (mActiveSticker.isCanScale(scale)) {
                mActiveSticker.postMatrixScale(scale, scale);
            }
            mLastFingersDistance = distance;

            // 旋转
            float rotation = CalculateUtils.calculateRotation(event);
            mActiveSticker.postMatrixRotate(rotation - mLastRotation);
            mLastRotation = rotation;
        }
    }
}

