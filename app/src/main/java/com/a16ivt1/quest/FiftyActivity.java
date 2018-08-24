package com.a16ivt1.quest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import helpClasses.DialogFragment;



public class FiftyActivity extends AppCompatActivity {

    private static final int FIFTY_INFO = 4;

    ImageButton[][] masCell = new ImageButton[4][4];
    int emX, emY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fifty);
        inBut();
        genField();
        setEnab();


    }

    public void genField()
    {
        /*Расставляет картинки в рандомном порядке на поле*/
        for (int i = 0; i < 16; i++) {
            int x = (int)(Math.random()*4);
            int y = (int)(Math.random()*4);
            while(!(masCell[x][y].getTag()=="0"))
            {
                x = (int)(Math.random()*4);
                y = (int)(Math.random()*4);
           }
           if(i==0)
           {
               emX = x;
               emY = y;
           }
            setPic(x,y,i);
            masCell[x][y].setTag(String.valueOf(i));
            /*Тег помогает узнать, какая именно картинка сейчас стоит в кнопке*/
        }
    }

    public void setPic(int x, int y, int id)
    {
        /*Ставит картинку по номеру ее айди в необходимую кнопку*/
        switch(id) {
            case 0: {
                masCell[x][y].setBackgroundResource(R.drawable.cell0);
                break;
            }
            case 1: {
                masCell[x][y].setBackgroundResource(R.drawable.cell1);
                break;
            }
            case 2: {
                masCell[x][y].setBackgroundResource(R.drawable.cell2);
                break;
            }
            case 3: {
                masCell[x][y].setBackgroundResource(R.drawable.cell3);
                break;
            }
            case 4: {
                masCell[x][y].setBackgroundResource(R.drawable.cell4);
                break;
            }
            case 5: {
                masCell[x][y].setBackgroundResource(R.drawable.cell5);
                break;
            }
            case 6: {
                masCell[x][y].setBackgroundResource(R.drawable.cell6);
                break;
            }
            case 7: {
                masCell[x][y].setBackgroundResource(R.drawable.cell7);
                break;
            }
            case 8: {
                masCell[x][y].setBackgroundResource(R.drawable.cell8);
                break;
            }
            case 9: {
                masCell[x][y].setBackgroundResource(R.drawable.cell9);
                break;
            }
            case 10: {
                masCell[x][y].setBackgroundResource(R.drawable.cell10);
                break;
            }
            case 11: {
                masCell[x][y].setBackgroundResource(R.drawable.cell11);
                break;
            }
            case 12: {
                masCell[x][y].setBackgroundResource(R.drawable.cell12);
                break;
            }
            case 13: {
                masCell[x][y].setBackgroundResource(R.drawable.cell13);
                break;
            }
            case 14: {
                masCell[x][y].setBackgroundResource(R.drawable.cell14);
                break;
            }
            case 15: {
                masCell[x][y].setBackgroundResource(R.drawable.cell15);
                break;
            }
            default:
            {
                masCell[x][y].setBackgroundResource(R.drawable.cell0);
                break;
            }
        }
    }

    public void inBut()
    {
        /*Заполнение массива и заполнение кнопок*/
        masCell[0][0] = (ImageButton) findViewById(R.id.cell1);
        masCell[0][0].setTag("0");
        masCell[0][0].setEnabled(false);

        masCell[0][1] = (ImageButton) findViewById(R.id.cell2);
        masCell[0][1].setTag("0");
        masCell[0][1].setEnabled(false);

        masCell[0][2] = (ImageButton) findViewById(R.id.cell3);
        masCell[0][2].setTag("0");
        masCell[0][2].setEnabled(false);

        masCell[0][3] = (ImageButton) findViewById(R.id.cell4);
        masCell[0][3].setTag("0");
        masCell[0][3].setEnabled(false);

        masCell[1][0] = (ImageButton) findViewById(R.id.cell5);
        masCell[1][0].setTag("0");
        masCell[1][0].setEnabled(false);

        masCell[1][1] = (ImageButton) findViewById(R.id.cell6);
        masCell[1][1].setTag("0");
        masCell[1][1].setEnabled(false);

        masCell[1][2] = (ImageButton) findViewById(R.id.cell7);
        masCell[1][2].setTag("0");
        masCell[1][2].setEnabled(false);

        masCell[1][3] = (ImageButton) findViewById(R.id.cell8);
        masCell[1][3].setTag("0");
        masCell[1][3].setEnabled(false);

        masCell[2][0] = (ImageButton) findViewById(R.id.cell9);
        masCell[2][0].setTag("0");
        masCell[2][0].setEnabled(false);

        masCell[2][1] = (ImageButton) findViewById(R.id.cell10);
        masCell[2][1].setTag("0");
        masCell[2][1].setEnabled(false);

        masCell[2][2] = (ImageButton) findViewById(R.id.cell11);
        masCell[2][2].setTag("0");
        masCell[2][2].setEnabled(false);

        masCell[2][3] = (ImageButton) findViewById(R.id.cell12);
        masCell[2][3].setTag("0");
        masCell[2][3].setEnabled(false);

        masCell[3][0] = (ImageButton) findViewById(R.id.cell13);
        masCell[3][0].setTag("0");
        masCell[3][0].setEnabled(false);

        masCell[3][1] = (ImageButton) findViewById(R.id.cell14);
        masCell[3][1].setTag("0");
        masCell[3][1].setEnabled(false);

        masCell[3][2] = (ImageButton) findViewById(R.id.cell15);
        masCell[3][2].setTag("0");
        masCell[3][2].setEnabled(false);

        masCell[3][3] = (ImageButton) findViewById(R.id.cell16);
        masCell[3][3].setTag("0");
        masCell[3][3].setEnabled(false);
    }

    public void setEnab()
    {
        /*Выставление кнопок, на которые можно нажать*/
        if(emX-1>-1)
        {
            masCell[emX-1][emY].setEnabled(true);
        }

        if(emX+1<4)
        {
            masCell[emX+1][emY].setEnabled(true);
        }

        if(emY-1>-1)
        {
            masCell[emX][emY-1].setEnabled(true);
        }

        if(emY+1<4)
        {
            masCell[emX][emY+1].setEnabled(true);
        }
    }

    public void setUnenab()
    {
        /*Вновь делает кнопки недействующими*/
        if(emX-1>-1)
        {
            masCell[emX-1][emY].setEnabled(false);
        }

        if(emX+1<4)
        {
            masCell[emX+1][emY].setEnabled(false);
        }

        if(emY-1>-1)
        {
            masCell[emX][emY-1].setEnabled(false);
        }

        if(emY+1<4)
        {
            masCell[emX][emY+1].setEnabled(false);
        }
    }

    public void change(View v)
    {
        /*Меняет выбранную кнопку с пустой местами*/
        int x=0, y=0;
        for(int i =0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                if (masCell[i][j].equals(v)) {
                     x = i;
                     y = j;
                    break;
                }
            }
        }
        setPic(emX,emY,Integer.parseInt((String)masCell[x][y].getTag()));
        masCell[emX][emY].setTag(masCell[x][y].getTag());
        setPic(x,y,0);
        masCell[x][y].setTag("0");
        setUnenab();
        emX = x;
        emY = y;
        setEnab();

    }

    public void infoClick(View view) {
        // Вызов алерта для пояснения правил игры
        // На экрана выделяется фрагмент, куда помещается алерт
        android.app.DialogFragment newFragment = DialogFragment.newInstance(FIFTY_INFO);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void cont(View v)
    {
        MainActivity.progressOfGame = 5;
        Intent intent = new Intent(FiftyActivity.this, GameActivity.class);
        startActivity(intent);
        this.finish();

    }

}
