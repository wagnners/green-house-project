/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.core;

import br.udesc.greenhouse.modelo.entidade.Configuracao;
import java.util.List;

/**
 *
 * @author ignoi
 */
public interface ConfiguracaoDAO {

    public void inserir(Configuracao a);

    public void editar(Configuracao a);

    public void remover(long id);

    public Configuracao pesquisar(long id);

    public List<Configuracao> listar();

}
