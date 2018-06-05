package maven.company.controller;

import maven.company.academico_db.SemestresDetalle;
import maven.company.academico_db.Evaluaciones;
import java.util.Collection;
import maven.company.facade.SemestresDetalleFacade;
import maven.company.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "semestresDetalleController")
@ViewScoped
public class SemestresDetalleController extends AbstractController<SemestresDetalle> {

    @Inject
    private MateriasController idmateriaController;
    @Inject
    private ProfesoresController idprofesorController;
    @Inject
    private SemestresController idsemestreController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEvaluacionesCollectionEmpty;

    public SemestresDetalleController() {
        // Inform the Abstract parent controller of the concrete SemestresDetalle Entity
        super(SemestresDetalle.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idmateriaController.setSelected(null);
        idprofesorController.setSelected(null);
        idsemestreController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEvaluacionesCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Materias controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdmateria(ActionEvent event) {
        SemestresDetalle selected = this.getSelected();
        if (selected != null && idmateriaController.getSelected() == null) {
            idmateriaController.setSelected(selected.getIdmateria());
        }
    }

    /**
     * Sets the "selected" attribute of the Profesores controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdprofesor(ActionEvent event) {
        SemestresDetalle selected = this.getSelected();
        if (selected != null && idprofesorController.getSelected() == null) {
            idprofesorController.setSelected(selected.getIdprofesor());
        }
    }

    /**
     * Sets the "selected" attribute of the Semestres controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdsemestre(ActionEvent event) {
        SemestresDetalle selected = this.getSelected();
        if (selected != null && idsemestreController.getSelected() == null) {
            idsemestreController.setSelected(selected.getIdsemestre());
        }
    }

    public boolean getIsEvaluacionesCollectionEmpty() {
        return this.isEvaluacionesCollectionEmpty;
    }

    private void setIsEvaluacionesCollectionEmpty() {
        SemestresDetalle selected = this.getSelected();
        if (selected != null) {
            SemestresDetalleFacade ejbFacade = (SemestresDetalleFacade) this.getFacade();
            this.isEvaluacionesCollectionEmpty = ejbFacade.isEvaluacionesCollectionEmpty(selected);
        } else {
            this.isEvaluacionesCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Evaluaciones entities
     * that are retrieved from SemestresDetalle and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Evaluaciones page
     */
    public String navigateEvaluacionesCollection() {
        SemestresDetalle selected = this.getSelected();
        if (selected != null) {
            SemestresDetalleFacade ejbFacade = (SemestresDetalleFacade) this.getFacade();
            Collection<Evaluaciones> selectedEvaluacionesCollection = ejbFacade.findEvaluacionesCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Evaluaciones_items", selectedEvaluacionesCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/evaluaciones/index";
    }

}
