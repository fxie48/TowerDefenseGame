package com.example.towerdefensegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.towerdefensegame.R;

public class Heart {
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap heart;

    public Heart(int x, int y, Resources res) {
        super();
        heart = BitmapFactory.decodeResource(res, R.drawable.heart);
        width = heart.getWidth();
        height = heart.getHeight();
        width *= 1.78;
        height *= 1.78;
        heart = Bitmap.createScaledBitmap(heart, width, height, false);
        this.x = x;
        this.y = y;
    }

    public Bitmap getHeart() {
        return heart;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
