package zatsu.mushroommod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;

public class PlacementBehaviors
{
    public static final BlockPlacementBehavior MUSHROOM_PLACEMENT = (block, ctx) ->
    {
        Level level = ctx.getLevel();
        if (level.isClientSide()) return InteractionResult.SUCCESS;

        BlockPos placePos = ctx.getClickedPos().relative(ctx.getClickedFace());

        if (!level.getBlockState(placePos).canBeReplaced())
            return InteractionResult.FAIL;

        BlockPos below = placePos.below();
        var state = level.getBlockState(below);

        // Vanilla mushroom rules
        boolean valid = state.is(net.minecraft.tags.BlockTags.MUSHROOM_GROW_BLOCK)
                || state.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)
                || state.is(net.minecraft.world.level.block.Blocks.DIRT)
                || state.is(net.minecraft.world.level.block.Blocks.PODZOL)
                || state.is(net.minecraft.world.level.block.Blocks.MYCELIUM)
                || state.is(net.minecraft.world.level.block.Blocks.NETHERRACK);

        if (!valid) return InteractionResult.FAIL;

        level.setBlock(placePos, block.defaultBlockState(), 3);

        // consume item
        if (ctx.getPlayer() != null && !ctx.getPlayer().getAbilities().instabuild)
            ctx.getItemInHand().shrink(1);

        return InteractionResult.SUCCESS;
    };
}
