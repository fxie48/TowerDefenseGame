package com.example.towerdefensegame.model;
import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.towerdefensegame.GameView;

import java.util.ArrayList;

public abstract class Tower {
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    private Weapon weapon;
    private Bitmap shoot;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> trashBullets;
    private Bitmap tower;
    private GameView gameView;
    private int coolDownWeapon;
    private boolean upgraded;
    public abstract Bitmap getTower();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int shoot() {
        return weapon.getDamage();
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

    public ArrayList<Bullet> getTrashBullets() {
        return trashBullets;
    }

    public Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }

    public void setTrashBullets(ArrayList<Bullet> trashBullets) {
        this.trashBullets = trashBullets;
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

    public int getCoolDownWeapon() {
        return coolDownWeapon;
    }

    public void setCoolDownWeapon(int coolDownWeapon) {
        this.coolDownWeapon = coolDownWeapon;
    }

    public boolean isUpgraded() {
        return upgraded;
    }

    public void setUpgraded(boolean upgrade) {
        this.upgraded = upgrade;
    }
}
