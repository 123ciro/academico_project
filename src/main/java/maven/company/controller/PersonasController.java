package maven.company.controller;

import maven.company.academico_db.Personas;
import maven.company.academico_db.Profesores;
import maven.company.academico_db.Alumnos;
import java.util.Collection;
import maven.company.facade.PersonasFacade;
import maven.company.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "personasController")
@ViewScoped
public class PersonasController extends AbstractController<Personas> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isProfesoresCollectionEmpty;
    private boolean isAlumnosCollectionEmpty;

    public PersonasController() {
        // Inform the Abstract parent controller of the concrete Personas Entity
        super(Personas.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsProfesoresCollectionEmpty();
        this.setIsAlumnosCollectionEmpty();
    }

    public boolean getIsProfesoresCollectionEmpty() {
        return this.isProfesoresCollectionEmpty;
    }

    private void setIsProfesoresCollectionEmpty() {
        Personas selected = this.getSelected();
        if (selected != null) {
            PersonasFacade ejbFacade = (PersonasFacade) this.getFacade();
            this.isProfesoresCollectionEmpty = ejbFacade.isProfesoresCollectionEmpty(selected);
        } else {
            this.isProfesoresCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Profesores entities that
     * are retrieved from Personas and returns the navigation outcome.
     *
     * @return navigation outcome for Profesores page
     */
    public String navigateProfesoresCollection() {
        Personas selected = this.getSelected();
        if (selected != null) {
            PersonasFacade ejbFacade = (PersonasFacade) this.getFacade();
            Collection<Profesores> selectedProfesoresCollection = ejbFacade.findProfesoresCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Profesores_items", selectedProfesoresCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/profesores/index";
    }

    public boolean getIsAlumnosCollectionEmpty() {
        return this.isAlumnosCollectionEmpty;
    }

    private void setIsAlumnosCollectionEmpty() {
        Personas selected = this.getSelected();
        if (selected != null) {
            PersonasFacade ejbFacade = (PersonasFacade) this.getFacade();
            this.isAlumnosCollectionEmpty = ejbFacade.isAlumnosCollectionEmpty(selected);
        } else {
            this.isAlumnosCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Alumnos entities that are
     * retrieved from Personas and returns the navigation outcome.
     *
     * @return navigation outcome for Alumnos page
     */
    public String navigateAlumnosCollection() {
        Personas selected = this.getSelected();
        if (selected != null) {
            PersonasFacade ejbFacade = (PersonasFacade) this.getFacade();
            Collection<Alumnos> selectedAlumnosCollection = ejbFacade.findAlumnosCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Alumnos_items", selectedAlumnosCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/alumnos/index";
    }

}
