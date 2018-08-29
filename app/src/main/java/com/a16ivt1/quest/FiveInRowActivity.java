package com.a16ivt1.quest;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import helpClasses.DialogFragment;
import helpClasses.Works;

public class FiveInRowActivity extends AppCompatActivity {

    /*Класс реализует работу активити с игрой 5 в ряд*/

    private static final int FIVE_IN_ROW_INFO = 3;
    int l = 0;
    int m = 0;
    int st = 0;

    ArrayList<ImageButton> cellArray = new ArrayList<>(); // Массив всех кнопок на поле
    Works work = new Works();
    boolean que = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_five_in_row);
    }

    public void infoClick(View view) {
        // Вызов алерта для пояснения правил игры
        // На экрана выделяется фрагмент, куда помещается алерт
        android.app.DialogFragment newFragment = DialogFragment.newInstance(FIVE_IN_ROW_INFO);
        newFragment.show(getFragmentManager(), "dialog");
    }

    void createArrayListOfSell() {
        /*Объявление всех клеток с xml файла*/
        cellArray.add((ImageButton) findViewById(R.id.cell0));
        cellArray.add((ImageButton) findViewById(R.id.cell1));
        cellArray.add((ImageButton) findViewById(R.id.cell2));
        cellArray.add((ImageButton) findViewById(R.id.cell3));
        cellArray.add((ImageButton) findViewById(R.id.cell4));
        cellArray.add((ImageButton) findViewById(R.id.cell5));
        cellArray.add((ImageButton) findViewById(R.id.cell6));
        cellArray.add((ImageButton) findViewById(R.id.cell7));
        cellArray.add((ImageButton) findViewById(R.id.cell8));
        cellArray.add((ImageButton) findViewById(R.id.cell9));
        cellArray.add((ImageButton) findViewById(R.id.cell10));
        cellArray.add((ImageButton) findViewById(R.id.cell11));
        cellArray.add((ImageButton) findViewById(R.id.cell12));
        cellArray.add((ImageButton) findViewById(R.id.cell13));
        cellArray.add((ImageButton) findViewById(R.id.cell14));
        cellArray.add((ImageButton) findViewById(R.id.cell15));
        cellArray.add((ImageButton) findViewById(R.id.cell16));
        cellArray.add((ImageButton) findViewById(R.id.cell17));
        cellArray.add((ImageButton) findViewById(R.id.cell18));
        cellArray.add((ImageButton) findViewById(R.id.cell19));
        cellArray.add((ImageButton) findViewById(R.id.cell20));
        cellArray.add((ImageButton) findViewById(R.id.cell21));
        cellArray.add((ImageButton) findViewById(R.id.cell22));
        cellArray.add((ImageButton) findViewById(R.id.cell23));
        cellArray.add((ImageButton) findViewById(R.id.cell24));
        cellArray.add((ImageButton) findViewById(R.id.cell25));
        cellArray.add((ImageButton) findViewById(R.id.cell26));
        cellArray.add((ImageButton) findViewById(R.id.cell27));
        cellArray.add((ImageButton) findViewById(R.id.cell28));
        cellArray.add((ImageButton) findViewById(R.id.cell29));
        cellArray.add((ImageButton) findViewById(R.id.cell30));
        cellArray.add((ImageButton) findViewById(R.id.cell31));
        cellArray.add((ImageButton) findViewById(R.id.cell32));
        cellArray.add((ImageButton) findViewById(R.id.cell33));
        cellArray.add((ImageButton) findViewById(R.id.cell34));
        cellArray.add((ImageButton) findViewById(R.id.cell35));
        cellArray.add((ImageButton) findViewById(R.id.cell36));
        cellArray.add((ImageButton) findViewById(R.id.cell37));
        cellArray.add((ImageButton) findViewById(R.id.cell38));
        cellArray.add((ImageButton) findViewById(R.id.cell39));
        cellArray.add((ImageButton) findViewById(R.id.cell40));
        cellArray.add((ImageButton) findViewById(R.id.cell41));
        cellArray.add((ImageButton) findViewById(R.id.cell42));
        cellArray.add((ImageButton) findViewById(R.id.cell43));
        cellArray.add((ImageButton) findViewById(R.id.cell44));
        cellArray.add((ImageButton) findViewById(R.id.cell45));
        cellArray.add((ImageButton) findViewById(R.id.cell46));
        cellArray.add((ImageButton) findViewById(R.id.cell47));
        cellArray.add((ImageButton) findViewById(R.id.cell48));
        cellArray.add((ImageButton) findViewById(R.id.cell49));
        cellArray.add((ImageButton) findViewById(R.id.cell50));
        cellArray.add((ImageButton) findViewById(R.id.cell51));
        cellArray.add((ImageButton) findViewById(R.id.cell52));
        cellArray.add((ImageButton) findViewById(R.id.cell53));
        cellArray.add((ImageButton) findViewById(R.id.cell54));
        cellArray.add((ImageButton) findViewById(R.id.cell55));
        cellArray.add((ImageButton) findViewById(R.id.cell56));
        cellArray.add((ImageButton) findViewById(R.id.cell57));
        cellArray.add((ImageButton) findViewById(R.id.cell58));
        cellArray.add((ImageButton) findViewById(R.id.cell59));
        cellArray.add((ImageButton) findViewById(R.id.cell60));
        cellArray.add((ImageButton) findViewById(R.id.cell61));
        cellArray.add((ImageButton) findViewById(R.id.cell62));
        cellArray.add((ImageButton) findViewById(R.id.cell63));

    }

    public void choseCell(View cell) {
        if (st == 0) {
            work.creat();
            createArrayListOfSell();
        }
        st = 1;
        if (l == 0) {
            // Устанавливает на выбранной кнопке изобрание хода игрока
            cell.setBackground(this.getResources().getDrawable(R.drawable.five_in_row_x));
            cell.setEnabled(false);

            int z = 0;
            for (ImageButton k : cellArray) {
                if (!k.isEnabled()) {
                    m = z;
                    l = work.stepMan(m, que);
                    if (l==1) break;
                }
                z++;
            }
            work.vivodM();
            que = !que;
        }

        if (l == 0) {
            // Устанавливает на выбранной кнопке изобрание хода компьютера
            // cell.setBackground(this.getResources().getDrawable(R.drawable.five_in_row_zero));
            int h = work.StepAi();
            cellArray.get(h).setBackground(this.getResources().getDrawable(R.drawable.five_in_row_zero));
            cellArray.get(h).setEnabled(false);
            // Кнопка не активна для дальнейшего выбора
            int z = 0;
            for (ImageButton k : cellArray) {
                if (!k.isEnabled()) {
                    m = z;
                    l = work.stepMan(m, que);
                    if (l==2) break;
                }
                z++;
            }
            work.vivodM();
            que = !que;
        }
        switch (l) {
            case 1: {
                TextView lostText = findViewById(R.id.resultText);
                lostText.setVisibility(View.VISIBLE);
                lostText.setText(getString(R.string.win));
                break;
            }
            case 2: {
                Button retryBut = findViewById(R.id.retry);
                retryBut.setVisibility(View.VISIBLE);
                TextView lostText = findViewById(R.id.resultText);
                lostText.setVisibility(View.VISIBLE);
                lostText.setText(getString(R.string.lost));
                break;
            }
        }
    }
}