/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.core;

import br.udesc.greenhouse.modelo.dao.jpa.JPAFactoryDAO;

/**
 *
 * @author ignoi
 */
public abstract class FactoryDAO {

    public static FactoryDAO getFactoryDAO() {
        return new JPAFactoryDAO();
    }

    public abstract NoticiaDAO getNoticiaDAO();

    public abstract OficinaDAO getOficinaDAO();

    public abstract PeriodoDAO getPeridoDAO();

    public abstract RegistroDAO getRegistroDAO();

    public abstract UsuarioDAO getUsuarioDAO();
    
    public abstract ConfiguracaoDAO getConfiguracaoDAO();
    
    public abstract ImagemDAO getImagemDAO();

}
