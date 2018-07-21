package com.a16ivt1.quest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

public class DialogFragment extends android.app.DialogFragment {

    int mNum;

    static DialogFragment newInstance(int num) {
        DialogFragment f = new DialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        switch (mNum)
        {
            case 1:
            {
                final String[] mChooseLvl = {"Просто", "Средне", "Сложно"};

                builder.setTitle("Выберите уровень сложности для мини-игр")
                        .setCancelable(false)
                        .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setSingleChoiceItems(mChooseLvl, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.complexityOfGame = which;
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startAct();
                                dialogInterface.cancel();

                            }
                        });
                break;
            }
            case 2:
            {
                builder.setMessage("ИГРА ОТЕЛЛО").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                break;
            }
            case 3:
            {
                builder.setMessage("ИГРА 5 В РЯД").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                break;
            }
        }





        return builder.create();
    }


    public void startAct()
    {
        Intent intent = new Intent(getActivity(), GameActivity.class);
        startActivity(intent);
    }
}

