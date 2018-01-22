/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.jpa;

import br.udesc.greenhouse.modelo.dao.core.OficinaDAO;
import br.udesc.greenhouse.modelo.entidade.Oficina;
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
public class JPAOficinaDAO implements OficinaDAO {

    private EntityManagerFactory emf = null;

    public JPAOficinaDAO() {
        emf = Persistence.createEntityManagerFactory("greenhousePU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void inserir(Oficina a) {
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
    public void editar(Oficina a) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            a = em.merge(a);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = a.getOficinaid();
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
            Oficina usuario = null;
            try {
                usuario = em.getReference(Oficina.class, id);
                usuario.getOficinaid();
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
    public Oficina pesquisar(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Oficina.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Oficina pesquisar(String nome) {
        EntityManager em = getEntityManager();
        try {
            Query consulta = em.createQuery("select p from Oficina p where p.nome = :off");
            consulta.setParameter("off", nome);
            List<Oficina> oficinas = consulta.getResultList();
            System.out.println(oficinas.get(0) + "tagdepesquisa");
            if (oficinas.size() >= 1){
                return oficinas.get(0);
            } else {
                return null;
            }
        } finally {
            em.close();
        }
    }

    @Override
    public List<Oficina> listar() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Oficina.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
