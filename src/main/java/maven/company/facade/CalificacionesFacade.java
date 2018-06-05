/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.company.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import maven.company.academico_db.Calificaciones;
import maven.company.academico_db.Calificaciones_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import maven.company.academico_db.Alumnos;
import maven.company.academico_db.Evaluaciones;

/**
 *
 * @author oym-dev06
 */
@Stateless
public class CalificacionesFacade extends AbstractFacade<Calificaciones> {

    @PersistenceContext(unitName = "maven.company_academico_project_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalificacionesFacade() {
        super(Calificaciones.class);
    }

    public boolean isIdalumnoEmpty(Calificaciones entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Calificaciones> calificaciones = cq.from(Calificaciones.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(calificaciones, entity), cb.isNotNull(calificaciones.get(Calificaciones_.idalumno)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Alumnos findIdalumno(Calificaciones entity) {
        return this.getMergedEntity(entity).getIdalumno();
    }

    public boolean isIdevalucionEmpty(Calificaciones entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Calificaciones> calificaciones = cq.from(Calificaciones.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(calificaciones, entity), cb.isNotNull(calificaciones.get(Calificaciones_.idevalucion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Evaluaciones findIdevalucion(Calificaciones entity) {
        return this.getMergedEntity(entity).getIdevalucion();
    }
    
}
