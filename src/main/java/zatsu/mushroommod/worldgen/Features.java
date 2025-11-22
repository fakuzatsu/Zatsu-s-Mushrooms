package zatsu.mushroommod.worldgen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import zatsu.mushroommod.ZatsusMushroomMod;
import zatsu.mushroommod.worldgen.feature.CurvedStemMushroomFeature;
import zatsu.mushroommod.worldgen.feature.TwoByTwoMushroomFeature;

public class Features
{
    // Registers world generation features for custom huge mushrooms
    public static final Feature<HugeMushroomFeatureConfiguration> CURVED_STEM_MUSHROOM = 
        new CurvedStemMushroomFeature(HugeMushroomFeatureConfiguration.CODEC);
    
    public static final Feature<HugeMushroomFeatureConfiguration> TWO_BY_TWO_MUSHROOM = 
        new TwoByTwoMushroomFeature(HugeMushroomFeatureConfiguration.CODEC);

    public static void init()
    {
        // Curved stem mushroom
        Registry.register(
            BuiltInRegistries.FEATURE,
            ResourceLocation.fromNamespaceAndPath(ZatsusMushroomMod.MOD_ID, "curved_stem_mushroom"),
            CURVED_STEM_MUSHROOM
        );

        // 2x2 mushroom
        Registry.register(
            BuiltInRegistries.FEATURE,
            ResourceLocation.fromNamespaceAndPath(ZatsusMushroomMod.MOD_ID, "two_by_two_mushroom"),
            TWO_BY_TWO_MUSHROOM
        );

        ZatsusMushroomMod.LOGGER.info("Registered custom mushroom features");
    }
}
