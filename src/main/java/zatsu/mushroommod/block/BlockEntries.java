package zatsu.mushroommod.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;

public enum BlockEntries 
{
    MUSHROOM_BLOCK_BUTTON_BROWN("button_brown_block",
            BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM),
            false
    );

    public final String id;
    public final BlockBehaviour.Properties properties;
    public final boolean createBlockItem;

    BlockEntries(String id, BlockBehaviour.Properties properties, boolean createBlockItem) {
        this.id = id;
        this.properties = properties;
        this.createBlockItem = createBlockItem;
    }
}