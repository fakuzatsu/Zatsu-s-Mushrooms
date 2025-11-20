package zatsu.mushroommod.block;

import java.util.List;

import net.minecraft.world.level.block.state.BlockBehaviour;
import zatsu.mushroommod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.resources.ResourceKey;

public enum BlockEntries 
{
    MUSHROOM_BUTTON_BROWN(
        "button_brown",
        BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM),
        false,
        RenderLayerType.CUTOUT,
        CustomBlockSize.MUSHROOM,
        List.of("mushroommod:button_brown"),
        ModConfiguredFeatures.HUGE_BUTTON_BROWN_MUSHROOM
    ),
    MUSHROOM_BUTTON_RED(
        "button_red",
        BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM),
        false,
        RenderLayerType.CUTOUT,
        CustomBlockSize.MUSHROOM,
        List.of("mushroommod:button_red"),
        ModConfiguredFeatures.HUGE_BUTTON_RED_MUSHROOM
    ),
    MUSHROOM_CURVED_RED(
        "curved_red",
        BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM),
        false,
        RenderLayerType.CUTOUT,
        CustomBlockSize.MUSHROOM,
        List.of("mushroommod:curved_red"),
        ModConfiguredFeatures.HUGE_CURVED_RED_MUSHROOM
    ),
    MUSHROOM_SKINNY_BROWN(
        "skinny_brown",
        BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM),
        false,
        RenderLayerType.CUTOUT,
        CustomBlockSize.MUSHROOM,
        List.of("mushroommod:skinny_brown"),
        ModConfiguredFeatures.HUGE_SKINNY_BROWN_MUSHROOM
    ),
    MUSHROOM_BLOCK_BUTTON_BROWN(
        "button_brown_block",
        BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK),
        false,
        null,
        null,
        List.of("mushroommod:button_brown"),
        null
    ),
    MUSHROOM_BLOCK_BUTTON_RED(
        "button_red_block",
        BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM_BLOCK),
        false,
        null,
        null,
        List.of("mushroommod:button_red"),
        null
    ),
    MUSHROOM_BLOCK_CURVED_RED(
        "curved_red_block",
        BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM_BLOCK),
        false,
        null,
        null,
        List.of("mushroommod:curved_red"),
        null
    ),
    MUSHROOM_BLOCK_SKINNY_BROWN(
        "skinny_brown_block",
        BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK),
        false,
        null,
        null,
        List.of("mushroommod:skinny_brown"),
        null
    );

    public final String id;
    public final BlockBehaviour.Properties properties;
    public final boolean createBlockItem;
    public final RenderLayerType renderLayer;
    public final CustomBlockSize blockSize;
    public final List<String> drops;
    public final ResourceKey<ConfiguredFeature<?, ?>> configuredFeature;

    BlockEntries(String id, 
        BlockBehaviour.Properties properties, 
        boolean createBlockItem, 
        RenderLayerType renderLayer,
        CustomBlockSize blockSize,
        List<String> drops,
        ResourceKey<ConfiguredFeature<?, ?>> configuredFeature)
    {
        this.id = id;
        this.properties = properties;
        this.createBlockItem = createBlockItem;
        this.renderLayer = renderLayer;
        this.blockSize = blockSize;
        this.drops = drops;
        this.configuredFeature = configuredFeature;
    }

    public enum RenderLayerType
    {
        NONE,
        CUTOUT,
        TRANSLUCENT
    }

    public enum CustomBlockSize
    {
        NONE,
        MUSHROOM,
        BONEMEALABLE_MUSHROOM
    }
}