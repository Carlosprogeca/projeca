package dev.delivery.project.deliverymanagment.ui.Interface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dev.delivery.project.deliverymanagment.R;
import dev.delivery.project.deliverymanagment.ui.Classes.Entrega;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link interface_entrega#newInstance} factory method to
 * create an instance of this fragment.
 */
public class interface_entrega extends Fragment implements View.OnClickListener, Response.Listener, Response.ErrorListener {

    private View view;
    private EditText txtCliente;
    private EditText txtLogradouro;
    private EditText txtCpf;
    private EditText txtBairro;
    private EditText txtContact;
    private Button btnEnvio;
    private Spinner spinnervendedor;
    private Spinner spinnerveiculo;
    private CalendarView cvcalendario;
    //volley
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReq;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public interface_entrega() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment local_entrega.
     */
    // TODO: Rename and change types and number of parameters
    public static interface_entrega newInstance(String param1, String param2) {
        interface_entrega fragment = new interface_entrega();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_interface_entrega, container, false);

        this.txtCliente = (EditText) view.findViewById(R.id.txtClient);
        this.txtBairro= (EditText) view.findViewById(R.id.txtBairro);
        this.txtCpf = (EditText) view.findViewById(R.id.txtCpf);
        this.txtContact = (EditText) view.findViewById(R.id.txtContact);
        this.txtLogradouro = (EditText) view.findViewById(R.id.txtLogradouro);
        this.spinnervendedor = (Spinner) view.findViewById(R.id.spinnervendedor);
        this.spinnerveiculo = (Spinner) view.findViewById(R.id.spinnerveiculo);
        this.cvcalendario = (CalendarView)view.findViewById(R.id.calendario);
        this.btnEnvio = (Button) view.findViewById(R.id.btnEnvio);
        //*****************
        this.btnEnvio.setOnClickListener(this);
        //----------------------------------
        this.requestQueue = Volley.newRequestQueue(view.getContext());
        //inicializando a fila de requests do SO
        this.requestQueue.start();
        // Inflate the layout for this fragment

        return this.view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnEnvio){
            Entrega ent = new Entrega();
            ent.setCliente(this.txtCliente.getText().toString());
            ent.setBairro(this.txtBairro.getText().toString());
            ent.setCpf(this.txtCpf.getId());
            ent.setLogradouro(this.txtLogradouro.getText().toString());
            ent.setContato(this.txtContact.getText().toString());
            ent.setVeiculo(this.spinnerveiculo.getSelectedItem().toString());
            ent.setVendedor(this.spinnervendedor.getSelectedItem().toString());

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String dataSelecionada = formato.format(new Date(cvcalendario.getDate()));
            String dataConvertida = dataSelecionada.replace("/", "-");
            ent.setDataentrega(dataConvertida);

            //msg
            Snackbar mySnackbar = Snackbar.make(view, "Cliente " + ent.getCliente(), Snackbar.LENGTH_SHORT);
            mySnackbar.show();
//10.0.2.2
            jsonObjectReq = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8080/deliverymanagmentrest/rest/entrega",ent.toJsonObject(), this, this);
            requestQueue.add(jsonObjectReq);

            txtCliente.setText("");
            txtContact.setText("");
            txtCpf.setText("");
            txtBairro.setText("");
            txtLogradouro.setText("");
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.
                make(view, "Ops! Houve um problema ao realizar a entrega: " + error.toString(),Snackbar.LENGTH_LONG);
        mensagem.show();
    }

    @Override
    public void onResponse(Object response) {
        String resposta = response.toString();
        try {
            if (resposta.equals("500")) {
                Snackbar mensagem = Snackbar.make(view, "Erro! = " + resposta, Snackbar.LENGTH_LONG);
                mensagem.show();
            } else {
//sucesso
//limpar campos da tela
                txtCliente.setText("");
                txtContact.setText("");
                txtCpf.setText("");
                txtBairro.setText("");
                txtLogradouro.setText("");
//mensagem de sucesso
                Snackbar mensagem = Snackbar.make(view, "Sucesso! = " + resposta, Snackbar.LENGTH_LONG);
                mensagem.show();
            }
        } catch
        (Exception e) {
            e.printStackTrace();
        }
    }
}