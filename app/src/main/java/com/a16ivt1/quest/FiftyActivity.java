package com.a16ivt1.quest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import helpClasses.DialogFragment;

public class FiftyActivity extends AppCompatActivity {

    private static final int FIFTY_INFO = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fifty);
    }

    public void infoClick(View view) {
        // Вызов алерта для пояснения правил игры
        // На экрана выделяется фрагмент, куда помещается алерт
        android.app.DialogFragment newFragment = DialogFragment.newInstance(FIFTY_INFO);
        newFragment.show(getFragmentManager(), "dialog");
    }
}
