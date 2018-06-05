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
import maven.company.academico_db.Semestres;
import maven.company.academico_db.Semestres_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import maven.company.academico_db.Carreras;
import maven.company.academico_db.SemestresDetalle;

/**
 *
 * @author oym-dev06
 */
@Stateless
public class SemestresFacade extends AbstractFacade<Semestres> {

    @PersistenceContext(unitName = "maven.company_academico_project_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SemestresFacade() {
        super(Semestres.class);
    }

    public boolean isIdcarreraEmpty(Semestres entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Semestres> semestres = cq.from(Semestres.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(semestres, entity), cb.isNotNull(semestres.get(Semestres_.idcarrera)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Carreras findIdcarrera(Semestres entity) {
        return this.getMergedEntity(entity).getIdcarrera();
    }

    public boolean isSemestresDetalleCollectionEmpty(Semestres entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Semestres> semestres = cq.from(Semestres.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(semestres, entity), cb.isNotEmpty(semestres.get(Semestres_.semestresDetalleCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<SemestresDetalle> findSemestresDetalleCollection(Semestres entity) {
        Semestres mergedEntity = this.getMergedEntity(entity);
        Collection<SemestresDetalle> semestresDetalleCollection = mergedEntity.getSemestresDetalleCollection();
        semestresDetalleCollection.size();
        return semestresDetalleCollection;
    }
    
}
