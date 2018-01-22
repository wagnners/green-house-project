/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.uc;

import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.dao.core.OficinaDAO;
import br.udesc.greenhouse.modelo.dao.core.OficinaDAO;
import br.udesc.greenhouse.modelo.entidade.Oficina;
import br.udesc.greenhouse.modelo.entidade.Oficina;
import java.util.List;

/**
 *
 * @author ignoi
 */
public class GerenciarOficinasUC {

    private OficinaDAO dao;
    private List<Oficina> registros;

    public GerenciarOficinasUC() {
        dao = FactoryDAO.getFactoryDAO().getOficinaDAO();
        registros = dao.listar();
    }

    public List<Oficina> listar() {
        return registros;
    }

    public boolean inserir(Oficina r) {
        try {
            dao.inserir(r);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString() + "tag");
            return false;
        }
    }

    public boolean editar(Oficina r) {
        try {
            dao.editar(r);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remover(long id) {
        try {
            dao.remover(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Oficina pesquisar(long id){
        return dao.pesquisar(id);
    }

}
