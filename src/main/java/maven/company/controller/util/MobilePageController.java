/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.company.controller.util;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.mobile.component.page.Page;
import org.primefaces.expression.SearchExpressionFacade;

@Named(value = "mobilePageController")
@SessionScoped
public class MobilePageController implements Serializable {

    private static final String MOBILE_PAGES_PREFIX = "/mobile";

    private List<MobilePage> pageHistory;
    private MobilePage currentPage;
    private String nextPage;

    /**
     * Creates a new instance of HistoryBean
     */
    public MobilePageController() {
    }

    public void clearPageHistory(ActionEvent event) {
        this.pageHistory.clear();
        this.currentPage = null;
    }

    public void currentPageListener(ActionEvent event) {
        MobilePage nearestPage = getNearestPage(event.getComponent());
        if (nearestPage != null && !nearestPage.equals(currentPage)) {
            this.currentPage = nearestPage;
        }
    }

    public String navigateWithHistory(String actionName) {
        if (currentPage != null) {
            pageHistory.add(currentPage);
        }
        this.nextPage = null; // Reset next page to avoid any double posting
        return actionName;
    }

    public String navigateWithHistory() {
        if (nextPage != null) {
            return navigateWithHistory(nextPage);
        }
        return null;
    }

    public String navigateBackInHistory(String fallBackActionName) {
        MobilePage lastPage = this.getLastPage();
        if (lastPage != null) {
            this.pageHistory.remove(lastPage);
            if (lastPage.getViewId().equals(this.getViewId())) {
                return "pm:" + lastPage.getPageId();
            } else {
                return lastPage.getViewId();
            }
        }
        return fallBackActionName;
    }

    public MobilePage getLastPage() {
        if (pageHistory.size() > 0) {
            return this.pageHistory.get(pageHistory.size() - 1);
        }
        return null;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    private MobilePage getNearestPage(UIComponent component) {
        UIComponent nearestPageComponent = findPageComponent(component);
        if (nearestPageComponent != null) {
            return (new MobilePage(nearestPageComponent.getId(), this.getViewId()));
        }
        return null;
    }

    private UIComponent findPageComponent(UIComponent component) {
        if (Page.class.isAssignableFrom(component.getClass())) {
            return component;
        }
        if (component.getParent() != null) {
            UIComponent parentComponent = findPageComponent(component.getParent());
            if (parentComponent != null) {
                return parentComponent;
            }
        }
        return null;
    }

    public String getViewId() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }

    public String getMobilePagesPrefix() {
        if (getViewId().startsWith(MOBILE_PAGES_PREFIX)) {
            return MOBILE_PAGES_PREFIX;
        }
        return "";
    }

    public String updatableWidgets(UIComponent component, String widgets) {
        String updatableWidgets = "";
        FacesContext context = FacesContext.getCurrentInstance();
        for (String widget : widgets.split(",")) {
            try {
                UIComponent widgetComponent = SearchExpressionFacade.resolveComponent(context, component, widget);
                if (widgetComponent != null) {
                    if (updatableWidgets.length() == 0) {
                        updatableWidgets = widget;
                    } else {
                        updatableWidgets = updatableWidgets + "," + widget;
                    }
                }
            } catch (FacesException ex) {
                // Do nothing -> widget that cannot be found will not be included in final expression
            }
        }
        return updatableWidgets;
    }

    @PostConstruct
    public void init() {
        this.pageHistory = new ArrayList<>();
    }

}
