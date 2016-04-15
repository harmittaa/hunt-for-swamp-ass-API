package SwampPackage;

import SwampPackage.Beacon;
import SwampPackage.Clue;
import SwampPackage.Hunt;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-15T14:27:57")
@StaticMetamodel(Location.class)
public class Location_ { 

    public static volatile SingularAttribute<Location, Beacon> beaconid;
    public static volatile SingularAttribute<Location, Integer> locationid;
    public static volatile CollectionAttribute<Location, Hunt> huntCollection;
    public static volatile SingularAttribute<Location, String> wintitle;
    public static volatile SingularAttribute<Location, String> windescription;
    public static volatile CollectionAttribute<Location, Clue> clueCollection;

}