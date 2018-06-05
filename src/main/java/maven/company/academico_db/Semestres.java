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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Semestres.findAll", query = "SELECT s FROM Semestres s")
    , @NamedQuery(name = "Semestres.findByIdsemestre", query = "SELECT s FROM Semestres s WHERE s.idsemestre = :idsemestre")
    , @NamedQuery(name = "Semestres.findByCodsemestre", query = "SELECT s FROM Semestres s WHERE s.codsemestre = :codsemestre")
    , @NamedQuery(name = "Semestres.findByNombre", query = "SELECT s FROM Semestres s WHERE s.nombre = :nombre")})
public class Semestres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idsemestre;
    @Basic(optional = false)
    @NotNull
    private int codsemestre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nombre;
    @JoinColumn(name = "idcarrera", referencedColumnName = "idcarrera")
    @ManyToOne(optional = false)
    private Carreras idcarrera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsemestre")
    private Collection<SemestresDetalle> semestresDetalleCollection;

    public Semestres() {
    }

    public Semestres(Integer idsemestre) {
        this.idsemestre = idsemestre;
    }

    public Semestres(Integer idsemestre, int codsemestre, String nombre) {
        this.idsemestre = idsemestre;
        this.codsemestre = codsemestre;
        this.nombre = nombre;
    }

    public Integer getIdsemestre() {
        return idsemestre;
    }

    public void setIdsemestre(Integer idsemestre) {
        this.idsemestre = idsemestre;
    }

    public int getCodsemestre() {
        return codsemestre;
    }

    public void setCodsemestre(int codsemestre) {
        this.codsemestre = codsemestre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Carreras getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Carreras idcarrera) {
        this.idcarrera = idcarrera;
    }

    @XmlTransient
    public Collection<SemestresDetalle> getSemestresDetalleCollection() {
        return semestresDetalleCollection;
    }

    public void setSemestresDetalleCollection(Collection<SemestresDetalle> semestresDetalleCollection) {
        this.semestresDetalleCollection = semestresDetalleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsemestre != null ? idsemestre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semestres)) {
            return false;
        }
        Semestres other = (Semestres) object;
        if ((this.idsemestre == null && other.idsemestre != null) || (this.idsemestre != null && !this.idsemestre.equals(other.idsemestre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maven.company.academico_db.Semestres[ idsemestre=" + idsemestre + " ]";
    }
    
}
