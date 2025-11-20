package zatsu.mushroommod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import zatsu.mushroommod.block.voxelshapes.MushroomBlock;

public class BonemeableMushroomBlock extends MushroomBlock implements BonemealableBlock
{
    private final ResourceKey<ConfiguredFeature<?, ?>> featureKey;

    public BonemeableMushroomBlock(BlockBehaviour.Properties settings, ResourceKey<ConfiguredFeature<?, ?>> featureKey)
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

        // Remove the small mushroom
        level.removeBlock(pos, false);

        // Try to place the huge mushroom feature
        if (!holderResult.get().value().place(level, level.getChunkSource().getGenerator(), random, pos))
        {
            // If feature placement fails, restore the small mushroom
            level.setBlock(pos, state, 3);
        }
    }
}
