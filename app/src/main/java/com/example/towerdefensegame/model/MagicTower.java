package com.example.towerdefensegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.towerdefensegame.R;

public class MagicTower extends Tower {
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap magicTower;
    private boolean upgraded;

    public MagicTower(int x, int y, Resources res) {
        super();
        magicTower = BitmapFactory.decodeResource(res, R.drawable.tower2);
        width = magicTower.getWidth();
        height = magicTower.getHeight();
        magicTower = Bitmap.createScaledBitmap(magicTower, width, height, false);
        this.x = x;
        this.y = y;
        this.setWeapon(new MagicTowerWeapon(10));
        this.upgraded = false;
    }
    public MagicTower() {
        this.x = x;
        this.y = y;
        this.setWeapon(new MagicTowerWeapon(20));
    }

    public Bitmap getTower() {
        return magicTower;
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
    @Override
    public String getName() {
        return "MagicTower";
    }

    @Override
    public boolean isUpgraded() {
        return upgraded;
    }

    @Override
    public void setUpgraded(boolean upgrade) {
        this.upgraded = upgrade;
    }

}
