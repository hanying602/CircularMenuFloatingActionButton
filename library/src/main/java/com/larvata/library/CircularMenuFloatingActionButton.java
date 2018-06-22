package com.larvata.library;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CircularMenuFloatingActionButton extends FloatingActionButton implements View.OnTouchListener {

    private final static float CLICK_DRAG_TOLERANCE = 10; // Often, there will be a slight, unintentional, drag when the user taps the FAB, so we need to account for this.

    private float downRawX, downRawY;
    private float dX, dY;

    private boolean isFABOpen = false;

    private FloatingActionButton fab1, fab2, fab3, fab4, fab5;
    private View parentView;
    private int btnSize = 80;
    private float degree0x, degree30x, degree45x, degree60x, degree90x, degree0y, degree30y, degree45y, degree60y, degree90y;


    private static final String TAG = CircularMenuFloatingActionButton.class.getSimpleName();

    public CircularMenuFloatingActionButton(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public CircularMenuFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public CircularMenuFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    public void setMenuButtons(FloatingActionButton fab1, FloatingActionButton fab2, FloatingActionButton fab3, FloatingActionButton fab4, FloatingActionButton fab5) {
        this.fab1 = fab1;
        this.fab1.setVisibility(GONE);
        this.fab2 = fab2;
        this.fab2.setVisibility(GONE);
        this.fab3 = fab3;
        this.fab3.setVisibility(GONE);
        this.fab4 = fab4;
        this.fab4.setVisibility(GONE);
        this.fab5 = fab5;
        this.fab5.setVisibility(GONE);
    }

    public void setMenuButtonsSize(int fabBtnWidth) {
        this.fab1.setCustomSize(fabBtnWidth);
        this.fab2.setCustomSize(fabBtnWidth);
        this.fab3.setCustomSize(fabBtnWidth);
        this.fab4.setCustomSize(fabBtnWidth);
        this.fab5.setCustomSize(fabBtnWidth);
        this.btnSize = fabBtnWidth;
    }

    public void setParentView(View parentView) {
        this.parentView = parentView;
    }

    public void setBtnDistance(int btnDistance) {
        this.degree0x = Double.valueOf(btnDistance * Math.cos(Math.toRadians(0))).floatValue();
        this.degree30x = Double.valueOf(btnDistance * Math.cos(Math.toRadians(22.5))).floatValue();
        this.degree45x = Double.valueOf(btnDistance * Math.cos(Math.toRadians(45))).floatValue();
        this.degree60x = Double.valueOf(btnDistance * Math.cos(Math.toRadians(67.5))).floatValue();
        this.degree90x = Double.valueOf(btnDistance * Math.cos(Math.toRadians(90))).floatValue();
        this.degree0y = Double.valueOf(btnDistance * Math.sin(Math.toRadians(0))).floatValue();
        this.degree30y = Double.valueOf(btnDistance * Math.sin(Math.toRadians(22.5))).floatValue();
        this.degree45y = Double.valueOf(btnDistance * Math.sin(Math.toRadians(45))).floatValue();
        this.degree60y = Double.valueOf(btnDistance * Math.sin(Math.toRadians(67.5))).floatValue();
        this.degree90y = Double.valueOf(btnDistance * Math.sin(Math.toRadians(90))).floatValue();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int action = motionEvent.getAction();
        //Main View (The Entire View)
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();

        //Object View (FAB View)
        View viewParent = (View) view.getParent();
        int parentWidth = viewParent.getWidth();
        int parentHeight = viewParent.getHeight();

        if (action == MotionEvent.ACTION_DOWN) {
            Log.i(TAG, "onTouch: ACTION_DOWN");
            downRawX = motionEvent.getRawX();
            downRawY = motionEvent.getRawY();
            dX = view.getX() - downRawX;
            dY = view.getY() - downRawY;

            return true; // Consumed

        } else if (action == MotionEvent.ACTION_MOVE) {
            Log.i(TAG, "onTouch: ACTION_MOVE");

            float newX = motionEvent.getRawX() + dX;
            newX = Math.max(0, newX); // Don't allow the FAB past the left hand side of the parent
            newX = Math.min(parentWidth - viewWidth, newX); // Don't allow the FAB past the right hand side of the parent

            float newY = motionEvent.getRawY() + dY;
            newY = Math.max(0, newY); // Don't allow the FAB past the top of the parent
            newY = Math.min(parentHeight - viewHeight, newY); // Don't allow the FAB past the bottom of the parent

            view.animate()
                    .x(newX)
                    .y(newY)
                    .setDuration(0)
                    .start();
            controlFABMenu(newX, newY);
            return true; // Consumed

        } else if (action == MotionEvent.ACTION_UP) {

            float upRawX = motionEvent.getRawX();
            float upRawY = motionEvent.getRawY();

            float upDX = upRawX - downRawX;
            float upDY = upRawY - downRawY;

            if (Math.abs(upDX) < CLICK_DRAG_TOLERANCE && Math.abs(upDY) < CLICK_DRAG_TOLERANCE) { // A click
                Log.i(TAG, "onTouch: performClick");
                isFABOpen = !isFABOpen;
                controlFABMenu(view.getX(), view.getY());
                return performClick();

            } else { // A drag
                Log.i(TAG, "onTouch: ACTION_UP");

                return true; // Consumed
            }

        } else {
            return super.onTouchEvent(motionEvent);
        }

    }

    private void controlFABMenu(float dx, float dy) {
        if (isFABOpen) {
            showButtons();
        } else {
            closeFABMenu();
        }
        moveMenuButtons(parentView.getWidth(), parentView.getHeight(), dx, dy);
    }

    private void moveMenuButtons(int viewWidth, int viewHeight, float fabDownX, float fabDownY) {

        fabDownX = fabDownX + getCustomSize() / 2 - btnSize / 2;
        fabDownY = fabDownY + getCustomSize() / 2 - btnSize / 2;
        if ((Math.round(fabDownX)) < viewWidth / 2) {
            //if current X < half viewWidth then the button might be in II Quadrant or III Quadrant
            if (Math.round(fabDownY) < viewHeight / 2) {
                //if current Y < half viewHeight then the button is in II Quadrant
                createIIQuadrantButtons(fabDownX, fabDownY);
            } else {
                //if current Y > half viewHeight then the button is in III Quadrant
                createIIIQuadrantButtons(fabDownX, fabDownY);
            }
        } else {
            //if current X > half viewWidth then the button might be in I Quadrant or IV Quadrant
            if (Math.round(fabDownY) < viewHeight / 2) {
                //if current Y < half viewHeight then the button is in I Quadrant
                createIQuadrantButtons(fabDownX, fabDownY);
            } else {
                //if current Y > half viewHeight then the button is in IV Quadrant
                createIVQuadrantButtons(fabDownX, fabDownY);
            }
        }
    }

    private void createIQuadrantButtons(float x, float y) {
        fab1.setX(x - degree0x);
        fab1.setY(y + degree0y);
        fab2.setX(x - degree30x);
        fab2.setY(y + degree30y);
        fab3.setX(x - degree45x);
        fab3.setY(y + degree45y);
        fab4.setX(x - degree60x);
        fab4.setY(y + degree60y);
        fab5.setX(x - degree90x);
        fab5.setY(y + degree90y);
    }

    private void createIIQuadrantButtons(float x, float y) {

        fab1.setX(x + degree0x);
        fab1.setY(y + degree0y);
        fab2.setX(x + degree30x);
        fab2.setY(y + degree30y);
        fab3.setX(x + degree45x);
        fab3.setY(y + degree45y);
        fab4.setX(x + degree60x);
        fab4.setY(y + degree60y);
        fab5.setX(x + degree90x);
        fab5.setY(y + degree90y);
    }

    private void createIIIQuadrantButtons(float x, float y) {
        fab1.setX(x + degree0x);
        fab1.setY(y - degree0y);
        fab2.setX(x + degree30x);
        fab2.setY(y - degree30y);
        fab3.setX(x + degree45x);
        fab3.setY(y - degree45y);
        fab4.setX(x + degree60x);
        fab4.setY(y - degree60y);
        fab5.setX(x + degree90x);
        fab5.setY(y - degree90y);
    }

    private void createIVQuadrantButtons(float x, float y) {
        fab1.setX(x - degree0x);
        fab1.setY(y - degree0y);
        fab2.setX(x - degree30x);
        fab2.setY(y - degree30y);
        fab3.setX(x - degree45x);
        fab3.setY(y - degree45y);
        fab4.setX(x - degree60x);
        fab4.setY(y - degree60y);
        fab5.setX(x - degree90x);
        fab5.setY(y - degree90y);
    }

    private void showButtons() {
        fab1.setVisibility(View.VISIBLE);
        fab2.setVisibility(View.VISIBLE);
        fab3.setVisibility(View.VISIBLE);
        fab4.setVisibility(View.VISIBLE);
        fab5.setVisibility(View.VISIBLE);
    }

    private void closeFABMenu() {
        if (fab1 != null) fab1.setVisibility(View.GONE);
        if (fab2 != null) fab2.setVisibility(View.GONE);
        if (fab3 != null) fab3.setVisibility(View.GONE);
        if (fab4 != null) fab4.setVisibility(View.GONE);
        if (fab5 != null) fab5.setVisibility(View.GONE);
    }

}