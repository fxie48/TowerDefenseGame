package com.example.towerdefensegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.towerdefensegame.R;

public class HealthBar {
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap healthBar;

    public HealthBar(int x, int y, Resources res) {
        super();
        healthBar = BitmapFactory.decodeResource(res, R.drawable.healthbar);
        width = healthBar.getWidth();
        height = healthBar.getHeight();
        healthBar = Bitmap.createScaledBitmap(healthBar, width, height, false);
        this.x = x;
        this.y = y;
    }

    public Bitmap getHealthBar() {
        return healthBar;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}