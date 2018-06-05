package maven.company.controller;

import maven.company.academico_db.Evaluaciones;
import maven.company.facade.EvaluacionesFacade;
import maven.company.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "evaluacionesController")
@ViewScoped
public class EvaluacionesController extends AbstractController<Evaluaciones> {

    @Inject
    private CalificacionesController calificacionesController;
    @Inject
    private SemestresDetalleController idsemestredetalleController;
    @Inject
    private MobilePageController mobilePageController;

    public EvaluacionesController() {
        // Inform the Abstract parent controller of the concrete Evaluaciones Entity
        super(Evaluaciones.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        calificacionesController.setSelected(null);
        idsemestredetalleController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Calificaciones controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCalificaciones(ActionEvent event) {
        Evaluaciones selected = this.getSelected();
        if (selected != null && calificacionesController.getSelected() == null) {
            calificacionesController.setSelected(selected.getCalificaciones());
        }
    }

    /**
     * Sets the "selected" attribute of the SemestresDetalle controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdsemestredetalle(ActionEvent event) {
        Evaluaciones selected = this.getSelected();
        if (selected != null && idsemestredetalleController.getSelected() == null) {
            idsemestredetalleController.setSelected(selected.getIdsemestredetalle());
        }
    }

}
