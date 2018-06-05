package maven.company.controller.util;

import java.io.Serializable;
import java.util.Objects;

public class MobilePage implements Serializable {

    private String pageId;
    private String viewId;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public MobilePage(String pageId, String viewId) {
        this.pageId = pageId;
        this.viewId = viewId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.pageId);
        hash = 13 * hash + Objects.hashCode(this.viewId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MobilePage other = (MobilePage) obj;
        if (!Objects.equals(this.pageId, other.pageId)) {
            return false;
        }
        if (!Objects.equals(this.viewId, other.viewId)) {
            return false;
        }
        return true;
    }

}
