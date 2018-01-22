/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.jpa;

import br.udesc.greenhouse.modelo.dao.core.ImagemDAO;
import br.udesc.greenhouse.modelo.entidade.Imagem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ignoi
 */
public class JPAImagemDAO implements ImagemDAO {

    private EntityManagerFactory emf = null;

    public JPAImagemDAO() {
        emf = Persistence.createEntityManagerFactory("greenhousePU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   
    @Override
    public Imagem pesquisar(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Imagem.class, id);
        } finally {
            em.close();
        }
    }

   
}
