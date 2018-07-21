package com.a16ivt1.quest;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public static int progressOfGame;   //Прогресс квеста
    static int complexityOfGame = 0;    //Сложность мини-игр

    private static final int GAME_ACTIVITY = 1;
    private static final int MINI_GAMES_ACTIVITY = 2;
    public static boolean res = false;

    Button continueButton;
    TextView v;


    /*В файле хранится информация о моменте в квесте
    * О сложности мини-игр
    * О мини играх, которые уже доступны для прохождения*/
    final String FILENAME = "file";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continueButton = (Button) findViewById(R.id.continueButton);

        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            String str = "";
            // читаем содержимое
            str = br.readLine();
            progressOfGame = Integer.parseInt(str);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goTo(View v) {
        /* Открывает новую активити в зависимости от нажатой кнопки*/
        switch (v.getId()) {
            case R.id.continueButton: {
                startAct(GAME_ACTIVITY);
                break;
            }
            case R.id.newGameButton: {
                complexityOfGame = 1000;
                progressOfGame = 0;

                /*Создается и открывается новое диалоговое окно
                * Узнается сложность мини-игр*/
                DialogFragment newFragment = com.a16ivt1.quest.DialogFragment.newInstance(GAME_ACTIVITY );
                newFragment.show(getFragmentManager(), "dialog");
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

