package com.example.towerdefensegame.model;

import static org.junit.Assert.*;

import com.example.towerdefensegame.CashAmount;
import com.example.towerdefensegame.GameView;
import com.example.towerdefensegame.NinjaEnemy;
import com.example.towerdefensegame.Enemy;
import com.example.towerdefensegame.FlameEnemy;
import com.example.towerdefensegame.SpiritEnemy;

import org.junit.Test;

public class BulletTest {
    // this test can check if the speed of projectiles is set with 50
    @Test
    public void speedOfBulletIsEqualTo50() {
        Bullet b = new Bullet(1, 1, 20.0f, 20.0f);
        assertEquals(50, b.getProjectileSpeed());
    }
    // this test would check getBulletDistance method works correctly.
    @Test
    public void bulletDistanceMethodCanCalculateDistanceBetweenBulletAndEnemy() {
        Bullet b = new Bullet(3, 0, 20.f, 20.f);
        NinjaEnemy e = new NinjaEnemy(0, 4);
        assertEquals(5, GameView.getBulletDistance(b, e));
    }
    
    // this test checks that the health of a Ninja decrements properly when hit by a bullet
    @Test
    public void bulletAttackNinjaEnemy() {
        Enemy ninja = new NinjaEnemy(0, 0);
        Bullet b = new Bullet(1, 1, 20.0f, 20.0f);
        CashAmount money = new CashAmount(500);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        assertEquals(60, ninja.getEnemyHealth());
    }
    
    // this test checks that the health of a Flame Enemy decrements properly when hit by a bullet
    @Test
    public void bulletAttackFlameEnemy() {
        FlameEnemy flame  = new FlameEnemy();
        CashAmount money = new CashAmount(500);
        Bullet b = new Bullet(1, 1, 20.0f, 20.0f);
        b.attackEnemy(flame, money);
        b.attackEnemy(flame, money);
        b.attackEnemy(flame, money);
        b.attackEnemy(flame, money);
        b.attackEnemy(flame, money);
        b.attackEnemy(flame, money);
        assertEquals(40, flame.getEnemyHealth());
    }
    // this test checks that the health of a Spirit Enemy decrements properly when hit by a bullet
    @Test
    public void bulletAttackSpiritEnemy() {
        SpiritEnemy spirit  = new SpiritEnemy();
        spirit.setEnemyHealth(200);
        CashAmount money = new CashAmount(500);
        Bullet b = new Bullet(1, 1, 20.0f, 20.0f);
        b.attackEnemy(spirit, money);
        b.attackEnemy(spirit, money);
        b.attackEnemy(spirit, money);
        b.attackEnemy(spirit, money);
        b.attackEnemy(spirit, money);
        assertEquals(150, spirit.getEnemyHealth());
    }

    @Test
    // this test checks that the health of a Boss Enemy decrements properly when hit by a bullet
    public void bulletAttackBossEnemy() {
        Enemy boss = new BossEnemy();
        CashAmount money = new CashAmount(500);
        Bullet b = new Bullet(1, 1, 20.0f, 20.0f);
        b.attackEnemy(boss, money);
        b.attackEnemy(boss, money);
        b.attackEnemy(boss, money);
        assertEquals(10, boss.getEnemyHealth());
    }
}
