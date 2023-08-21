package com.example.towerdefensegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.towerdefensegame.GameView;
import com.example.towerdefensegame.R;

public class FrogTower extends Tower {
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap frogTower;
    private GameView gameView;
    private boolean upgraded;


    public FrogTower(GameView gameView, int x, int y, Resources res) {
        super();
        frogTower = BitmapFactory.decodeResource(res, R.drawable.tower1);
        width = frogTower.getWidth();
        height = frogTower.getHeight();
        frogTower = Bitmap.createScaledBitmap(frogTower, width, height, false);
        this.x = x;
        this.y = y;
        this.setWeapon(new FrogTowerWeapon(10));
        this.upgraded = false;
    }
    public FrogTower() {
        this.x = x;
        this.y = y;
        this.setWeapon(new FrogTowerWeapon(10));
    }

    public Bitmap getTower() {
        return frogTower;
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
        return "FrogTower";
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
