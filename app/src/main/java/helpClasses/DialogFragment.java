package helpClasses;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.a16ivt1.quest.GameActivity;
import com.a16ivt1.quest.MainActivity;

public class DialogFragment extends android.app.DialogFragment {

    static int mNum;

    /*Создает фрагмент
    * В него заливает Алерт - уведомление для пользователя
    * В зависимости от места создания фрагмента
    * Создаются разные Алерты*/

    public static DialogFragment newInstance(int num) {
        DialogFragment f = new DialogFragment();
        mNum = num;
        return f;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        switch (mNum)
        {
            case 1:
            {
                /*Создает новый Алерт на фрагменте экрана с 3 пунктами
                * Для выбора сложности игры*/
                final String[] mChooseLvl = {"Просто", "Средне", "Сложно"};

                builder.setTitle("Выберите уровень сложности для мини-игр")
                        .setCancelable(false)
                        .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                            //Добавляет на Алерт новую кнопку НАЗАД и ставит на нее слушител
                            // на нажатие
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
                /*Создается алерт с описанием игры Оттелло*/
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
                /*Создается алерт с описанием игры 5 в ряд */
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
        /*Класс вызывает новую активити
        * Находится здесь по причине того, что точно такой же метод не может быть вызван из мейна
        * Так как должен быть статиком
        * Но метож startActivity не статик и не может вызываться из статичного метода*/
        Intent intent = new Intent(getActivity(), GameActivity.class);
        startActivity(intent);
    }
}

