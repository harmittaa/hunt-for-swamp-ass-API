package SwampPackage;

import SwampPackage.Location;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-15T14:27:57")
@StaticMetamodel(Clue.class)
public class Clue_ { 

    public static volatile SingularAttribute<Clue, Integer> clueid;
    public static volatile SingularAttribute<Clue, String> cluetitle;
    public static volatile CollectionAttribute<Clue, Location> locationCollection;
    public static volatile SingularAttribute<Clue, String> cluedescription;

}