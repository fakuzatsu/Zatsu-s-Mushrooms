package zatsu.mushroommod.block;

import java.util.List;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;

public enum BlockEntries 
{
    MUSHROOM_BUTTON_BROWN(
        "button_brown",
        BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM),
        false,
        RenderLayerType.CUTOUT,
        CustomBlockSize.MUSHROOM,
        List.of("mushroommod:button_brown")
    ),
    MUSHROOM_BUTTON_RED(
        "button_red",
        BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM),
        false,
        RenderLayerType.CUTOUT,
        CustomBlockSize.MUSHROOM,
        List.of("mushroommod:button_red")
    ),
    MUSHROOM_CURVED_RED(
        "curved_red",
        BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM),
        false,
        RenderLayerType.CUTOUT,
        CustomBlockSize.MUSHROOM,
        List.of("mushroommod:curved_red")
    ),
    MUSHROOM_SKINNY_BROWN(
        "skinny_brown",
        BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM),
        false,
        RenderLayerType.CUTOUT,
        CustomBlockSize.MUSHROOM,
        List.of("mushroommod:skinny_brown")
    ),
    MUSHROOM_BLOCK_SKINNY_BROWN(
        "skinny_brown_block",
        BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK),
        false,
        null,
        null,
        List.of("mushroommod:skinny_brown")
    );

    public final String id;
    public final BlockBehaviour.Properties properties;
    public final boolean createBlockItem;
    public final RenderLayerType renderLayer;
    public final CustomBlockSize blockSize;
    public final List<String> drops;

    BlockEntries(String id, 
        BlockBehaviour.Properties properties, 
        boolean createBlockItem, 
        RenderLayerType renderLayer,
        CustomBlockSize blockSize,
        List<String> drops)
    {
        this.id = id;
        this.properties = properties;
        this.createBlockItem = createBlockItem;
        this.renderLayer = renderLayer;
        this.blockSize = blockSize;
        this.drops = drops;
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