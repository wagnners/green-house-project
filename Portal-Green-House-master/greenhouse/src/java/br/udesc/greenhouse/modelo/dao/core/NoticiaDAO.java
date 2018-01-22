/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.core;

import br.udesc.greenhouse.modelo.entidade.Noticia;
import java.util.List;

/**
 *
 * @author ignoi
 */
public interface NoticiaDAO {
    
     public void inserir(Noticia a);

    public void editar(Noticia a);

    public void remover(long id);

    public Noticia pesquisar(long id);

    public List<Noticia> listar();
    
}
