package com.example.towerdefensegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

public class GameOverScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);

        Button restart = findViewById(R.id.restart);
        Button endGame = findViewById(R.id.quit);

        TextView enemiesKilled = (TextView) findViewById(R.id.enemiesKilled);
        enemiesKilled.setText(Integer.toString(GameView.getEnemiesKilled()) + "  Enemies  Killed");

        TextView moneySpent = (TextView) findViewById(R.id.moneySpent);
        moneySpent.setText(Integer.toString(GameScreen.getTotalMoneySpent()) + "  Coins  Spent");

        Intent intent = getIntent();
        int money = intent.getExtras().getInt("money");

        TextView finalMoney = (TextView) findViewById(R.id.finalMoney);
        finalMoney.setText(Integer.toString(money) + "  Coins  Left");

        restart.setOnClickListener((view -> {
            GameScreen.setTotalMoneySpent(0);
            GameView.setEnemiesKilled(0);
            Intent toGameScreen = new Intent(GameOverScreen.this, Configuration.class);
            startActivity(toGameScreen);
        }));

        endGame.setOnClickListener((view -> {
            this.finishAffinity();
        }));
    }



}