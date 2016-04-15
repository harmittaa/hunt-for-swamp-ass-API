/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwampPackage;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author iosdev
 */
@Entity
@Table(name = "CLUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clue.findAll", query = "SELECT c FROM Clue c"),
    @NamedQuery(name = "Clue.findByClueid", query = "SELECT c FROM Clue c WHERE c.clueid = :clueid"),
    @NamedQuery(name = "Clue.findByCluetitle", query = "SELECT c FROM Clue c WHERE c.cluetitle = :cluetitle"),
    @NamedQuery(name = "Clue.findByCluedescription", query = "SELECT c FROM Clue c WHERE c.cluedescription = :cluedescription")})
public class Clue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLUEID")
    private Integer clueid;
    @Size(max = 255)
    @Column(name = "CLUETITLE")
    private String cluetitle;
    @Size(max = 255)
    @Column(name = "CLUEDESCRIPTION")
    private String cluedescription;
    @ManyToMany(mappedBy = "clueCollection")
    private Collection<Location> locationCollection;

    public Clue() {
    }

    public Clue(Integer clueid) {
        this.clueid = clueid;
    }

    public Integer getClueid() {
        return clueid;
    }

    public void setClueid(Integer clueid) {
        this.clueid = clueid;
    }

    public String getCluetitle() {
        return cluetitle;
    }

    public void setCluetitle(String cluetitle) {
        this.cluetitle = cluetitle;
    }

    public String getCluedescription() {
        return cluedescription;
    }

    public void setCluedescription(String cluedescription) {
        this.cluedescription = cluedescription;
    }

    @XmlTransient
    public Collection<Location> getLocationCollection() {
        return locationCollection;
    }

    public void setLocationCollection(Collection<Location> locationCollection) {
        this.locationCollection = locationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clueid != null ? clueid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clue)) {
            return false;
        }
        Clue other = (Clue) object;
        if ((this.clueid == null && other.clueid != null) || (this.clueid != null && !this.clueid.equals(other.clueid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SwampPackage.Clue[ clueid=" + clueid + " ]";
    }
    
}
