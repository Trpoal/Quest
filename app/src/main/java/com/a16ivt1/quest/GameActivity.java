package com.a16ivt1.quest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import helpClasses.DatabaseHelper;

public class GameActivity extends AppCompatActivity {

    /*Класс реализует работу активити с основым квестом игры*/

    Button var1But;
    Button var2But;
    Button var3But;
    TextView text;

    int progress = 0;
    DatabaseHelper DbH;
    Cursor c = null; // Необходим для перемешения по базе данных

    public static final String APP_PREFERENSES = "file";

    public static final int THIRD_EMPTY = 1;
    public static final int FIFTH_EMPTY = 2;
    public static final int SEVENTH_EMPTY = 3;

    public static int DB_VERSION;

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        progress = MainActivity.progressOfGame;

        settings = getSharedPreferences(APP_PREFERENSES, Context.MODE_PRIVATE);

        // Инициализируем все кнопки и текс вьюверы для удобной работы
        var1But = (Button) findViewById(R.id.var1But);
        var2But = (Button) findViewById(R.id.var2But);
        var3But = (Button) findViewById(R.id.var3But);
        text = (TextView) findViewById(R.id.text);

        // Открываем базу данных
       DB_VERSION= 3;
        DbH = new DatabaseHelper(this);
        try {
            DbH.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            DbH.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        //Вспомогательные уведомления, которые появляются снизу экрана
        //Пока оставить, потом уберем
        //Toast.makeText(GameActivity.this, String.valueOf(complex), Toast.LENGTH_SHORT).show();
        //Toast.makeText(GameActivity.this, "Success", Toast.LENGTH_SHORT).show();


        c = DbH.query("Quest", null, null, null, null, null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("progressOfGame", progress - 1);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MainActivity.newGame) {
            progress = 0;
            setText(progress);
            return;
        } else {

            if (settings.contains("progressOfGame")) {
                progress = settings.getInt("progressOfGame", 0);
                if (MainActivity.progressOfGame > progress) {
                    progress = MainActivity.progressOfGame;
                    setText(MainActivity.progressOfGame);
                    return;
                } else {
                    if (progress == -1) {
                        progress = 0;
                        MainActivity.progressOfGame = 0;
                        setText(0);
                        return;
                    }
                }
            } else {
                progress = 0;
                setText(progress);
                return;
            }

        }

        if (c.getCount() < progress) {
            text.setText("Конец истории");
            var1But.setEnabled(false);
            var1But.setVisibility(View.INVISIBLE);
            var2But.setEnabled(false);
            var2But.setVisibility(View.INVISIBLE);
            var3But.setEnabled(false);
            var3But.setVisibility(View.INVISIBLE);
            return;
        }

    }

    public void nextText(View view) {
        /* При нажатии на определенную кнопку
         * Считывается следующая позиция для курсора*/
        int newProgress;
        switch (view.getId()) {
            case R.id.var1But:
                newProgress = Integer.parseInt(c.getString(2));
                break;
            case R.id.var2But:
                newProgress = Integer.parseInt(c.getString(4));
                break;
            case R.id.var3But:
                newProgress = Integer.parseInt(c.getString(6));
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }

        /* Далее несколько проверок на поля базы данных
         * Если ее поле с выбором пустое, то эта кнопка не будет отобраться за экране выбора
         * Если больше нет истории, то будет выведен "Конец истории" */
        if (newProgress == 1000) {
            //progress++;
            Intent intent = new Intent(GameActivity.this, FiftyActivity.class);
            startActivity(intent);
            this.finish();
            return;
        }

        MainActivity.progressOfGame = newProgress - 1;
        progress = newProgress;
        if( progress>c.getCount())
        {
            text.setText("Конец истории");
            emptyBut(FIFTH_EMPTY);
            emptyBut(THIRD_EMPTY);
            emptyBut(SEVENTH_EMPTY);
            return;
        }
        setText(progress);

    }

    public void setText(int pos) {
        int i = c.getCount();
        if (pos == 0) {
            c.moveToFirst();
        } else {
            c.moveToPosition(pos);
        }
        if (c.getString(1) == null) {
            text.setText("Конец истории");
        } else {
            text.setText(c.getString(1));
        }
        if (c.getString(3) == null) {
            emptyBut(THIRD_EMPTY);
        } else {
            var1But.setText(c.getString(3));
            var1But.setEnabled(true);
            var1But.setVisibility(View.VISIBLE);
        }
        if (c.getString(5) == null) {
            emptyBut(FIFTH_EMPTY);
        } else {
            var2But.setText(c.getString(5));
            var2But.setEnabled(true);
            var2But.setVisibility(View.VISIBLE);
        }
        if (c.getString(7) == null) {
            emptyBut(SEVENTH_EMPTY);
        } else {
            var3But.setText(c.getString(7));
            var3But.setEnabled(true);
            var3But.setVisibility(View.VISIBLE);
        }

    }

    public void emptyBut(int idBut) {
        switch (idBut) {
            case 1: {
                var1But.setEnabled(false);
                var1But.setVisibility(View.INVISIBLE);
                break;
            }
            case 2: {
                var2But.setEnabled(false);
                var2But.setVisibility(View.INVISIBLE);
                break;
            }
            case 3: {
                var3But.setEnabled(false);
                var3But.setVisibility(View.INVISIBLE);
                break;
            }
            default: {

            }
        }
    }



}
