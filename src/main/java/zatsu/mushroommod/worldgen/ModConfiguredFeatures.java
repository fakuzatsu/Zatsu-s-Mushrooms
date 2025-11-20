package zatsu.mushroommod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import zatsu.mushroommod.ZatsusMushroomMod;
import zatsu.mushroommod.block.BlockEntries;
import zatsu.mushroommod.block.BlockRegistration;

public class ModConfiguredFeatures
{
    // Registry for custom world generation configured features.

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_SKINNY_BROWN_MUSHROOM = 
        ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(ZatsusMushroomMod.MOD_ID, "huge_skinny_brown_mushroom")
        );

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context)
    {
        // Register the huge skinny brown mushroom feature
        context.register(
            HUGE_SKINNY_BROWN_MUSHROOM,
            new ConfiguredFeature<>(
                Feature.HUGE_BROWN_MUSHROOM,
                new HugeMushroomFeatureConfiguration(
                    // Cap provider - use our custom mushroom head block
                    BlockStateProvider.simple(
                        BlockRegistration.getBlock(BlockEntries.MUSHROOM_BLOCK_SKINNY_BROWN)
                            .defaultBlockState()
                            .setValue(HugeMushroomBlock.NORTH, true)
                            .setValue(HugeMushroomBlock.EAST, true)
                            .setValue(HugeMushroomBlock.SOUTH, true)
                            .setValue(HugeMushroomBlock.WEST, true)
                            .setValue(HugeMushroomBlock.UP, true)
                            .setValue(HugeMushroomBlock.DOWN, false)
                    ),
                    // Stem provider - use vanilla mushroom stem
                    BlockStateProvider.simple(
                        Blocks.MUSHROOM_STEM.defaultBlockState()
                            .setValue(HugeMushroomBlock.NORTH, false)
                            .setValue(HugeMushroomBlock.EAST, false)
                            .setValue(HugeMushroomBlock.SOUTH, false)
                            .setValue(HugeMushroomBlock.WEST, false)
                            .setValue(HugeMushroomBlock.UP, false)
                            .setValue(HugeMushroomBlock.DOWN, false)
                    ),
                    // Foliage radius - controls mushroom cap size (3 is vanilla)
                    3
                )
            )
        );
    }

    public static void init()
    {
        ZatsusMushroomMod.LOGGER.info("Initializing mod configured features");
    }
}
