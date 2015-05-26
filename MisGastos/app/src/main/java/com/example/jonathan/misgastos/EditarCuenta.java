package com.example.jonathan.misgastos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by jonathan on 24/05/2015.
 */
public class EditarCuenta extends Activity implements View.OnClickListener{

    EditText  txtEtiqueta, txtDescripcion, txtSaldo;
    Spinner spiMoneda ,spiTipo;
    Button btnactualizar, btnEliminar;

    SQLControlador dbcon;

    long _id;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editarcuenta);

        dbcon = new SQLControlador(this);
        try {
            dbcon.abrirBaseDeDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        txtEtiqueta  = (EditText) findViewById(R.id.txtEtiquetaEditar);
        txtDescripcion  = (EditText) findViewById(R.id.txtDescripcionEditar);
        txtSaldo  = (EditText) findViewById(R.id.txtSaldoEditar);
        spiMoneda = (Spinner) findViewById(R.id.spiMonedaEditar);
        spiTipo = (Spinner) findViewById(R.id.spiTipoEditar);
        btnactualizar = (Button) findViewById(R.id.btnactualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);



        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String etiqueta = i.getStringExtra("etiqueta");
        String descripcion = i.getStringExtra("descripcion");
        String saldo = i.getStringExtra("saldo");
        String spioneda = i.getStringExtra("spiMoneda");
        String Tipo = i.getStringExtra("spiTipo");

        _id = Long.parseLong(id);

        txtEtiqueta.setText(etiqueta);
        txtDescripcion.setText(descripcion);
        txtSaldo.setText(saldo);
        spiMoneda.getSelectedItem();
        spiTipo.getSelectedItem();

        btnactualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnactualizar:
                String etiqueta = txtEtiqueta.getText().toString();
                String descripcion = txtDescripcion.getText().toString();
                String saldo = txtSaldo.getText().toString();
                String spimoneda = spiMoneda.getSelectedItem().toString();
                String spitipo = spiTipo.getSelectedItem().toString();

                if(etiqueta.equals("")) {

                    Toast.makeText(getApplicationContext(), "Error, Revisar nuevamente", Toast.LENGTH_SHORT).show();

                }
                else{
                    dbcon.actualizarCuenta(_id, etiqueta, descripcion, saldo, spimoneda, spitipo);
                }


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
