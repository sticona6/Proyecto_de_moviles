package com.upt.touchupt.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upt.touchupt.Adapters.RequestClientAdapter;
import com.upt.touchupt.ListRequestActivity;
import com.upt.touchupt.Models.ClsSolicitudes;
import com.upt.touchupt.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestClientFragment extends Fragment {


    public RequestClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_client, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ArrayList<ClsSolicitudes> Xcursos;
        ListRequestActivity readX = new ListRequestActivity();
        Xcursos = readX.EjecutarListMensaje(getActivity());

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_solicitudes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RequestClientAdapter(Xcursos, R.layout.row_request_client));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
