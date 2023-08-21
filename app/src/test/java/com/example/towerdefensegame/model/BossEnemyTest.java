package com.example.towerdefensegame.model;

import static org.junit.Assert.*;

import com.example.towerdefensegame.Enemy;

import org.junit.Test;



public class BossEnemyTest {
    @Test
    // this test can check if the speed of the boss is set with 5
    public void speedOfTheBossIsFive() {
        Enemy boss = new BossEnemy();
        assertEquals(5, boss.getSpeed());
    }
    @Test
    // this test can check if the boss is initiated in the right y position.
    public void theBossStartsOnTheRightPosition() {
        Enemy boss = new BossEnemy();
        assertEquals(750, boss.getY());
    }
    @Test
    // this test can check if the boss is initiated with health value 100.
    public void theHealthOfTheBossIs100() {
        Enemy boss = new BossEnemy();
        assertEquals(100, boss.getEnemyHealth());
    }
    @Test
    // the boss has 20 different jump steps to show the smooth movement on the screen.
    // This method can check if the step starts at index 1.
    public void theBossStartsJumpingOnTheRightStep() {
        BossEnemy boss = new BossEnemy();
        assertEquals(1, boss.getJumpMovement());
    }

    @Test
    // This testcase checks that the jump action of the boss can be changed.
    public void bossCheckIsJump() {
        BossEnemy boss = new BossEnemy();
        assertEquals(false, boss.isJump());
        boss.setJump(true);
        assertEquals(true, boss.isJump());
    }

    @Test
    // This testcase checks that the speed of the boss cannot be below 0.
    public void bossSpeedCannotBeBelowZero() {
        BossEnemy boss = new BossEnemy();
        boss.setSpeed(-5);
        assertEquals(0, boss.getSpeed());
    }
}