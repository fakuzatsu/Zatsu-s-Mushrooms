package zatsu.mushroommod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import zatsu.mushroommod.item.ItemEntries;

import net.minecraft.world.item.Items;
import zatsu.mushroommod.item.ItemRegistration;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        valueLookupBuilder(Tags.Items.MUSHROOMS)
            .add(Items.BROWN_MUSHROOM)
            .add(ItemRegistration.get(ItemEntries.MUSHROOM_BUTTON_BROWN))
            .add(ItemRegistration.get(ItemEntries.MUSHROOM_CURVED_RED))
            .add(ItemRegistration.get(ItemEntries.MUSHROOM_SKINNY_BROWN));

        valueLookupBuilder(Tags.Items.POISONOUS_MUSHROOMS)
            .add(Items.RED_MUSHROOM)
            .add(ItemRegistration.get(ItemEntries.MUSHROOM_BUTTON_RED));

    }
}