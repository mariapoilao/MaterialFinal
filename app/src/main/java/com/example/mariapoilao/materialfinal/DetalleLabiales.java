package com.example.mariapoilao.materialfinal;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetalleLabiales extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Labial p;
    private String marca,precio,urlfoto;
    private Bundle b;
    private Intent i;
    private ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_labiales);

        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        i = getIntent();
        b=i.getBundleExtra("datos");
        marca = b.getString("marca");
        precio = b.getString("precio");
        urlfoto = b.getString("urlfoto");

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        foto = (ImageView)findViewById(R.id.fotoLabial);

        Picasso.with(getApplicationContext()).load(urlfoto).into(foto);
        collapsingToolbarLayout.setTitle(marca+" "+precio);

    }
}