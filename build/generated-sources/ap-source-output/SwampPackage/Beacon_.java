package SwampPackage;

import SwampPackage.Location;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-15T14:27:57")
@StaticMetamodel(Beacon.class)
public class Beacon_ { 

    public static volatile SingularAttribute<Beacon, Integer> beaconid;
    public static volatile SingularAttribute<Beacon, String> minor;
    public static volatile CollectionAttribute<Beacon, Location> locationCollection;
    public static volatile SingularAttribute<Beacon, String> uuid;
    public static volatile SingularAttribute<Beacon, String> major;

}