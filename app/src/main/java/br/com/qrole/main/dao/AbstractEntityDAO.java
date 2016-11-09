package br.com.qrole.main.dao;

import java.util.List;

import br.com.qrole.main.entities.Entity;

/**
 * Defines a contract for DAO classes.
 * <p/>
 * DAO = Data Access Object
 */
public interface AbstractEntityDAO<T extends Entity> {

    List<T> findAllEntities();

    T findEntityByID(int ID);

    List<T> findEntitiesByQuery(String query);
}
