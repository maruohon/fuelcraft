package iceman11a.fuelcraft.reference;


public class ReferenceNames {

	public static final String NAME_FLUID_OIL						= "oil";
	public static final String NAME_FLUID_DIESEL					= "diesel";

	public static final String NAME_TILE_CORBAMITORE                = "corbamiteore";
	public static final String NAME_TILE_CORCOALORE                 = "corcoalore";
	public static final String NAME_TILE_MACHINES					= "machine0";
	public static final String NAME_TILE_CART_PAINTER				= "cartpainter";
	public static final String NAME_TILE_DIESEL_PRODUCER			= "dieselproducer";
	public static final String NAME_TILE_TOKEN_CONTROLLER           = "tokencontroller";
	
	public static final String NAME_ENTITY_DIESEL_TRAIN_ENGINE      = "dieseltrainengine";

	public static final String FORESTBIOMENAME						= "Blue light Forest";

	public static String getPrefixedName(String name) {
		return Reference.MOD_ID + "." + name;
	}
}
