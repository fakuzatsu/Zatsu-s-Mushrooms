package zatsu.mushroommod.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import zatsu.mushroommod.block.BlockEntries;
import zatsu.mushroommod.block.BlockPlacementBehavior;
import zatsu.mushroommod.block.PlacementBehaviors;

public enum ItemEntries 
{
    // Add items and properties here
    MUSHROOM_BUTTON_BROWN(
        "button_brown",
        ItemGroups.MUSHROOM_ITEM_GROUP_KEY,
        0.5f,
        0,
        BlockEntries.MUSHROOM_BLOCK_BUTTON_BROWN,
        PlacementBehaviors.MUSHROOM_PLACEMENT
    ),
    MUSHROOM_BUTTON_RED(
        "button_red",
        ItemGroups.MUSHROOM_ITEM_GROUP_KEY,
        0.5f,
        0,
        BlockEntries.MUSHROOM_BLOCK_BUTTON_RED,
        PlacementBehaviors.MUSHROOM_PLACEMENT
    ),
    MUSHROOM_CURVED_RED(
        "curved_red",
        ItemGroups.MUSHROOM_ITEM_GROUP_KEY,
        0.5f,
        0,
        BlockEntries.MUSHROOM_BLOCK_CURVED_RED,
        PlacementBehaviors.MUSHROOM_PLACEMENT
    ),
    MUSHROOM_SKINNY_BROWN(
        "skinny_brown",
        ItemGroups.MUSHROOM_ITEM_GROUP_KEY,
        0.5f,
        0,
        BlockEntries.MUSHROOM_BLOCK_SKINNY_BROWN,
        PlacementBehaviors.MUSHROOM_PLACEMENT
    );

    public final String id;
    public final ResourceKey<CreativeModeTab> itemGroup;
    public final Float compostChance;
    public final Integer fuelSeconds;
    public final BlockEntries blockToPlace;
    public final BlockPlacementBehavior placementBehavior;

    ItemEntries(String id,
        ResourceKey<CreativeModeTab> itemGroup,
        Float compostChance,
        Integer fuelSeconds,
        BlockEntries blockToPlace,
        BlockPlacementBehavior placementBehavior)
    {
        this.id = id;
        this.itemGroup = itemGroup;
        this.compostChance = compostChance;
        this.fuelSeconds = fuelSeconds;
        this.blockToPlace = blockToPlace;
        this.placementBehavior = placementBehavior;
    }
}
