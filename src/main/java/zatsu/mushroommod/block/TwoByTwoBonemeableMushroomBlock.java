package zatsu.mushroommod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import zatsu.mushroommod.block.voxelshapes.MushroomBlock;
import net.minecraft.world.level.block.BonemealableBlock;

/**
 * Mushroom block that requires a 2x2 pattern (like dark oak saplings) to grow into a huge mushroom.
 */
public class TwoByTwoBonemeableMushroomBlock extends MushroomBlock implements BonemealableBlock
{
    private final ResourceKey<ConfiguredFeature<?, ?>> featureKey;

    public TwoByTwoBonemeableMushroomBlock(BlockBehaviour.Properties settings, ResourceKey<ConfiguredFeature<?, ?>> featureKey)
    {
        super(settings);
        this.featureKey = featureKey;
    }

    @Override
    public boolean isValidBonemealTarget(net.minecraft.world.level.LevelReader level, BlockPos pos, BlockState state)
    {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state)
    {
        // 40% success rate like vanilla mushrooms
        return random.nextFloat() < 0.4;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state)
    {
        // Check for 2x2 pattern
        // The bonemeal can be applied to any of the 4 mushrooms
        // We need to find the northwest corner of the 2x2 pattern
        
        BlockPos northwestCorner = findNorthwestCorner(level, pos, state.getBlock());
        
        if (northwestCorner == null)
        {
            // Not in a valid 2x2 pattern, bonemeal fails silently
            return;
        }

        // Get the configured feature from the registry
        var lookupResult = level.registryAccess().lookup(Registries.CONFIGURED_FEATURE);

        if (lookupResult.isEmpty())
        {
            return;
        }

        var registry = lookupResult.get();
        var holderResult = registry.get(featureKey);

        if (holderResult.isEmpty())
        {
            return;
        }

        // Store the 4 mushroom states in case we need to restore them
        BlockState[] mushroomStates = new BlockState[4];
        BlockPos[] positions = {
            northwestCorner,
            northwestCorner.east(),
            northwestCorner.south(),
            northwestCorner.east().south()
        };

        for (int i = 0; i < 4; i++)
        {
            mushroomStates[i] = level.getBlockState(positions[i]);
        }

        // Remove all 4 mushrooms
        for (BlockPos position : positions)
        {
            level.removeBlock(position, false);
        }

        // Try to place the huge mushroom feature at the northwest corner
        if (!holderResult.get().value().place(level, level.getChunkSource().getGenerator(), random, northwestCorner))
        {
            // If feature placement fails, restore all 4 mushrooms
            for (int i = 0; i < 4; i++)
            {
                level.setBlock(positions[i], mushroomStates[i], 3);
            }
        }
    }

    /**
     * Finds the northwest corner of a 2x2 pattern of the same mushroom type.
     * Returns null if the mushroom at pos is not part of a valid 2x2 pattern.
     */
    private BlockPos findNorthwestCorner(ServerLevel level, BlockPos pos, Block mushroomType)
    {
        // Check all 4 possible positions where this mushroom could be part of a 2x2
        BlockPos[] possibleCorners = {
            pos,                        // This is NW corner
            pos.west(),                  // This is NE corner
            pos.north(),                 // This is SW corner
            pos.west().north()           // This is SE corner
        };

        for (BlockPos corner : possibleCorners)
        {
            if (is2x2Pattern(level, corner, mushroomType))
            {
                return corner;
            }
        }

        return null;
    }

    /**
     * Checks if there's a valid 2x2 pattern starting at the given northwest corner.
     */
    private boolean is2x2Pattern(ServerLevel level, BlockPos northwestCorner, Block mushroomType)
    {
        BlockPos[] positions = {
            northwestCorner,
            northwestCorner.east(),
            northwestCorner.south(),
            northwestCorner.east().south()
        };

        for (BlockPos position : positions)
        {
            BlockState state = level.getBlockState(position);
            if (!state.is(mushroomType))
            {
                return false;
            }
        }

        return true;
    }
}
