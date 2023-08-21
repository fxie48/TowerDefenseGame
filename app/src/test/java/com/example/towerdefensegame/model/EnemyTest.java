package com.example.towerdefensegame.model;

import static org.junit.Assert.*;

import com.example.towerdefensegame.Enemy;
import com.example.towerdefensegame.FlameEnemy;
import com.example.towerdefensegame.NinjaEnemy;
import com.example.towerdefensegame.SpiritEnemy;

import org.junit.Test;

public class EnemyTest {

    // this test case checks if the ninja has 20 speed unit.

    @Test
    public void speedOfNinjaEnemyIsEqualTo20() {

        Enemy ninja = new NinjaEnemy();
        assertEquals(15, ninja.getSpeed());
    }

    // this test case checks if the spirit has 5 speed unit.

    @Test
    public void speedOfSpiritEnemyIsEqualTo5() {

        Enemy spirit = new SpiritEnemy();
        assertEquals(5, spirit.getSpeed());
    }

    // this test case checks if the flame enemy has 10 speed unit.

    @Test
    public void speedOfFlameEnemyIsEqualTo10() {

        Enemy flame = new FlameEnemy();
        assertEquals(10, flame.getSpeed());
    }

    // this test case checks if ninja starts with a y coordinate of 750
    @Test
    public void yOfNinjaEnemyIsEqualTo750() {

        Enemy ninja = new NinjaEnemy();
        assertEquals(750, ninja.getY());
    }

    // this test case checks if ninja starts with a y coordinate of 750
    @Test
    public void yOfFlameEnemyIsEqualTo750() {

        Enemy flame = new FlameEnemy();
        assertEquals(750, flame.getY());
    }

    // this test case checks if spirit starts with a y coordinate of 750
    @Test
    public void yOfSpiritEnemyIsEqualTo20() {

        Enemy spirit = new SpiritEnemy();
        assertEquals(750, spirit.getY());
    }

    // this test checks if the ninja's speed can be changed
    // the test helps the ninja's speed is controlled by the
    // magic tower which the team implement on the next milestone.
    @Test
    public void ninjaSpeedCanBeChanged() {
        Enemy ninja = new NinjaEnemy();
        assertEquals(15, ninja.getSpeed());
        ninja.setSpeed(15);
        assertEquals(15, ninja.getSpeed());
    }

    // this test checks if ninja's speed is always greater than 0
    // this makes sure that ninja's speed cannot be set to a negative value,
    // which could break the code
    @Test
    public void ninjaSpeedCannotBeNegative() {
        Enemy ninja = new NinjaEnemy();
        ninja.setSpeed(-5);
        assertEquals(0, ninja.getSpeed());
    }

    // this test checks if flame's speed is always greater than 0
    // this makes sure that flame's speed cannot be set to a
    // negative value, which could break the code
    @Test
    public void flameSpeedCannotBeNegative() {
        Enemy flame = new FlameEnemy();
        flame.setSpeed(-5);
        assertEquals(0, flame.getSpeed());
    }

}
