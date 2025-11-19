package zatsu.mushroommod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import zatsu.mushroommod.item.ItemEntries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import zatsu.mushroommod.item.ItemRegistration;
import zatsu.mushroommod.item.ItemTags;

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
        // Automatically add all items to their respective tags
        for (ItemEntries entry : ItemEntries.values())
        {
            for (TagKey<Item> tag : entry.tags)
            {
                valueLookupBuilder(tag).add(ItemRegistration.get(entry));
            }
        }
        
        // Add vanilla items manually
        valueLookupBuilder(ItemTags.Items.MUSHROOMS).add(Items.BROWN_MUSHROOM);
        valueLookupBuilder(ItemTags.Items.POISONOUS_MUSHROOMS).add(Items.RED_MUSHROOM);
    }
}