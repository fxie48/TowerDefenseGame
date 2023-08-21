package com.example.towerdefensegame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.towerdefensegame.CashAmount;
import com.example.towerdefensegame.Enemy;
import com.example.towerdefensegame.FlameEnemy;
import com.example.towerdefensegame.NinjaEnemy;
import com.example.towerdefensegame.R;
import com.example.towerdefensegame.SpiritEnemy;

import java.util.Random;

public class Bullet {
    private int x;
    private int y;
    private int width;
    private int height;
    private float xSpeed;
    private float ySpeed;
    private Bitmap bullet;
    private int range;
    private Enemy target;
    private Tower fromTower;
    private int projectileSpeed;
    private int max;
    private int min;
    private Random random;

    public Bullet(float xSpeed, float ySpeed, Tower fromTower, Enemy target, Resources res) {
        this.x = fromTower.getX() + fromTower.getWidth() / 2;
        this.y = fromTower.getY() + fromTower.getHeight() / 2;
        this.bullet = BitmapFactory.decodeResource(res, R.drawable.tigerweapon);
        this.range = 10;
        this.width = bullet.getWidth();
        this.height = bullet.getHeight();
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.target = target;
        this.fromTower = fromTower;
        this.projectileSpeed = 50;
        this.max = 9;
        this.min = 0;
        this.random = new Random();
        bullet = Bitmap.createScaledBitmap(bullet, width, height, false);
    }
    public Bullet(int x, int y, float xSpeed, float ySpeed) {
        this.x = x;
        this.y = y;
        this.range = 10;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.projectileSpeed = 50;
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
    public Bitmap getBullet() {
        return bullet;
    }

    public Rect getCollisionShape() {
        return new Rect(x, y, x + 1, y + 1);
    }

    public void attackEnemy(Enemy e, CashAmount m) {
        if (e instanceof BossEnemy) {
            if (!(e.isJump())) {
                e.setEnemyHealth(e.getEnemyHealth()  - 30);
            }
        } else {
            e.setEnemyHealth(e.getEnemyHealth()  - 10);
        }
        if (e instanceof NinjaEnemy) {
            m.setAmount(m.getAmount() + 1);
        } else if (e instanceof FlameEnemy) {
            m.setAmount(m.getAmount() + 2);
        } else if (e instanceof SpiritEnemy) {
            m.setAmount(m.getAmount() + 3);
        } else if (e instanceof BossEnemy) {
            m.setAmount(m.getAmount() + 5);
        }

    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public Tower getFromTower() {
        return fromTower;
    }

    public void setFromTower(Tower fromTower) {
        this.fromTower = fromTower;
    }

    public int getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setProjectileSpeed(int projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }
}
