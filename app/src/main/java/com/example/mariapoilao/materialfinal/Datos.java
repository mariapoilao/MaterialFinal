package com.example.mariapoilao.materialfinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by mariapoilao on 4/06/17.
 */

public class Datos {

    public static ArrayList<Labial> traerLabiales(Context contexto){
        ArrayList<Labial> labiales = new ArrayList<>();
        SQLiteDatabase db;
        String sql, uuid,urlfoto, idlabial, marca, precio, idfoto;
        Labial p;
        LabialesSQLiteOpenHelper aux = new LabialesSQLiteOpenHelper(contexto,"DBLabiales",null);
        db = aux.getReadableDatabase();

        sql ="select * from Labiales";
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                uuid = c.getString(0);
                urlfoto=c.getString(1);
                idlabial = c.getString(2);
                marca =c.getString(3);
                precio = c.getString(4);
                idfoto = c.getColumnName(5);

                p = new Labial(uuid,urlfoto,idlabial,marca,precio,idfoto);
                labiales.add(p);

            }while (c.moveToNext());
        }

        db.close();

        return labiales;

    }



}