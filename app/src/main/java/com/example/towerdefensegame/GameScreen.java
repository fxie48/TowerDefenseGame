package com.example.towerdefensegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.towerdefensegame.model.TowerCoordinates;

import java.io.Serializable;
import java.util.ArrayList;

public class GameScreen extends AppCompatActivity {
    private static int totalMoneySpent;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;
    private ImageView heart4;
    private ImageView heart5;
    private ImageView heart6;
    private ImageView heart7;
    private ImageView heart8;
    private ImageView heart9;
    private ImageView heart10;
    private ImageView heart11;
    private ImageView heart12;
    private TextView money;
    private int finalMoney;
    private String difficulty;
    private ArrayList<ImageButton> towerLocation;
    private ArrayList<TowerCoordinates> towerCoordinates;
    private ArrayList<TowerCoordinates> purchasedTower;
    private ArrayList<ImageView> heartList = new ArrayList<>();
    private RelativeLayout towerMap;
    private int health;
    private int towerType;
    private int heartCounter; //hearts left after battle
    private int moneyFromBattle; //money returned from battle
    private boolean battled = false; //boolean indicating whether or not
    // the player has battled at least once
    private int updatedHeartCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.towerCoordinates = new ArrayList<>();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game_screen);
        Intent intent = getIntent();
        difficulty = intent.getExtras().getString("difficulty");
        heartCounter = intent.getExtras().getInt("heartCount");
        moneyFromBattle = intent.getExtras().getInt("money");

        battled = intent.getExtras().getBoolean("battled");

        System.out.println("BATTLED: " + battled);
        showHeart(difficulty);

        towerLocation = new ArrayList<>();
        ViewGroup towerMap = (ViewGroup) findViewById(R.id.towerMap);
        for (int i = 0; i < towerMap.getChildCount(); i++) {
            View child = towerMap.getChildAt(i);
            if (child instanceof ImageButton) {
                ImageButton eachTower = (ImageButton) child;
                eachTower.setTag("available");
                towerLocation.add(eachTower);

                System.out.println("Check this " + eachTower.getX());
            }
        }
        if (battled) {
            Bundle bundle = intent.getBundleExtra("BUNDLE");
            towerCoordinates = (ArrayList<TowerCoordinates>)
                    bundle.getSerializable("TowerCoordinates");
        }
        Button shopButton = (Button) findViewById(R.id.Shop);
        shopButton.setOnClickListener((View view) -> {
            createShop();
        });
        Button battleButton = (Button) findViewById(R.id.Battle);
        ImageView enemy1 = (ImageView) findViewById(R.id.enemy1);
        enemy1.setVisibility(View.INVISIBLE);
        battleButton.setOnClickListener((View view) -> {
            battleButton.setVisibility(Button.INVISIBLE);

            Intent intent2 = new Intent(GameScreen.this, GameActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("TowerCoordinates", (Serializable) towerCoordinates);
            intent2.putExtra("BUNDLE", bundle);
            intent2.putExtra("difficulty", difficulty);
            intent2.putExtra("money", finalMoney);
            intent2.putExtra("battledGS", true);
            intent2.putExtra("heartCounter", heartCounter);
            intent2.putExtra("updatedHeartCount", updatedHeartCount);
            startActivity(intent2);
        });
        if (battled) {
            showPurchasedTower();
        }

    }

    public void showPurchasedTower() {
        for (TowerCoordinates tower : towerCoordinates) {
//            ImageButton towerBuilt = new ImageButton(this);
//            towerBuilt.setMaxWidth(45);
//            towerBuilt.setMaxHeight(45);
//            towerBuilt.setX(((int) tower.getX()) - 45);
//            towerBuilt.setY(((int) tower.getY()) - 45);
//            towerBuilt.setBackground(null);
            ImageButton towerBuilt = new ImageButton(this);
            towerBuilt.setMaxWidth(45);
            towerBuilt.setMaxHeight(45);
            towerBuilt.setX(((int) tower.getX()) - 45);
            towerBuilt.setY(((int) tower.getY()) - 45);
            towerBuilt.setBackground(null);

//            ImageButton b = new ImageButton(this);
//            b.setX(towerBuilt.getX());
//            b.setY(towerBuilt.getY());
//            b.setMaxWidth(45);
//            b.setMaxHeight(45);
//            towerBuilt.setX(((int) tower.getX()));
//            towerBuilt.setY(((int) tower.getY()));
//            b.setX(((int) tower.getX()));
//            b.setY(((int) tower.getY()));
//            b.setBackground(null);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            // place the tower
            if (tower.getTowerType() == 1) {
                if (tower.isUpgraded()) {
                    towerBuilt.setImageResource(R.drawable.upgradetower1);
//                    b.setImageResource(R.drawable.upgradetower1);
//                    b.setVisibility(View.INVISIBLE);
                } else {
                    towerBuilt.setImageResource(R.drawable.tower1);
//                    b.setImageResource(R.drawable.tower1);
//                    b.setVisibility(View.INVISIBLE);
                }
                // set tag not available
                towerBuilt.setTag("occupied");
                //b.setTag("occupied");

//                towerBuilt.setX(((int) tower.getX()));
//                towerBuilt.setY(((int) tower.getY()));
                towerLocation.add(towerBuilt);
            } else if (tower.getTowerType() == 2) {
                if (tower.isUpgraded()) {
                    towerBuilt.setImageResource(R.drawable.upgradetower2);
                } else {
                    towerBuilt.setImageResource(R.drawable.tower2);
                }
                towerBuilt.setTag("occupied");
                //towerBuilt.setVisibility(View.VISIBLE);
                towerLocation.add(towerBuilt);
            } else if (tower.getTowerType() == 3) {
                System.out.println("Tiger Tower will be built");
                if (tower.isUpgraded()) {
                    towerBuilt.setImageResource(R.drawable.upgradetower3);
                } else {
                    towerBuilt.setImageResource(R.drawable.tower3);
                }
                towerBuilt.setTag("occupied");
                //towerBuilt.setVisibility(View.VISIBLE);
                towerLocation.add(towerBuilt);
            }
            addContentView(towerBuilt, params);
            //for future to update tower
            //towerBuilt.setOnClickListener(new Button.OnClickListener(){
            //@Override
            //public void onClick(View v) {}
            if (((String) towerBuilt.getTag()).equals("occupied")) {
                System.out.println("Visibility can be set");
                towerBuilt.setVisibility(View.VISIBLE);
            }

        }
    }


    public void showHeart(String difficulty) {
        heart1 = (ImageView) findViewById(R.id.heart1);
        heart2 = (ImageView) findViewById(R.id.heart2);
        heart3 = (ImageView) findViewById(R.id.heart3);
        heart4 = (ImageView) findViewById(R.id.heart4);
        heart5 = (ImageView) findViewById(R.id.heart5);
        heart6 = (ImageView) findViewById(R.id.heart6);
        heart7 = (ImageView) findViewById(R.id.heart7);
        heart8 = (ImageView) findViewById(R.id.heart8);
        heart9 = (ImageView) findViewById(R.id.heart9);
        heart10 = (ImageView) findViewById(R.id.heart10);
        heart11 = (ImageView) findViewById(R.id.heart11);
        heart12 = (ImageView) findViewById(R.id.heart12);

        heartList.add(heart1);
        heartList.add(heart2);
        heartList.add(heart3);
        heartList.add(heart4);
        heartList.add(heart5);
        heartList.add(heart6);
        heartList.add(heart7);
        heartList.add(heart8);
        heartList.add(heart9);
        heartList.add(heart10);
        heartList.add(heart11);
        heartList.add(heart12);

        money = (TextView) findViewById(R.id.money);

        if (difficulty.equals("Easy")) {
            health = 12;
            updatedHeartCount = 12;
            if (battled) {
                updatedHeartCount = heartCounter;
                money.setText(Integer.toString(moneyFromBattle));
                finalMoney = moneyFromBattle;
                int heartNum = 11;
                for (int h = 0; h < 12 - heartCounter; h++) {
                    heartList.get(heartNum).setVisibility(View.INVISIBLE);
                    heartNum--;
                }
            } else {
                money.setText("500");
                finalMoney = 500;
            }

        } else if (difficulty.equals("Medium")) {
            System.out.println("difficulty is medium");
            health = 6;
            updatedHeartCount = 6;
            for (int i = 6; i < 12; i++) {
                heartList.get(i).setVisibility(View.INVISIBLE);
            }
            if (battled) {
                updatedHeartCount = heartCounter;
                money.setText(Integer.toString(moneyFromBattle));
                finalMoney = moneyFromBattle;
                int heartNum = 5;
                for (int h = 0; h < 6 - heartCounter; h++) {
                    heartList.get(heartNum).setVisibility(View.INVISIBLE);
                    heartNum--;
                }
            } else {
                money.setText("400");
                finalMoney = 400;
            }

        } else if (difficulty.equals("Hard")) {
            health = 3;
            updatedHeartCount = 3;
            for (int i = 3; i < 12; i++) {
                heartList.get(i).setVisibility(View.INVISIBLE);
            }
            if (battled) {
                updatedHeartCount = heartCounter;
                money.setText(Integer.toString(moneyFromBattle));
                finalMoney = moneyFromBattle;
                int heartNum = 2;
                for (int h = 0; h < 3- heartCounter; h++) {
                    heartList.get(heartNum).setVisibility(View.INVISIBLE);
                    heartNum--;
                }
            } else {
                money.setText("400");
                finalMoney = 400;
            }
        }
    }

    //CREATING THE SHOP POP-UP

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private Button purchaseTower1;
    private Button purchaseTower2;
    private Button purchaseTower3;

    private Button exit;
    private Button upgrade;

    private TextView insufficientFunds;

    private int costOfTower1;
    private int costOfTower2;
    private int costOfTower3;
    protected void createShop() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View shopPopUp = getLayoutInflater().
                inflate(R.layout.shop_pop_up, null); //creating the pop-up window itself

        purchaseTower1 = (Button) shopPopUp.findViewById(R.id.tower1);
        purchaseTower2 = (Button) shopPopUp.findViewById(R.id.tower2);
        purchaseTower3 = (Button) shopPopUp.findViewById(R.id.tower3);

        exit = (Button) shopPopUp.findViewById(R.id.exit);
        upgrade = (Button) shopPopUp.findViewById(R.id.upgrade);

        insufficientFunds = (TextView) shopPopUp.findViewById(R.id.insufficient_funds);
        insufficientFunds.setVisibility(View.INVISIBLE);

        if (difficulty.equals("Easy")) {
            purchaseTower1.setText("Purchase ($20)");
            costOfTower1 = 20;
            purchaseTower2.setText("Purchase ($50)");
            costOfTower2 = 50;
            purchaseTower3.setText("Purchase ($100)");
            costOfTower3 = 100;

        } else if (difficulty.equals("Medium")) {
            purchaseTower1.setText("Purchase ($50)");
            costOfTower1 = 50;
            purchaseTower2.setText("Purchase ($100)");
            costOfTower2 = 100;
            purchaseTower3.setText("Purchase ($150)");
            costOfTower3 = 150;
        } else {
            purchaseTower1.setText("Purchase ($100)");
            costOfTower1 = 100;
            purchaseTower2.setText("Purchase ($120)");
            costOfTower2 = 120;
            purchaseTower3.setText("Purchase ($150)");
            costOfTower3 = 150;
        }

        dialogBuilder.setView(shopPopUp);
        dialog = dialogBuilder.create();
        dialog.show();
        dialog.getWindow().setLayout(1700, 1000);


        String moneyAmount = (String) money.getText();
        int moneyInt = Integer.parseInt(moneyAmount);
        int finalAfterTower1Purchase = moneyInt - costOfTower1;
        int finalAfterTower2Purchase = moneyInt - costOfTower2;
        int finalAfterTower3Purchase = moneyInt - costOfTower3;

        if (finalAfterTower1Purchase < 0) {
            purchaseTower1.setEnabled(false);
        }
        if (finalAfterTower2Purchase < 0) {
            purchaseTower2.setEnabled(false);
        }
        if (finalAfterTower3Purchase < 0) {
            purchaseTower3.setEnabled(false);
        }

        purchaseTower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchaseTower(purchaseTower1, 1, costOfTower1);
            }
        });
        purchaseTower2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchaseTower(purchaseTower2, 2, costOfTower2);
            }
        });

        purchaseTower3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchaseTower(purchaseTower3, 3, costOfTower3);
            }
        });

        exit.setOnClickListener(v -> dialog.dismiss());
        upgrade.setOnClickListener((View view) -> {
            dialog.dismiss();
            towerUpgrade();
        });
    }

    private Button upgradeTower1;
    private Button upgradeTower2;
    private Button upgradeTower3;
    private int costOfTower1upgrade;
    private int costOfTower2upgrade;
    private int costOfTower3upgrade;
    protected void towerUpgrade() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View upgradePopUp = getLayoutInflater().
                inflate(R.layout.upgrade_pop_up, null); //creating the pop-up window itself

        upgradeTower1 = (Button) upgradePopUp.findViewById(R.id.tower1);
        upgradeTower2 = (Button) upgradePopUp.findViewById(R.id.tower2);
        upgradeTower3 = (Button) upgradePopUp.findViewById(R.id.tower3);

        exit = (Button) upgradePopUp.findViewById(R.id.exit);
        upgrade = (Button) upgradePopUp.findViewById(R.id.upgrade);

        insufficientFunds = (TextView) upgradePopUp.findViewById(R.id.insufficient_funds);
        insufficientFunds.setVisibility(View.INVISIBLE);

        if (difficulty.equals("Easy")) {
            upgradeTower1.setText("Purchase ($10)");
            costOfTower1upgrade = 10;
            upgradeTower2.setText("Purchase ($10)");
            costOfTower2upgrade = 10;
            upgradeTower3.setText("Purchase ($20)");
            costOfTower3upgrade = 20;

        } else if (difficulty.equals("Medium")) {
            upgradeTower1.setText("Purchase ($20)");
            costOfTower1upgrade = 20;
            upgradeTower2.setText("Purchase ($25)");
            costOfTower2upgrade = 25;
            upgradeTower3.setText("Purchase ($60)");
            costOfTower3upgrade = 60;
        } else {
            upgradeTower1.setText("Purchase ($30)");
            costOfTower1upgrade = 30;
            upgradeTower2.setText("Purchase ($40)");
            costOfTower2upgrade = 40;
            upgradeTower3.setText("Purchase ($70)");
            costOfTower3upgrade = 70;
        }

        dialogBuilder.setView(upgradePopUp);
        dialog = dialogBuilder.create();
        dialog.show();
        dialog.getWindow().setLayout(1700, 1000);


        String moneyAmount = (String) money.getText();
        int moneyInt = Integer.parseInt(moneyAmount);
        int finalAfterTower1Purchase = moneyInt - costOfTower1upgrade;
        int finalAfterTower2Purchase = moneyInt - costOfTower2upgrade;
        int finalAfterTower3Purchase = moneyInt - costOfTower3upgrade;

        if (finalAfterTower1Purchase < 0) {
            upgradeTower1.setEnabled(false);
        }
        if (finalAfterTower2Purchase < 0) {
            upgradeTower2.setEnabled(false);
        }
        if (finalAfterTower3Purchase < 0) {
            upgradeTower3.setEnabled(false);
        }

        upgradeTower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moneyInt >= costOfTower1upgrade) {
                    purchaseUpgrade(upgradeTower1, 1, costOfTower1upgrade);
                }
            }
        });
        upgradeTower2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moneyInt >= costOfTower2upgrade) {
                    purchaseUpgrade(upgradeTower2, 2, costOfTower2upgrade);
                }
            }
        });

        upgradeTower3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moneyInt >= costOfTower3upgrade) {
                    purchaseUpgrade(upgradeTower3, 3, costOfTower3upgrade);
                }
            }
        });

        exit.setOnClickListener(v -> dialog.dismiss());
        upgrade.setOnClickListener((View view) -> {
            dialog.dismiss();
            createShop();
        });
    }

    private void purchaseTower(Button purchaseTower, int towerType, int costOfTower) {
        String moneyAmount = (String) money.getText();
        int moneyInt = Integer.parseInt(moneyAmount);
        totalMoneySpent += costOfTower;
        if (moneyInt - costOfTower < 0) {
            purchaseTower.setEnabled(false);
            insufficientFunds.setVisibility(View.VISIBLE);
        } else {
            insufficientFunds.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            placeTower(towerType, costOfTower);
        }
    }

    protected void placeTower(int towerType, int towerCost) {
        for (ImageButton eachLocation : towerLocation) {
            System.out.println("each location x before" + (int) eachLocation.getX());
            if (((String) eachLocation.getTag()).equals("available")) {
                eachLocation.setVisibility(View.VISIBLE);
                System.out.println("each location x after" + (int) eachLocation.getX());
                eachLocation.setOnClickListener(view -> {
                    if (towerCoordinates.size() < 50) {
                        int positionX = (int) eachLocation.getX();
                        int positionY = (int) eachLocation.getY();

                        if (towerType == 1) {
                            eachLocation.setTag("occupied");
                            //eachLocation.setVisibility(View.INVISIBLE);
                            eachLocation.setImageResource(R.drawable.tower1);
                            TowerCoordinates eachTowerCoordinates = new TowerCoordinates();
                            eachTowerCoordinates.setX(positionX);
                            eachTowerCoordinates.setY(positionY);
                            eachTowerCoordinates.setTowerType(towerType);
                            towerCoordinates.add(eachTowerCoordinates);
                        } else if (towerType == 2) {
                            eachLocation.setTag("occupied");
                            //eachLocation.setVisibility(View.INVISIBLE);
                            eachLocation.setImageResource(R.drawable.tower2);
                            TowerCoordinates eachTowerCoordinates = new TowerCoordinates();
                            eachTowerCoordinates.setX(positionX);
                            eachTowerCoordinates.setY(positionY);
                            eachTowerCoordinates.setTowerType(towerType);
                            towerCoordinates.add(eachTowerCoordinates);
                        } else if (towerType == 3) {
                            eachLocation.setTag("occupied");
                            //eachLocation.setVisibility(View.INVISIBLE);
                            eachLocation.setImageResource(R.drawable.tower3);
                            TowerCoordinates eachTowerCoordinates = new TowerCoordinates();
                            eachTowerCoordinates.setX(positionX);
                            eachTowerCoordinates.setY(positionY);
                            eachTowerCoordinates.setTowerType(towerType);
                            towerCoordinates.add(eachTowerCoordinates);
                        }

                    }
                    String moneyAmount = (String) money.getText();
                    int moneyInt = Integer.parseInt(moneyAmount);
                    money.setText("" + (moneyInt - towerCost));
                    finalMoney = moneyInt - towerCost;
                    for (ImageView other : towerLocation) {
                        if (((String) other.getTag()).equals("available")) {
                            other.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }

        }

    }

    private void purchaseUpgrade(Button button, int upgradeType, int costOfUpgrade) {
        String moneyAmount = (String) money.getText();
        int moneyInt = Integer.parseInt(moneyAmount);
        totalMoneySpent += costOfUpgrade;
        if (moneyInt - costOfUpgrade < 0) {
            button.setEnabled(false);
            insufficientFunds.setVisibility(View.VISIBLE);
        } else {
            insufficientFunds.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            upgradeTower(upgradeType, costOfUpgrade);
        }
    }
    boolean upgradedTower1 = false;
    boolean upgradedTower2 = false;
    boolean upgradedTower3 = false;
    protected void upgradeTower(int upgradeType, int upgradeCost) {
        for (ImageButton eachLocation : towerLocation) {
            if (((String) eachLocation.getTag()).equals("occupied")) {
                eachLocation.setVisibility(View.VISIBLE);
                eachLocation.setOnClickListener(view -> {
                    if (towerCoordinates.size() < 50) {
                        int positionX = (int) eachLocation.getX();
                        int positionY = (int) eachLocation.getY();

                        if (upgradeType == 1) {
                            eachLocation.setImageResource(R.drawable.upgradetower1);
                            eachLocation.setTag("occupied");
                            TowerCoordinates eachTowerCoordinates = new TowerCoordinates();
                            eachTowerCoordinates.setX(positionX);
                            eachTowerCoordinates.setY(positionY);
                            eachTowerCoordinates.setTowerType(upgradeType);
                            eachTowerCoordinates.setUpgraded(true);
                            towerCoordinates.add(eachTowerCoordinates);
                        } else if (upgradeType == 2) {
                            eachLocation.setImageResource(R.drawable.upgradetower2);
                            eachLocation.setTag("occupied");
                            TowerCoordinates eachTowerCoordinates = new TowerCoordinates();
                            eachTowerCoordinates.setX(positionX);
                            eachTowerCoordinates.setY(positionY);
                            eachTowerCoordinates.setTowerType(upgradeType);
                            eachTowerCoordinates.setUpgraded(true);
                            towerCoordinates.add(eachTowerCoordinates);
                        } else if (upgradeType == 3) {
                            eachLocation.setImageResource(R.drawable.upgradetower3);
                            eachLocation.setTag("occupied");
                            TowerCoordinates eachTowerCoordinates = new TowerCoordinates();
                            eachTowerCoordinates.setX(positionX);
                            eachTowerCoordinates.setY(positionY);
                            eachTowerCoordinates.setTowerType(upgradeType);
                            eachTowerCoordinates.setUpgraded(true);
                            towerCoordinates.add(eachTowerCoordinates);
                        }

                    }
                    String moneyAmount = (String) money.getText();
                    int moneyInt = Integer.parseInt(moneyAmount);
                    money.setText("" + (moneyInt - upgradeCost));
                    finalMoney = moneyInt - upgradeCost;
                    for (ImageView other : towerLocation) {
                        if (((String) other.getTag()).equals("available")) {
                            other.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        }
    }

    private void gameOver() {
        Intent i = new Intent(this, GameOverScreen.class);
        startActivity(i);
    }

    public static int getTotalMoneySpent() {
        return totalMoneySpent;
    }

    public static void setTotalMoneySpent(int num) {
        totalMoneySpent = num;
    }
}