package com.a16ivt1.quest;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
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

    int progress=0;
    DatabaseHelper DbH;
    Cursor c = null; // Необходим для перемешения по базе данных

    int complex;

    public  static final String APP_PREFERENSES = "file";

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        progress = MainActivity.progressOfGame;
        complex = MainActivity.complexityOfGame;

        settings = getSharedPreferences(APP_PREFERENSES, Context.MODE_PRIVATE);

        // Инициализируем все кнопки и текс вьюверы для удобной работы
        var1But = (Button)findViewById(R.id.var1But);
        var2But = (Button)findViewById(R.id.var2But);
        var3But = (Button)findViewById(R.id.var3But);
        text = (TextView)findViewById(R.id.text);

        // Открываем базу данных
        DbH = new DatabaseHelper(GameActivity.this);
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
    protected void onPause()
    {
        super.onPause();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("progressOfGame", progress-1);
        editor.apply();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(MainActivity.newGame)
        {
            progress = 0;
            c.moveToFirst();
            text.setText(c.getString(1));
            var1But.setText(c.getString(3));
            var2But.setText(c.getString(5));
            var3But.setText(c.getString(7));
            return;
        }
        else
        {

            if(settings.contains("progressOfGame"))
            {
                progress = settings.getInt("progressOfGame",0);
                if(progress == -1)
                {
                    c.moveToFirst();
                    return;
                }
                c.moveToPosition(progress);
            }
            else
            {
                progress = 0;
                c.moveToFirst();
            }
        }
        if(c.getCount()<progress)
        {
            text.setText("Конец истории");
            var1But.setEnabled(false);
            var1But.setVisibility(View.INVISIBLE);
            var2But.setEnabled(false);
            var2But.setVisibility(View.INVISIBLE);
            var3But.setEnabled(false);
            var3But.setVisibility(View.INVISIBLE);
            return;
        }
        else
        {
            text.setText(c.getString(1));
            var1But.setText(c.getString(3));
            var2But.setText(c.getString(5));
            var3But.setText(c.getString(7));
        }
    }

    public void nextText(View view) {
        /* При нажатии на определенную кнопку
        * Считывается следующая позиция для курсора*/
        switch (view.getId()) {
            case R.id.var1But:
                progress = Integer.parseInt(c.getString(2));
                break;
            case R.id.var2But:
                progress = Integer.parseInt(c.getString(4));
                break;
            case R.id.var3But:
                progress = Integer.parseInt(c.getString(6));
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }

        /* Далее несколько проверок на поля базы данных
        * Если ее поле с выбором пустое, то эта кнопка не будет отобраться за экране выбора
        * Если больше нет истории, то будет выведен "Конец истории"*/
        MainActivity.progressOfGame = progress - 1;
        if(c.isNull(progress - 1))
        {
            text.setText("Конец истории");
            var1But.setEnabled(false);
            var1But.setVisibility(View.INVISIBLE);
            var2But.setEnabled(false);
            var2But.setVisibility(View.INVISIBLE);
            var3But.setEnabled(false);
            var3But.setVisibility(View.INVISIBLE);
            progress = 1000;
            MainActivity.progressOfGame = 1000;
            return;
        }
        else
        {
            c.moveToPosition(progress-1);
        }
        if(c.getString(1) == null) {
            text.setText("Конец истории");
        }
        else {
            text.setText(c.getString(1));
        }
        if(c.getString(3)== null)
        {
            var1But.setEnabled(false);
            var1But.setVisibility(View.INVISIBLE);
        }
        else{
            var1But.setText(c.getString(3));
            var1But.setEnabled(true);
            var1But.setVisibility(View.VISIBLE);
        }
        if(c.getString(5)== null)
        {
            var2But.setEnabled(false);
            var2But.setVisibility(View.INVISIBLE);
        }
        else{
            var2But.setText(c.getString(5));
            var2But.setEnabled(true);
            var2But.setVisibility(View.VISIBLE);
        }
        if(c.getString(7)== null)
        {
            var3But.setEnabled(false);
            var3But.setVisibility(View.VISIBLE);
        }
        else{
            var3But.setText(c.getString(7));
            var3But.setEnabled(true);
            var3But.setVisibility(View.VISIBLE);
        }
    }
}
