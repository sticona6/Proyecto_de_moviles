package com.upt.touchupt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.upt.touchupt.Conexion.Httppostaux;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    //Variables
    private EditText user;
    private EditText pass;
    private Button btnIr;
    private TextView btnReg;



    String IP_Server = Httppostaux.IP_Server;
    String URL_connect="http://"+"192.168.0.6:8080"+"/touchupt/funciones_bd.php";//ruta en donde estan nuestros archivos

    //volley
    private RequestQueue requestQueue;

    private StringRequest request;

    //private FloatingActionButton blogin;
    Httppostaux post;

    boolean result_back;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText)findViewById(R.id.input_email);
        pass = (EditText)findViewById(R.id.input_password);

        btnIr = (Button)findViewById(R.id.btn_login);
        btnReg = (TextView)findViewById(R.id.link_signup);

//------------------LOGIN

        requestQueue = Volley.newRequestQueue(this);

        btnIr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //Extreamos datos de los EditText
                final String usuario=user.getText().toString();
                final String passw=pass.getText().toString();


                //verificamos si estan en blanco
                if( checklogindata( usuario , passw )==true) {

                    request = new StringRequest(Request.Method.POST, URL_connect, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                if (jsonObject.names().get(0).equals("success")) {

                                    btnIr.setEnabled(false);

                                    final ProgressDialog pDialog = new ProgressDialog(LoginActivity.this);
                                    pDialog.setMessage("Autenticando....");
                                    pDialog.setIndeterminate(true);
                                    pDialog.setCancelable(false);
                                    pDialog.show();

                                    final String Usuario = jsonObject.getString("success");

                                    //para el progress dialog

                                    new android.os.Handler().postDelayed(
                                            new Runnable() {
                                                public void run() {

                                                    onLoginSuccess();
                                                    Toast.makeText(getApplicationContext(), Usuario, Toast.LENGTH_SHORT).show();
                                                    pDialog.dismiss();

                                                }
                                            }, 1000);



                                    //Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(getApplicationContext(), "Error" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                err_login2();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("email", user.getText().toString());
                            hashMap.put("password", pass.getText().toString());

                            return hashMap;
                        }
                    };

                    requestQueue.add(request);

                }else{
                    //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
                    err_login();
                }

            }
        });


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void onLoginSuccess() {
        Intent i=new Intent(LoginActivity.this, WelcomActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        btnIr.setEnabled(true);
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

    private void limpiarcajas(){
        user.setText("");
        pass.setText("");
        user.setFocusable(true);
    }
}
