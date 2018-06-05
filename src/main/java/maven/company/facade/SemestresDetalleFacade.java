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
import maven.company.academico_db.SemestresDetalle;
import maven.company.academico_db.SemestresDetalle_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import maven.company.academico_db.Materias;
import maven.company.academico_db.Profesores;
import maven.company.academico_db.Semestres;
import maven.company.academico_db.Evaluaciones;

/**
 *
 * @author oym-dev06
 */
@Stateless
public class SemestresDetalleFacade extends AbstractFacade<SemestresDetalle> {

    @PersistenceContext(unitName = "maven.company_academico_project_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SemestresDetalleFacade() {
        super(SemestresDetalle.class);
    }

    public boolean isIdmateriaEmpty(SemestresDetalle entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SemestresDetalle> semestresDetalle = cq.from(SemestresDetalle.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(semestresDetalle, entity), cb.isNotNull(semestresDetalle.get(SemestresDetalle_.idmateria)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Materias findIdmateria(SemestresDetalle entity) {
        return this.getMergedEntity(entity).getIdmateria();
    }

    public boolean isIdprofesorEmpty(SemestresDetalle entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SemestresDetalle> semestresDetalle = cq.from(SemestresDetalle.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(semestresDetalle, entity), cb.isNotNull(semestresDetalle.get(SemestresDetalle_.idprofesor)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Profesores findIdprofesor(SemestresDetalle entity) {
        return this.getMergedEntity(entity).getIdprofesor();
    }

    public boolean isIdsemestreEmpty(SemestresDetalle entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SemestresDetalle> semestresDetalle = cq.from(SemestresDetalle.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(semestresDetalle, entity), cb.isNotNull(semestresDetalle.get(SemestresDetalle_.idsemestre)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Semestres findIdsemestre(SemestresDetalle entity) {
        return this.getMergedEntity(entity).getIdsemestre();
    }

    public boolean isEvaluacionesCollectionEmpty(SemestresDetalle entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SemestresDetalle> semestresDetalle = cq.from(SemestresDetalle.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(semestresDetalle, entity), cb.isNotEmpty(semestresDetalle.get(SemestresDetalle_.evaluacionesCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Evaluaciones> findEvaluacionesCollection(SemestresDetalle entity) {
        SemestresDetalle mergedEntity = this.getMergedEntity(entity);
        Collection<Evaluaciones> evaluacionesCollection = mergedEntity.getEvaluacionesCollection();
        evaluacionesCollection.size();
        return evaluacionesCollection;
    }
    
}
