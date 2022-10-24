package dev.delivery.project.deliverymanagment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dev.delivery.project.deliverymanagment.placeholder.PlaceholderContent;
import dev.delivery.project.deliverymanagment.ui.Classes.Entrega;

/**
 * A fragment representing a list of Items.
 */
public class InterfaceConsulta extends Fragment implements Response.ErrorListener, Response.Listener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private ArrayList<Entrega> entregas;
    private RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayReq;
    private View view;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InterfaceConsulta() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static InterfaceConsulta newInstance(int columnCount) {
        InterfaceConsulta fragment = new InterfaceConsulta();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_interface_consulta_list, container, false);

        //array parametro de envio para o serviço
        JSONArray jsonArray = new JSONArray();
        //objeto com informações de filtro da consulta
        Entrega e = new Entrega();
        e.setCpf(0);
        e.setCliente("");
        e.setBairro("");
        e.setLogradouro("");
        e.setContato("");
        e.setDataentrega("");
        e.setVeiculo("");
        e.setVendedor("");
        //incluindo objeto no array de envio
        jsonArray.put(e.toJsonObject());
        //requisição para o Rest Server
        jsonArrayReq = new JsonArrayRequest(Request.Method.GET,
                "http://10.0.2.2:8080/deliverymanagmentrest/rest/entrega", jsonArray, this, this);
//mando executar a requisção na fila do sistema
        requestQueue.add(jsonArrayReq);

        this.requestQueue = Volley.newRequestQueue(view.getContext());
        this.requestQueue.start();
        return this.view;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //mostrar mensagem que veio do servidor
        Snackbar mensagem = Snackbar.make(view,
                "Ops! Algo não deu certo em sua consulta: " +
                        error.toString(), Snackbar.LENGTH_LONG);
        mensagem.show();
    }

    @Override
    public void onResponse(Object response) {
        try {
            JSONArray jsonArray = null;
            jsonArray = new JSONArray(response.toString());
            if (jsonArray != null) {
                //objeto
                Entrega entrega = null;
                //array que recebe resposta
                this.entregas = new ArrayList<Entrega>();
                //preencherndo JSONarray list
                for (int i = 0, size = jsonArray.length(); i < size; i++) {
                    JSONObject jo = jsonArray.getJSONObject(i);
                    entrega = new Entrega(jo);
                    this.entregas.add(entrega);
                }

                if (view instanceof RecyclerView) {
                    Context context = view.getContext();
                    RecyclerView recyclerView = (RecyclerView) view;
                    if (mColumnCount <= 1) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    } else {
                        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                    }
                    recyclerView.setAdapter(new ConEntregaRecyclerViewAdapter(this.entregas));
                }

            }else {
                Snackbar mensagem = Snackbar.make(view,
                        "A consulta não retornou nenhum registro!",
                        Snackbar.LENGTH_LONG);
                mensagem.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}