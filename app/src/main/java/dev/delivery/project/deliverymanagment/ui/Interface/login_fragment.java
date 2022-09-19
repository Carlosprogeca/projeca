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
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import dev.delivery.project.deliverymanagment.R;
import dev.delivery.project.deliverymanagment.ui.Classes.Usuario;
import dev.delivery.project.deliverymanagment.ui.Classes.Login;



//essa classe vai ser modificada futuramente, a classe em funcionamento é a local_entrega








/**
 * A simple {@link Fragment} subclass.
 * Use the {@link login_fragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class login_fragment extends Fragment implements View.OnClickListener,
        Response.Listener, Response.ErrorListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private View view;
    private String mParam1;
    private String mParam2;
    private EditText txtUser;
    private EditText txtPassword;
    private EditText txtCadEmail;
    private EditText txtCadUser;
    private EditText txtCadPassword;
    private Button btn;
    //volley
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReq;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment login_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static login_fragment newInstance(String param1, String param2) {
        login_fragment fragment = new login_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public login_fragment() {
        // Required empty public constructor
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


        this.txtPassword = (EditText) view.findViewById(R.id.txtPassword);
        this.txtUser = (EditText) view.findViewById(R.id.txtUser);
        this.txtCadUser = (EditText) view.findViewById(R.id.txtCadUser);
        this.txtCadPassword = (EditText) view.findViewById(R.id.txtCadPassword);
        this.txtCadEmail = (EditText) view.findViewById(R.id.txtCadEmail);
        this.btn = (Button) view.findViewById(R.id.btnLogin);
        //*****************
        this.btn.setOnClickListener(this);
        //----------------------------------
        this.requestQueue = Volley.newRequestQueue(view.getContext());
        //inicializando a fila de requests do SO
        this.requestQueue.start();
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_login_fragment, container, false);
        return this.view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            Login usr = new Login();
            usr.setUser(this.txtUser.getText().toString());
            usr.setPassword(this.txtPassword.getText().toString());

            //msg
            Snackbar mySnackbar = Snackbar.make(view, "Usuário " + usr.getUser(), Snackbar.LENGTH_SHORT);
            mySnackbar.show();

            txtPassword.setText("");
            txtUser.setText("");
            //enviar objeto para o REST Server
            jsonObjectReq = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8080/deliverymanagmentrest/rest/login", usr.toJsonObject(), this, this);
            requestQueue.add(jsonObjectReq);
        }

        if (view.getId() == R.id.btnCadastro) {
            Usuario usr = new Usuario();
            usr.setUsername(this.txtCadUser.getText().toString());
            usr.setPassword(this.txtCadPassword.getText().toString());
            usr.setEmail(this.txtCadEmail.getText().toString());

            //msg
            Snackbar mySnackbar = Snackbar.make(view, "Cadastrado ", Snackbar.LENGTH_SHORT);
            mySnackbar.show();

            txtCadUser.setText("");
            txtCadPassword.setText("");
            txtCadEmail.setText("");
            //enviar objeto para o REST Server
            jsonObjectReq = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8080/deliverymanagmentrest/rest/login", usr.toJsonObject(), this, this);
            requestQueue.add(jsonObjectReq);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.
                make(view, "Ops! Houve um problema ao realizar o cadastro: " + error.toString(),Snackbar.LENGTH_LONG);
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
                txtCadUser.setText("");
                txtCadPassword.setText("");
                txtCadEmail.setText("");
                txtPassword.setText("");
                txtUser.setText("");
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