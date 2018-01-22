/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.jpa;

import br.udesc.greenhouse.modelo.dao.core.NoticiaDAO;
import br.udesc.greenhouse.modelo.entidade.Noticia;
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
public class JPANoticiaDAO implements NoticiaDAO {

    private EntityManagerFactory emf = null;

    public JPANoticiaDAO() {
        emf = Persistence.createEntityManagerFactory("greenhousePU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void inserir(Noticia a) {
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
    public void editar(Noticia a) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            a = em.merge(a);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = a.getNoticiaid();
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
            Noticia usuario = null;
            try {
                usuario = em.getReference(Noticia.class, id);
                usuario.getNoticiaid();
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
    public Noticia pesquisar(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Noticia.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Noticia> listar() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Noticia> c = cq.from(Noticia.class);
            cq.select(cq.from(Noticia.class)).orderBy(em.getCriteriaBuilder().desc(c.get("fixada")), em.getCriteriaBuilder().desc(c.get("data")));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
