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
    @NamedQuery(name = "Profesores.findAll", query = "SELECT p FROM Profesores p")
    , @NamedQuery(name = "Profesores.findByIdprofesor", query = "SELECT p FROM Profesores p WHERE p.idprofesor = :idprofesor")
    , @NamedQuery(name = "Profesores.findByEspecialidad", query = "SELECT p FROM Profesores p WHERE p.especialidad = :especialidad")})
public class Profesores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idprofesor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String especialidad;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Personas idpersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprofesor")
    private Collection<SemestresDetalle> semestresDetalleCollection;

    public Profesores() {
    }

    public Profesores(Integer idprofesor) {
        this.idprofesor = idprofesor;
    }

    public Profesores(Integer idprofesor, String especialidad) {
        this.idprofesor = idprofesor;
        this.especialidad = especialidad;
    }

    public Integer getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Integer idprofesor) {
        this.idprofesor = idprofesor;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Personas getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Personas idpersona) {
        this.idpersona = idpersona;
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
        hash += (idprofesor != null ? idprofesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesores)) {
            return false;
        }
        Profesores other = (Profesores) object;
        if ((this.idprofesor == null && other.idprofesor != null) || (this.idprofesor != null && !this.idprofesor.equals(other.idprofesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maven.company.academico_db.Profesores[ idprofesor=" + idprofesor + " ]";
    }
    
}
