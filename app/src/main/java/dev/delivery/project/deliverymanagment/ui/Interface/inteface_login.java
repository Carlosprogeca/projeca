package dev.delivery.project.deliverymanagment.ui.Interface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

import dev.delivery.project.deliverymanagment.R;
import dev.delivery.project.deliverymanagment.ui.Classes.Entrega;
import dev.delivery.project.deliverymanagment.ui.Classes.Login;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link inteface_login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class inteface_login extends Fragment implements View.OnClickListener, Response.Listener, Response.ErrorListener{

    private View view;
    private EditText txtUsuario;
    private EditText txtSenha;
    private Button btnLogin;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReq;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public inteface_login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment inteface_login.
     */
    // TODO: Rename and change types and number of parameters
    public static inteface_login newInstance(String param1, String param2) {
        inteface_login fragment = new inteface_login();
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
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_interface_entrega, container, false);
        this.txtUsuario = (EditText) view.findViewById(R.id.txtUsuario);
        this.txtSenha= (EditText) view.findViewById(R.id.txtSenha);
        this.btnLogin = (Button) view.findViewById(R.id.btnLogin);
        this.btnLogin.setOnClickListener(this);



        return view ;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnEnvio){
            Login lgn = new Login();
            lgn.setUsuario(this.txtUsuario.getText().toString());
            lgn.setSenha(this.txtSenha.getText().toString());


            jsonObjectReq = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8080/deliverymanagmentrest/rest/login",lgn.toJsonObject(), this, this);
            requestQueue.add(jsonObjectReq);

            txtSenha.setText("");
            txtUsuario.setText("");
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.
                make(view, "Ops! Houve um problema ao realizar o login: " + error.toString(),Snackbar.LENGTH_LONG);
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
                txtUsuario.setText("");
                txtSenha.setText("");

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