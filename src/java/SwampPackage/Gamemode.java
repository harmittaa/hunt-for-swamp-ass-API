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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author iosdev
 */
@Entity
@Table(name = "GAMEMODE")
//XmlRootElement
@XmlRootElement(name = "gamemode")
@NamedQueries({
    @NamedQuery(name = "Gamemode.findAll", query = "SELECT g FROM Gamemode g"),
    @NamedQuery(name = "Gamemode.findByGamemodeid", query = "SELECT g FROM Gamemode g WHERE g.gamemodeid = :gamemodeid"),
    @NamedQuery(name = "Gamemode.findByTitle", query = "SELECT g FROM Gamemode g WHERE g.title = :title"),
    @NamedQuery(name = "Gamemode.findByDescription", query = "SELECT g FROM Gamemode g WHERE g.description = :description")})
public class Gamemode implements Serializable {

    @Size(max = 255)
    @Column(name = "MEDIA")
    private String media;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GAMEMODEID")
    private Integer gamemodeid;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToMany(mappedBy = "gamemodeCollection")
    private Collection<Hunt> huntCollection;

    public Gamemode() {
    }

    public Gamemode(Integer gamemodeid) {
        this.gamemodeid = gamemodeid;
    }

    public Integer getGamemodeid() {
        return gamemodeid;
    }

    public void setGamemodeid(Integer gamemodeid) {
        this.gamemodeid = gamemodeid;
    }
    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Hunt> getHuntCollection() {
        return huntCollection;
    }

    public void setHuntCollection(Collection<Hunt> huntCollection) {
        this.huntCollection = huntCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gamemodeid != null ? gamemodeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gamemode)) {
            return false;
        }
        Gamemode other = (Gamemode) object;
        if ((this.gamemodeid == null && other.gamemodeid != null) || (this.gamemodeid != null && !this.gamemodeid.equals(other.gamemodeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SwampPackage.Gamemode[ gamemodeid=" + gamemodeid + " ]";
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
    
}
