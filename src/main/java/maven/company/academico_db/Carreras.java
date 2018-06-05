/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maven.company.academico_db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NamedQuery(name = "Carreras.findAll", query = "SELECT c FROM Carreras c")
    , @NamedQuery(name = "Carreras.findByIdcarrera", query = "SELECT c FROM Carreras c WHERE c.idcarrera = :idcarrera")
    , @NamedQuery(name = "Carreras.findByNombre", query = "SELECT c FROM Carreras c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Carreras.findByDescripcion", query = "SELECT c FROM Carreras c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Carreras.findByDuracion", query = "SELECT c FROM Carreras c WHERE c.duracion = :duracion")})
public class Carreras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idcarrera;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    private int duracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcarrera")
    private Collection<Alumnos> alumnosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcarrera")
    private Collection<Semestres> semestresCollection;

    public Carreras() {
    }

    public Carreras(Integer idcarrera) {
        this.idcarrera = idcarrera;
    }

    public Carreras(Integer idcarrera, String nombre, String descripcion, int duracion) {
        this.idcarrera = idcarrera;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    public Integer getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Integer idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @XmlTransient
    public Collection<Alumnos> getAlumnosCollection() {
        return alumnosCollection;
    }

    public void setAlumnosCollection(Collection<Alumnos> alumnosCollection) {
        this.alumnosCollection = alumnosCollection;
    }

    @XmlTransient
    public Collection<Semestres> getSemestresCollection() {
        return semestresCollection;
    }

    public void setSemestresCollection(Collection<Semestres> semestresCollection) {
        this.semestresCollection = semestresCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcarrera != null ? idcarrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carreras)) {
            return false;
        }
        Carreras other = (Carreras) object;
        if ((this.idcarrera == null && other.idcarrera != null) || (this.idcarrera != null && !this.idcarrera.equals(other.idcarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maven.company.academico_db.Carreras[ idcarrera=" + idcarrera + " ]";
    }
    
}
