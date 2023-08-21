package com.example.towerdefensegame;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class Enemy {
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap enemy;
    private int speed;
    private Bitmap healthbar;
    private int enemyHealth;
    private Bitmap healthbar90;
    private Bitmap healthbar80;
    private Bitmap healthbar70;
    private Bitmap healthbar60;
    private Bitmap healthbar50;
    private Bitmap healthbar40;
    private Bitmap healthbar30;
    private Bitmap healthbar20;
    private Bitmap healthbar10;
    private Bitmap healthbar0;
    private boolean isJump;
    private Bitmap bossMessage;

    public abstract Bitmap getEnemy();

    public Bitmap getHealthbar() {
        return healthbar;
    }

    public abstract Rect getCollisionShape();

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

    public int getSpeed() {
        return speed;

    }
    public void setEnemySide(){}
    public void setEnemyFront(){}
    public void setEnemyBack(){}
    public void setEnemyJump(){}

    public boolean isJump() {
        return isJump;
    }


    public void setJump(boolean jump) {
        isJump = jump;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public Bitmap getBossMessage() {
        return bossMessage;
    }
}
