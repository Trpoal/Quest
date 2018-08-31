package com.a16ivt1.quest;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final int GAME_ACTIVITY = 1;
    private static final int MINI_GAMES_ACTIVITY = 2;
    protected static final int NO_QUEST_GAME = 0;
    protected static final int ON_QUEST_GAME = 1;

    static int progressOfGame;   //Прогресс квеста

    static int modeOfGame = NO_QUEST_GAME;


    public static boolean res = false;

    public static boolean newGame = false;


    /*В файле хранится информация о моменте в квесте
     * О сложности мини-игр
     * О мини играх, которые уже доступны для прохождения*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

    }


    public void goTo(View v) {
        /* Открывает новую активити в зависимости от нажатой кнопки*/
        switch (v.getId()) {
            case R.id.continueButton: {
                startAct(GAME_ACTIVITY);
                break;
            }
            case R.id.newGameButton: {
                newGame = true;
                /*Создается и открывается новое диалоговое окно
                 * Узнается сложность мини-игр*/
                startAct(GAME_ACTIVITY);
                break;
            }
            case R.id.miniGamesButton: {
                startAct(MINI_GAMES_ACTIVITY);
                break;
            }
            default:
                throw new RuntimeException("Unknow button ID");

        }
    }

    public void startAct(int kod) {
        Intent intent;
        switch (kod) {
            case GAME_ACTIVITY: {
                intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                break;
            }
            case MINI_GAMES_ACTIVITY: {
                intent = new Intent(MainActivity.this, MiniGamesActivity.class);
                startActivity(intent);
            }

        }
    }


}

