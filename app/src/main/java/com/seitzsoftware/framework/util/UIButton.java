package com.seitzsoftware.framework.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by Kevin on 4/28/2016.
 */
public class UIButton {
    public Rect buttonRect;
    private boolean buttonDown = false;
    public Bitmap buttonImage, buttonDownImage;
    public int originalLeft, originalTop, originalRight, originalBottom;

    public UIButton(int left, int top, int right, int bottom,
                    Bitmap buttonImage, Bitmap buttonPressedImage) {
        buttonRect = new Rect(left, top, right, bottom);
        this.buttonImage = buttonImage;
        this.buttonDownImage = buttonPressedImage;
        this.originalLeft = left;
        this.originalTop = top;
        this.originalRight = right;
        this.originalBottom = bottom;
    }

    public void render(Painter g) {
        Bitmap currentButtonImage = buttonDown ? buttonDownImage : buttonImage;
        g.drawImage(currentButtonImage, buttonRect.left, buttonRect.top,
                buttonRect.width(), buttonRect.height());
    }

    public void onTouchDown(int touchX, int touchY) {
        if (buttonRect.contains(touchX, touchY)) {
            buttonDown = true;
        } else {
            buttonDown = false;
        }
    }

    public void cancel() {
        buttonDown = false;
    }

    public boolean isPressed(int touchX, int touchY) {
        return buttonDown && buttonRect.contains(touchX, touchY);
    }

    public void onDrag(int touchX, int touchY, MotionEvent e){
        int buttonHeight = this.buttonRect.bottom - this.buttonRect.top;
        int buttonWidth = this.buttonRect.right - this.buttonRect.left;
        if (isPressed(touchX, touchY)) {
            this.buttonRect.left = touchX-buttonWidth/2;
            this.buttonRect.top = touchY-buttonHeight/2;
            this.buttonRect.right = this.buttonRect.left + buttonWidth;
            this.buttonRect.bottom = this.buttonRect.top + buttonHeight;
        }
    }

    public void resetPosition(){
        this.buttonRect.left = originalLeft;
        this.buttonRect.top = originalTop;
        this.buttonRect.right = originalRight;
        this.buttonRect.bottom = originalBottom;
    }
}