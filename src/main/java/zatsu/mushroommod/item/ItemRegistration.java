package zatsu.mushroommod.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import zatsu.mushroommod.ZatsusMushroomMod;
import zatsu.mushroommod.block.BlockRegistration;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistration
{
    public static final Map<ItemEntries, Item> ITEMS = new HashMap<>();

    public static void registerAll()
    {
        for (var entry : ItemEntries.values())
        {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                ZatsusMushroomMod.MOD_ID,
                entry.id
            );

            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);

            Item item;

            // If this item places a block, construct the special item
            if (entry.blockToPlace != null)
            {
                Block block = BlockRegistration.getBlock(entry.blockToPlace);

                item = new CustomPlaceableItem(
                    block,
                    entry.placementBehavior,
                    new Item.Properties().setId(key)
                );
            }
            else
            {
                // Normal item
                item = new Item(new Item.Properties().setId(key));
            }

            Item registered = Registry.register(BuiltInRegistries.ITEM, id, item);
            ITEMS.put(entry, registered);
        }
    }

    public static Item get(ItemEntries entry)
    {
        return ITEMS.get(entry);
    }

    public static void init()
    {
        registerAll();
    }
}