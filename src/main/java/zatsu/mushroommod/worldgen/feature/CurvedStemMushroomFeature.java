package zatsu.mushroommod.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

/**
 * Custom feature for mushrooms with gently curved stems.
 * The stem has a subtle diagonal lean (1-2 blocks offset).
 */
public class CurvedStemMushroomFeature extends Feature<HugeMushroomFeatureConfiguration>
{
    public CurvedStemMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec)
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

        // Choose random height between 5-8 blocks
        int height = 5 + random.nextInt(4);

        // Basic space check
        if (pos.getY() + height + 3 > level.getHeight())
        {
            return false;
        }

        // Place the curved stem
        BlockState stemState = config.stemProvider.getState(random, pos);
        
        // Choose random lean direction (N, S, E, W)
        Direction leanDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        
        // Random variation: curve at y=1 or y=2 (1 or 2 blocks before curving)
        int curveHeight = 1 + random.nextInt(2); // Either 1 or 2
        
        BlockPos currentPos = pos;
        
        // Place blocks straight up until curve point
        for (int y = 0; y <= curveHeight; y++)
        {
            setBlock(level, currentPos, stemState);
            if (y < curveHeight)
            {
                currentPos = currentPos.above();
            }
        }
        
        // Now shift 1 block in the chosen direction
        BlockPos shiftedPos = currentPos.relative(leanDirection);
        
        // Place diagonal connector block to make stem continuous
        currentPos = currentPos.above();
        setBlock(level, currentPos, stemState); // connector at original x,z
        
        // Move to shifted position and continue
        currentPos = shiftedPos.above();
        setBlock(level, currentPos, stemState); // at shifted x,z
        
        // Continue straight up from shifted position for remaining height
        int startY = curveHeight + 2; // Account for curve blocks
        for (int y = startY; y < height; y++)
        {
            currentPos = currentPos.above();
            setBlock(level, currentPos, stemState);
        }

        // Place cap on top of the stem
        BlockPos capBasePos = currentPos;
        placeMushroomCap(level, capBasePos, random, config);

        return true;
    }

    private void placeMushroomCap(WorldGenLevel level, BlockPos pos, RandomSource random, HugeMushroomFeatureConfiguration config)
    {
        BlockState capState = config.capProvider.getState(random, pos);
        int radius = 2; // Standard mushroom cap radius

        // Classic mushroom cap shape (dome) - only 2 layers
        for (int y = 0; y <= 1; y++)
        {
            for (int x = -radius; x <= radius; x++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    // Skip corners and create rounded cap
                    if (Math.abs(x) == radius && Math.abs(z) == radius)
                    {
                        continue; // Skip corner blocks
                    }

                    // Top layer is smaller
                    if (y == 1 && (Math.abs(x) >= radius || Math.abs(z) >= radius))
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
