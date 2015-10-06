package iceman11a.fuelcraft.reference;


public class ReferenceNames
{
    public static final String NAME_FLUID_DIESEL                    = "diesel";

    public static final String NAME_TILE_ENTITY_DIESEL_PRODUCER     = "dieselproducer";
    public static final String NAME_TILE_CORBAMITORE                = "corbamiteore";
    public static final String NAME_TILE_CORCOALORE                 = "corcoalore";

    public static final String FORESTBIOMENAME                      = "Blue light Forest";

    public static String getPrefixedName(String name)
    {
        return Reference.MOD_ID + "." + name;
    }
}
