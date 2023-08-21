package com.example.towerdefensegame.model;

import android.graphics.Bitmap;

public class FrogTowerWeapon extends Weapon {
    private Bitmap shoot;
    public FrogTowerWeapon(int initialDamage) {
        if (initialDamage < 0) {
            this.setDamage(10);
        }
        this.setDamage(initialDamage);
    }
    public void upgradeWeapon() {
        if (this.getDamage() < 50) {
            this.setDamage(this.getDamage() + 10);
        } else {
            this.setDamage(this.getDamage());
        }
    }

}
