package com.example.towerdefensegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Monument {
    private int x;
    private int y;
    private int width;
    private int height;
    private int health = 1000;
    private Bitmap monument;
    public Monument(int screenX, int screenY, Resources res) {
        monument = BitmapFactory.decodeResource(res, R.drawable.collision);
        width = monument.getWidth();
        height = monument.getHeight();
        monument = Bitmap.createScaledBitmap(monument, width, height, false);
        x = 1712;
        y = 150;
    }

    public Monument() {
        x = 1712;
        y = 150;
    }

    Bitmap getMonument() {
        return monument;
    }
    public int getHealth() {
        return health;
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

    public Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }
}
