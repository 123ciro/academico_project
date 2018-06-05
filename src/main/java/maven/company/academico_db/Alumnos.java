/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.company.academico_db;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author oym-dev06
 */
@Entity
@Table(catalog = "academico_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumnos.findAll", query = "SELECT a FROM Alumnos a")
    , @NamedQuery(name = "Alumnos.findByIdalumno", query = "SELECT a FROM Alumnos a WHERE a.idalumno = :idalumno")
    , @NamedQuery(name = "Alumnos.findByCodigo", query = "SELECT a FROM Alumnos a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Alumnos.findByFechainscripcion", query = "SELECT a FROM Alumnos a WHERE a.fechainscripcion = :fechainscripcion")})
public class Alumnos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idalumno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fechainscripcion;
    @JoinColumn(name = "idcarrera", referencedColumnName = "idcarrera")
    @ManyToOne(optional = false)
    private Carreras idcarrera;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Personas idpersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idalumno")
    private Collection<Calificaciones> calificacionesCollection;

    public Alumnos() {
    }

    public Alumnos(Integer idalumno) {
        this.idalumno = idalumno;
    }

    public Alumnos(Integer idalumno, String codigo, Date fechainscripcion) {
        this.idalumno = idalumno;
        this.codigo = codigo;
        this.fechainscripcion = fechainscripcion;
    }

    public Integer getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(Integer idalumno) {
        this.idalumno = idalumno;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechainscripcion() {
        return fechainscripcion;
    }

    public void setFechainscripcion(Date fechainscripcion) {
        this.fechainscripcion = fechainscripcion;
    }

    public Carreras getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Carreras idcarrera) {
        this.idcarrera = idcarrera;
    }

    public Personas getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Personas idpersona) {
        this.idpersona = idpersona;
    }

    @XmlTransient
    public Collection<Calificaciones> getCalificacionesCollection() {
        return calificacionesCollection;
    }

    public void setCalificacionesCollection(Collection<Calificaciones> calificacionesCollection) {
        this.calificacionesCollection = calificacionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalumno != null ? idalumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumnos)) {
            return false;
        }
        Alumnos other = (Alumnos) object;
        if ((this.idalumno == null && other.idalumno != null) || (this.idalumno != null && !this.idalumno.equals(other.idalumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maven.company.academico_db.Alumnos[ idalumno=" + idalumno + " ]";
    }
    
}
