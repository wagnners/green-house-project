/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.jpa;

import br.udesc.greenhouse.modelo.dao.core.ConfiguracaoDAO;
import br.udesc.greenhouse.modelo.entidade.Configuracao;
import br.udesc.greenhouse.modelo.entidade.Configuracao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author ignoi
 */
public class JPAConfiguracaoDAO implements ConfiguracaoDAO{

    private EntityManagerFactory emf = null;

    public JPAConfiguracaoDAO() {
        emf = Persistence.createEntityManagerFactory("greenhousePU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void inserir(Configuracao a) {
         EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void editar(Configuracao a) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            a = em.merge(a);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = a.getId();
                if (pesquisar(id) == null) {
                    System.out.println("The noticia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void remover(long id) {
         EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Configuracao usuario = null;
            try {
                usuario = em.getReference(Configuracao.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                System.out.println("The noticia with id " + id + " no longer exists.");
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Configuracao pesquisar(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Configuracao.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Configuracao> listar() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Configuracao.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    
}
