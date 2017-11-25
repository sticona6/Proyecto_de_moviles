package com.upt.touchupt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.upt.touchupt.Conexion.Httppostaux;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    private TextView irLogin;

    private EditText name;
    private EditText mobile;
    private EditText email;
    private EditText password;
    private EditText reContraseña;

    Httppostaux post;

    //String Contrasena;
    /*private String Contrasena;
    private String Nombres;
    private String EstadoUsuario;
    private String Email;
    private String Foto;
    private String NivelUsuario;
    private String Celular;*/


    String IP_Server = Httppostaux.IP_Server;
    String URL_connect_agregar="http://"+IP_Server+"/touchupt/agregarmensaje.php";

    private ProgressDialog pDialog;

    private Button btn_signup;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText)findViewById(R.id.input_name);
        mobile =(EditText)findViewById(R.id.input_mobile);
        email = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        reContraseña = (EditText) findViewById(R.id.input_reContraseña);

        Log.e("Esto es en el boton :", name.getText().toString() + " - " + mobile.getText().toString());

        btn_signup = (Button)findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Contrasena = password.getText().toString();
                String Nombres=name.getText().toString();
                String EstadoUsuario="activo";
                String Email= email.getText().toString();
                String Foto="";
                String NivelUsuario="cliente";
                String Celular=mobile.getText().toString();

                new asyncAgregar().execute(Contrasena,Nombres,EstadoUsuario,Email,Foto,NivelUsuario,Celular);
                Log.e("Esto es en el boton :", Contrasena + " - " + Nombres);
            }
        });

        irLogin = (TextView) findViewById(R.id.link_login);

        irLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        });
    }

    //===========AGREGAR
    public boolean Agregar(String  Contrasena,String Nombres, String EstadoUsuario,String Email,String Foto,String NivelUsuario,String Celular) {
        String status="null";

        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
        postparameters2send.add(new BasicNameValuePair("Contrasena",Contrasena));
        postparameters2send.add(new BasicNameValuePair("Nombres",Nombres));
        postparameters2send.add(new BasicNameValuePair("EstadoUsuario",EstadoUsuario));
        postparameters2send.add(new BasicNameValuePair("Email",Email));
        postparameters2send.add(new BasicNameValuePair("Foto",Foto));
        postparameters2send.add(new BasicNameValuePair("NivelUsuario",NivelUsuario));
        postparameters2send.add(new BasicNameValuePair("Celular",Celular));
        //Log.e("JSON  ",  IdCurso+ IdDocente+  titulo+ detalle+ fecha);
        post=new Httppostaux();
        JSONArray jdatos = post.getserverdata(postparameters2send, URL_connect_agregar);

        Log.e("lista de datos",Contrasena + " " + Nombres);

        if (jdatos!=null && jdatos.length() > 0){
            JSONObject json_data;
            try {
                json_data = jdatos.getJSONObject(0);
                status=json_data.getString("estado");

                Log.e("agregarstatus", "estado = " + status);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //validamos el valor obtenido
            if (status.equals("0")){// [{"estado":"0"}]
                Log.e("agregarstatus ", "invalido");
                return false;
            }
            else{// [{"estado":"activo"}]
                Log.e("agregarstatus ", "valido");
                return true;
            }

        }else{	//json obtenido invalido verificar parte WEB.
            Log.e("JSON  ", "ERROR");
            return false;
        }

    }
    public class asyncAgregar extends AsyncTask< String, String, String > {

        String   Contrasena, Nombres,  EstadoUsuario, Email, Foto, NivelUsuario, Celular;
        protected void onPreExecute() {
            //para el progress dialog
            pDialog = new ProgressDialog(SignUpActivity.this);
            pDialog.setMessage("Agregando....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... params) {

            Log.e("lista de datos",params[0] + " , " + params[1] + " , " + params[2] + " , " + params[3] + " , " + params[4]);

            //obtnemos usr y pass
            Contrasena=params[0];
            Nombres=params[1];
            EstadoUsuario=params[2];
            Email=params[3];
            Foto=params[4];
            NivelUsuario=params[5];
            Celular=params[6];

            //enviamos y recibimos y analizamos los datos en segundo plano.
            if (Agregar(Contrasena, Nombres,  EstadoUsuario, Email, Foto, NivelUsuario, Celular)==true){
                return "ok"; //login valido
            }else{
                return "err"; //login invalido
            }
        }
        protected void onPostExecute(String result) {

            pDialog.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=",""+result);

            if (result.equals("ok")){
                msg_agregar();
            }else{
                err_agregar();
            }
        }
    }

    public void err_agregar(){
        //Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //vibrator.vibrate(200);
        Toast toast1 = Toast.makeText(getApplicationContext(),"Error: No se pudo Agregar", Toast.LENGTH_SHORT);
        toast1.show();
    }
    public void msg_agregar(){
        //Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //vibrator.vibrate(200);
        Toast toast1 = Toast.makeText(getApplicationContext(),"Agregardo correctamente", Toast.LENGTH_SHORT);
        toast1.show();
    }

    private boolean  validarContenido( String url){
        if(!url.equals("")){
            return true;
        }
        else{
            return false;
        }
    }
}
