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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.upt.touchupt.Conexion.Httppostaux;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    //Variables
    private EditText user;
    private EditText pass;
    private Button btnIr;
    private TextView btnReg;

    private static final int INTERVALO = 2000; //2 segundos para salir
    private long tiempoPrimerClick;

    String IP_Server = Httppostaux.IP_Server;
    String URL_connect="http://"+IP_Server+"/touchupt/acces.php";//ruta en donde estan nuestros archivos

    //volley
    private RequestQueue requestQueue;

    private StringRequest request;

    //private FloatingActionButton blogin;
    Httppostaux post;

    /*Datos de Usuario logeado*/
    public String usuID;
    public String usuCodigo;
    public String usuEstado;
    public String usuNombre;
    public String usuApellidos;
    public String usuFoto;
    public String usuEmail;
    public String usuNivel;
    public String usuCelular;
    public String usuTelefono;

    boolean result_back;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText)findViewById(R.id.input_email);
        pass = (EditText)findViewById(R.id.input_password);

        btnIr = (Button)findViewById(R.id.btn_login);
        btnReg = (TextView)findViewById(R.id.link_signup);

//------------------LOGIN

        btnIr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Extreamos datos de los EditText
                String usuario=user.getText().toString();
                String passw=pass.getText().toString();

                //verificamos si estan en blanco
                if( checklogindata( usuario , passw )==true){

                    //si pasamos esa validacion ejecutamos el asynctask pasando el usuario y clave como parametros
                    new asynclogin().execute(usuario,passw);
                    Log.e("datos usu y contra", usuario + passw);

                }else if(checklogindata(usuario , passw )==false){
                    //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
                    err_login();
                }else{
                    err_login3();
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });
    }

    /*   public void onClick1(View v){
        Toast.makeText(this, "Bienvenido :)", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,Agenda.class);
        startActivity(intent);
    }*/

    //vibra y muestra un Toast
    public void err_login(){

        Toast toast1 = Toast.makeText(getApplicationContext(),"Usuario o password incorrectos", Toast.LENGTH_SHORT);
        toast1.show();

    }
    //vibra y muestra un Toast
    public void err_login2(){

        Toast toast1 = Toast.makeText(getApplicationContext(),"Conectece a internet", Toast.LENGTH_SHORT);
        toast1.show();
    }
    //vibra y muestra un Toast
    private void err_login3() {
        Toast toast1 = Toast.makeText(getApplicationContext(),"Ingrese Usuario y password", Toast.LENGTH_SHORT);
        toast1.show();
    }
    /*Valida el estado del logueo solamente necesita como parametros el usuario y passw*/
    public Boolean loginstatus(String username ,String password ) {
        String logstatus="null";

    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();

        postparameters2send.add(new BasicNameValuePair("Email",username));
        postparameters2send.add(new BasicNameValuePair("Contrasena",password));

        post=new Httppostaux();
        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdatos = post.getserverdata(postparameters2send, URL_connect);
        //SystemClock.sleep(950);
        //si lo que obtuvimos no es null
        if (jdatos!=null && jdatos.length() > 0){
            JSONObject json_data; //creamos un objeto JSON
            try {
                json_data = jdatos.getJSONObject(0); //leemos el primer segmento en nuestro caso el unico
                logstatus=json_data.getString("EstadoUsuario");//accedemos al valor

                usuID = json_data.getString("IdUsuario");
                usuNombre = json_data.getString("Nombres");
                usuEstado = json_data.getString("EstadoUsuario");
                usuEmail = json_data.getString("Email");
                usuFoto = json_data.getString("Foto");
                usuNivel= json_data.getString("NivelUsuario");
                usuCelular= json_data.getString("Celular");

                Log.e("loginstatus", "EstadoUsuario = " + logstatus);//muestro por log que obtuvimos
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //validamos el valor obtenido
            if (logstatus.equals("null") || logstatus.equals("inactivo")){// [{"estado":"null"}]
                Log.e("loginstatus ", "invalido");
                return false;
            }
            else{
                Log.e("loginstatus ", "invalido");
                return true;
            }
        }else{	//json obtenido invalido verificar parte WEB.
            Log.e("JSON  ", "ERROR");
            return false;
        }
    }

    //validamos si no hay ningun campo en blanco
    public boolean checklogindata(String username ,String password ){

        if 	(username.equals("") || password.equals("")){
            Log.e("Login ui", "checklogindata user or pass error");
            return false;
        }else{
            return true;
        }
    }

    class asynclogin extends AsyncTask< String, String, String > {

        String user,pass;
        protected void onPreExecute() {
            //para el progress dialog
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Autenticando....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... params) {
            //obtnemos usr y pass
            user=params[0];
            pass=params[1];

            //enviamos y recibimos y analizamos los datos en segundo plano.
            if (loginstatus(user,pass)==true){
                return "ok"; //login valido
            }else {
                return "err"; //login invalido
            }
        }

        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a la sig. activity
        o mostramos error*/
        protected void onPostExecute(String result) {

            pDialog.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=",""+result);

            if (result.equals("ok")){
                limpiarcajas();
                if(usuNivel.equals("cliente")){
                    Intent intent=new Intent(LoginActivity.this, WelcomActivity.class);
                    intent.putExtra("IdUsuario",usuID);
                    intent.putExtra("usernombre",usuNombre);
                    intent.putExtra("userestado",usuEstado);
                    intent.putExtra("useremail",usuEmail);
                    intent.putExtra("userfoto",usuFoto);
                    intent.putExtra("usernivel",usuNivel);
                    intent.putExtra("usercelular",usuCelular);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }else if(usuNivel.equals("empleado")){
                    Intent intent=new Intent(LoginActivity.this, ServicioEmployeeActivity.class);
                    intent.putExtra("IdUsuario",usuID);
                    intent.putExtra("usernombre",usuNombre);
                    intent.putExtra("userestado",usuEstado);
                    intent.putExtra("useremail",usuEmail);
                    intent.putExtra("userfoto",usuFoto);
                    intent.putExtra("usernivel",usuNivel);
                    intent.putExtra("usercelular",usuCelular);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }else if(usuNivel.equals("administrador")){
                    Intent intent=new Intent(LoginActivity.this, ListRequestActivity.class);
                    intent.putExtra("IdUsuario",usuID);
                    intent.putExtra("usernombre",usuNombre);
                    intent.putExtra("userestado",usuEstado);
                    intent.putExtra("useremail",usuEmail);
                    intent.putExtra("userfoto",usuFoto);
                    intent.putExtra("usernivel",usuNivel);
                    intent.putExtra("usercelular",usuCelular);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            }else{
                err_login2();
            }
        }
    }

    @Override
    public void onBackPressed(){
        if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show();
        }
        tiempoPrimerClick = System.currentTimeMillis();
    }

    private void limpiarcajas(){
        user.setText("");
        pass.setText("");
        user.setFocusable(true);
    }
}
