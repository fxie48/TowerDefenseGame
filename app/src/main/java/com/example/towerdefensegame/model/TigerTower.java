package com.example.towerdefensegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.towerdefensegame.GameView;
import com.example.towerdefensegame.R;

import java.util.ArrayList;

public class TigerTower extends Tower {
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap tigerTower;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> trashBullets;
    private int range;
    private int coolDownWeapon;
    private boolean upgraded;



    private Bitmap shoot;
    private GameView gameView;

    public TigerTower(GameView gameView, int x, int y, Resources res) {
        this.gameView = gameView;
        tigerTower = BitmapFactory.decodeResource(res, R.drawable.tower3);
        width = tigerTower.getWidth();
        height = tigerTower.getHeight();
        tigerTower = Bitmap.createScaledBitmap(tigerTower, width, height, false);
        this.x = x;
        this.y = y;
        this.setWeapon(new TigerTowerWeapon(50));
        this.shoot = BitmapFactory.decodeResource(res, R.drawable.tigerweapon);
        this.shoot  = Bitmap.createScaledBitmap(this.shoot, width, height, false);
        this.range = 200;
        this.coolDownWeapon = 20;
        this.upgraded = false;
    }
    public TigerTower() {
        this.x = x;
        this.y = y;
        this.setWeapon(new TigerTowerWeapon(50));
    }

    public Bitmap getTower() {
        return tigerTower;
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

    public Bitmap getShoot() {
        return shoot;
    }

    public void setShoot(Bitmap shoot) {
        this.shoot = shoot;
    }
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public ArrayList<Bullet> getTrashBullets() {
        return trashBullets;
    }

    @Override
    public void setTrashBullets(ArrayList<Bullet> trashBullets) {
        this.trashBullets = trashBullets;
    }

    @Override
    public String getName() {
        return "TigerTower";
    }

    @Override
    public Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
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
