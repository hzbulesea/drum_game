package drumgame.example.drum_game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyDB extends SQLiteOpenHelper {

    SQLiteDatabase db;
    Context ctx;
    int VERSION = 1;
    String TABLE_NAME = "SCORE_TABLE";


    public MyDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +  "(_id INTEGER PRIMARY KEY, SONG STRING, SCORE INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(VERSION == oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
            VERSION = newVersion;
            onCreate(db);
        }
    }


    public Cursor view() {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+";", null);
        return cursor;
    }

    public void insert(String song, int scores) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("SONG", song);
        cv.put("SCORE", scores);
        db.insert(TABLE_NAME, null, cv);
    }

    public void update(String song, int scores) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("SONG", song);
        cv.put("SCORE", scores);
        db.execSQL("UPDATE " + TABLE_NAME + " SET SONG = \"" + song + "\", SCORE = \"" + scores + "\" WHERE SONG = \"" + song + "\" OR SCORE = \"" + scores + "\";");
    }
}
