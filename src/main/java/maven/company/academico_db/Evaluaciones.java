/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.company.academico_db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oym-dev06
 */
@Entity
@Table(catalog = "academico_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluaciones.findAll", query = "SELECT e FROM Evaluaciones e")
    , @NamedQuery(name = "Evaluaciones.findByIdevaluacion", query = "SELECT e FROM Evaluaciones e WHERE e.idevaluacion = :idevaluacion")
    , @NamedQuery(name = "Evaluaciones.findByDescripcion", query = "SELECT e FROM Evaluaciones e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Evaluaciones.findByFecha", query = "SELECT e FROM Evaluaciones e WHERE e.fecha = :fecha")})
public class Evaluaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idevaluacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idevalucion")
    private Calificaciones calificaciones;
    @JoinColumn(name = "idsemestredetalle", referencedColumnName = "idsemestredetalle")
    @ManyToOne(optional = false)
    private SemestresDetalle idsemestredetalle;

    public Evaluaciones() {
    }

    public Evaluaciones(Integer idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    public Evaluaciones(Integer idevaluacion, String descripcion, Date fecha) {
        this.idevaluacion = idevaluacion;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Integer getIdevaluacion() {
        return idevaluacion;
    }

    public void setIdevaluacion(Integer idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Calificaciones getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(Calificaciones calificaciones) {
        this.calificaciones = calificaciones;
    }

    public SemestresDetalle getIdsemestredetalle() {
        return idsemestredetalle;
    }

    public void setIdsemestredetalle(SemestresDetalle idsemestredetalle) {
        this.idsemestredetalle = idsemestredetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevaluacion != null ? idevaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluaciones)) {
            return false;
        }
        Evaluaciones other = (Evaluaciones) object;
        if ((this.idevaluacion == null && other.idevaluacion != null) || (this.idevaluacion != null && !this.idevaluacion.equals(other.idevaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maven.company.academico_db.Evaluaciones[ idevaluacion=" + idevaluacion + " ]";
    }
    
}
