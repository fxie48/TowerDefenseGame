package com.example.towerdefensegame.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TowerTest {

    @Test
    public void damageFromFrogTowerWeaponIsUpgradedByTen() {
        Tower frogTower = new FrogTower();
        frogTower.getWeapon().upgradeWeapon();
        assertEquals(20, frogTower.getWeapon().getDamage());
    }

    @Test
    public void frogTowerHasTheMaximumDamageToBeUpgraded() {
        Tower frogTower = new FrogTower();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        assertEquals(50, frogTower.getWeapon().getDamage());
    }

    // This tests that magicTower's damage is updated by 20 everytime it gets upgraded
    @Test
    public void magicTowerWeaponUpdatedCorrectly() {
        Tower magicTower = new MagicTower();
        magicTower.getWeapon().upgradeWeapon();
        assertEquals(40, magicTower.getWeapon().getDamage());
    }

    // This tests that magicTower's damage can't further get
    // updated when it reaches its maximum damage
    @Test
    public void magicTowerWeaponCannotExceedMaximumDamage() {
        Tower magicTower = new MagicTower();
        magicTower.getWeapon().upgradeWeapon();
        magicTower.getWeapon().upgradeWeapon();
        magicTower.getWeapon().upgradeWeapon();
        magicTower.getWeapon().upgradeWeapon();
        magicTower.getWeapon().upgradeWeapon();
        magicTower.getWeapon().upgradeWeapon();
        assertEquals(100, magicTower.getWeapon().getDamage());

    }
    // checks to see that the tigerTowerWeapon is upgrading properly
    @Test
    public void tigerTowerWeaponUpgradeTest() {
        Tower tigerTower = new TigerTower();
        tigerTower.getWeapon().upgradeWeapon();
        assertEquals(100, tigerTower.getWeapon().getDamage());
    }

    // checks that the the tigerTowerWeapon cannot be
    // upgraded further than its maximum of 200
    @Test
    public void tigerTowerWeaponMaximum() {
        Tower tigerTower = new TigerTower();
        tigerTower.getWeapon().upgradeWeapon();
        tigerTower.getWeapon().upgradeWeapon();
        tigerTower.getWeapon().upgradeWeapon();
        tigerTower.getWeapon().upgradeWeapon();
        tigerTower.getWeapon().upgradeWeapon();
        assertEquals(200, tigerTower.getWeapon().getDamage());
    }

    //tests to check if upgrading frog tower three times gives us a damage of 40
    @Test
    public void frogTowerDamageThreeUpgrades() {
        Tower frogTower = new FrogTower();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        frogTower.getWeapon().upgradeWeapon();
        assertEquals(40, frogTower.getWeapon().getDamage());
    }
    //tests to check if upgrading magic tower three times gives us a damage of 80
    @Test
    public void magicTowerDamageThreeUpgrades() {
        Tower magicTower = new MagicTower();
        magicTower.getWeapon().upgradeWeapon();
        magicTower.getWeapon().upgradeWeapon();
        magicTower.getWeapon().upgradeWeapon();
        assertEquals(80, magicTower.getWeapon().getDamage());
    }

    //tests to check if upgrading magic tower once gives a damage of 100
    @Test
    public void tigerTowerDamageOnceUpgrades() {
        Tower tigerTower = new TigerTower();
        tigerTower.getWeapon().upgradeWeapon();
        assertEquals(100, tigerTower.getWeapon().getDamage());
    }

    //tests to check if upgrading the magic tower twice gives a damage of 150
    @Test
    public void tigerTowerDamageTwiceUpgrades() {
        Tower tigerTower = new TigerTower();
        tigerTower.getWeapon().upgradeWeapon();
        tigerTower.getWeapon().upgradeWeapon();
        assertEquals(150, tigerTower.getWeapon().getDamage());
    }

    // This test makes sure that frog tower can be determined upgraded or not
    @Test
    public void checkFrogTowerUpgradedOrNot() {
        Tower frogT = new upgradedFrogTower();
        assertEquals(false, frogT.isUpgraded());
        frogT.setUpgraded(true);
        assertEquals(true, frogT.isUpgraded());
    }

    // This test makes sure that frog tower can be determined upgraded or not
    @Test
    public void checkMagicTowerUpgradedOrNot() {
        Tower magicT = new upgradedMagicTower();
        assertEquals(false, magicT.isUpgraded());
        magicT.setUpgraded(true);
        assertEquals(true, magicT.isUpgraded());
    }

    @Test
    // This test makes sure that Tiger tower can be determined upgraded or not
    public void checkTigerTowerUpgradedOrNot() {
        Tower tigerT = new upgradedTigerTower();
        assertEquals(false, tigerT.isUpgraded());
        tigerT.setUpgraded(true);
        assertEquals(true, tigerT.isUpgraded());
    }

}

