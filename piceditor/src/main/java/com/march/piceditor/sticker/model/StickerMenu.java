package com.march.piceditor.sticker.model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.march.piceditor.common.model.Point;

/**
 * CreateAt : 7/21/17
 * Describe :
 *
 * @author chendong
 */
public class StickerMenu {

    private Drawable mMenuIcon;
    private Rect     mBoundRect;
    private int      mScale;
    private int      mPositionType;
    private Sticker  mAttachSticker;

    public StickerMenu(int type, Context context, int res) {
        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), BitmapFactory.decodeResource(context.getResources(), res));
        init(type, drawable);
    }

    public void init(int type, Drawable menuIcon) {
        mMenuIcon = menuIcon;
        mPositionType = type;
        mScale = 2;
        mBoundRect = new Rect();

    }

    public void attachSticker(Sticker sticker) {
        mAttachSticker = sticker;
    }

    public Drawable getMenuIcon() {
        return mMenuIcon;
    }

    public void setMenuIcon(Drawable menuIcon) {
        mMenuIcon = menuIcon;
    }

    public int getMenuWidth() {
        return mMenuIcon.getIntrinsicWidth();
    }

    public int getMenuHeight() {
        return mMenuIcon.getIntrinsicHeight();
    }

    public int getPositionType() {
        return mPositionType;
    }

    public void updateBounds() {
        mMenuIcon.setBounds(getBounds());
    }

    public Rect getBounds() {

        Point p = mAttachSticker.getPointMap().get(mPositionType);

        mBoundRect.set((int) (p.x - getMenuWidth() / mScale / 2),
                (int) (p.y - getMenuHeight() / mScale / 2),
                (int) (p.x - getMenuWidth() / mScale / 2 + getMenuWidth() / mScale),
                (int) (p.y - getMenuHeight() / mScale / 2 + getMenuHeight() / mScale));

        return mBoundRect;
    }


    public boolean isTouchIn(float x, float y) {
        return getBounds().contains((int) x, (int) y);
    }
}
