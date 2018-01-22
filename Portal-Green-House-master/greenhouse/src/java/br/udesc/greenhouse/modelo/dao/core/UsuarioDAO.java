/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.core;

import br.udesc.greenhouse.modelo.entidade.Usuario;
import java.util.List;

/**
 *
 * @author ignoi
 */
public interface UsuarioDAO {

    public void inserir(Usuario a);

    public void editar(Usuario a);

    public void remover(long id);

    public Usuario pesquisar(long id);

    public List<Usuario> listar();

    public Usuario autenticar(String email, String senha);
}


