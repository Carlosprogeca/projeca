package dev.delivery.project.deliverymanagment.ui.Interface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import dev.delivery.project.deliverymanagment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link inteface_login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class inteface_login extends Fragment implements View.OnClickListener{

    private View view;
    private EditText txtUsuario;
    private EditText txtSenha;
    private Button btnLogin;

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
        return view ;
    }

    @Override
    public void onClick(View view) {

    }
}