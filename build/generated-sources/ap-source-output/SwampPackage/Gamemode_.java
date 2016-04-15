package SwampPackage;

import SwampPackage.Hunt;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-15T14:27:57")
@StaticMetamodel(Gamemode.class)
public class Gamemode_ { 

    public static volatile SingularAttribute<Gamemode, String> title;
    public static volatile SingularAttribute<Gamemode, String> description;
    public static volatile SingularAttribute<Gamemode, Integer> gamemodeid;
    public static volatile CollectionAttribute<Gamemode, Hunt> huntCollection;

}