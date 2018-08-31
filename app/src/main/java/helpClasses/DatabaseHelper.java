package helpClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.a16ivt1.quest.GameActivity;
import com.a16ivt1.quest.MainActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.a16ivt1.quest.GameActivity.APP_PREFERENSES;

/* Класс для работы с базой данных */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH = null;
    private static String DB_NAME = "Quest.db";
    private static int DB_VERSION = 9;
    private SQLiteDatabase myDataBase;
    private final Context myContext;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
        this.DB_PATH = this.myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        Log.e("Path 1", DB_PATH);
    }


    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        SQLiteDatabase db_Read = null;

        if (!dbExist) {
            synchronized (this) {

                db_Read = this.getReadableDatabase();
                Log.e("Path 2", this.getReadableDatabase().getPath());
                db_Read.close();

                copyDataBase();
                Log.v("copyDataBase---", "Successfully");
            }
        }
        else
        {
            this.getReadableDatabase();
        }

    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {

            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            //onUpgrade(checkDB, GameActivity.DB_VERSION, checkDB.getVersion());
        } catch (SQLiteException e) {
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        /*Чтение базы данных*/
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query("Quest", null, null, null, null, null, null);
    }

}
