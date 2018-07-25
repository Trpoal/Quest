package com.a16ivt1.quest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;

import helpClasses.DialogFragment;

public class FiveInRowActivity extends AppCompatActivity {

    /*Класс реализует работу активити с игрой 5 в ряд*/

    private static final int FIVE_IN_ROW_INFO = 3;

    ArrayList<ImageButton> cellArray = new ArrayList<>(); // Массив всех кнопок на поле

    boolean que = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_five_in_row);
    }

    public void infoClick(View view) {
        // Вызов алерта для пояснения правил игры
        // На экрана выделяется фрагмент, куда помещается алерт
        android.app.DialogFragment newFragment = DialogFragment.newInstance(FIVE_IN_ROW_INFO);
        newFragment.show(getFragmentManager(), "dialog");
    }

    void createArrayListOfSell()
    {
        /*Объявление всех клеток с xml файла*/
        cellArray.add((ImageButton) findViewById(R.id.cell0));
        cellArray.add((ImageButton) findViewById(R.id.cell1));
        cellArray.add((ImageButton) findViewById(R.id.cell1));
        cellArray.add((ImageButton) findViewById(R.id.cell2));
        cellArray.add((ImageButton) findViewById(R.id.cell3));
        cellArray.add((ImageButton) findViewById(R.id.cell4));
        cellArray.add((ImageButton) findViewById(R.id.cell5));
        cellArray.add((ImageButton) findViewById(R.id.cell6));
        cellArray.add((ImageButton) findViewById(R.id.cell8));
        cellArray.add((ImageButton) findViewById(R.id.cell8));
        cellArray.add((ImageButton) findViewById(R.id.cell10));
        cellArray.add((ImageButton) findViewById(R.id.cell11));
        cellArray.add((ImageButton) findViewById(R.id.cell12));
        cellArray.add((ImageButton) findViewById(R.id.cell13));
        cellArray.add((ImageButton) findViewById(R.id.cell14));
        cellArray.add((ImageButton) findViewById(R.id.cell15));
        cellArray.add((ImageButton) findViewById(R.id.cell16));
        cellArray.add((ImageButton) findViewById(R.id.cell16));
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
        /*Ставит на клетку поля "фишку" игрока*/
        if(que)
        {
            // Устанавливает на выбранной кнопке изобрание хода игрока
            cell.setBackground(this.getResources().getDrawable(R.drawable.five_in_row_x));
        }
        else
        {
            // Устанавливает на выбранной кнопке изобрание хода компьютера
            cell.setBackground(this.getResources().getDrawable(R.drawable.five_in_row_zero));
        }
        cell.setEnabled(false); // Кнопка не активна для дальнейшего выбора
        que = !que;
    }
}
