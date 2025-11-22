package zatsu.mushroommod.block.voxelshapes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MushroomBlock extends Block
{
    private static final VoxelShape SHAPE =
        Block.box(4, 0, 4, // x1,y1,z1
            12, 8, 12); // x2,y2,z2

    public MushroomBlock(BlockBehaviour.Properties settings)
    {
        super(settings);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return SHAPE;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        BlockPos below = pos.below();
        BlockState stateBelow = level.getBlockState(below);
        
        // Check if the block below is a valid surface for mushrooms
        return stateBelow.is(BlockTags.MUSHROOM_GROW_BLOCK)
            || stateBelow.is(Blocks.GRASS_BLOCK)
            || stateBelow.is(Blocks.DIRT)
            || stateBelow.is(Blocks.PODZOL)
            || stateBelow.is(Blocks.MYCELIUM)
            || stateBelow.is(Blocks.NETHERRACK);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston)
    {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        if (!level.isClientSide() && !this.canSurvive(state, level, pos))
        {
            level.destroyBlock(pos, true);
        }
    }
    
    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader world, 
            net.minecraft.world.level.ScheduledTickAccess tickView, 
            BlockPos pos, 
            Direction direction, 
            BlockPos neighborPos, 
            BlockState neighborState, 
            net.minecraft.util.RandomSource random)
    {
        // If we can't survive, return air to break the block
        if (!this.canSurvive(state, world, pos))
        {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }
}
