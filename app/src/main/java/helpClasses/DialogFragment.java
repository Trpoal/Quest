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
        switch (mNum) {
            case 1: {

            }
            case 2: {
                /*Создается алерт с описанием игры Оттелло*/
                builder.setMessage("ИГРА ОТЕЛЛО").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                break;
            }
            case 3: {
                /*Создается алерт с описанием игры 5 в ряд */
                builder.setMessage("ИГРА 5 В РЯД").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                break;
            }
            case 4: {
                /*Создается алерт с описанием игры 5 в ряд */
                builder.setMessage("ИГРА ПЯТНАШКИ").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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

}

