package zatsu.mushroommod.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;

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
    public VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return SHAPE;
    }
}
