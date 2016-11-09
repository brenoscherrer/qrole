package br.com.qrole.main.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.qrole.main.R;
import br.com.qrole.main.dao.RoleDAO;
import br.com.qrole.main.entities.Role;
import br.com.qrole.main.resources.MessageResource;
import br.com.qrole.main.view.adapter.RoleAdapter;

public class MainScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private RoleAdapter roleAdapter;
    private Handler mHandler;
    private String searchTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Sobrescrevo o resources existente pois a LoginActivity morre assim que finaliza
        MessageResource.resources = getResources();

        ListView listView = (ListView) findViewById(R.id.list_role);
        listView.setEmptyView(findViewById(R.id.empty_view_role));

        roleAdapter = new RoleAdapter(this, RoleDAO.getInstance().findAllEntities());

        listView.setAdapter(roleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Role selectedRole = roleAdapter.getItem(i);

                Bundle bundle = new Bundle();
                bundle.putSerializable("entity", selectedRole);

                Intent intent = new Intent(MainScreenActivity.this, RoleViewerActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        mHandler = new Handler();
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
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Quando o usuário volta da busca, "reseto" tudo que foi buscado.
                roleAdapter.refreshAll();
                return true;
            }
        });

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchTerm = newText;
        mHandler.removeCallbacksAndMessages(null);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                roleAdapter.doFilter(searchTerm);
            }
        }, 300);
        return true;
    }
}
