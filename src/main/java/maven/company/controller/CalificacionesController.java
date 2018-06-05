package maven.company.controller;

import maven.company.academico_db.Calificaciones;
import maven.company.facade.CalificacionesFacade;
import maven.company.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "calificacionesController")
@ViewScoped
public class CalificacionesController extends AbstractController<Calificaciones> {

    @Inject
    private AlumnosController idalumnoController;
    @Inject
    private EvaluacionesController idevalucionController;
    @Inject
    private MobilePageController mobilePageController;

    public CalificacionesController() {
        // Inform the Abstract parent controller of the concrete Calificaciones Entity
        super(Calificaciones.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idalumnoController.setSelected(null);
        idevalucionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Alumnos controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdalumno(ActionEvent event) {
        Calificaciones selected = this.getSelected();
        if (selected != null && idalumnoController.getSelected() == null) {
            idalumnoController.setSelected(selected.getIdalumno());
        }
    }

    /**
     * Sets the "selected" attribute of the Evaluaciones controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdevalucion(ActionEvent event) {
        Calificaciones selected = this.getSelected();
        if (selected != null && idevalucionController.getSelected() == null) {
            idevalucionController.setSelected(selected.getIdevalucion());
        }
    }

}
