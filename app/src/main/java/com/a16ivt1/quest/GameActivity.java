package com.a16ivt1.quest;

import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class GameActivity extends AppCompatActivity {

    Button var1But;
    Button var2But;
    Button var3But;
    TextView text;

    int progress=0;
    DatabaseHelper DbH;
    Cursor c = null;

    int complex=0;

    int countOfHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        progress = MainActivity.progressOfGame;
        complex = MainActivity.complexityOfGame;

        var1But = (Button)findViewById(R.id.var1But);
        var2But = (Button)findViewById(R.id.var2But);
        var3But = (Button)findViewById(R.id.var3But);
        text = (TextView)findViewById(R.id.text);

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
        Toast.makeText(GameActivity.this, String.valueOf(complex), Toast.LENGTH_SHORT).show();
        Toast.makeText(GameActivity.this, "Success", Toast.LENGTH_SHORT).show();
        c = DbH.query("Quest", null, null, null, null, null, null);
        countOfHistory = c.getCount();
        if(progress==0) {
            c.moveToFirst();
        }
        else
        {
            c.moveToPosition(progress);
        }
        text.setText(c.getString(1));
        var1But.setText(c.getString(3));
        var2But.setText(c.getString(5));
        var3But.setText(c.getString(7));


    }

    public void nextText(View view) {

        switch (view.getId()) {
            case R.id.var1But:
                progress = Integer.parseInt(c.getString(2));
// handle button A click;
                break;
            case R.id.var2But:
                progress = Integer.parseInt(c.getString(4));
// handle button B click;
                break;
            case R.id.var3But:
                progress = Integer.parseInt(c.getString(6));
// handle button B click;
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }


        if(c.isNull(progress - 1))
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
