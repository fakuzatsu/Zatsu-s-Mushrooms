package zatsu.mushroommod;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public enum ModItemEntries 
{
    // Add items and properties here
    MUSHROOM_BUTTON_BROWN("button_brown", ModItemGroups.MUSHROOM_ITEM_GROUP_KEY, 0.5f, 0),
    MUSHROOM_BUTTON_RED("button_red", ModItemGroups.MUSHROOM_ITEM_GROUP_KEY, 0.5f, 0),
    MUSHROOM_CURVED_RED("curved_red", ModItemGroups.MUSHROOM_ITEM_GROUP_KEY, 0.5f, 0),
    MUSHROOM_SKINNY_BROWN("skinny_brown", ModItemGroups.MUSHROOM_ITEM_GROUP_KEY, 0.5f, 0);

    public final String id;
    public final ResourceKey<CreativeModeTab> itemGroup;
    public final Float compostChance;
    public final Integer fuelSeconds;

    ModItemEntries(String id, ResourceKey<CreativeModeTab> itemGroup, Float compostChance, Integer fuelSeconds) 
    {
        this.id = id;
        this.itemGroup = itemGroup;
        this.compostChance = compostChance;
        this.fuelSeconds = fuelSeconds;
    }
}
