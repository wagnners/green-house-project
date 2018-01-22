/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.uc;

import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.dao.core.UsuarioDAO;
import br.udesc.greenhouse.modelo.entidade.Usuario;

/**
 *
 * @author ignoi
 */
public class LoginUC {

    private UsuarioDAO dao;

    public LoginUC() {
        dao = FactoryDAO.getFactoryDAO().getUsuarioDAO();
    }

    public Usuario getUser(String u, String p) {
        return dao.autenticar(u, p);
    }

}
