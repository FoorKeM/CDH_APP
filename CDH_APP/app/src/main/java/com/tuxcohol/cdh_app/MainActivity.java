package com.tuxcohol.cdh_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tuxcohol.cdh_app.Fragments.Form1Fragment;
import com.tuxcohol.cdh_app.Fragments.Form2Fragment;
import com.tuxcohol.cdh_app.Fragments.Form3Fragment;
import com.tuxcohol.cdh_app.Vistas.Acerca;
import com.tuxcohol.cdh_app.Vistas.Formulario;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        // getSupportActionBar().setTitle("TuxCohol");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }
    
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent i1 = null;

        Fragment miFragment=null;
        boolean fragmentSeleccionado=false;

        int id = item.getItemId();

        if (id == R.id.nav_Inicio) {
            i1 = new Intent(this, MainActivity.class);
        } else if (id == R.id.nav_formu) {
            miFragment=new Form1Fragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_formu_mod) {
            miFragment=new Form2Fragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_formu_foto) {
            miFragment=new Form3Fragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_Acerca) {
            i1 = new Intent(this, Acerca.class);
        }/*else if (id == R.id.nav_Login) {
           i1 = new Intent(this, Login.class);
        }*/

        if (fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.,miFragment).commit();
        }

        if (i1 != null) {
            startActivity(i1);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
