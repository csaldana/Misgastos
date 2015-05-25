package com.example.jonathan.misgastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by jonathan on 24/05/2015.
 */
public class SQLControlador  {

    private DBHelper dbhelper;
    private Context outcontext;
    private SQLiteDatabase database;

    //Constructor
    public SQLControlador(Context context){
        outcontext = context;

    }
    //Abrir conexión a base de datos
    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBHelper(outcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }
    //Cerrar conexión a base de datos
    public void cerrar(){
        dbhelper.close();
    }

    public void insertarCuenta(String etiqueta, String descripcion, String saldo, String spimoneda, String spitipo){

        ContentValues valores =  new ContentValues();
        valores.put(DBHelper.CL_ETIQUETA, etiqueta);
        valores.put(DBHelper.CL_DESCRIPCION, descripcion);
        valores.put(DBHelper.CL_SALDO, saldo);
        valores.put(DBHelper.CL_SPIMONEDA, spimoneda);
        valores.put(DBHelper.CL_SPITIPO, spitipo);
        database.insert(DBHelper.TABLE_NAME, null ,valores);
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                DBHelper.CL_ID,
                DBHelper.CL_ETIQUETA,
                DBHelper.CL_DESCRIPCION,
                DBHelper.CL_SALDO,
                DBHelper.CL_SPIMONEDA,
                DBHelper.CL_SPITIPO
        };
        Cursor c = database.query(DBHelper.TABLE_NAME, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int actualizarCuenta(long _id, String etiqueta, String descripcion, String saldo, String spimoneda, String spitipo) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.CL_ETIQUETA, etiqueta);
        values.put(DBHelper.CL_DESCRIPCION, descripcion);
        values.put(DBHelper.CL_SALDO, saldo);
        values.put(DBHelper.CL_SPIMONEDA, spimoneda);
        values.put(DBHelper.CL_SPITIPO, spitipo);
        int i = database.update(DBHelper.TABLE_NAME, values,
                DBHelper.CL_ID + " = " + _id, null);
        return i;
    }

    public void deleteCuenta(long id) {
        database.delete(DBHelper.TABLE_NAME, DBHelper.CL_ID + "="
                + id, null);
    }




}
