package com.tuxcohol.cdh_app.Vistas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tuxcohol.cdh_app.R;

public class Acerca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);
        //Entrega un nombre a la barra superior y habilita el boton de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Acerca de TuxCohol");
    }
}
