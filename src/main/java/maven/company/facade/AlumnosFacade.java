/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.company.facade;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import maven.company.academico_db.Alumnos;
import maven.company.academico_db.Alumnos_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import maven.company.academico_db.Carreras;
import maven.company.academico_db.Personas;
import maven.company.academico_db.Calificaciones;

/**
 *
 * @author oym-dev06
 */
@Stateless
public class AlumnosFacade extends AbstractFacade<Alumnos> {

    @PersistenceContext(unitName = "maven.company_academico_project_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnosFacade() {
        super(Alumnos.class);
    }

    public boolean isIdcarreraEmpty(Alumnos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Alumnos> alumnos = cq.from(Alumnos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(alumnos, entity), cb.isNotNull(alumnos.get(Alumnos_.idcarrera)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Carreras findIdcarrera(Alumnos entity) {
        return this.getMergedEntity(entity).getIdcarrera();
    }

    public boolean isIdpersonaEmpty(Alumnos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Alumnos> alumnos = cq.from(Alumnos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(alumnos, entity), cb.isNotNull(alumnos.get(Alumnos_.idpersona)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Personas findIdpersona(Alumnos entity) {
        return this.getMergedEntity(entity).getIdpersona();
    }

    public boolean isCalificacionesCollectionEmpty(Alumnos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Alumnos> alumnos = cq.from(Alumnos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(alumnos, entity), cb.isNotEmpty(alumnos.get(Alumnos_.calificacionesCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Calificaciones> findCalificacionesCollection(Alumnos entity) {
        Alumnos mergedEntity = this.getMergedEntity(entity);
        Collection<Calificaciones> calificacionesCollection = mergedEntity.getCalificacionesCollection();
        calificacionesCollection.size();
        return calificacionesCollection;
    }
    
}
