package com.upt.touchupt;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.upt.touchupt.Conexion.Httppostaux;
import com.upt.touchupt.Fragments.RequestClientFragment;
import com.upt.touchupt.Models.ClsSolicitudes;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListRequestActivity extends AppCompatActivity{

    String IP_Server = Httppostaux.IP_Server;
    Httppostaux post;
    String URL_connect="http://"+IP_Server+"/touchupt/listarmensaje.php";
    private ArrayList<ClsSolicitudes> datasetObje= new ArrayList<ClsSolicitudes>();
    private ArrayList<ClsSolicitudes> contacAuxObjeto = new ArrayList<>();
    static String codigoid,idcurso,toolbartitulo,idDocente,codCurso,nomCurso;
    //private String tipo = Agenda.UsuTipo;
    static String iusu, cocur;
    private ProgressDialog pDialog;
    private FloatingActionButton bagregarMensaje;


    TextView titulocur;
    private FragmentManager fragmentManager = getFragmentManager();
    private Fragment[] fragments = new Fragment[]{
            new RequestClientFragment()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_request);

        //titulocur=(TextView)findViewById(R.id.txtCursoMensaje);
        //bagregarMensaje = (FloatingActionButton) findViewById(R.id.btnAgregarMensaje);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idcurso = extras.getString("idcurso");//usercodigo
            codCurso  = extras.getString("codcurso");//usuario curso
            nomCurso  = extras.getString("curso");
        }else{
            idcurso="error";
            codCurso="error";
            nomCurso="error";
        }
        cocur=codCurso;
        //iusu=Agenda.Id_usuario;
        //ValidarControles();
        CargarTooblar();
        CargarVista(0);
        /**
         * *
         *
         bagregarMensaje.setOnClickListener(new View.OnClickListener(){
         public void onClick(View view){
         Intent i=new Intent(ListaMensajes.this, GestionMensaje.class);
         i.putExtra("validar","agregar");
         i.putExtra("idcur",idcurso);

         startActivity(i);
         }
         });
         *
         */
    }
    public boolean ListarMensaje(String codcurso) {
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
        postparameters2send.add(new BasicNameValuePair("codcurso",codcurso));

        post=new Httppostaux();
        JSONArray jdatos = post.getserverdata(postparameters2send, URL_connect);
        if (jdatos!=null && jdatos.length() > 0){
            datasetObje = mensajes(jdatos);
            return true;
        }else{
            Log.e("JSON  ", "ERROR");
            return false;
        }
    }

    //Segundo plano
    class asyncListarDelegado extends AsyncTask< String, String, String > {
        String codcur;
        protected void onPreExecute(){

        }
        protected String doInBackground(String... params) {
            codcur = params[0];
            if (ListarMensaje(codcur) == true) {
                return "ok"; //login valido
            } else {
                return "err"; //login invalido
            }
        }
        protected void onPostExecute(String result) {
            Log.e("onPostExecute=",""+result);

            if (result.equals("ok")){
                Log.e("JSON - ", "Si");
            }else{
                Log.e("JSON - ", "No");
            }
        }
    }
    public ArrayList<ClsSolicitudes> mensajes(JSONArray jdatos){
        for(int i = 0; i<jdatos.length(); i++){
            ClsSolicitudes Ccurso = new ClsSolicitudes();
            try {
                JSONObject jsonObject = (JSONObject) jdatos.get(i);
                Ccurso.setIdSolicitud(jsonObject.getInt("idsolicitud"));
                Ccurso.setIdUsuario((jsonObject.getInt("idusuario")));
                Ccurso.setIdServicio((jsonObject.getInt("idservicio")));
                Ccurso.setNivelSolicitud((jsonObject.getString("nivel")));
                Ccurso.setEstadoSolicitud((jsonObject.getString("estado")));
                Ccurso.setFechaSolicitud((jsonObject.getString("fecha")));

                //Log.e("dato",Ccurso.getTitulo().toString());
                contacAuxObjeto.add(Ccurso);

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  contacAuxObjeto;
    }
    public ArrayList<ClsSolicitudes> EjecutarListMensaje(Activity activity){
        new asyncListarDelegado().execute(cocur);
        return contacAuxObjeto;
    }
    private void CargarVista(int i){

        fragmentManager.beginTransaction().replace(R.id.Contenedor_Mensajes, fragments[i]).commit();

    }
    private void CargarTooblar(){
        titulocur.setText(nomCurso);
    }

    /***
     *
     *
     * private void ValidarControles(){
     if (tipo.equals("docente")){
     bagregarMensaje.setVisibility(View.VISIBLE);
     }else{
     bagregarMensaje.setVisibility(View.INVISIBLE);
     }
     }
     *
     */
}
