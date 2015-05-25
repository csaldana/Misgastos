package com.example.jonathan.misgastos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.SQLException;

/**
 * Created by jonathan on 24/05/2015.
 */
public class EditarCuenta extends Activity {

    EditText  txtEtiqueta, txtDescripcion, txtSaldo;
    Spinner spiMoneda ,spiTipo;
    Button btnactualizar, btnEliminar;
    SQLControlador dbcon;

    long _id;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editarcuenta);

        txtEtiqueta  = (EditText) findViewById(R.id.txtEtiqueta);
        txtDescripcion  = (EditText) findViewById(R.id.txtDescripcion);
        txtSaldo  = (EditText) findViewById(R.id.txtSaldo);
        spiMoneda = (Spinner) findViewById(R.id.spiMoneda);
        spiTipo = (Spinner) findViewById(R.id.spiTipo);
        btnactualizar = (Button) findViewById(R.id.btnactualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        dbcon = new SQLControlador(this);
        try {
            dbcon.abrirBaseDeDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String etiqueta = i.getStringExtra("etiqueta");
        String descripcion = i.getStringExtra("descripcion");
        String saldo = i.getStringExtra("saldo");
        String Moneda = i.getStringExtra("spiMoneda");
        String Tipo = i.getStringExtra("spiTipo");

        _id = Long.parseLong(id);

        txtEtiqueta.setText(etiqueta);
        txtDescripcion.setText(descripcion);
        txtSaldo.setText(saldo);
        spiMoneda.getSelectedItem();
        spiTipo.getSelectedItem();

        btnactualizar.setOnClickListener((android.view.View.OnClickListener) this);
        btnEliminar.setOnClickListener((android.view.View.OnClickListener) this);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnactualizar:
                String etiqueta = txtEtiqueta.getText().toString();
                String descripcion = txtDescripcion.getText().toString();
                String saldo = txtSaldo.getText().toString();
                String moneda = spiMoneda.getSelectedItem().toString();
                String tipo = spiTipo.getSelectedItem().toString();
                //dbcon.actualizarCuenta(_id, etiqueta);
                //dbcon.actualizarCuenta(_id, descripcion);
                //dbcon.actualizarCuenta(_id, saldo);
                //dbcon.actualizarCuenta(_id, moneda);
                //dbcon.actualizarCuenta(_id, tipo);

                this.returnHome();
                break;

            case R.id.btnEliminar:
                dbcon.deleteCuenta(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {

        Intent home_intent = new Intent(getApplicationContext(),
                MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }
}
