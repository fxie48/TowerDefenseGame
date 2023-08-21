package com.example.towerdefensegame.model;

public class TigerTowerWeapon extends Weapon {
    public TigerTowerWeapon(int initialDamage) {
        if (initialDamage < 0) {
            this.setDamage(50);
        }
        this.setDamage(initialDamage);
    }
    public void upgradeWeapon() {
        if (this.getDamage() < 200) {
            this.setDamage(this.getDamage() + 50);
        } else {
            this.setDamage(this.getDamage());
        }
    }
}
