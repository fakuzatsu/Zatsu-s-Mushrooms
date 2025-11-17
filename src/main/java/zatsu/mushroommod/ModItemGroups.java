package zatsu.mushroommod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups 
{
    // Creates an item group key for tab registration
    public static final ResourceKey<CreativeModeTab> MUSHROOM_ITEM_GROUP_KEY =
        ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(ZatsusMushroomMod.MOD_ID, "item_group")
        );

    // Builds the tab and adds all items that belong to that tab from ModItemEntries
    public static final CreativeModeTab MUSHROOM_ITEM_GROUP =
        FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.mushrooms"))
            .icon(() -> new ItemStack(ModItemRegistration.get(ModItemEntries.MUSHROOM_BUTTON_BROWN)))
            .displayItems((params, entries) ->
            {
                // Auto-add every enum entry that belongs to this tab
                for (var entry : ModItemEntries.values())
                {
                    if (entry.itemGroup == MUSHROOM_ITEM_GROUP_KEY)
                    {
                        entries.accept(ModItemRegistration.get(entry));
                    }
                }
            })
            .build();

    public static void init() 
    {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MUSHROOM_ITEM_GROUP_KEY, MUSHROOM_ITEM_GROUP);
    }
}
