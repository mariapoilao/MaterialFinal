package com.example.mariapoilao.materialfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements AdaptadorLabiales.OnLabialClickListener {
    private RecyclerView listado;
    private ArrayList<Labial> labiales;
    private AdaptadorLabiales adapter;
    private LinearLayoutManager llm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listado = (RecyclerView) findViewById(R.id.lstOpciones);

        labiales = Datos.traerLabiales(getApplicationContext());
        adapter = new AdaptadorLabiales(labiales,this);

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);

    }

    public void agregar(View v){
        finish();
        Intent i = new Intent(Principal.this, AgregarLabiales.class);
        startActivity(i);
    }

    @Override
    public void onLabialClick(Labial p) {
        Intent i = new Intent(Principal.this,DetalleLabiales.class);
        Bundle b = new Bundle();
        b.putString("marca",p.getMarca());
        b.putString("precio",p.getPrecio());
        b.putString("urlfoto",p.getUrlfoto());

        i.putExtra("datos",b);
        startActivity(i);
    }

}
