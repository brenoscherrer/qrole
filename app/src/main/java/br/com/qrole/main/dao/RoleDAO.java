package br.com.qrole.main.dao;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.qrole.main.entities.Role;
import br.com.qrole.main.utilities.DefaultExceptionHandler;
import br.com.qrole.main.view.activities.RoleViewerActivity;
import br.com.qrole.main.view.adapter.RoleAdapter;

/**
 * Role DAO class.
 */
public class RoleDAO implements AbstractEntityDAO<Role> {

    private static RoleDAO instance;

    // Singleton
    private RoleDAO() {
    }

    /**
     * Gets an instance of this RoleDAO class.
     *
     * @return A new or existent instance of this class.
     */
    public static RoleDAO getInstance() {
        return (instance == null ? (instance = new RoleDAO()) : instance);
    }

    @Override
    public AsyncTask findAllEntities(Context context, BuscaRolesTask.AsyncResponse delegate)
            throws Exception {

        return new BuscaRolesTask(context, delegate);
    }

    @Override
    public Role findEntityByID(Context context, int ID) throws Exception {
        return null;
    }

    @Override
    public List<Role> findEntitiesByQuery(Context context, String query) throws Exception {

        // Não funciona mais, movido para o doFilter do RoleAdapter
//        List<Role> roles = new ArrayList<>();
//
//        for (Role r : findAllEntities(context)) {
//            if (r.getDescription().toUpperCase().contains(query.toUpperCase())
//                    || r.getTitle().toUpperCase().contains(query.toUpperCase())) {
//                roles.add(r);
//            }
//        }

        return null;
    }

    // Método auxiliar para criar Roles para teste
    private List<Role> buildSomeFakeRoles() {
        List<Role> roles = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            Role r = new Role();
            r.setID(i);
            r.setTitle("Rolê número " + i);
            r.setDescription("Apenas uma descrição stub do role número " + i +
                    "\nTestando quebra de linha lorem ipsum sir dolor meu blablabla");

            if (i % 2 == 0) {
                r.setImage("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");
            }

            r.setRoleDateTime(new Date());
            r.setAddress("Rua Dos testes número " + i + " bairro santa joana dos testes, " +
                    "São Paulo - Brasil");

            if (i == 10) {
                r.setDescription(r.getDescription() +
                        "\nDescrição grande para testar o Scroll: " + r.getDescription()
                        + r.getDescription() + r.getDescription() + r.getDescription() + r.getDescription());
            }

            roles.add(r);
        }

        return roles;
    }

    public static class BuscaRolesTask extends AsyncTask<String, Void, List<Role>> {

        // you may separate this or combined to caller class.
        public interface AsyncResponse {
            void processFinish(List<Role> output);
        }

        public AsyncResponse delegate = null;

        private ProgressDialog progressDialog;

        private Context context;

        public BuscaRolesTask(Context context, AsyncResponse delegate) {
            this.context = context;
            this.delegate = delegate;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.v("breno", "onPre");

            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Buscando...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected List<Role> doInBackground(String... params) {
            List<Role> roles = null;
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(
                        "http://" + IP_SERVER + "/qrole-server/webresources/role").openConnection();
                connection.connect();

                int statusCode = connection.getResponseCode();
                if (statusCode != HttpURLConnection.HTTP_OK) {
                    return null;
                }

                InputStream in = new BufferedInputStream(
                        connection.getInputStream());

                Reader reader = new InputStreamReader(in);
                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<Role>>() {
                }.getType();

                roles = gson.fromJson(reader, listType);

                in.close();
            } catch (Exception ex) {
                Log.v("breno", "ex=" + ex.getMessage());
                DefaultExceptionHandler.handleException(ex, context);
            }
            Log.v("breno", "fim do doInB");

            return roles;
        }

        @Override
        protected void onPostExecute(List<Role> roles) {
            super.onPostExecute(roles);
            progressDialog.dismiss();

            if (roles != null) {
                delegate.processFinish(roles);
            } else {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setMessage("Não foi possível obter os rolês do Servidor.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }).create();

                dialog.show();
            }
        }
    }
}
