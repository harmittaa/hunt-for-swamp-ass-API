package SwampPackage;

import SwampPackage.Gamemode;
import SwampPackage.Location;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-15T14:27:57")
@StaticMetamodel(Hunt.class)
public class Hunt_ { 

    public static volatile SingularAttribute<Hunt, String> title;
    public static volatile SingularAttribute<Hunt, String> description;
    public static volatile CollectionAttribute<Hunt, Gamemode> gamemodeCollection;
    public static volatile SingularAttribute<Hunt, Integer> huntid;
    public static volatile SingularAttribute<Hunt, String> wintitle;
    public static volatile CollectionAttribute<Hunt, Location> locationCollection;
    public static volatile SingularAttribute<Hunt, String> windescription;

}