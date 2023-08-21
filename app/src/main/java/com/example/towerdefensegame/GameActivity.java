package com.example.towerdefensegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.example.towerdefensegame.model.TowerCoordinates;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private ArrayList<TowerCoordinates> towerCoordinates;
    private String difficulty;
    private int money;
    private boolean battled;
    private int heartCounter;
    private int updatedHeartCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        towerCoordinates = (ArrayList<TowerCoordinates>) bundle.getSerializable("TowerCoordinates");
        difficulty = intent.getExtras().getString("difficulty");
        money = intent.getExtras().getInt("money");
        battled = intent.getExtras().getBoolean("battledGS");
        heartCounter = intent.getExtras().getInt("heartCounter");

        updatedHeartCounter = intent.getExtras().getInt("updatedHeartCount");

        DisplayMetrics display = this.getResources().getDisplayMetrics();
        gameView = new GameView(this, display.widthPixels,
                display.heightPixels, towerCoordinates, difficulty, money,
                battled, updatedHeartCounter);
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}