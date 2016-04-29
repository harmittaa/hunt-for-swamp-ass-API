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
@Table(name = "USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserid", query = "SELECT u FROM User u WHERE u.userid = :userid"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByUserpass", query = "SELECT u FROM User u WHERE u.userpass = :userpass"),
    @NamedQuery(name = "User.findByUserdescription", query = "SELECT u FROM User u WHERE u.userdescription = :userdescription"),
    @NamedQuery(name = "User.findByUsermedia", query = "SELECT u FROM User u WHERE u.usermedia = :usermedia")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USERID")
    private Integer userid;
    @Size(max = 255)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 255)
    @Column(name = "USERPASS")
    private String userpass;
    @Size(max = 255)
    @Column(name = "USERDESCRIPTION")
    private String userdescription;
    @Size(max = 255)
    @Column(name = "USERMEDIA")
    private String usermedia;
    @OneToMany(mappedBy = "userid")
    private Collection<CompletedHunt> completedHuntCollection;

    public User() {
    }

    public User(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getUserdescription() {
        return userdescription;
    }

    public void setUserdescription(String userdescription) {
        this.userdescription = userdescription;
    }

    public String getUsermedia() {
        return usermedia;
    }

    public void setUsermedia(String usermedia) {
        this.usermedia = usermedia;
    }

    @XmlTransient
    public Collection<CompletedHunt> getCompletedHuntCollection() {
        return completedHuntCollection;
    }

    public void setCompletedHuntCollection(Collection<CompletedHunt> completedHuntCollection) {
        this.completedHuntCollection = completedHuntCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SwampPackage.User[ userid=" + userid + " ]";
    }
    
}
