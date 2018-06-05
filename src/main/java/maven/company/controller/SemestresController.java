package maven.company.controller;

import maven.company.academico_db.Semestres;
import maven.company.academico_db.SemestresDetalle;
import java.util.Collection;
import maven.company.facade.SemestresFacade;
import maven.company.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "semestresController")
@ViewScoped
public class SemestresController extends AbstractController<Semestres> {

    @Inject
    private CarrerasController idcarreraController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isSemestresDetalleCollectionEmpty;

    public SemestresController() {
        // Inform the Abstract parent controller of the concrete Semestres Entity
        super(Semestres.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idcarreraController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsSemestresDetalleCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Carreras controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdcarrera(ActionEvent event) {
        Semestres selected = this.getSelected();
        if (selected != null && idcarreraController.getSelected() == null) {
            idcarreraController.setSelected(selected.getIdcarrera());
        }
    }

    public boolean getIsSemestresDetalleCollectionEmpty() {
        return this.isSemestresDetalleCollectionEmpty;
    }

    private void setIsSemestresDetalleCollectionEmpty() {
        Semestres selected = this.getSelected();
        if (selected != null) {
            SemestresFacade ejbFacade = (SemestresFacade) this.getFacade();
            this.isSemestresDetalleCollectionEmpty = ejbFacade.isSemestresDetalleCollectionEmpty(selected);
        } else {
            this.isSemestresDetalleCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of SemestresDetalle entities
     * that are retrieved from Semestres and returns the navigation outcome.
     *
     * @return navigation outcome for SemestresDetalle page
     */
    public String navigateSemestresDetalleCollection() {
        Semestres selected = this.getSelected();
        if (selected != null) {
            SemestresFacade ejbFacade = (SemestresFacade) this.getFacade();
            Collection<SemestresDetalle> selectedSemestresDetalleCollection = ejbFacade.findSemestresDetalleCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("SemestresDetalle_items", selectedSemestresDetalleCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/semestresDetalle/index";
    }

}
