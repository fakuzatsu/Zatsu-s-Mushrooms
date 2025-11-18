package zatsu.mushroommod.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import zatsu.mushroommod.block.BlockPlacementBehavior;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public class CustomPlaceableItem extends BlockItem
{
    private final BlockPlacementBehavior behavior;

    public CustomPlaceableItem(Block block, BlockPlacementBehavior behavior, Properties props)
    {
        super(block, props);
        this.behavior = behavior;
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx)
    {
        // If no behavior, fallback to vanilla block placement
        if (behavior == null)
            return super.useOn(ctx);

        return behavior.place(getBlock(), ctx);
    }
}
