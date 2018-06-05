package maven.company.controller;

import maven.company.academico_db.Profesores;
import maven.company.academico_db.SemestresDetalle;
import java.util.Collection;
import maven.company.facade.ProfesoresFacade;
import maven.company.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "profesoresController")
@ViewScoped
public class ProfesoresController extends AbstractController<Profesores> {

    @Inject
    private PersonasController idpersonaController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isSemestresDetalleCollectionEmpty;

    public ProfesoresController() {
        // Inform the Abstract parent controller of the concrete Profesores Entity
        super(Profesores.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idpersonaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsSemestresDetalleCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Personas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdpersona(ActionEvent event) {
        Profesores selected = this.getSelected();
        if (selected != null && idpersonaController.getSelected() == null) {
            idpersonaController.setSelected(selected.getIdpersona());
        }
    }

    public boolean getIsSemestresDetalleCollectionEmpty() {
        return this.isSemestresDetalleCollectionEmpty;
    }

    private void setIsSemestresDetalleCollectionEmpty() {
        Profesores selected = this.getSelected();
        if (selected != null) {
            ProfesoresFacade ejbFacade = (ProfesoresFacade) this.getFacade();
            this.isSemestresDetalleCollectionEmpty = ejbFacade.isSemestresDetalleCollectionEmpty(selected);
        } else {
            this.isSemestresDetalleCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of SemestresDetalle entities
     * that are retrieved from Profesores and returns the navigation outcome.
     *
     * @return navigation outcome for SemestresDetalle page
     */
    public String navigateSemestresDetalleCollection() {
        Profesores selected = this.getSelected();
        if (selected != null) {
            ProfesoresFacade ejbFacade = (ProfesoresFacade) this.getFacade();
            Collection<SemestresDetalle> selectedSemestresDetalleCollection = ejbFacade.findSemestresDetalleCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("SemestresDetalle_items", selectedSemestresDetalleCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/semestresDetalle/index";
    }

}
