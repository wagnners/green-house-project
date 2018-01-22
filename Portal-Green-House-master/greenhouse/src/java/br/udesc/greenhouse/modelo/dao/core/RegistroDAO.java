/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.core;

import br.udesc.greenhouse.modelo.entidade.Registro;
import java.util.List;

/**
 *
 * @author ignoi
 */
public interface RegistroDAO {
    
     public void inserir(Registro a);

    public void editar(Registro a);

    public void remover(long id);

    public Registro pesquisar(long id);

    public List<Registro> listar();
    
}
