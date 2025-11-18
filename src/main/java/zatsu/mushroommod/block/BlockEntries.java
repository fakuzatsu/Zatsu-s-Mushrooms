package zatsu.mushroommod.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;

public enum BlockEntries 
{
    MUSHROOM_BLOCK_BUTTON_BROWN(
        "button_brown_block",
        BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM),
        false,
        RenderLayerType.CUTOUT
    ),
    MUSHROOM_BLOCK_BUTTON_RED(
        "button_red_block",
        BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM),
        false,
        RenderLayerType.CUTOUT
    ),
    MUSHROOM_BLOCK_CURVED_RED(
        "curved_red_block",
        BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM),
        false,
        RenderLayerType.CUTOUT
    ),
    MUSHROOM_BLOCK_SKINNY_BROWN(
        "skinny_brown_block",
        BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM),
        false,
        RenderLayerType.CUTOUT
    );

    public final String id;
    public final BlockBehaviour.Properties properties;
    public final boolean createBlockItem;
    public final RenderLayerType renderLayer;

    BlockEntries(String id, 
        BlockBehaviour.Properties properties, 
        boolean createBlockItem, 
        RenderLayerType renderLayer)
    {
        this.id = id;
        this.properties = properties;
        this.createBlockItem = createBlockItem;
        this.renderLayer = renderLayer;
    }

    public enum RenderLayerType {
        NONE,
        CUTOUT,
        TRANSLUCENT
    }
}