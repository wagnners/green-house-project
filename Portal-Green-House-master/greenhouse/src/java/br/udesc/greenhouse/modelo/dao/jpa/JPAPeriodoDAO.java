/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.dao.jpa;

import br.udesc.greenhouse.modelo.dao.core.PeriodoDAO;
import br.udesc.greenhouse.modelo.entidade.Periodo;
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
public class JPAPeriodoDAO implements PeriodoDAO {

    private EntityManagerFactory emf = null;

    public JPAPeriodoDAO() {
        emf = Persistence.createEntityManagerFactory("greenhousePU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void inserir(Periodo a) {
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
    public void editar(Periodo a) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            a = em.merge(a);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = a.getPeriodoid();
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
            Periodo usuario = null;
            try {
                usuario = em.getReference(Periodo.class, id);
                usuario.getPeriodoid();
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
    public Periodo pesquisar(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodo.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Periodo> listar() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Periodo.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
      @Override
    public List<Object[]> countPeriodos() {
        EntityManager em = getEntityManager();
        Query consulta = em.createNativeQuery("SELECT DIADASEMANA,COUNT(*) FROM  greenhouse.usuario_periodo left join greenhouse.periodos on greenhouse.periodos.periodoid=greenhouse.usuario_periodo.periodoid GROUP by DIADASEMANA;");
        
        return consulta.getResultList();
    }


}
