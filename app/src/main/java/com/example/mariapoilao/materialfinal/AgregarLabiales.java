package com.example.mariapoilao.materialfinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;

public class AgregarLabiales extends AppCompatActivity {
    private EditText cajaID;
    private EditText cajaMarca;
    private EditText cajaPrecio;
    private boolean guardado;
    private TextInputLayout icajaID;
    private TextInputLayout icajaMarca;
    private TextInputLayout icajaPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_labiales);

        cajaID = (EditText)findViewById(R.id.idLabial);
        cajaMarca= (EditText)findViewById(R.id.marca);
        cajaPrecio = (EditText)findViewById(R.id.precio);

        icajaID = (TextInputLayout) findViewById(R.id.idlabial);
        icajaMarca = (TextInputLayout)findViewById(R.id.marcaLabial);
        icajaPrecio = (TextInputLayout)findViewById(R.id.precioLabial);
        guardado = false;

        cajaID.addTextChangedListener(new TextWatcherPersonalizado(icajaID,getResources().getString(R.string.mensaje_error_id)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s) && !guardado) return true;
                else return false;
            }
        });


        cajaMarca.addTextChangedListener(new TextWatcherPersonalizado(icajaMarca,getResources().getString(R.string.mensaje_error_marca)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });


        cajaPrecio.addTextChangedListener(new TextWatcherPersonalizado(icajaPrecio,getResources().getString(R.string.mensaje_error_precio)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });

    }




    public int fotoAleatoria(){
        int fotos[] = {R.drawable.foto1,R.drawable.foto2,R.drawable.foto3};
        int numero = (int)(Math.random() * 3);
        return fotos[numero];
    }
    public void guardar(View v)  {
        String urlfoto,idlabial,marca,precio,idfoto;
        Labial p;
        int foto;

        if(validarTodo()){
            idlabial = cajaID.getText().toString();

            marca = cajaMarca.getText().toString();
            precio=cajaPrecio.getText().toString();

            foto = fotoAleatoria();
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),foto);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
            byte[] imagenBytes = baos.toByteArray();
            urlfoto = Base64.encodeToString(imagenBytes,Base64.DEFAULT);
            try {
                baos.close();
            }catch (Exception e){

            }
            idfoto=String.valueOf(foto);
            p = new Labial(urlfoto,idlabial,marca,precio,idfoto);
            p.guardar(getApplicationContext());

            InputMethodManager imp =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imp.hideSoftInputFromWindow(cajaID.getWindowToken(),0);
            Snackbar.make(v,getResources().getString(R.string.mensaje_exitoso_guardar),Snackbar.LENGTH_SHORT).show();
            guardado= true;
            limpiar();


        }
    }

    public void limpiar(){
        cajaID.setText("");
        cajaMarca.setText("");
        cajaPrecio.setText("");

        cajaID.requestFocus();

        guardado = false;
    }

    public boolean validarTodo(){
        if(cajaID.getText().toString().isEmpty()){
            icajaID.setError(getResources().getString(R.string.mensaje_error_id));
            cajaID.requestFocus();
            return false;
        }
        if(cajaMarca.getText().toString().isEmpty()){
            icajaMarca.setError(getResources().getString(R.string.mensaje_error_marca));
            cajaMarca.requestFocus();
            return false;
        }
        if(cajaPrecio.getText().toString().isEmpty()){
            icajaPrecio.setError(getResources().getString(R.string.mensaje_error_precio));
            cajaPrecio.requestFocus();
            return false;
        }


        return true;
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarLabiales.this,Principal.class);
        startActivity(i);
    }
}

