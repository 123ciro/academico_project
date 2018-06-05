/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.company.academico_db;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oym-dev06
 */
@Entity
@Table(catalog = "academico_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calificaciones.findAll", query = "SELECT c FROM Calificaciones c")
    , @NamedQuery(name = "Calificaciones.findByIdcalificacion", query = "SELECT c FROM Calificaciones c WHERE c.idcalificacion = :idcalificacion")
    , @NamedQuery(name = "Calificaciones.findByNota", query = "SELECT c FROM Calificaciones c WHERE c.nota = :nota")})
public class Calificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idcalificacion;
    @Basic(optional = false)
    @NotNull
    private int nota;
    @JoinColumn(name = "idalumno", referencedColumnName = "idalumno")
    @ManyToOne(optional = false)
    private Alumnos idalumno;
    @JoinColumn(name = "idevalucion", referencedColumnName = "idevaluacion")
    @OneToOne(optional = false)
    private Evaluaciones idevalucion;

    public Calificaciones() {
    }

    public Calificaciones(Integer idcalificacion) {
        this.idcalificacion = idcalificacion;
    }

    public Calificaciones(Integer idcalificacion, int nota) {
        this.idcalificacion = idcalificacion;
        this.nota = nota;
    }

    public Integer getIdcalificacion() {
        return idcalificacion;
    }

    public void setIdcalificacion(Integer idcalificacion) {
        this.idcalificacion = idcalificacion;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Alumnos getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(Alumnos idalumno) {
        this.idalumno = idalumno;
    }

    public Evaluaciones getIdevalucion() {
        return idevalucion;
    }

    public void setIdevalucion(Evaluaciones idevalucion) {
        this.idevalucion = idevalucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalificacion != null ? idcalificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calificaciones)) {
            return false;
        }
        Calificaciones other = (Calificaciones) object;
        if ((this.idcalificacion == null && other.idcalificacion != null) || (this.idcalificacion != null && !this.idcalificacion.equals(other.idcalificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maven.company.academico_db.Calificaciones[ idcalificacion=" + idcalificacion + " ]";
    }
    
}
