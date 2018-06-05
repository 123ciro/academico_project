package maven.company.controller;

import maven.company.academico_db.Carreras;
import maven.company.academico_db.Alumnos;
import maven.company.academico_db.Semestres;
import java.util.Collection;
import maven.company.facade.CarrerasFacade;
import maven.company.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "carrerasController")
@ViewScoped
public class CarrerasController extends AbstractController<Carreras> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isAlumnosCollectionEmpty;
    private boolean isSemestresCollectionEmpty;

    public CarrerasController() {
        // Inform the Abstract parent controller of the concrete Carreras Entity
        super(Carreras.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsAlumnosCollectionEmpty();
        this.setIsSemestresCollectionEmpty();
    }

    public boolean getIsAlumnosCollectionEmpty() {
        return this.isAlumnosCollectionEmpty;
    }

    private void setIsAlumnosCollectionEmpty() {
        Carreras selected = this.getSelected();
        if (selected != null) {
            CarrerasFacade ejbFacade = (CarrerasFacade) this.getFacade();
            this.isAlumnosCollectionEmpty = ejbFacade.isAlumnosCollectionEmpty(selected);
        } else {
            this.isAlumnosCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Alumnos entities that are
     * retrieved from Carreras and returns the navigation outcome.
     *
     * @return navigation outcome for Alumnos page
     */
    public String navigateAlumnosCollection() {
        Carreras selected = this.getSelected();
        if (selected != null) {
            CarrerasFacade ejbFacade = (CarrerasFacade) this.getFacade();
            Collection<Alumnos> selectedAlumnosCollection = ejbFacade.findAlumnosCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Alumnos_items", selectedAlumnosCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/alumnos/index";
    }

    public boolean getIsSemestresCollectionEmpty() {
        return this.isSemestresCollectionEmpty;
    }

    private void setIsSemestresCollectionEmpty() {
        Carreras selected = this.getSelected();
        if (selected != null) {
            CarrerasFacade ejbFacade = (CarrerasFacade) this.getFacade();
            this.isSemestresCollectionEmpty = ejbFacade.isSemestresCollectionEmpty(selected);
        } else {
            this.isSemestresCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Semestres entities that
     * are retrieved from Carreras and returns the navigation outcome.
     *
     * @return navigation outcome for Semestres page
     */
    public String navigateSemestresCollection() {
        Carreras selected = this.getSelected();
        if (selected != null) {
            CarrerasFacade ejbFacade = (CarrerasFacade) this.getFacade();
            Collection<Semestres> selectedSemestresCollection = ejbFacade.findSemestresCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Semestres_items", selectedSemestresCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/semestres/index";
    }

}
