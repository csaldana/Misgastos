package com.example.jonathan.misgastos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.SQLException;


public class MainActivity extends ActionBarActivity {

    ListView listview;
    SQLControlador dbConexion;
    TextView txtid, txtEtiqueta2, txtDescripcion2, txtSaldo2, Moneda2, txtTipo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbConexion = new SQLControlador(this);

        try {
            dbConexion.abrirBaseDeDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        listview = (ListView) findViewById(R.id.lsPrincipal);


        // Tomar los datos desde la base de datos para poner en el curso y después en el adapter
        Cursor cursor = dbConexion.leerDatos();

        String[] from = new String[] {
                DBHelper.CL_ID,
                DBHelper.CL_ETIQUETA,
                DBHelper.CL_DESCRIPCION,
                DBHelper.CL_SALDO,
                DBHelper.CL_SPIMONEDA,
                DBHelper.CL_SPITIPO
        };
        int[] to = new int[] {

                R.id.txtid,
                R.id.txtEtiqueta2,
                R.id.txtDescripcion2,
                R.id.txtSaldo2,
                R.id.txtMoneda2,
                R.id.txtTipo2
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this, R.layout.otro_formato, cursor, from, to);

        adapter.notifyDataSetChanged();
        listview.setAdapter(adapter);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                txtid = (TextView) view.findViewById(R.id.txtid);
                txtEtiqueta2 = (TextView) view.findViewById(R.id.txtEtiqueta2);
                txtDescripcion2 = (TextView) view.findViewById(R.id.txtDescripcion2);
                txtSaldo2 = (TextView) view.findViewById(R.id.txtSaldo2);
                Moneda2 = (TextView) view.findViewById(R.id.txtMoneda2);
                txtTipo2 = (TextView) view.findViewById(R.id.txtTipo2);


                String aux_Id = txtid.getText().toString();
                String aux_Etiqueta = txtEtiqueta2.getText().toString();
                String aux_Descripcion = txtDescripcion2.getText().toString();
                String aux_Saldo = txtSaldo2.getText().toString();
                String aux_Moneda = Moneda2.getText().toString();
                String aux_Tipo = txtTipo2.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), EditarCuenta.class);
                modify_intent.putExtra("id", aux_Id);
                modify_intent.putExtra("etiqueta", aux_Etiqueta);
                modify_intent.putExtra("descripcion", aux_Descripcion);
                modify_intent.putExtra("saldo", aux_Saldo);
                modify_intent.putExtra("spimoneda", aux_Moneda);
                modify_intent.putExtra("spitipo", aux_Tipo);
                startActivity(modify_intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_registro) {
            return true;
        }*/

        //Se controlan las acciones del action bar
        switch(item.getItemId()){
            case R.id.action_registro:
                Intent nuevaCuenta =  new Intent(getApplicationContext(), NuevaCuenta.class);
                startActivity(nuevaCuenta);
                //Toast.makeText(getApplicationContext(), "Error al inicar sesión, Por favor intenta de nuevo!",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_salir:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
