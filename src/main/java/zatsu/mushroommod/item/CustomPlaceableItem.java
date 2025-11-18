package zatsu.mushroommod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import zatsu.mushroommod.block.BlockPlacementBehavior;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public class CustomPlaceableItem extends Item
{
    private final Block block;
    private final BlockPlacementBehavior behavior;

    public CustomPlaceableItem(Block block, BlockPlacementBehavior behavior, Properties props)
    {
        super(props);
        this.block = block;
        this.behavior = behavior;
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx)
    {
        return behavior.place(block, ctx);
    }
}