package zatsu.mushroommod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import zatsu.mushroommod.datagen.ItemTagProvider;
import zatsu.mushroommod.datagen.RecipeGenProvider;
import zatsu.mushroommod.datagen.BlockLootProvider;
import zatsu.mushroommod.datagen.WorldGenProvider;

public class ZatsusMushroomModDataGenerator implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator)
    {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ItemTagProvider::new);
        pack.addProvider(RecipeGenProvider::new);
        pack.addProvider(BlockLootProvider::new);
        pack.addProvider(WorldGenProvider::new);
    }

}
