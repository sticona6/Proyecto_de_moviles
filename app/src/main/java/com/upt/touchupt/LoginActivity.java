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

public class LoginActivity extends AppCompatActivity {

    //Variables
    private EditText user;
    private EditText pass;
    private Button btnIr;
    private TextView btnReg;

    /*Datos de Usuario logeado*/
    public String usuID;
    public String usuNombre;
    public String usuApellidos;
    public String usuFoto;
    public String usuEmail;
    public String usuCelular;
    public String usuNivel;
    public String usuEstado;

    String IP_Server = Httppostaux.IP_Server;
    String URL_connect="http://"+IP_Server+"/touchupt/acces.php";//ruta en donde estan nuestros archivos

    //private FloatingActionButton blogin;
    Httppostaux post;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText)findViewById(R.id.input_email);
        pass = (EditText)findViewById(R.id.input_password);

        btnIr = (Button)findViewById(R.id.btn_login);
        btnReg = (TextView)findViewById(R.id.link_signup);

//------------------LOGIN

        btnIr.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                //Extreamos datos de los EditText
                String usuario=user.getText().toString();
                String passw=pass.getText().toString();

                //verificamos si estan en blanco
                if( checklogindata( usuario , passw )==true){

                    //si pasamos esa validacion ejecutamos el asynctask pasando el usuario y clave como parametros
                    new asynclogin().execute(usuario,passw);

                }else{
                    //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
                    err_login();
                }

            }
        });
//------------------REGISTER
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    //vibra y muestra un Toast
    public void err_login(){
        //Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //vibrator.vibrate(200);
        Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
    }

    public void err_login2(){
        //Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //vibrator.vibrate(200);
        Toast.makeText(getApplicationContext(),"Correo electrónico o contraseña incorrectos", Toast.LENGTH_SHORT).show();
    }

    /*Valida el estado del logueo solamente necesita como parametros el usuario y passw*/
    public boolean loginstatus(String username ,String password ) {
        String logstatus="null";

    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();

        postparameters2send.add(new BasicNameValuePair("email",username));
        postparameters2send.add(new BasicNameValuePair("password",password));

        post=new Httppostaux();
        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdatos = post.getserverdata(postparameters2send, URL_connect);
        //SystemClock.sleep(950);
        //si lo que obtuvimos no es null
        if (jdatos!=null && jdatos.length() > 0){
            JSONObject json_data; //creamos un objeto JSON
            try {
                json_data = jdatos.getJSONObject(0); //leemos el primer segmento en nuestro caso el unico
                logstatus=json_data.getString("estado");//accedemos al valor

                //usuID = json_data.getString("id");
                usuNombre = json_data.getString("nombre");
                usuApellidos = json_data.getString("apellido");
                usuFoto = json_data.getString("foto");
                usuEmail= json_data.getString("email");
                usuCelular= json_data.getString("celular");
                usuNivel = json_data.getString("nivel");

                Log.e("loginstatus", "estado = " + logstatus);//muestro por log que obtuvimos
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //validamos el valor obtenido
            if (logstatus.equals("null") || logstatus.equals("inactivo")){// [{"estado":"null"}]
                Log.e("loginstatus ", "invalido");
                return false;
            }
            else{// [{"estado":"activo"}]
                Log.e("loginstatus ", "valido");
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

    /*		CLASE ASYNCTASK
 *
 * usaremos esta para poder mostrar el dialogo de progreso mientras enviamos y obtenemos los datos
 * podria hacerse lo mismo sin usar esto pero si el tiempo de respuesta es demasiado lo que podria ocurrir
 * si la conexion es lenta o el servidor tarda en responder la aplicacion sera inestable.
 * ademas observariamos el mensaje de que la app no responde.
 */

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
            }else{
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
                Intent i=new Intent(LoginActivity.this, WelcomActivity.class);

                i.putExtra("usernombre",usuNombre);
                i.putExtra("userapellidos",usuApellidos);
                i.putExtra("userfoto",usuFoto);
                i.putExtra("useremail",usuEmail);
                i.putExtra("usercelular",usuCelular);

                startActivity(i);
            }else{
                err_login2();
            }

        }
    }

    private void limpiarcajas(){
        user.setText("");
        pass.setText("");
        user.setFocusable(true);

    }

}
