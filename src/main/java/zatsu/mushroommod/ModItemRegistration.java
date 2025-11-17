package zatsu.mushroommod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModItemRegistration
{
    public static final Map<ModItemEntries, Item> ITEMS = new HashMap<>();

    public static void registerAll()
    {
        for (var entry : ModItemEntries.values())
        {
            // 1. Create the item ID (location)
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                ZatsusMushroomMod.MOD_ID,
                entry.id
            );

            // 2. Create the item KEY (required for setId)
            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);

            // 3. Construct item WITH the key
            Item item = new Item(
                new Item.Properties().setId(key)
            );

            // 4. Register it
            Item registered = Registry.register(
                BuiltInRegistries.ITEM,
                id,
                item
            );

            ITEMS.put(entry, registered);
        }
    }

    public static Item get(ModItemEntries entry)
    {
        return ITEMS.get(entry);
    }

    public static void init()
    {
        registerAll();
    }
}