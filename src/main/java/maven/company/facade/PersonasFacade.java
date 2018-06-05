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
import maven.company.academico_db.Personas;
import maven.company.academico_db.Personas_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import maven.company.academico_db.Profesores;
import maven.company.academico_db.Alumnos;

/**
 *
 * @author oym-dev06
 */
@Stateless
public class PersonasFacade extends AbstractFacade<Personas> {

    @PersistenceContext(unitName = "maven.company_academico_project_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasFacade() {
        super(Personas.class);
    }

    public boolean isProfesoresCollectionEmpty(Personas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Personas> personas = cq.from(Personas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(personas, entity), cb.isNotEmpty(personas.get(Personas_.profesoresCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Profesores> findProfesoresCollection(Personas entity) {
        Personas mergedEntity = this.getMergedEntity(entity);
        Collection<Profesores> profesoresCollection = mergedEntity.getProfesoresCollection();
        profesoresCollection.size();
        return profesoresCollection;
    }

    public boolean isAlumnosCollectionEmpty(Personas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Personas> personas = cq.from(Personas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(personas, entity), cb.isNotEmpty(personas.get(Personas_.alumnosCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Alumnos> findAlumnosCollection(Personas entity) {
        Personas mergedEntity = this.getMergedEntity(entity);
        Collection<Alumnos> alumnosCollection = mergedEntity.getAlumnosCollection();
        alumnosCollection.size();
        return alumnosCollection;
    }
    
}
