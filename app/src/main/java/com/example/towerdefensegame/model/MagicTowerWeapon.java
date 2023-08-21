package com.example.towerdefensegame.model;

public class MagicTowerWeapon extends Weapon {
    public MagicTowerWeapon(int initialDamage) {
        if (initialDamage < 0) {
            this.setDamage(20);
        }
        this.setDamage(initialDamage);
    }
    public void upgradeWeapon() {
        if (this.getDamage() < 100) {
            this.setDamage(this.getDamage() + 20);
        } else {
            this.setDamage(this.getDamage());
        }
    }
}
