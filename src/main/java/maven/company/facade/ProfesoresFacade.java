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
import maven.company.academico_db.Profesores;
import maven.company.academico_db.Profesores_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import maven.company.academico_db.Personas;
import maven.company.academico_db.SemestresDetalle;

/**
 *
 * @author oym-dev06
 */
@Stateless
public class ProfesoresFacade extends AbstractFacade<Profesores> {

    @PersistenceContext(unitName = "maven.company_academico_project_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesoresFacade() {
        super(Profesores.class);
    }

    public boolean isIdpersonaEmpty(Profesores entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Profesores> profesores = cq.from(Profesores.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(profesores, entity), cb.isNotNull(profesores.get(Profesores_.idpersona)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Personas findIdpersona(Profesores entity) {
        return this.getMergedEntity(entity).getIdpersona();
    }

    public boolean isSemestresDetalleCollectionEmpty(Profesores entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Profesores> profesores = cq.from(Profesores.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(profesores, entity), cb.isNotEmpty(profesores.get(Profesores_.semestresDetalleCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<SemestresDetalle> findSemestresDetalleCollection(Profesores entity) {
        Profesores mergedEntity = this.getMergedEntity(entity);
        Collection<SemestresDetalle> semestresDetalleCollection = mergedEntity.getSemestresDetalleCollection();
        semestresDetalleCollection.size();
        return semestresDetalleCollection;
    }
    
}
