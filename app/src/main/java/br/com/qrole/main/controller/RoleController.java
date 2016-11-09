package br.com.qrole.main.controller;

import br.com.qrole.main.dao.RoleDAO;

/**
 * Controller for the Role entity.
 */
public class RoleController {

    // Inútil (useless) mas apenas para ter algo no controller por enquanto
    public RoleDAO getDAO() {
        return RoleDAO.getInstance();
    }
}
