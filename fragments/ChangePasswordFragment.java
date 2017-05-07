package com.example.android.menudietas.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.menudietas.Constantes;
import com.example.android.menudietas.R;
import com.example.android.menudietas.database.DatabaseHelper;

/**
 * Created by angelcastillo on 5/5/17.
 */

public class ChangePasswordFragment extends Fragment {


    Button changePassWord;
    EditText old_pass;
    EditText new_pass;
    EditText repeat_pass;

    private String loggedPass;
    private String loggedEmail;
    private String oldPass;
    private String newPass;
    private String repeatPass;

    DatabaseHelper databaseHelper;


    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());

        //Recupero de las preferencias la contraseña del usuario logeado
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences(Constantes.PREFERENCES_FILE, Context.MODE_PRIVATE);

        loggedPass = sharedPreferences.getString(Constantes.PREFERENCES_USER_PASSWORD, "");
        loggedEmail = sharedPreferences.getString(Constantes.PREFERENCES_USER_EMAIL, "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pass_change_layout, container, false);

        changePassWord = (Button) view.findViewById(R.id.change_password);
        old_pass = (EditText) view.findViewById(R.id.old_pass);
        new_pass = (EditText) view.findViewById(R.id.new_pass);
        repeat_pass = (EditText) view.findViewById(R.id.repeat_pass);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oldPass = old_pass.getText().toString();
                newPass = new_pass.getText().toString();
                repeatPass = repeat_pass.getText().toString();

                if (!oldPass.equals(loggedPass)) {
                    Toast.makeText(getActivity(), "Las contraseña antigua no es correcta.", Toast.LENGTH_LONG).show();
                }
                else if(!newPass.equals(repeatPass)){
                        Toast.makeText(getActivity(), "Las contraseñas no coinciden.", Toast.LENGTH_LONG).show();
                    }else{
                        //UPDATE PASSWORD in DataBase
                        //TODO TOAST EXITO!
                        Toast.makeText(getActivity(), "Ha cambiado su contraseña.", Toast.LENGTH_LONG).show();
                        databaseHelper.updateUserPassword(newPass, loggedEmail);
                        getActivity().finish();
                    }

                }
        });

    }


}

