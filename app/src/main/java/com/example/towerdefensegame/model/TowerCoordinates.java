package com.example.towerdefensegame.model;

import java.io.Serializable;

public class TowerCoordinates implements Serializable {
    private int x;
    private int y;
    private int towerType;
    private boolean upgraded;

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

    public int getTowerType() {
        return towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public boolean isUpgraded() {
        return upgraded;
    }

    public void setUpgraded(boolean upgrade) {
        this.upgraded = upgrade;
    }
}
