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
    @NamedQuery(name = "Materias.findAll", query = "SELECT m FROM Materias m")
    , @NamedQuery(name = "Materias.findByIdmateria", query = "SELECT m FROM Materias m WHERE m.idmateria = :idmateria")
    , @NamedQuery(name = "Materias.findByCodigo", query = "SELECT m FROM Materias m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "Materias.findByNombre", query = "SELECT m FROM Materias m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Materias.findByDescripcion", query = "SELECT m FROM Materias m WHERE m.descripcion = :descripcion")})
public class Materias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idmateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmateria")
    private Collection<SemestresDetalle> semestresDetalleCollection;

    public Materias() {
    }

    public Materias(Integer idmateria) {
        this.idmateria = idmateria;
    }

    public Materias(Integer idmateria, String codigo, String nombre, String descripcion) {
        this.idmateria = idmateria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(Integer idmateria) {
        this.idmateria = idmateria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        hash += (idmateria != null ? idmateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materias)) {
            return false;
        }
        Materias other = (Materias) object;
        if ((this.idmateria == null && other.idmateria != null) || (this.idmateria != null && !this.idmateria.equals(other.idmateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maven.company.academico_db.Materias[ idmateria=" + idmateria + " ]";
    }
    
}
