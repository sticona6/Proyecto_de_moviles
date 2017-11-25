package com.upt.touchupt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditClientDataActivity extends AppCompatActivity {

    private EditText _name;
    private EditText _celular;
    private EditText _email;

    private Button bEdit;

    public String usuId,usuCodigo,usuNombre,usuFoto,usuNivel,usuEmail,usuCelular,usuEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client_data);

        _name = (EditText)findViewById(R.id.input_name);
        _celular = (EditText)findViewById(R.id.input_mobile);
        _email = (EditText)findViewById(R.id.input_email);

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

        bEdit = (Button)findViewById(R.id.btn_signup);

        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }
    private void ColocarDatos(String usuName,String usuCelular,String usuEmail) {
        _name.setText(usuName);
        _email.setText(usuEmail);
        _celular.setText(usuCelular);
    }
}
