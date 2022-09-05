package dev.delivery.project.deliverymanagment.ui.Interface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import dev.delivery.project.deliverymanagment.R;
import dev.delivery.project.deliverymanagment.ui.Classes.Local;
import dev.delivery.project.deliverymanagment.ui.Classes.Login;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link local_entrega#newInstance} factory method to
 * create an instance of this fragment.
 */
public class local_entrega extends Fragment implements View.OnClickListener {

    private View view;
    private EditText txtClient;
    private EditText txtLogradouro;
    private EditText txtCpf;
    private EditText txtBairro;
    private EditText txtContact;
    private Button btnEnvio;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public local_entrega() {
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
    public static local_entrega newInstance(String param1, String param2) {
        local_entrega fragment = new local_entrega();
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


        this.txtClient = (EditText) view.findViewById(R.id.txtClient);
        this.txtBairro= (EditText) view.findViewById(R.id.txtBairro);
        this.txtCpf = (EditText) view.findViewById(R.id.txtCpf);
        this.txtContact = (EditText) view.findViewById(R.id.txtContact);
        this.txtLogradouro = (EditText) view.findViewById(R.id.txtLogradouro);
        this.btnEnvio = (Button) view.findViewById(R.id.btnLogin);
        //*****************
        this.btnEnvio.setOnClickListener(this);
        //----------------------------------

        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_login_fragment, container, false);
        return this.view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin){
            Local lcl = new Local();
            lcl.setClient(this.txtClient.getText().toString());
            lcl.setBairro(this.txtBairro.getText().toString());
            //lcl.setCpf(this.txtCpf.getText().());
            lcl.setLogradouro(this.txtLogradouro.getText().toString());
            lcl.setContato(this.txtContact.getText().toString());

            //msg
            Snackbar mySnackbar = Snackbar.make(view, "Cliente " + lcl.getClient(), Snackbar.LENGTH_SHORT);
            mySnackbar.show();

            txtClient.setText("");
            txtContact.setText("");
            txtCpf.setText("");
            txtBairro.setText("");
            txtLogradouro.setText("");
        }
    }
}