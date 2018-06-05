package maven.company.controller;

import maven.company.academico_db.Alumnos;
import maven.company.academico_db.Calificaciones;
import java.util.Collection;
import maven.company.facade.AlumnosFacade;
import maven.company.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "alumnosController")
@ViewScoped
public class AlumnosController extends AbstractController<Alumnos> {

    @Inject
    private CarrerasController idcarreraController;
    @Inject
    private PersonasController idpersonaController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isCalificacionesCollectionEmpty;

    public AlumnosController() {
        // Inform the Abstract parent controller of the concrete Alumnos Entity
        super(Alumnos.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idcarreraController.setSelected(null);
        idpersonaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCalificacionesCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Carreras controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdcarrera(ActionEvent event) {
        Alumnos selected = this.getSelected();
        if (selected != null && idcarreraController.getSelected() == null) {
            idcarreraController.setSelected(selected.getIdcarrera());
        }
    }

    /**
     * Sets the "selected" attribute of the Personas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdpersona(ActionEvent event) {
        Alumnos selected = this.getSelected();
        if (selected != null && idpersonaController.getSelected() == null) {
            idpersonaController.setSelected(selected.getIdpersona());
        }
    }

    public boolean getIsCalificacionesCollectionEmpty() {
        return this.isCalificacionesCollectionEmpty;
    }

    private void setIsCalificacionesCollectionEmpty() {
        Alumnos selected = this.getSelected();
        if (selected != null) {
            AlumnosFacade ejbFacade = (AlumnosFacade) this.getFacade();
            this.isCalificacionesCollectionEmpty = ejbFacade.isCalificacionesCollectionEmpty(selected);
        } else {
            this.isCalificacionesCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Calificaciones entities
     * that are retrieved from Alumnos and returns the navigation outcome.
     *
     * @return navigation outcome for Calificaciones page
     */
    public String navigateCalificacionesCollection() {
        Alumnos selected = this.getSelected();
        if (selected != null) {
            AlumnosFacade ejbFacade = (AlumnosFacade) this.getFacade();
            Collection<Calificaciones> selectedCalificacionesCollection = ejbFacade.findCalificacionesCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Calificaciones_items", selectedCalificacionesCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/calificaciones/index";
    }

}
