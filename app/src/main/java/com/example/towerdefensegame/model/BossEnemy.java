package com.example.towerdefensegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.towerdefensegame.Enemy;
import com.example.towerdefensegame.R;

public class BossEnemy extends Enemy {
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
    private Bitmap bossSide1;
    private Bitmap bossSide2;
    private Bitmap bossBack1;
    private Bitmap bossBack2;
    private Bitmap bossFront1;
    private Bitmap bossFront2;
    private Bitmap bossMessage;
    private Bitmap jump1;
    private Bitmap jump2;
    private Bitmap jump3;
    private Bitmap jump4;
    private Bitmap jump5;
    private Bitmap jump6;
    private Bitmap jump7;
    private Bitmap jump8;
    private Bitmap jump9;
    private Bitmap jump10;
    private Bitmap jump11;
    private Bitmap jump12;
    private Bitmap jump13;
    private Bitmap jump14;
    private Bitmap jump15;
    private Bitmap jump16;
    private Bitmap jump17;
    private Bitmap jump18;
    private Bitmap jump19;
    private Bitmap jump20;



    private int movement;
    private int jumpMovement;
    private boolean isJump;

    private int enemyHealth;
    public BossEnemy(Resources res) {
        super();

        bossMessage = BitmapFactory.decodeResource(res, R.drawable.bossmessage);
//        int messageWidth = 2 * bossMessage.getWidth();
//        int messageHeight = 2 * bossMessage.getHeight();
//        bossMessage = Bitmap.createScaledBitmap(bossMessage, messageWidth, messageHeight, false);

        enemy = BitmapFactory.decodeResource(res, R.drawable.bossside1);
        bossFront1 = BitmapFactory.decodeResource(res, R.drawable.bossfront1);
        bossFront2 = BitmapFactory.decodeResource(res, R.drawable.bossfront2);
        bossSide1 = BitmapFactory.decodeResource(res, R.drawable.bossside1);
        bossSide2 = BitmapFactory.decodeResource(res, R.drawable.bossside2);
        bossBack1 = BitmapFactory.decodeResource(res, R.drawable.bossback1);
        bossBack2 = BitmapFactory.decodeResource(res, R.drawable.bossback2);

        jump1 = BitmapFactory.decodeResource(res, R.drawable.jump1);
        jump2 = BitmapFactory.decodeResource(res, R.drawable.jump1);
        jump3 = BitmapFactory.decodeResource(res, R.drawable.jump1);
        jump4 = BitmapFactory.decodeResource(res, R.drawable.jump1);

        jump5 = BitmapFactory.decodeResource(res, R.drawable.jump2);
        jump6 = BitmapFactory.decodeResource(res, R.drawable.jump2);
        jump7 = BitmapFactory.decodeResource(res, R.drawable.jump2);
        jump8 = BitmapFactory.decodeResource(res, R.drawable.jump2);

        jump9 = BitmapFactory.decodeResource(res, R.drawable.jump3);
        jump10 = BitmapFactory.decodeResource(res, R.drawable.jump3);
        jump11 = BitmapFactory.decodeResource(res, R.drawable.jump3);
        jump12 = BitmapFactory.decodeResource(res, R.drawable.jump3);

        jump13 = BitmapFactory.decodeResource(res, R.drawable.jump4);
        jump14 = BitmapFactory.decodeResource(res, R.drawable.jump4);
        jump15 = BitmapFactory.decodeResource(res, R.drawable.jump4);
        jump16 = BitmapFactory.decodeResource(res, R.drawable.jump4);

        jump17 = BitmapFactory.decodeResource(res, R.drawable.jump5);
        jump18 = BitmapFactory.decodeResource(res, R.drawable.jump5);
        jump19 = BitmapFactory.decodeResource(res, R.drawable.jump5);
        jump20 = BitmapFactory.decodeResource(res, R.drawable.jump5);


        width = enemy.getWidth();
        height = enemy.getHeight();
        width *= 3;
        height *= 3;
        enemy = Bitmap.createScaledBitmap(enemy, width, height, false);
        bossSide1 = Bitmap.createScaledBitmap(bossSide1, width, height, false);
        bossSide2 = Bitmap.createScaledBitmap(bossSide2, width, height, false);
        bossFront1 = Bitmap.createScaledBitmap(bossFront1, width, height, false);
        bossFront2 = Bitmap.createScaledBitmap(bossFront2, width, height, false);
        bossBack1 = Bitmap.createScaledBitmap(bossBack1, width, height, false);
        bossBack2 = Bitmap.createScaledBitmap(bossBack2, width, height, false);

//        bossSide2 = Bitmap.createScaledBitmap(bossSide2, width, height, false);
//        bossFront1 = Bitmap.createScaledBitmap(bossFront1, width, height, false);
//        bossFront2 = Bitmap.createScaledBitmap(bossFront2, width, height, false);
//        bossBack1 = Bitmap.createScaledBitmap(bossBack1, width, height, false);
//        bossBack2 = Bitmap.createScaledBitmap(bossBack2, width, height, false);



        speed = 10;
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
        movement = 0;
        jumpMovement = 1;
        enemyHealth = 100;
        isJump = false;
    }
    public BossEnemy() {
        movement = 0;
        jumpMovement = 1;
        enemyHealth = 100;
        isJump = false;
        speed = 10;
        y = 750;
    }



