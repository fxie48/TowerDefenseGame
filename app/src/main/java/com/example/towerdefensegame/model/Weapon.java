package com.example.towerdefensegame.model;

import android.graphics.Bitmap;

public abstract class Weapon {
    private String name;
    private int damage;
    private Bitmap shoot;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    abstract void upgradeWeapon();

}
