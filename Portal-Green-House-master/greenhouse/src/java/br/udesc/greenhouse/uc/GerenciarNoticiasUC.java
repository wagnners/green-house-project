/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.uc;

import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.dao.core.NoticiaDAO;
import br.udesc.greenhouse.modelo.entidade.Noticia;
import java.util.List;

/**
 *
 * @author ignoi
 */
public class GerenciarNoticiasUC {
    
    private NoticiaDAO noticiaDAO;

    public GerenciarNoticiasUC() {
        this.noticiaDAO = FactoryDAO.getFactoryDAO().getNoticiaDAO();

    }

    public boolean inserir(Noticia u) {
        try {
            noticiaDAO.inserir(u);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean editar(Noticia u) {
        try {
            noticiaDAO.editar(u);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean remover(long id) {
        try {
            noticiaDAO.remover(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Noticia pesquisar(long id) {
        return noticiaDAO.pesquisar(id);
    }

    public List<Noticia> listar() {
        return noticiaDAO.listar();
    }
    
}
