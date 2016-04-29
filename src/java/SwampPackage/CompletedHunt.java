/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwampPackage;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iosdev
 */
@Entity
@Table(name = "COMPLETED_HUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompletedHunt.findAll", query = "SELECT c FROM CompletedHunt c"),
    @NamedQuery(name = "CompletedHunt.findByPoints", query = "SELECT c FROM CompletedHunt c WHERE c.points = :points"),
    @NamedQuery(name = "CompletedHunt.findByCompid", query = "SELECT c FROM CompletedHunt c WHERE c.compid = :compid")})
public class CompletedHunt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "POINTS")
    private Integer points;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COMPID")
    private Integer compid;
    @JoinColumn(name = "HUNTID", referencedColumnName = "HUNTID")
    @ManyToOne
    private Hunt huntid;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne
    private User userid;

    public CompletedHunt() {
    }

    public CompletedHunt(Integer compid) {
        this.compid = compid;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getCompid() {
        return compid;
    }

    public void setCompid(Integer compid) {
        this.compid = compid;
    }

    public Hunt getHuntid() {
        return huntid;
    }

    public void setHuntid(Hunt huntid) {
        this.huntid = huntid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compid != null ? compid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompletedHunt)) {
            return false;
        }
        CompletedHunt other = (CompletedHunt) object;
        if ((this.compid == null && other.compid != null) || (this.compid != null && !this.compid.equals(other.compid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SwampPackage.CompletedHunt[ compid=" + compid + " ]";
    }
    
}
