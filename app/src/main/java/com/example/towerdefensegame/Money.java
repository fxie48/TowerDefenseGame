package com.example.towerdefensegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Money {
    private int x;
    private int y;
    private int width;
    private int height;
    private int amount;
    private Bitmap money;

    public Money(int x, int y, Resources res) {
        super();
        money = BitmapFactory.decodeResource(res, R.drawable.goldcoin);
        width = money.getWidth();
        height = money.getHeight();
        width *= 3;
        height *= 3;
        money = Bitmap.createScaledBitmap(money, width, height, false);
        this.x = x;
        this.y = y;
        this.amount = amount;
    }
    public Bitmap getMoney() {
        return money;
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
