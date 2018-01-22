/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.uc;

import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.dao.core.OficinaDAO;
import br.udesc.greenhouse.modelo.dao.core.PeriodoDAO;
import br.udesc.greenhouse.modelo.dao.core.UsuarioDAO;
import br.udesc.greenhouse.modelo.entidade.Oficina;
import br.udesc.greenhouse.modelo.entidade.Periodo;
import br.udesc.greenhouse.modelo.entidade.Usuario;
import java.util.List;

/**
 *
 * @author ignoi
 */
public class GerenciarUsuariosUC {

    private UsuarioDAO usuarioDAO;
    private PeriodoDAO periodoDAO;

    public GerenciarUsuariosUC() {
        this.usuarioDAO = FactoryDAO.getFactoryDAO().getUsuarioDAO();
        this.periodoDAO = FactoryDAO.getFactoryDAO().getPeridoDAO();
    }

    public boolean inserir(Usuario u) {
        try {
            usuarioDAO.inserir(u);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean editar(Usuario u) {
        try {
            usuarioDAO.editar(u);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean remover(long id) {
        try {
            usuarioDAO.remover(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario pesquisar(long id) {
        return usuarioDAO.pesquisar(id);
    }

    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    public List<Periodo> listarPeriodos() {
        return periodoDAO.listar();
    }

    public boolean incluir(Periodo p) {
        try {
            if (p.getHoraInicio().before(p.getHoraFim())) {
                periodoDAO.inserir(p);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
