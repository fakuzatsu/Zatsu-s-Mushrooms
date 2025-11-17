package zatsu.mushroommod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZatsusMushroomMod implements ModInitializer 
{
	public static final String MOD_ID = "mushroommod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() 
	{
		LOGGER.info("Hello Fabric world!");

		// Force item class loading (optional in many cases, but good practice early on)
		ModItemRegistration.init();
		ModItemGroups.init();
		ModItemProperties.init();
	}

}