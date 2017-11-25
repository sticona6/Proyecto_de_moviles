package com.upt.touchupt.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.upt.touchupt.Models.ClsSolicitudes;
import com.upt.touchupt.R;

import java.util.ArrayList;

/**
 * Created by SERGIO on 24/11/2017.
 */

public class RequestClientAdapter extends RecyclerView.Adapter<RequestClientAdapter.ViewHolder>{
    private ArrayList<ClsSolicitudes> objetoss;
    private int itemLayout;

    //variable publica static
    public static ArrayList<ClsSolicitudes> objetodatos ;

    public RequestClientAdapter(ArrayList<ClsSolicitudes> cursos, int itemLayout){
        this.objetoss = cursos;
        this.itemLayout = itemLayout;

        //variable publica static
        objetodatos = cursos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {
        ImageView image;
        TextView titulo;
        TextView fecha;

    public ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            image = (ImageView) itemView.findViewById(R.id.imgEstadoMensaje);
            titulo = (TextView) itemView.findViewById(R.id.txtM_Titulo);
            fecha = (TextView) itemView.findViewById(R.id.txtM_fecha);

        }

        @Override
        public void onClick(View v) {
            ClsSolicitudes objetoX = objetodatos.get(getPosition());
            String idsoliciutedes = String.valueOf(objetoX.getIdSolicitud());
            String idusuario = String.valueOf(objetoX.getIdUsuario());
            String idubicacion = String.valueOf(objetoX.getIdUbicacion());
            String idServicio = String.valueOf(objetoX.getIdServicio());
            String nivelsolicitud = String.valueOf(objetoX.getEstadoSolicitud());
            String estadosolicitud = String.valueOf(objetoX.getFechaSolicitud());
            String fechasolicitud = String.valueOf(objetoX.getNivelSolicitud());
            /*if(Agenda.UsuTipo.equals("alumno")){
                Toast.makeText(itemView.getContext(), "Mensaje : " + idcurso + " " + titulo, Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(v.getContext(), DetalleMensaje.class);
                myIntent.putExtra("idmensaje", id);
                myIntent.putExtra("idcur", idcurso);
                myIntent.putExtra("iddoc", iddocente);
                myIntent.putExtra("titulo", titulo);
                myIntent.putExtra("fecha", fecha);
                myIntent.putExtra("detalle", detalle);
                myIntent.putExtra("estado", estado);

                v.getContext().startActivity(myIntent);
            }else{
                Toast.makeText(itemView.getContext(), "Mensaje : " + getPosition() + " " + titulo, Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(v.getContext(),GestionMensaje.class);
                myIntent.putExtra("validar", "actualizar");
                myIntent.putExtra("idmensaje", id);
                myIntent.putExtra("idcur", idcurso);
                myIntent.putExtra("iddoc", iddocente);
                myIntent.putExtra("titulo", titulo);
                myIntent.putExtra("fecha", fecha);
                myIntent.putExtra("detalle", detalle);
                myIntent.putExtra("estado", estado);

                v.getContext().startActivity(myIntent);
            }*/
        }
    }

    @Override
    public RequestClientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RequestClientAdapter.ViewHolder viewHolder, int position) {
        /*String fechaactual= Agenda.fechaactual;

        ClsSolicitudes objetoX = objetoss.get(position);
        viewHolder.titulo.setText(objetoX.getTitulo());
        viewHolder.fecha.setText(objetoX.getFecha());
        */
/*
        if(objetoX.getFecha().equals(fechaactual)){
            viewHolder.image.setImageResource(R.drawable.ico_esstado_1);
        }
        else {
            viewHolder.image.setImageResource(R.drawable.ico_estado_2);
        }

        viewHolder.itemView.setTag(objetoX);*/
    }

    @Override
    public int getItemCount() {
        return this.objetoss.size();
    }
}
