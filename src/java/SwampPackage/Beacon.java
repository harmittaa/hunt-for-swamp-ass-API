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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author iosdev
 */
@Entity
@Table(name = "BEACON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beacon.findAll", query = "SELECT b FROM Beacon b"),
    @NamedQuery(name = "Beacon.findByBeaconid", query = "SELECT b FROM Beacon b WHERE b.beaconid = :beaconid"),
    @NamedQuery(name = "Beacon.findByUuid", query = "SELECT b FROM Beacon b WHERE b.uuid = :uuid"),
    @NamedQuery(name = "Beacon.findByMajor", query = "SELECT b FROM Beacon b WHERE b.major = :major"),
    @NamedQuery(name = "Beacon.findByMinor", query = "SELECT b FROM Beacon b WHERE b.minor = :minor")})
public class Beacon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BEACONID")
    private Integer beaconid;
    @Size(max = 100)
    @Column(name = "UUID")
    private String uuid;
    @Size(max = 100)
    @Column(name = "MAJOR")
    private String major;
    @Size(max = 100)
    @Column(name = "MINOR")
    private String minor;
    @OneToMany(mappedBy = "beaconid")
    private Collection<Location> locationCollection;

    public Beacon() {
    }

    public Beacon(Integer beaconid) {
        this.beaconid = beaconid;
    }

    public Integer getBeaconid() {
        return beaconid;
    }

    public void setBeaconid(Integer beaconid) {
        this.beaconid = beaconid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
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
        hash += (beaconid != null ? beaconid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beacon)) {
            return false;
        }
        Beacon other = (Beacon) object;
        if ((this.beaconid == null && other.beaconid != null) || (this.beaconid != null && !this.beaconid.equals(other.beaconid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SwampPackage.Beacon[ beaconid=" + beaconid + " ]";
    }
    
}
