/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.uc;

import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.dao.core.OficinaDAO;
import br.udesc.greenhouse.modelo.dao.core.RegistroDAO;
import br.udesc.greenhouse.modelo.entidade.Oficina;
import br.udesc.greenhouse.modelo.entidade.Registro;
import java.util.List;

/**
 *
 * @author ignoi
 */
public class GerenciarRegistrosUC {

    private RegistroDAO dao;
    private OficinaDAO odao;
    private List<Registro> registros;

    public GerenciarRegistrosUC() {
        dao = FactoryDAO.getFactoryDAO().getRegistroDAO();
        odao = FactoryDAO.getFactoryDAO().getOficinaDAO();
        registros = dao.listar();
    }

    public List<Registro> getRegistros() {
        return registros;
    }
    
    public List<Oficina> getOficinas(){
        return odao.listar();
    }
    
    public boolean inserir(Registro r){
        try {
            dao.inserir(r);
            return true;
        } catch(Exception e){
            System.out.println(e.toString() + "tag");
            return false;
        }
    }
    
    public boolean editar(Registro r){
        try {
            dao.editar(r);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    public boolean remover(long id){
        try {
            dao.remover(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    

}
