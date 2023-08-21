package com.example.towerdefensegame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.TextView;

import com.example.towerdefensegame.model.BossEnemy;
import com.example.towerdefensegame.model.Bullet;
import com.example.towerdefensegame.model.FrogTower;
import com.example.towerdefensegame.model.Heart;
import com.example.towerdefensegame.model.HealthBar;
import com.example.towerdefensegame.model.MagicTower;
import com.example.towerdefensegame.model.TigerTower;
import com.example.towerdefensegame.model.Tower;
import com.example.towerdefensegame.model.TowerCoordinates;
import com.example.towerdefensegame.model.upgradedFrogTower;
import com.example.towerdefensegame.model.upgradedMagicTower;
import com.example.towerdefensegame.model.upgradedTigerTower;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    private static int enemiesKilled;
    private Context mContext;
    private Thread thread;
    private boolean isPlaying;
    private boolean isGameOver = false;
    private boolean victory = false;
    private Background background;
    private int screenX;
    private int screenY;
    private Monument monument;
    private float screenRatioX;
    private float screenRatioY;
    private ArrayList<Enemy> ninjas;
    private ArrayList<Enemy> flames;
    private ArrayList<Enemy> spirits;
    private int betweenMonster;
    private Paint paint;
    private ArrayList<TowerCoordinates> towerCoordinates;
    private ArrayList<Tower> towers;
    private int stageOne = 1;
    private int stageTwo = 2;
    private int stageThree = 3;
    private int difficulty;
    private String difficultyLevel;
    private int money;
    private TextView moneyView;
    private Money goldCoin;
    private CashAmount cash;
    private double health;
    private int heartCounter;
    private ArrayList<Heart> heartsGarbage;
    private ArrayList<Heart> hearts;
    private ArrayList<Bullet> bulletArray;
    private ArrayList<Bullet> trashBullet;
    private ArrayList<Enemy> trashEnemies;
    private ArrayList<Enemy> boss;
    private int totalEnemies = 0;
    private ArrayList<HealthBar> healthbars;
    private Paint moneyPaint;
    private boolean battled;
    private HashMap<Integer, Integer> magicTLocation;
    private HashMap<Integer, Integer> upgradedMagicTLocation;
    private Random random;
    private int max;
    private int min;

    public GameView(Context context, int screenX, int screenY,
                    ArrayList<TowerCoordinates> towerCoordinates,
                    String difficultyLevel, int finalMoney,
                    boolean battled, int updatedHeartCount) {
        super(context);
        this.mContext = context;
        this.towerCoordinates = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.towerCoordinates = towerCoordinates;
        this.screenX = screenX;
        this.screenY = screenY;
        this.difficultyLevel = difficultyLevel;
        this.difficulty = stageThree;
        this.money = finalMoney;
        this.battled = battled;

        screenRatioX = 2340f / screenX;
        screenRatioY = 1080f / screenY;
        background = new Background(screenX, screenY, getResources());
        monument = new Monument(screenX, screenY, getResources());
        paint = new Paint();
        ninjas = new ArrayList<>();
        flames  = new ArrayList<>();
        spirits = new ArrayList<>();
        boss = new ArrayList<>();
        trashEnemies = new ArrayList<>();
        healthbars = new ArrayList<>();
        bulletArray = new ArrayList<>();
        trashBullet = new ArrayList<>();
        configureHearts(difficultyLevel, updatedHeartCount);
        initializeTowers(towerCoordinates);
        initializeHearts();
        initializeEnemies();
        initializeCoin();
        random = new Random();
        max = 9;
        min = 0;
        moneyView = new TextView(this.getContext());
        moneyPaint = new Paint();
    }
  
    private int frogTowerNum;
    private void initializeTowers(ArrayList<TowerCoordinates> towerCoordinates) {
        magicTLocation = new HashMap<>();
        upgradedMagicTLocation = new HashMap<>();
        frogTowerNum = 0;
        for (TowerCoordinates each : towerCoordinates) {
            if (each.getTowerType() == 1) {
                Tower tower;
                if (each.isUpgraded()) {
                    tower = new upgradedFrogTower(this, each.getX(), each.getY(), getResources());
                } else {
                    tower = new FrogTower(this, each.getX(), each.getY(), getResources());
                }
                towers.add(tower);
                frogTowerNum++;
            } else if (each.getTowerType() == 2) {
                Tower tower;
                if (each.isUpgraded()) {
                    tower = new upgradedMagicTower(each.getX(), each.getY(), getResources());
                    upgradedMagicTLocation.put(each.getX(), each.getY());
                } else {
                    tower = new MagicTower(each.getX(), each.getY(), getResources());
                    magicTLocation.put(each.getX(), each.getY());
                }
                towers.add(tower);
            } else if (each.getTowerType() == 3) {
                Tower tower;
                if (each.isUpgraded()) {
                    tower = new upgradedTigerTower(this, each.getX(), each.getY(), getResources());
                } else {
                    tower = new TigerTower(this, each.getX(), each.getY(), getResources());
                }
                towers.add(tower);
            }
        }
    }

    private void initializeEnemies() {
        for (int i = 0; i < difficulty + 2 - frogTowerNum; i++) {
            totalEnemies++;
            Enemy spirit = new SpiritEnemy(getResources());
            spirit.setX(betweenMonster);
            HealthBar healthbar = new HealthBar(spirit.getX(), spirit.getY() + 5, getResources());
            spirits.add(spirit);
            healthbars.add(healthbar);
            betweenMonster -= 150;
        }
        betweenMonster = 0;

        if (frogTowerNum > difficulty + 2) {
            frogTowerNum -= (difficulty + 2);
        } else {
            frogTowerNum = 0;
        }
        for (int i = 0; i < difficulty + 1 - frogTowerNum; i++) {
            totalEnemies++;
            Enemy flame = new FlameEnemy(getResources());
            flame.setX(betweenMonster);
            HealthBar healthbar = new HealthBar(flame.getX(), flame.getY() + 5, getResources());
            flames.add(flame);
            healthbars.add(healthbar);
            betweenMonster -= 150;
        }
        betweenMonster = 0;

        if (frogTowerNum > difficulty + 1) {
            frogTowerNum -= (difficulty + 1);
        } else {
            frogTowerNum = 0;
        }
        for (int i = 0; i < difficulty + 1 - frogTowerNum; i++) {
            totalEnemies++;
            Enemy ninja = new NinjaEnemy(getResources());
            ninja.setX(betweenMonster);
            HealthBar healthbar = new HealthBar(ninja.getX(), ninja.getY() + 5, getResources());
            ninjas.add(ninja);
            healthbars.add(healthbar);
            betweenMonster -= 150;
        }
        betweenMonster -= 2500;
        for (int i = 0; i < 1; i++) {
            totalEnemies++;
            Enemy finalBoss = new BossEnemy(getResources());
            finalBoss.setX(betweenMonster);
            HealthBar healthbar = new HealthBar(finalBoss.getX(), finalBoss.getY() + 5, getResources());
            boss.add(finalBoss);
            healthbars.add(healthbar);
        }
    }

    private void initializeHearts() {
        int posX1 = -65;
        int posX2 = -65;
        int posY1 = 10;
        int poxY2 = 125;
        heartCounter = 0;
        for (int i = 0; i < (int) health; i++) {
            Heart h = new Heart(0, 0, getResources());
            if (heartCounter >= 6) {
                h.setY(poxY2);
                posX2 += 85;
                h.setX(posX2);
            } else {
                h.setY(posY1);
                posX1 += 85;
                h.setX(posX1);
            }
            hearts.add(h);

            heartCounter++;
        }
    }

    private void initializeCoin() {
        if (difficultyLevel.equals("Hard")) {
            goldCoin = new Money(20, 250, getResources());
            cash = new CashAmount(money);
        } else if (difficultyLevel.equals("Medium")) {
            goldCoin = new Money(20, 250, getResources());
            cash = new CashAmount(money);
        } else if (difficultyLevel.equals("Easy")) {
            goldCoin = new Money(20, 250, getResources());
            cash = new CashAmount(money);
        }
    }

    private void configureHearts(String difficultyLevel, int updatedHeartCount) {
        health = updatedHeartCount;
        this.hearts = new ArrayList<>((int) health);
        this.heartsGarbage = new ArrayList<>((int) health);

        if (difficultyLevel.equals("Hard")) {
            difficulty = 3;
        } else if (difficultyLevel.equals("Medium")) {
            difficulty = 2;
        } else if (difficultyLevel.equals("Easy")) {
            difficulty = 1;
        }


    }


    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {
        updateNinjas();
        updateFlames();
        updateSpirits();
        updateBoss();
        updateDetectionEnemies();
        updateProjectile();
        updateTrashObject();
    }

    private void updateTrashObject() {
        for (Bullet bullet : trashBullet) {
            bulletArray.remove(bullet);
        }

        for (Enemy trash : trashEnemies) {
            if (trash instanceof NinjaEnemy) {
                ninjas.remove(trash);
            } else if (trash instanceof FlameEnemy) {
                flames.remove(trash);
            } else if (trash instanceof SpiritEnemy) {
                spirits.remove(trash);
            } else if (trash instanceof BossEnemy) {
                boss.remove(trash);
            }
        }
    }

    private void updateDetectionEnemies() {
        for (Enemy ninja : ninjas) {
            for (Tower tower : towers) {
                if (tower instanceof TigerTower) {
                    if (tower.getCoolDownWeapon() >= 20 && getDistance(tower, ninja) < 200) {
                        newProjectile(tower, ninja);
                        tower.setCoolDownWeapon(0);
                    } else {
                        tower.setCoolDownWeapon(tower.getCoolDownWeapon() + 1);
                    }
                } else if (tower instanceof upgradedTigerTower) {
                    if (tower.getCoolDownWeapon() >= 10 && getDistance(tower, ninja) < 200) {
                        newProjectile(tower, ninja);
                        tower.setCoolDownWeapon(0);
                    } else {
                        tower.setCoolDownWeapon(tower.getCoolDownWeapon() + 1);
                    }
                }
            }
        }
        for (Enemy flame : flames) {
            for (Tower tower : towers) {
                if (tower instanceof TigerTower) {
                    if (tower.getCoolDownWeapon() >= 20 && getDistance(tower, flame) < 200) {
                        newProjectile(tower, flame);
                        tower.setCoolDownWeapon(0);
                    } else {
                        tower.setCoolDownWeapon(tower.getCoolDownWeapon() + 1);
                    }
                } else if (tower instanceof upgradedTigerTower) {
                    if (tower.getCoolDownWeapon() >= 10 && getDistance(tower, flame) < 200) {
                        newProjectile(tower, flame);
                        tower.setCoolDownWeapon(0);
                    } else {
                        tower.setCoolDownWeapon(tower.getCoolDownWeapon() + 1);
                    }
                }
            }
        }
        for (Enemy spirit : spirits) {
            for (Tower tower : towers) {
                if (tower instanceof TigerTower) {
                    if (tower.getCoolDownWeapon() >= 20 && getDistance(tower, spirit) < 200) {
                        newProjectile(tower, spirit);
                        tower.setCoolDownWeapon(0);
                    } else {
                        tower.setCoolDownWeapon(tower.getCoolDownWeapon() + 1);
                    }
                } else if (tower instanceof upgradedTigerTower) {
                    if (tower.getCoolDownWeapon() >= 10 && getDistance(tower, spirit) < 200) {
                        newProjectile(tower, spirit);
                        tower.setCoolDownWeapon(0);
                    } else {
                        tower.setCoolDownWeapon(tower.getCoolDownWeapon() + 1);
                    }
                }
            }
        }
        for (Enemy b : boss) {
            for (Tower tower : towers) {
                if (tower instanceof TigerTower) {
                    if (tower.getCoolDownWeapon() >= 20 && getDistance(tower, b) < 200) {
                        newProjectile(tower, b);
                        tower.setCoolDownWeapon(0);
                    } else {
                        tower.setCoolDownWeapon(tower.getCoolDownWeapon() + 1);
                    }
                } else if (tower instanceof upgradedTigerTower) {
                    if (tower.getCoolDownWeapon() >= 10 && getDistance(tower, b) < 200) {
                        newProjectile(tower, b);
                        tower.setCoolDownWeapon(0);
                    } else {
                        tower.setCoolDownWeapon(tower.getCoolDownWeapon() + 1);
                    }
                }
            }
        }
    }

    private void updateHeart() {
        if ((int) health <= 1) {
            isGameOver = true;
        } else {
            Heart h = hearts.remove((int) health - 1);
            heartCounter--;
            h.setX(2000);
            h.setY(2200);
            heartsGarbage.add(h);
            health -= 1.0;
            System.out.println("health is: " + health);
        }
    }

    private void updateNinjas() {
        enemyMotion(ninjas);
    }

    private void updateFlames() {
        enemyMotion(flames);
    }

    private void updateSpirits() {
        enemyMotion(spirits);
    }

    private void updateBoss() {enemyMotion(boss);}

    private void enemyMotion(ArrayList<Enemy> enemyType) {
        for (Enemy enemy : enemyType) {
            if (enemy != null) {
                int type = 0;
                if (enemyType == boss) {
                    type = 3;
                } else if (enemyType == ninjas) {
                    type = 2;
                } else if (enemyType == flames) {
                    type = 1;
                } else if (enemyType == spirits) {
                    type = 1;
                }
                boolean slow = false;
                int slowRate = 0;
                if (enemy.getX() + enemy.getWidth() > this.screenX) {
                    enemy.setX(0);
                } else if (enemy.getX() < 300) {
                    // 1st go to the right
                    for (int num : magicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200) {
                            slow = true;
                            slowRate++;
                        }
                    }
                    for (int num : upgradedMagicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200) {
                            slow = true;
                            slowRate += 2;
                        }
                    }
                    if (slow) {
                        enemy.setX(enemy.getX() + enemy.getSpeed() - slowRate * type);
                    } else {
                        enemy.setX(enemy.getX() + enemy.getSpeed());
                        if (type == 3) {

                            int rand = random.nextInt(max - min + 1) + min;
                            if (enemy.isJump() || rand >= 8) {
                                enemy.setEnemyJump();
                            } else {
                                enemy.setEnemySide();

                            }
                        }
                    }
                } else if (300 <= enemy.getX() && enemy.getX() < 700 && 190 <= enemy.getY()) {
                    // 2nd go to up
                    for (int num : magicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200
                                && enemy.getY() - 200 <= magicTLocation.get(num)
                                && magicTLocation.get(num) < enemy.getY() + 200) {
                            slow = true;
                            slowRate++;
                        }
                    }
                    for (int num : upgradedMagicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200
                                && enemy.getY() - 200 <= upgradedMagicTLocation.get(num)
                                && upgradedMagicTLocation.get(num) < enemy.getY() + 200) {
                            slow = true;
                            slowRate += 2;
                        }
                    }
                    if (slow) {
                        enemy.setY(enemy.getY() - enemy.getSpeed() + slowRate * type);
                    } else {
                        enemy.setY(enemy.getY() - enemy.getSpeed());
                    }
                    if (type == 3) {
                        int rand = random.nextInt(max - min + 1) + min;
                        if (enemy.isJump() || rand >= 8) {
                            enemy.setJump(true);
                            enemy.setEnemyJump();
                        } else {
                            enemy.setEnemyBack();

                        }
                    }
                } else if (300 <= enemy.getX() && enemy.getX() < 700 && enemy.getX() < 700) {
                    // 3rd go to the right
                    for (int num : magicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200) {
                            slow = true;
                            slowRate++;
                        }
                    }
                    for (int num : upgradedMagicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200) {
                            slow = true;
                            slowRate += 2;
                        }
                    }
                    if (slow) {
                        enemy.setX(enemy.getX() + enemy.getSpeed() - slowRate * type);
                    } else {
                        enemy.setX(enemy.getX() + enemy.getSpeed());
                    }
                    if (type == 3) {
                        int rand = random.nextInt(max - min + 1) + min;
                        if (enemy.isJump() || rand >= 8) {
                            enemy.setJump(true);
                            enemy.setEnemyJump();
                        } else {
                            enemy.setEnemySide();

                        }
                    }
                } else if (700 <= enemy.getX() && enemy.getX() < 1080 && enemy.getY() < 450) {
                    // 4th go to the down
                    for (int num : magicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200
                                && enemy.getY() - 200 <= magicTLocation.get(num)
                                && magicTLocation.get(num) < enemy.getY() + 200) {
                            slow = true;
                            slowRate++;
                        }
                    }
                    for (int num : upgradedMagicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200
                                && enemy.getY() - 200 <= upgradedMagicTLocation.get(num)
                                && upgradedMagicTLocation.get(num) < enemy.getY() + 200) {
                            slow = true;
                            slowRate += 2;
                        }
                    }
                    if (slow) {
                        enemy.setY(enemy.getY() + enemy.getSpeed() - slowRate * type);
                    } else {
                        enemy.setY(enemy.getY() + enemy.getSpeed());
                    }
                    if (type == 3) {
                        int rand = random.nextInt(max - min + 1) + min;
                        if (enemy.isJump() || rand >= 8) {
                            enemy.setJump(true);
                            enemy.setEnemyJump();
                        } else {
                            enemy.setEnemyFront();

                        }
                    }
                } else if (700 <= enemy.getX() && enemy.getX() < 1080) {
                    // 5th go to the right
                    for (int num : magicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200) {
                            slow = true;
                            slowRate++;
                        }
                    }
                    for (int num : upgradedMagicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200) {
                            slow = true;
                            slowRate += 2;
                        }
                    }
                    if (slow) {
                        enemy.setX(enemy.getX() + enemy.getSpeed() - slowRate * type);
                    } else {
                        enemy.setX(enemy.getX() + enemy.getSpeed());
                    }
                    if (type == 3) {
                        int rand = random.nextInt(max - min + 1) + min;
                        if (enemy.isJump() || rand >= 8) {
                            enemy.setJump(true);
                            enemy.setEnemyJump();
                        } else {
                            enemy.setEnemySide();

                        }
                    }
                } else if (1080 <= enemy.getX() && enemy.getX() < 1650
                        && 450 <= enemy.getY() && enemy.getY() < 750) {
                    // 6th go to the down
                    for (int num : magicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200
                                && enemy.getY() - 200 <= magicTLocation.get(num)
                                && magicTLocation.get(num) < enemy.getY() + 200) {
                            slow = true;
                            slowRate++;
                        }
                    }
                    for (int num : upgradedMagicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200
                                && enemy.getY() - 200 <= upgradedMagicTLocation.get(num)
                                && upgradedMagicTLocation.get(num) < enemy.getY() + 200) {
                            slow = true;
                            slowRate += 2;
                        }
                    }
                    if (slow) {
                        enemy.setY(enemy.getY() + enemy.getSpeed() - slowRate * type);
                    } else {
                        enemy.setY(enemy.getY() + enemy.getSpeed());
                    }
                    if (type == 3) {
                        int rand = random.nextInt(max - min + 1) + min;
                        if (enemy.isJump() || rand >= 8) {
                            enemy.setJump(true);
                            enemy.setEnemyJump();
                        } else {
                            enemy.setEnemyFront();

                        }
                    }
                } else if (1080 <= enemy.getX() && enemy.getX() < 1650) {
                    // 7th go to the right
                    for (int num : magicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200) {
                            slow = true;
                            slowRate++;
                        }
                    }
                    for (int num : upgradedMagicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200) {
                            slow = true;
                            slowRate += 2;
                        }
                    }
                    if (slow) {
                        enemy.setX(enemy.getX() + enemy.getSpeed() - slowRate * type);
                    } else {
                        enemy.setX(enemy.getX() + enemy.getSpeed());
                    }
                    if (type == 3) {
                        int rand = random.nextInt(max - min + 1) + min;
                        if (enemy.isJump() || rand >= 8) {
                            enemy.setJump(true);
                            enemy.setEnemyJump();
                        } else {
                            enemy.setEnemySide();

                        }
                    }
                } else if (1650 <= enemy.getX()) {
                    // 8th go to the up
                    for (int num : magicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200
                                && enemy.getY() - 200 <= magicTLocation.get(num)
                                && magicTLocation.get(num) < enemy.getY() + 200) {
                            slow = true;
                            slowRate++;
                        }
                    }
                    for (int num : upgradedMagicTLocation.keySet()) {
                        if (enemy.getX() - 200 <= num && num < enemy.getX() + 200
                                && enemy.getY() - 200 <= upgradedMagicTLocation.get(num)
                                && upgradedMagicTLocation.get(num) < enemy.getY() + 200) {
                            slow = true;
                            slowRate += 2;
                        }
                    }
                    if (slow) {
                        enemy.setY(enemy.getY() - enemy.getSpeed() + slowRate * type);
                    } else {
                        enemy.setY(enemy.getY() - enemy.getSpeed());
                    }
                    if (type == 3) {
                        int rand = random.nextInt(max - min + 1) + min;
                        if (enemy.isJump() || rand >= 8) {
                            enemy.setJump(true);
                            enemy.setEnemyJump();
                        } else {
                            enemy.setEnemyBack();

                        }
                    }
                }
                if (Rect.intersects(enemy.getCollisionShape(), monument.getCollisionShape())) {
                    enemy.setX(2000);
                    trashEnemies.add(enemy);
                    if (enemyType == spirits) {
                        updateHeart();
                        updateHeart();
                    } else if (enemyType == flames) {
                        updateHeart();
                        updateHeart();
                    } else if (enemyType == ninjas) {
                        updateHeart();
                    } else if (enemyType == boss) {
                        updateHeart();
                        updateHeart();
                        updateHeart();
                    }
                    return;
                }
            }

        }

    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background.getBackground(),
                    background.getX(), background.getY(), paint);
            canvas.drawBitmap(monument.getMonument(), monument.getX(), monument.getY(), paint);
            for (Tower tower : towers) {

                canvas.drawBitmap(tower.getTower(), tower.getX(), tower.getY(), paint);
            }

            for (Heart heart : hearts) {
                canvas.drawBitmap(heart.getHeart(), heart.getX(), heart.getY(), paint);
            }

            canvas.drawBitmap(goldCoin.getMoney(), goldCoin.getX(), goldCoin.getY(), paint);


            moneyPaint.setTextSize(40.0f);
            moneyPaint.setColor(Color.WHITE);
            canvas.drawText(Integer.toString(money), 80, 290, moneyPaint);

            for (Enemy enemy : ninjas) {
                if (enemy != null) {
                    canvas.drawBitmap(enemy.getEnemy(), enemy.getX(), enemy.getY(), paint);
                    canvas.drawBitmap(enemy.getHealthbar(), enemy.getX() + 30,
                            enemy.getY() - 5, paint);
                }
            }
            for (Enemy enemy : flames) {
                if (enemy != null) {
                    canvas.drawBitmap(enemy.getEnemy(), enemy.getX(), enemy.getY(), paint);
                    canvas.drawBitmap(enemy.getHealthbar(), enemy.getX() + 20,
                            enemy.getY() - 10, paint);
                }
            }
            for (Enemy enemy : spirits) {
                if (enemy != null) {
                    canvas.drawBitmap(enemy.getEnemy(), enemy.getX(), enemy.getY(), paint);
                    canvas.drawBitmap(enemy.getHealthbar(), enemy.getX() + 20,
                            enemy.getY() - 10, paint);
                }
            }
            for (Enemy enemy : boss) {
                if (enemy != null) {
                    if (enemy.getX() > 0) {
                        canvas.drawBitmap(enemy.getBossMessage(), 580, 0, paint);
                    }
                     canvas.drawBitmap(enemy.getEnemy(), enemy.getX(), enemy.getY(), paint);
                    canvas.drawBitmap(enemy.getHealthbar(), enemy.getX() + 20,
                            enemy.getY() - 10, paint);
                }
            }
            for (Bullet bullet : bulletArray) {
                canvas.drawBitmap(bullet.getBullet(), bullet.getX(), bullet.getY(), paint);
            }
            getHolder().unlockCanvasAndPost(canvas);
            if (isGameOver) {
                isPlaying = false;
                gameOver();
            }
        }


    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }
    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void gameOver() {
        Intent i = new Intent(mContext, GameOverScreen.class);
        i.putExtra("money", money);
        mContext.startActivity(i);
    }

    private void victory(int money) {
        Intent i = new Intent(mContext, VictoryScreen.class);
        i.putExtra("money", money);
        mContext.startActivity(i);
    }

    public static int getDistance(Tower t, Enemy e) {
        int xDiff = Math.abs(t.getX() - e.getX());
        int yDiff = Math.abs(t.getY() - e.getY());
        return (int) Math.hypot(xDiff, yDiff);
    }
    public static int getBulletDistance(Bullet t, Enemy e) {
        int xDiff = Math.abs(t.getX() - e.getX());
        int yDiff = Math.abs(t.getY() - e.getY());
        return (int) Math.hypot(xDiff, yDiff);
    }
    public void newProjectile(Tower t, Enemy e) {
        int xDiff = Math.abs(t.getX() - e.getX());
        int yDiff = Math.abs(t.getY() - e.getY());
        int toDiff = xDiff + yDiff;
        float xPer = (float) xDiff / (float) toDiff;
        float yPer = 1.0f - xPer;
        float xSpeed = xPer;
        float ySpeed = yPer;
        if (t.getX() > e.getX()) {
            xSpeed *= -1;
        }
        if (t.getY() > e.getY()) {
            ySpeed *= -1;
        }
        Bullet newBullet = new Bullet(xSpeed, ySpeed, t,  e, getResources());
        bulletArray.add(newBullet);
    }

    public void updateProjectile() {
        for (Bullet bullet : bulletArray) {
            bullet.setX((int) (bullet.getX() + bullet.getxSpeed() * bullet.getProjectileSpeed()));
            bullet.setY((int) (bullet.getY() + bullet.getySpeed() * bullet.getProjectileSpeed()));
            if (Rect.intersects(bullet.getTarget().getCollisionShape(),
                    bullet.getCollisionShape())) {
                money++;
                moneyView.setText(Integer.toString(money));
                bullet.setX(2000);
                bullet.setxSpeed(0);
                bullet.setySpeed(0);
                bullet.attackEnemy(bullet.getTarget(), cash);

                if (bullet.getTarget().getEnemyHealth() <= 0) {
                    bullet.getTarget().setX(4000);
                    trashEnemies.add(bullet.getTarget());
                    enemiesKilled++;
                    if (bullet.getTarget() instanceof BossEnemy) {
                        victory = true;
                        victory(money);
                    }
                }
            } else if (getBulletDistance(bullet, bullet.getTarget()) > 200) {
                trashBullet.add(bullet);
            }
        }
        if ((trashEnemies.size() == totalEnemies) && (!isGameOver) && (!victory)) {
            Intent i = new Intent(mContext, GameScreen.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("TowerCoordinates", (Serializable) towerCoordinates);
            i.putExtra("BUNDLE", bundle);
            i.putExtra("heartCount", heartCounter);
            i.putExtra("money", money);
            i.putExtra("difficulty", difficultyLevel);
            i.putExtra("battled", true);
            System.out.println("HEART COUNT: " + heartCounter);
            mContext.startActivity(i);
        }

    }

    public static int getEnemiesKilled() {
        return enemiesKilled;
    }

    public static void setEnemiesKilled(int num) {
        enemiesKilled = num;
    }
}
