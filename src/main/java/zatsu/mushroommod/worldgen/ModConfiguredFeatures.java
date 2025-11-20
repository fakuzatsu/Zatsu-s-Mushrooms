package zatsu.mushroommod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import zatsu.mushroommod.ZatsusMushroomMod;

public class ModConfiguredFeatures
{
    // Resource key for our custom huge mushrooms
    // Configured in: data/mushroommod/worldgen/configured_feature/huge_skinny_brown_mushroom.json
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_BUTTON_BROWN_MUSHROOM = 
        ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(ZatsusMushroomMod.MOD_ID, "huge_button_brown_mushroom")
        );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_BUTTON_RED_MUSHROOM = 
        ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(ZatsusMushroomMod.MOD_ID, "huge_button_red_mushroom")
        );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_CURVED_RED_MUSHROOM = 
        ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(ZatsusMushroomMod.MOD_ID, "huge_curved_red_mushroom")
        );

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_SKINNY_BROWN_MUSHROOM = 
        ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(ZatsusMushroomMod.MOD_ID, "huge_skinny_brown_mushroom")
        );

    public static void init()
    {
        ZatsusMushroomMod.LOGGER.info("Initializing mod configured features");
    }
}
