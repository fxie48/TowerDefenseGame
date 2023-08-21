package com.example.towerdefensegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.towerdefensegame.GameView;
import com.example.towerdefensegame.R;

public class upgradedFrogTower extends Tower {
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap upgradedFrogTower;
    private GameView gameView;
    private boolean upgraded;


    public upgradedFrogTower(GameView gameView, int x, int y, Resources res) {
        super();
        upgradedFrogTower = BitmapFactory.decodeResource(res, R.drawable.upgradetower1);
        width = upgradedFrogTower.getWidth();
        height = upgradedFrogTower.getHeight();
        upgradedFrogTower = Bitmap.createScaledBitmap(upgradedFrogTower, width, height, false);
        this.x = x;
        this.y = y;
        this.setWeapon(new FrogTowerWeapon(10));
        this.upgraded = false;
    }
    public upgradedFrogTower() {
        this.x = x;
        this.y = y;
        this.setWeapon(new FrogTowerWeapon(10));
    }

    public Bitmap getTower() {
        return upgradedFrogTower;
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
        return "upgradedFrogTower";
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
