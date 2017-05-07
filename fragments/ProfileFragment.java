package com.example.android.menudietas.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.menudietas.Constantes;
import com.example.android.menudietas.R;
import com.example.android.menudietas.activity.LoginActivity;
import com.example.android.menudietas.activity.MainActivity;
import com.example.android.menudietas.activity.PasswordActivity;
import com.example.android.menudietas.activity.RegistroActivity;
import com.example.android.menudietas.database.DatabaseHelper;

import static com.example.android.menudietas.R.id.sexo_value;
import static com.example.android.menudietas.R.id.view_offset_helper;
import static com.example.android.menudietas.fragments.NewsFragment.URL_DIETAS_WEB;
import static com.example.android.menudietas.fragments.NewsFragment.newInstance;

/**
 * Created by angelcastillo on 5/5/17.
 */

public class ProfileFragment extends Fragment {


    Button changePassWord;
    Button baja;
    private DatabaseHelper databaseHelper;
    private String loggedEmail;
    private SharedPreferences sharedPreferences;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());
        //Recupero de las preferencias el email del usuario logeado
        sharedPreferences = getActivity()
                .getSharedPreferences(Constantes.PREFERENCES_FILE, Context.MODE_PRIVATE);
        loggedEmail = sharedPreferences.getString(Constantes.PREFERENCES_USER_EMAIL, "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_layout, container, false);

        changePassWord = (Button) view.findViewById(R.id.password);
        baja = (Button) view.findViewById(R.id.baja);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //INTENT PARA QUE PASE AL ACTIVITY QUE QUEREMOS
                Intent showContent = new Intent(getActivity(), PasswordActivity.class);
                startActivity(showContent);
            }
        });

        baja.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Borramos el usurio de la base de datos
                databaseHelper.deleteContactByEmail(loggedEmail);

                //borro la sesión del usuario
                sharedPreferences.edit().clear().apply();

                //Redirijo al login
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                //Borrar las actividades que había en la pila
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().finish();
                startActivity(intent);
            }
        });

    }
}


