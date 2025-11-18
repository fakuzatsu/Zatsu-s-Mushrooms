package zatsu.mushroommod.block;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

// This interface has one method called 'place'
@FunctionalInterface
public interface BlockPlacementBehavior
{
    InteractionResult place(Block block, UseOnContext ctx);
}
