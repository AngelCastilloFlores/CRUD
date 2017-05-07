package com.example.android.menudietas.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.menudietas.Constantes;
import com.example.android.menudietas.fragments.DietasFragment;
import com.example.android.menudietas.fragments.NewsFragment;
import com.example.android.menudietas.R;
import com.example.android.menudietas.fragments.ProfileFragment;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.fragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NewsFragment fragment = NewsFragment.newInstance();
        getFragmentManager().beginTransaction().add(R.id.main_fragment_container_ll, fragment).commit();
        String title = getString(R.string.menu_news);
        setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        String title = "";

        switch (id){
            case R.id.nav_news:
                fragment = NewsFragment.newInstance();
                title = getString(R.string.menu_news);
                break;

            case R.id.nav_dieta:
                fragment = DietasFragment.newInstance();
                title = getString(R.string.menu_dietas);
                break;

            case R.id.nav_profile:
                fragment = ProfileFragment.newInstance();
                title = getString(R.string.menu_profile);
                break;

            case R.id.log_out:
                //borro la sesión del usuario
                getSharedPreferences(Constantes.PREFERENCES_FILE, Context.MODE_PRIVATE).edit().clear().apply();
                //Redirijo al login
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                //Borrar las actividades que había en la pila
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
                break;
        }

        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(R.id.main_fragment_container_ll, fragment).commit();
            setTitle(title);
        }

        item.setChecked(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
