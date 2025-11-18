package zatsu.mushroommod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class PlacementBehaviors
{
    public static final BlockPlacementBehavior MUSHROOM_PLACEMENT = (block, ctx) ->
    {
        Level level = ctx.getLevel();
        BlockPos placePos = ctx.getClickedPos();
        var clickedState = level.getBlockState(placePos);
        if (!clickedState.canBeReplaced())
        {
            placePos = placePos.relative(ctx.getClickedFace());
        }

        if (!level.getBlockState(placePos).canBeReplaced()) return InteractionResult.FAIL;

        BlockPos below = placePos.below();
        var stateBelow = level.getBlockState(below);
        boolean validBelow = stateBelow.is(BlockTags.MUSHROOM_GROW_BLOCK)
                          || stateBelow.is(Blocks.GRASS_BLOCK)
                          || stateBelow.is(Blocks.DIRT)
                          || stateBelow.is(Blocks.PODZOL)
                          || stateBelow.is(Blocks.MYCELIUM)
                          || stateBelow.is(Blocks.NETHERRACK);

        if (!validBelow) return InteractionResult.FAIL;

        // Client-side: just tell client it *could* succeed
        if (level.isClientSide()) return InteractionResult.SUCCESS;

        // Server-side: place the block
        var toPlace = block.getStateForPlacement(new BlockPlaceContext(ctx));
        if (toPlace == null) return InteractionResult.FAIL;

        level.setBlock(placePos, toPlace, 3);

        // Play placement sound
        var sound = toPlace.getSoundType();
        level.playSound(
                null,
                placePos,
                sound.getPlaceSound(),
                net.minecraft.sounds.SoundSource.BLOCKS,
                (sound.getVolume() + 1f) / 2f,
                sound.getPitch() * 0.8f
        );

        // Consume item
        Player player = ctx.getPlayer();
        if (player != null && !player.getAbilities().instabuild)
        {
            ctx.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    };
}
