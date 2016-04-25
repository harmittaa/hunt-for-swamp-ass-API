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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author iosdev
 */
@Entity
@Table(name = "HUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hunt.findAll", query = "SELECT h FROM Hunt h"),
    @NamedQuery(name = "Hunt.findByHuntid", query = "SELECT h FROM Hunt h WHERE h.huntid = :huntid"),
    @NamedQuery(name = "Hunt.findByTitle", query = "SELECT h FROM Hunt h WHERE h.title = :title"),
    @NamedQuery(name = "Hunt.findByWindescription", query = "SELECT h FROM Hunt h WHERE h.windescription = :windescription"),
    @NamedQuery(name = "Hunt.findByWintitle", query = "SELECT h FROM Hunt h WHERE h.wintitle = :wintitle"),
    @NamedQuery(name = "Hunt.findByDescription", query = "SELECT h FROM Hunt h WHERE h.description = :description")})
public class Hunt implements Serializable {

    @Size(max = 255)
    @Column(name = "MEDIA")
    private String media;

    @JoinTable(name = "J_HUNT_LOCATION", joinColumns = {
        @JoinColumn(name = "HUNTID", referencedColumnName = "HUNTID")}, inverseJoinColumns = {
        @JoinColumn(name = "LOCATIONID", referencedColumnName = "LOCATIONID")})
    @ManyToMany
    private Collection<Location> locationCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HUNTID")
    private Integer huntid;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 255)
    @Column(name = "WINDESCRIPTION")
    private String windescription;
    @Size(max = 255)
    @Column(name = "WINTITLE")
    private String wintitle;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinTable(name = "J_GAMEMODE_HUNT", joinColumns = {
        @JoinColumn(name = "HUNTID", referencedColumnName = "HUNTID")}, inverseJoinColumns = {
        @JoinColumn(name = "GAMEMODEID", referencedColumnName = "GAMEMODEID")})
    @ManyToMany
    private Collection<Gamemode> gamemodeCollection;

    public Hunt() {
    }

    public Hunt(Integer huntid) {
        this.huntid = huntid;
    }

    public Integer getHuntid() {
        return huntid;
    }

    public void setHuntid(Integer huntid) {
        this.huntid = huntid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWindescription() {
        return windescription;
    }

    public void setWindescription(String windescription) {
        this.windescription = windescription;
    }

    public String getWintitle() {
        return wintitle;
    }

    public void setWintitle(String wintitle) {
        this.wintitle = wintitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Gamemode> getGamemodeCollection() {
        return gamemodeCollection;
    }

    public void setGamemodeCollection(Collection<Gamemode> gamemodeCollection) {
        this.gamemodeCollection = gamemodeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (huntid != null ? huntid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hunt)) {
            return false;
        }
        Hunt other = (Hunt) object;
        if ((this.huntid == null && other.huntid != null) || (this.huntid != null && !this.huntid.equals(other.huntid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SwampPackage.Hunt[ huntid=" + huntid + " ]";
    }

    @XmlTransient
    public Collection<Location> getLocationCollection() {
        return locationCollection;
    }

    public void setLocationCollection(Collection<Location> locationCollection) {
        this.locationCollection = locationCollection;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
    
}
