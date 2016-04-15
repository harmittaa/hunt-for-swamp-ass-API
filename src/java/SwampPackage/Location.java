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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "LOCATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findByLocationid", query = "SELECT l FROM Location l WHERE l.locationid = :locationid"),
    @NamedQuery(name = "Location.findByWintitle", query = "SELECT l FROM Location l WHERE l.wintitle = :wintitle"),
    @NamedQuery(name = "Location.findByWindescription", query = "SELECT l FROM Location l WHERE l.windescription = :windescription")})
public class Location implements Serializable {

    @ManyToMany(mappedBy = "locationCollection")
    private Collection<Hunt> huntCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LOCATIONID")
    private Integer locationid;
    @Size(max = 255)
    @Column(name = "WINTITLE")
    private String wintitle;
    @Size(max = 255)
    @Column(name = "WINDESCRIPTION")
    private String windescription;
    @JoinTable(name = "J_LOCATION_CLUE", joinColumns = {
        @JoinColumn(name = "LOCATIONID", referencedColumnName = "LOCATIONID")}, inverseJoinColumns = {
        @JoinColumn(name = "CLUEID", referencedColumnName = "CLUEID")})
    @ManyToMany
    private Collection<Clue> clueCollection;
    @JoinColumn(name = "BEACONID", referencedColumnName = "BEACONID")
    @ManyToOne
    private Beacon beaconid;

    public Location() {
    }

    public Location(Integer locationid) {
        this.locationid = locationid;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public String getWintitle() {
        return wintitle;
    }

    public void setWintitle(String wintitle) {
        this.wintitle = wintitle;
    }

    public String getWindescription() {
        return windescription;
    }

    public void setWindescription(String windescription) {
        this.windescription = windescription;
    }

    @XmlTransient
    public Collection<Clue> getClueCollection() {
        return clueCollection;
    }

    public void setClueCollection(Collection<Clue> clueCollection) {
        this.clueCollection = clueCollection;
    }

    public Beacon getBeaconid() {
        return beaconid;
    }

    public void setBeaconid(Beacon beaconid) {
        this.beaconid = beaconid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationid != null ? locationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationid == null && other.locationid != null) || (this.locationid != null && !this.locationid.equals(other.locationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SwampPackage.Location[ locationid=" + locationid + " ]";
    }

    @XmlTransient
    public Collection<Hunt> getHuntCollection() {
        return huntCollection;
    }

    public void setHuntCollection(Collection<Hunt> huntCollection) {
        this.huntCollection = huntCollection;
    }
    
}
