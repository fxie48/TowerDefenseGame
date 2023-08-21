package com.example.towerdefensegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private Button quitButton;
    private ImageView volumeCtrl;
    private SharedPreferences prefs;
    private boolean isMute;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        prefs = this.getSharedPreferences("game", Context.MODE_PRIVATE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(v -> openConfiguration());

        quitButton = (Button) findViewById(R.id.quit);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        volumeCtrl = findViewById(R.id.volumeCtrl);
        if (isMute) {
            volumeCtrl.setImageResource(R.drawable.ic_baseline_volume_off_24);
        } else {
            volumeCtrl.setImageResource(R.drawable.ic_baseline_volume_up_24);
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.startscreen);
        mediaPlayer.start();
        isMute = false;
        volumeCtrl.setOnClickListener(view -> {
            isMute = !isMute;
            if (!isMute) {
                volumeCtrl.setImageResource(R.drawable.ic_baseline_volume_up_24);
                mediaPlayer.start();

            } else {
                volumeCtrl.setImageResource(R.drawable.ic_baseline_volume_off_24);
                mediaPlayer.pause();

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(MainActivity.this, "I'm done!", Toast.LENGTH_LONG).show();
                releaseMediaPlayer();
            }
        });
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void openConfiguration() {
        Intent intent = new Intent(this, Configuration.class);
        startActivity(intent);
    }
}