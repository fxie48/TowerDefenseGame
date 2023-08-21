package com.example.towerdefensegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.towerdefensegame.model.TowerCoordinates;

import java.util.ArrayList;

public class VictoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory_screen);

        Button restart = findViewById(R.id.restart);
        Button quit = findViewById(R.id.quit);

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
            Intent toConfigScreen = new Intent(VictoryScreen.this, Configuration.class);
            startActivity(toConfigScreen);
        }));

        quit.setOnClickListener((view -> {
            this.finishAffinity();
        }));
    }
}