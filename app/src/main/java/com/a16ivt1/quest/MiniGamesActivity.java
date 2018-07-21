package com.a16ivt1.quest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MiniGamesActivity extends AppCompatActivity {

    ImageButton othelloBut;
    TextView othelloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_games);

        othelloBut = (ImageButton) findViewById(R.id.othelloBut);
        othelloText = (TextView) findViewById(R.id.othelloText);
    }


    public void goToGame(View v)
    {
        Intent intent;
        switch(v.getId())
        {
            case R.id.othelloBut: {
                intent = new Intent(MiniGamesActivity.this, OthelloActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.fiveInRowBut:{
                intent = new Intent(MiniGamesActivity.this, FiveInRowActivity.class);
                startActivity(intent);
                break;
            }
            default:
                throw new RuntimeException("Unknow button ID");
        }
    }
}
