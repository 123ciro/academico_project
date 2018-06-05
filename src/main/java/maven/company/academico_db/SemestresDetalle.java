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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author oym-dev06
 */
@Entity
@Table(name = "semestres_detalle", catalog = "academico_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SemestresDetalle.findAll", query = "SELECT s FROM SemestresDetalle s")
    , @NamedQuery(name = "SemestresDetalle.findByIdsemestredetalle", query = "SELECT s FROM SemestresDetalle s WHERE s.idsemestredetalle = :idsemestredetalle")
    , @NamedQuery(name = "SemestresDetalle.findByDescripcion", query = "SELECT s FROM SemestresDetalle s WHERE s.descripcion = :descripcion")})
public class SemestresDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idsemestredetalle;
    @Size(max = 255)
    private String descripcion;
    @JoinColumn(name = "idmateria", referencedColumnName = "idmateria")
    @ManyToOne(optional = false)
    private Materias idmateria;
    @JoinColumn(name = "idprofesor", referencedColumnName = "idprofesor")
    @ManyToOne(optional = false)
    private Profesores idprofesor;
    @JoinColumn(name = "idsemestre", referencedColumnName = "idsemestre")
    @ManyToOne(optional = false)
    private Semestres idsemestre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsemestredetalle")
    private Collection<Evaluaciones> evaluacionesCollection;

    public SemestresDetalle() {
    }

    public SemestresDetalle(Integer idsemestredetalle) {
        this.idsemestredetalle = idsemestredetalle;
    }

    public Integer getIdsemestredetalle() {
        return idsemestredetalle;
    }

    public void setIdsemestredetalle(Integer idsemestredetalle) {
        this.idsemestredetalle = idsemestredetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Materias getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(Materias idmateria) {
        this.idmateria = idmateria;
    }

    public Profesores getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Profesores idprofesor) {
        this.idprofesor = idprofesor;
    }

    public Semestres getIdsemestre() {
        return idsemestre;
    }

    public void setIdsemestre(Semestres idsemestre) {
        this.idsemestre = idsemestre;
    }

    @XmlTransient
    public Collection<Evaluaciones> getEvaluacionesCollection() {
        return evaluacionesCollection;
    }

    public void setEvaluacionesCollection(Collection<Evaluaciones> evaluacionesCollection) {
        this.evaluacionesCollection = evaluacionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsemestredetalle != null ? idsemestredetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SemestresDetalle)) {
            return false;
        }
        SemestresDetalle other = (SemestresDetalle) object;
        if ((this.idsemestredetalle == null && other.idsemestredetalle != null) || (this.idsemestredetalle != null && !this.idsemestredetalle.equals(other.idsemestredetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maven.company.academico_db.SemestresDetalle[ idsemestredetalle=" + idsemestredetalle + " ]";
    }
    
}
