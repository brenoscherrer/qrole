package br.com.qrole.main.dao;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import br.com.qrole.main.entities.Entity;

/**
 * Defines a contract for DAO classes.
 * <p/>
 * DAO = Data Access Object
 */
public interface AbstractEntityDAO<T extends Entity> {

    /**
     * Server IP.
     */
    String IP_SERVER = "25.47.185.72:8080";

    AsyncTask findAllEntities(Context context, RoleDAO.BuscaRolesTask.AsyncResponse delegate) throws Exception;

    T findEntityByID(Context context, int ID) throws Exception;

    List<T> findEntitiesByQuery(Context context, String query) throws Exception;
}
