package com.example.towerdefensegame.model;
import static org.junit.Assert.*;

import com.example.towerdefensegame.CashAmount;
import com.example.towerdefensegame.FlameEnemy;
import com.example.towerdefensegame.NinjaEnemy;
import com.example.towerdefensegame.Enemy;
import com.example.towerdefensegame.SpiritEnemy;

import org.junit.Test;

public class MoneyTest {
    // this test can check each time the Ninja Enemy is hit, the cash increments by 1
    @Test
    public void moneyIncrementsNinja() {
        Enemy ninja = new NinjaEnemy(0, 0);
        Bullet b = new Bullet(1, 1, 20.0f, 20.0f);
        CashAmount money = new CashAmount(500);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        assertEquals(504, money.getAmount());
    }
    // this test can check each time the Flame Enemy is hit, the cash increments by 2
    @Test
    public void moneyIncrementsFlame() {
        Enemy ninja = new FlameEnemy();
        Bullet b = new Bullet(1, 1, 20.0f, 20.0f);
        CashAmount money = new CashAmount(500);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        assertEquals(508, money.getAmount());
    }

    // this test can check each time the Spirit Enemy is hit, the cash increments by 3
    @Test
    public void moneyIncrementsSpirit() {
        Enemy ninja = new SpiritEnemy();
        Bullet b = new Bullet(1, 1, 20.0f, 20.0f);
        CashAmount money = new CashAmount(500);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        b.attackEnemy(ninja, money);
        assertEquals(512, money.getAmount());
    }
}
