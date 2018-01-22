/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.core;

import br.udesc.greenhouse.modelo.entidade.Oficina;
import java.util.List;

/**
 *
 * @author ignoi
 */
public interface OficinaDAO {
    
     public void inserir(Oficina a);

    public void editar(Oficina a);

    public void remover(long id);

    public Oficina pesquisar(long id);
    
    public Oficina pesquisar(String nome);

    public List<Oficina> listar();
    
}
