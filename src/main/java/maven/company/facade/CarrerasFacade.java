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
import maven.company.academico_db.Carreras;
import maven.company.academico_db.Carreras_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import maven.company.academico_db.Alumnos;
import maven.company.academico_db.Semestres;

/**
 *
 * @author oym-dev06
 */
@Stateless
public class CarrerasFacade extends AbstractFacade<Carreras> {

    @PersistenceContext(unitName = "maven.company_academico_project_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarrerasFacade() {
        super(Carreras.class);
    }

    public boolean isAlumnosCollectionEmpty(Carreras entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Carreras> carreras = cq.from(Carreras.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(carreras, entity), cb.isNotEmpty(carreras.get(Carreras_.alumnosCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Alumnos> findAlumnosCollection(Carreras entity) {
        Carreras mergedEntity = this.getMergedEntity(entity);
        Collection<Alumnos> alumnosCollection = mergedEntity.getAlumnosCollection();
        alumnosCollection.size();
        return alumnosCollection;
    }

    public boolean isSemestresCollectionEmpty(Carreras entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Carreras> carreras = cq.from(Carreras.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(carreras, entity), cb.isNotEmpty(carreras.get(Carreras_.semestresCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Semestres> findSemestresCollection(Carreras entity) {
        Carreras mergedEntity = this.getMergedEntity(entity);
        Collection<Semestres> semestresCollection = mergedEntity.getSemestresCollection();
        semestresCollection.size();
        return semestresCollection;
    }
    
}
