package com.example.jonathan.misgastos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jonathan on 24/05/2015.
 */
public class DBHelper extends SQLiteOpenHelper{

    //Información de la tabla
    public static final String TABLE_NAME = "cuentanueva";
    public static final String CL_ID = "_id";
    public static final String CL_ETIQUETA = "etiqueta";
    public static final String CL_DESCRIPCION = "descripcion";
    public static final String CL_SALDO = "saldo";
    public static final String CL_SPIMONEDA = "spimoneda";
    public static final String CL_SPITIPO = "spitipo";

    //Nombre de la base de datos y versión
   public  static final String BD_NAME = "misgastos";
    public static final int BD_VERSION = 1;

    public static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("+ CL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + ","
            + CL_ETIQUETA + " TEXT NOT NULL,"
            + CL_DESCRIPCION + " TEXT,"
            + CL_SALDO + " TEXT,"
            + CL_SPIMONEDA + " TEXT,"
            + CL_SPITIPO + " TEXT);";

    public DBHelper(Context context){
        super(context,BD_NAME, null, BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
}
