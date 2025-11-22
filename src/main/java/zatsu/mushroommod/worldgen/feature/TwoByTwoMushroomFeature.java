package zatsu.mushroommod.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

/**
 * Custom feature for 2x2 huge mushrooms with a stout/dwarf appearance.
 * Short and stout with a 2x2 stem base and two block thick cap.
 */
public class TwoByTwoMushroomFeature extends Feature<HugeMushroomFeatureConfiguration>
{
    public TwoByTwoMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec)
    {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<HugeMushroomFeatureConfiguration> context)
    {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        HugeMushroomFeatureConfiguration config = context.config();

        // Short height: 4-6 blocks (stout like a dwarf)
        int height = 4 + random.nextInt(3);

        // Check if there's enough space for 2x2 base + cap
        if (!hasEnoughSpace(level, pos, height))
        {
            return false;
        }

        // Place 2x2 stem base
        BlockState stemState = config.stemProvider.getState(random, pos);
        
        for (int y = 0; y < height; y++)
        {
            BlockPos basePos = pos.above(y);
            
            // 2x2 stem blocks
            setBlock(level, basePos, stemState);
            setBlock(level, basePos.east(), stemState);
            setBlock(level, basePos.south(), stemState);
            setBlock(level, basePos.east().south(), stemState);
        }

        // Place TWO block thick cap on top
        BlockPos capBasePos = pos.above(height);
        placeThickMushroomCap(level, capBasePos, random, config);

        return true;
    }

    private boolean hasEnoughSpace(WorldGenLevel level, BlockPos pos, int height)
    {
        // Check space for 2x2 base + cap area (radius 3)
        for (int y = 0; y <= height + 3; y++)
        {
            for (int x = -3; x <= 4; x++)
            {
                for (int z = -3; z <= 4; z++)
                {
                    BlockPos checkPos = pos.offset(x, y, z);
                    
                    // Check if position is replaceable
                    if (y < height)
                    {
                        // In stem area, only check 2x2 footprint
                        if (x >= 0 && x <= 1 && z >= 0 && z <= 1)
                        {
                            if (!level.isEmptyBlock(checkPos) && !level.getBlockState(checkPos).canBeReplaced())
                            {
                                return false;
                            }
                        }
                    }
                    else
                    {
                        // In cap area, check wider radius
                        if (!level.isEmptyBlock(checkPos) && !level.getBlockState(checkPos).canBeReplaced())
                        {
                            // Allow some non-replaceable blocks, but not in critical path
                            if (Math.abs(x) <= 2 && Math.abs(z) <= 2)
                            {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private void placeThickMushroomCap(WorldGenLevel level, BlockPos pos, RandomSource random, HugeMushroomFeatureConfiguration config)
    {
        BlockState capState = config.capProvider.getState(random, pos);
        int radius = 3; // Same as vanilla huge brown mushroom

        // TWO layers of cap (y = 0 and y = 1) - thick cap
        for (int y = 0; y <= 1; y++)
        {
            for (int x = -radius; x <= radius; x++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    // Calculate distance from center (considering 2x2 center)
                    double distX = x < 0 ? -x : (x > 1 ? x - 1 : 0);
                    double distZ = z < 0 ? -z : (z > 1 ? z - 1 : 0);
                    double dist = Math.max(distX, distZ);

                    // Skip far corners to create rounded cap
                    if (dist > radius - 0.5)
                    {
                        continue;
                    }

                    // Second layer (top) is slightly smaller
                    if (y == 1 && dist >= radius - 0.5)
                    {
                        continue;
                    }

                    BlockPos capPos = pos.offset(x, y, z);
                    setBlock(level, capPos, capState);
                }
            }
        }
    }
}
