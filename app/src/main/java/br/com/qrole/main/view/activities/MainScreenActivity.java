package br.com.qrole.main.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.qrole.main.R;
import br.com.qrole.main.controller.RoleController;
import br.com.qrole.main.dao.RoleDAO;
import br.com.qrole.main.entities.Role;
import br.com.qrole.main.resources.MessageResource;
import br.com.qrole.main.view.adapter.RoleAdapter;

public class MainScreenActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RoleAdapter roleAdapter;
    private Handler mHandler;
    private String searchTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_activity);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
