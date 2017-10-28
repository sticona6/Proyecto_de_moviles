package com.upt.touchupt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomActivity extends AppCompatActivity {

    public String usuId,usuCodigo,usuNombre,usuApellidos,usuFoto,usuemail,usucelular,usuTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);


        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
            //usuCodigo = extras.getString("usercodigo");//usercodigo
            usuNombre  = extras.getString("usernombre");//usuario
            usuApellidos  = extras.getString("userapellidos");
            usuFoto = extras.getString("userfoto");
            usuemail=extras.getString("useremail");
            usucelular=extras.getString("usercelular");
            //usuId = extras.getString("userid");
        }else{
            usuCodigo="error";
            usuNombre="error";
            usuApellidos="error";
            usuFoto="null";
            usuTipo="null";
            usuId="null";
        }

    }
}
