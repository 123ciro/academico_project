package maven.company.controller;

import maven.company.academico_db.Materias;
import maven.company.academico_db.SemestresDetalle;
import java.util.Collection;
import maven.company.facade.MateriasFacade;
import maven.company.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "materiasController")
@ViewScoped
public class MateriasController extends AbstractController<Materias> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isSemestresDetalleCollectionEmpty;

    public MateriasController() {
        // Inform the Abstract parent controller of the concrete Materias Entity
        super(Materias.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsSemestresDetalleCollectionEmpty();
    }

    public boolean getIsSemestresDetalleCollectionEmpty() {
        return this.isSemestresDetalleCollectionEmpty;
    }

    private void setIsSemestresDetalleCollectionEmpty() {
        Materias selected = this.getSelected();
        if (selected != null) {
            MateriasFacade ejbFacade = (MateriasFacade) this.getFacade();
            this.isSemestresDetalleCollectionEmpty = ejbFacade.isSemestresDetalleCollectionEmpty(selected);
        } else {
            this.isSemestresDetalleCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of SemestresDetalle entities
     * that are retrieved from Materias and returns the navigation outcome.
     *
     * @return navigation outcome for SemestresDetalle page
     */
    public String navigateSemestresDetalleCollection() {
        Materias selected = this.getSelected();
        if (selected != null) {
            MateriasFacade ejbFacade = (MateriasFacade) this.getFacade();
            Collection<SemestresDetalle> selectedSemestresDetalleCollection = ejbFacade.findSemestresDetalleCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("SemestresDetalle_items", selectedSemestresDetalleCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/semestresDetalle/index";
    }

}
