/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.jpa;

import br.udesc.greenhouse.modelo.dao.core.ConfiguracaoDAO;
import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.dao.core.ImagemDAO;
import br.udesc.greenhouse.modelo.dao.core.NoticiaDAO;
import br.udesc.greenhouse.modelo.dao.core.OficinaDAO;
import br.udesc.greenhouse.modelo.dao.core.PeriodoDAO;
import br.udesc.greenhouse.modelo.dao.core.RegistroDAO;
import br.udesc.greenhouse.modelo.dao.core.UsuarioDAO;

/**
 *
 * @author ignoi
 */
public class JPAFactoryDAO extends FactoryDAO {

    @Override
    public NoticiaDAO getNoticiaDAO() {
        return new JPANoticiaDAO();
    }

    @Override
    public OficinaDAO getOficinaDAO() {
        return new JPAOficinaDAO();
    }

    @Override
    public PeriodoDAO getPeridoDAO() {
        return new JPAPeriodoDAO();
    }

    @Override
    public RegistroDAO getRegistroDAO() {
        return new JPARegistroDAO();
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new JPAUsuarioDAO();
    }

    @Override
    public ConfiguracaoDAO getConfiguracaoDAO() {
        return new JPAConfiguracaoDAO();
    }

    @Override
    public ImagemDAO getImagemDAO() {
        return new JPAImagemDAO();
    }
    
    

}
