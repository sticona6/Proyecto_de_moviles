package com.upt.touchupt;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.upt.touchupt.Conexion.Httppostaux;
import com.upt.touchupt.Models.ClsServicios;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServiciosAdministratorActivity extends AppCompatActivity {

    String IP_Server = Httppostaux.IP_Server;
    Httppostaux post;
    String URL_connect="http://"+IP_Server+"/touchaupt/listarmensaje.php";
    private ArrayList<ClsServicios> datasetObje= new ArrayList<ClsServicios>();
    private ArrayList<ClsServicios> contacAuxObjeto = new ArrayList<>();
    static String codigoid,idcurso,toolbartitulo,idDocente,codCurso,nomCurso;
    //private String tipo = Agenda.UsuTipo;
    static String iusu, cocur;
    private ProgressDialog pDialog;

    //private FloatingActionButton bagregarMensaje;

    TextView titulocur;
    private FragmentManager fragmentManager = getFragmentManager();
    //private Fragment[] fragments = new Fragment[]{
      //      new MensajeFragment()
    //};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios_administrator);



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

    public ArrayList<ClsServicios> mensajes(JSONArray jdatos){
        for(int i = 0; i<jdatos.length(); i++){
            ClsServicios Ccurso = new ClsServicios();
            try {
                JSONObject jsonObject = (JSONObject) jdatos.get(i);
                Ccurso.setIdServicio(jsonObject.getString("IdServicio"));
                Ccurso.setNombreServicio(jsonObject.getString("NombreServicio"));
                Ccurso.setNombre((jsonObject.getString("Nombre")));
                Ccurso.setFechaServicio((jsonObject.getString("FechaServicio")));
                Ccurso.setCelular((jsonObject.getString("Celular")));
                Ccurso.setCiudad((jsonObject.getString("Ciudad")));

                //Log.e("dato",Ccurso.getTitulo().toString());
                contacAuxObjeto.add(Ccurso);

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  contacAuxObjeto;
    }
    public ArrayList<ClsServicios> EjecutarListMensaje(Activity activity){
        new asyncListarDelegado().execute(cocur);
        return contacAuxObjeto;
    }
    private void CargarVista(int i){

        //fragmentManager.beginTransaction().replace(R.id.Contenedor_Mensajes, fragments[i]).commit();

    }
    private void CargarTooblar(){
        titulocur.setText(nomCurso);
    }
    private void ValidarControles(){
        //if (tipo.equals("docente")){
            //bagregarMensaje.setVisibility(View.VISIBLE);
        //}else{
            //bagregarMensaje.setVisibility(View.INVISIBLE);
        //}
    }

}