    public Bitmap getEnemy() {
        return enemy;
    }

    public void setEnemySide() {
        if (movement == 0) {
            enemy = bossSide1;
            movement = 1;
        } else {
            enemy = bossSide2;
            movement = 0;
        }
    }
    public void setEnemyFront() {
        if (movement == 0) {
            enemy = bossFront1;
            movement = 1;
        } else {
            enemy = bossFront2;
            movement = 0;
        }
    }
    public void setEnemyBack() {
        if (movement == 0) {
            enemy = bossBack1;
            movement = 1;
        } else {
            enemy = bossBack2;
            movement = 0;
        }
    }
    public void setEnemyJump() {
        isJump = true;
        doJump();

    }
    public void doJump() {
        if (jumpMovement == 1) {
            enemy = jump1;
            jumpMovement++;
        } else if (jumpMovement == 2) {
            enemy = jump2;
            jumpMovement++;

        } else if (jumpMovement == 3) {
            enemy = jump3;
            jumpMovement++;

        } else if (jumpMovement == 4) {
            enemy = jump4;
            jumpMovement++;

        } else if (jumpMovement == 5) {
            enemy = jump5;
            jumpMovement++;

        } else if (jumpMovement == 6) {
            enemy = jump6;
            jumpMovement++;

        } else if (jumpMovement == 7) {
            enemy = jump7;
            jumpMovement++;

        } else if (jumpMovement == 8) {
            enemy = jump8;
            jumpMovement++;

        } else if (jumpMovement == 9) {
            enemy = jump9;
            jumpMovement++;

        } else if (jumpMovement == 10) {
            enemy = jump10;
            jumpMovement++;

        } else if (jumpMovement == 11) {
            enemy = jump11;
            jumpMovement++;
        } else if (jumpMovement == 12) {
            enemy = jump12;
            jumpMovement++;
        } else if (jumpMovement == 13) {
            enemy = jump13;
            jumpMovement++;
        } else if (jumpMovement == 14) {
            enemy = jump14;
            jumpMovement++;
        } else if (jumpMovement == 15) {
            enemy = jump15;
            jumpMovement++;
        } else if (jumpMovement == 16) {
            enemy = jump16;
            jumpMovement++;
        } else if (jumpMovement == 17) {
            enemy = jump17;
            jumpMovement++;
        } else if (jumpMovement == 18) {
            enemy = jump18;
            jumpMovement++;
        } else if (jumpMovement == 19) {
            enemy = jump19;
            jumpMovement++;
        } else if (jumpMovement == 20) {
            enemy = jump20;
            jumpMovement = 1;
            isJump = false;
        }
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

    public int getJumpMovement() {
        return jumpMovement;
    }

    public Bitmap getBossMessage() {
        return bossMessage;
    }

    public boolean isJump() {
        return isJump;
    }

    public void setJump(boolean jump) {
        isJump = jump;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }
}
