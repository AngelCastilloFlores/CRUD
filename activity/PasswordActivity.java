package com.example.android.menudietas.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.android.menudietas.R;
import com.example.android.menudietas.fragments.ChangePasswordFragment;

/**
 * Created by angelcastillo on 5/5/17.
 */

public class PasswordActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_activity);

        getFragmentManager().beginTransaction()
                .add(R.id.password_fragment_container, ChangePasswordFragment.newInstance())
                .commit();
    }


}
