package com.example.towerdefensegame.model;

import static org.junit.Assert.*;

import com.example.towerdefensegame.Monument;

import org.junit.Test;

public class MonumentTest {
    // this test case checks if the monument's health is 1000.
    @Test
    public void monumentHealthIsEqualTo1000() {

        Monument monument = new Monument();
        assertEquals(1000, monument.getHealth());
    }

    // this test case makes sure monument's x-coordinate is 1712.
    @Test
    public void monumentXIsEqualTo1712() {

        Monument monument = new Monument();
        assertEquals(1712, monument.getX());
    }

    // this test case makes sure monument's y-coordinate is 150.
    @Test
    public void monumentYIsEqualTo150() {

        Monument monument = new Monument();
        assertEquals(150, monument.getY());
    }



}