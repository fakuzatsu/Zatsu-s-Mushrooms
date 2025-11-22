package zatsu.mushroommod;

import net.fabricmc.api.ModInitializer;
import zatsu.mushroommod.block.BlockRegistration;
import zatsu.mushroommod.item.ItemGroups;
import zatsu.mushroommod.item.ItemProperties;
import zatsu.mushroommod.item.ItemRegistration;
import zatsu.mushroommod.recipe.RecipeSerializers;
import zatsu.mushroommod.worldgen.ConfiguredFeatures;
import zatsu.mushroommod.worldgen.Features;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZatsusMushroomMod implements ModInitializer 
{
	public static final String MOD_ID = "mushroommod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() 
	{
		BlockRegistration.init();
		ItemRegistration.init();
		ItemGroups.init();
		ItemProperties.init();
		RecipeSerializers.init();
		ConfiguredFeatures.init();
		Features.init();
	}

}