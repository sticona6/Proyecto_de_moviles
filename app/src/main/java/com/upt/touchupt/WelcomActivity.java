package com.upt.touchupt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomActivity extends AppCompatActivity {

    TextView _nombre;
    TextView _email;
    TextView _telefono;

    public String usuId,usuCodigo,usuNombre,usuFoto,usuNivel,usuEmail,usuCelular,usuEstado;

    private Button bIr;
    private Button bcerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        _nombre = (TextView)findViewById(R.id.nameTextView);
        _email = (TextView)findViewById(R.id.emailTextView);
        _telefono = (TextView)findViewById(R.id.telTextView);

        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
            //usuCodigo = extras.getString("usercodigo");//usercodigo
            usuNombre  = extras.getString("usernombre");//usuario
            usuEstado  = extras.getString("usuestado");
            usuFoto = extras.getString("userfoto");
            usuEmail=extras.getString("useremail");
            usuNivel=extras.getString("usernivel");
            usuCelular=extras.getString("usercelular");
            //usuId = extras.getString("userid");
            ColocarDatos(usuNombre,usuCelular,usuEmail);
        }else{
            usuCodigo="error";
            usuNombre="error";
            usuEstado="error";
            usuFoto="null";
        }

        bIr = (Button)findViewById(R.id.Ir);

        bIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WelcomActivity.this, OptionClientActivity.class);
                startActivity(i);
            }
        });

        bcerrar = (Button)findViewById(R.id.cerrar);

        bcerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WelcomActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_welcom,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_edit:
                showEditScreen();
                break;
        }
        return  super.onOptionsItemSelected(item);
    }

    private void showEditScreen() {
        Intent i=new Intent(WelcomActivity.this, EditClientDataActivity.class);
        i.putExtra("usernombre",usuNombre);
        i.putExtra("userestado",usuEstado);
        i.putExtra("useremail",usuEmail);
        i.putExtra("userfoto",usuFoto);
        i.putExtra("usernivel",usuNivel);
        i.putExtra("usercelular",usuCelular);
        startActivity(i);
    }

    private void ColocarDatos(String usuName,String usuCelular,String usuEmail) {
        _nombre.setText(usuName);
        _email.setText(usuEmail);
        _telefono.setText(usuCelular);
    }
}
