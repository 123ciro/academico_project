/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.company.controller.util;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

@Named(value = "currentPageActionListener")
@RequestScoped
public class CurrentPageActionListener implements Serializable, ActionListener {

    @Inject
    private MobilePageController mobilePageController;

    /**
     * Creates a new instance of CurrentPageActionListener
     */
    public CurrentPageActionListener() {
    }

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        this.mobilePageController.currentPageListener(event);
    }

}
