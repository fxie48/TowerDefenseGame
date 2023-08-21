package com.example.towerdefensegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class Configuration extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String[] ITEMS = {"Easy", "Medium", "Hard"};
    private String difficulty;
    private EditText userName;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button gameStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_configuration);

        AutoCompleteTextView editText = findViewById(R.id.auto_complete_txt);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ITEMS);
        editText.setAdapter(adapter);
        userName = (EditText) findViewById(R.id.userName);

        gameStartButton = (Button) findViewById(R.id.gamStartButton);
        gameStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.length() == 0) {
                    userName.setError("Enter User Name!");
                } else if (editText.length() == 0) {
                    editText.setError("Select Difficulty!");
                } else {
                    difficulty = editText.getText().toString();
                    openGameScreen();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        difficulty = parent.getItemAtPosition(position).toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.help) {
            popup();
        }
        return super.onOptionsItemSelected(item);
    }

    public void popup() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup, null);
        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }
    public void openGameScreen() {
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }
}