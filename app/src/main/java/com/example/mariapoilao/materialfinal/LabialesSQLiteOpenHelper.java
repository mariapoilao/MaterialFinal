package com.example.mariapoilao.materialfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mariapoilao on 4/06/17.
 */

public class LabialesSQLiteOpenHelper extends SQLiteOpenHelper {
    private String sql = "CREATE TABLE Labiales(uuid text, urlfoto text, idlabial text, marca text, precio text, idfoto text)";
    private static int version=1;
    public LabialesSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory){

        super(contexto, name, factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Labiales");
        db.execSQL(sql);
    }
}
