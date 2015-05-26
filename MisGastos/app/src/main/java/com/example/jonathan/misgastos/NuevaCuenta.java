package com.example.jonathan.misgastos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by jonathan on 22/05/2015.
 */
public class NuevaCuenta extends Activity implements View.OnClickListener {

    EditText txtEtiqueta, txtDescripcion, saldo_inicial, moneda, tipo;
    Button btnGuardar;
    Spinner spiMoneda, spiTipo;
    SQLControlador dbConexion;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevacuenta);


        txtEtiqueta = (EditText)findViewById(R.id.txtEtiqueta);
        txtDescripcion = (EditText)findViewById(R.id.txtDescripcion);
        saldo_inicial = (EditText)findViewById(R.id.txtSaldo);
        spiMoneda = (Spinner)findViewById(R.id.spiMoneda);
        spiTipo = (Spinner)findViewById(R.id.spiTipo);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);

        //Combo box de tipo de modena
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.moneda, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiMoneda.setAdapter(adapter);

        //Combo box de tipo de Cuenta
        ArrayAdapter adapterCuent = ArrayAdapter.createFromResource(this, R.array.tipo, android.R.layout.simple_spinner_item);
        adapterCuent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiTipo.setAdapter(adapterCuent);

        dbConexion = new SQLControlador(this);

        try {
            dbConexion.abrirBaseDeDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnGuardar.setOnClickListener(this);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnGuardar:
            String etiqueta = txtEtiqueta.getText().toString();
            String descripcion = txtDescripcion.getText().toString();
            String saldo = saldo_inicial.getText().toString();
            String spimoneda = spiMoneda.getSelectedItem().toString();
            String spitipo = spiTipo.getSelectedItem().toString();

            if(etiqueta.equals("")){
                Toast.makeText(getApplicationContext(), "Error, Revisar nuevamente", Toast.LENGTH_SHORT).show();
            }else {
                dbConexion.insertarCuenta(etiqueta, descripcion, saldo, spimoneda, spitipo);
                Intent main = new Intent(NuevaCuenta.this, MainActivity.class);
                startActivity(main);
            }
            default:
            break;
        }

    }



}
