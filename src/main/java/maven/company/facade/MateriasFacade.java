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
import maven.company.academico_db.Materias;
import maven.company.academico_db.Materias_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import maven.company.academico_db.SemestresDetalle;

/**
 *
 * @author oym-dev06
 */
@Stateless
public class MateriasFacade extends AbstractFacade<Materias> {

    @PersistenceContext(unitName = "maven.company_academico_project_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriasFacade() {
        super(Materias.class);
    }

    public boolean isSemestresDetalleCollectionEmpty(Materias entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Materias> materias = cq.from(Materias.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(materias, entity), cb.isNotEmpty(materias.get(Materias_.semestresDetalleCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<SemestresDetalle> findSemestresDetalleCollection(Materias entity) {
        Materias mergedEntity = this.getMergedEntity(entity);
        Collection<SemestresDetalle> semestresDetalleCollection = mergedEntity.getSemestresDetalleCollection();
        semestresDetalleCollection.size();
        return semestresDetalleCollection;
    }
    
}
