package com.example.towerdefensegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class NinjaEnemy extends Enemy {
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap enemy;
    private int speed;
    private Bitmap healthbar;
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


    private int enemyHealth;

    public NinjaEnemy(Resources res) {
        super();
        enemy = BitmapFactory.decodeResource(res, R.drawable.ninja);
        width = enemy.getWidth();
        height = enemy.getHeight();
        width /= 20;
        height /= 20;
        enemy = Bitmap.createScaledBitmap(enemy, width, height, false);
        speed = 25;
        y = 750;
        healthbar = BitmapFactory.decodeResource(res, R.drawable.healthbar);
        healthbar90 = BitmapFactory.decodeResource(res, R.drawable.healthbar90);
        healthbar80 = BitmapFactory.decodeResource(res, R.drawable.healthbar80);
        healthbar70 = BitmapFactory.decodeResource(res, R.drawable.healthbar70);
        healthbar60 = BitmapFactory.decodeResource(res, R.drawable.healthbar60);
        healthbar50 = BitmapFactory.decodeResource(res, R.drawable.healthbar50);
        healthbar40 = BitmapFactory.decodeResource(res, R.drawable.healthbar40);
        healthbar30 = BitmapFactory.decodeResource(res, R.drawable.healthbar30);
        healthbar20 = BitmapFactory.decodeResource(res, R.drawable.healthbar20);
        healthbar10 = BitmapFactory.decodeResource(res, R.drawable.healthbar10);
        healthbar0 = BitmapFactory.decodeResource(res, R.drawable.healthbar0);

        enemyHealth = 100;
    }

    public NinjaEnemy() {
        speed = 15;
        y = 750;
    }
    public NinjaEnemy(int x, int y) {
        this.x = x;
        this.y = y;
        enemyHealth = 100;
    }

    public Bitmap getEnemy() {

        return enemy;
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

    public int getSpeed() {

        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) {
            this.speed = 0;
        } else {
            this.speed = speed;
        }
    }
    public Rect getCollisionShape() {

        return new Rect(x, y, x + width / 2, y + height / 2);
    }

    public Bitmap getHealthbar() {
        if (enemyHealth == 100) {
            return healthbar;
        } else if (enemyHealth >= 90) {
            return healthbar90;
        } else if (enemyHealth >= 80) {
            return healthbar80;
        } else if (enemyHealth >= 70) {
            return healthbar70;
        } else if (enemyHealth >= 60) {
            return healthbar60;
        } else if (enemyHealth >= 50) {
            return healthbar50;
        } else if (enemyHealth >= 40) {
            return healthbar40;
        } else if (enemyHealth >= 30) {
            return healthbar30;
        } else if (enemyHealth >= 20) {
            return healthbar20;
        } else if (enemyHealth >= 10) {
            return healthbar10;
        } else {
            return healthbar0;
        }
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

}
