package zatsu.mushroommod.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import zatsu.mushroommod.block.BlockEntries;
import zatsu.mushroommod.block.BlockPlacementBehavior;
import zatsu.mushroommod.block.PlacementBehaviors;

import java.util.List;

public enum ItemEntries 
{
    // Add items and properties here
    MUSHROOM_BUTTON_BROWN(
        "button_brown",
        ItemGroups.MUSHROOM_ITEM_GROUP_KEY,
        0.5f,
        0,
        BlockEntries.MUSHROOM_BLOCK_BUTTON_BROWN,
        PlacementBehaviors.MUSHROOM_PLACEMENT,
        List.of(ItemTags.Items.MUSHROOMS)
    ),
    MUSHROOM_BUTTON_RED(
        "button_red",
        ItemGroups.MUSHROOM_ITEM_GROUP_KEY,
        0.5f,
        0,
        BlockEntries.MUSHROOM_BLOCK_BUTTON_RED,
        PlacementBehaviors.MUSHROOM_PLACEMENT,
        List.of(ItemTags.Items.POISONOUS_MUSHROOMS)
    ),
    MUSHROOM_CURVED_RED(
        "curved_red",
        ItemGroups.MUSHROOM_ITEM_GROUP_KEY,
        0.5f,
        0,
        BlockEntries.MUSHROOM_BLOCK_CURVED_RED,
        PlacementBehaviors.MUSHROOM_PLACEMENT,
        List.of(ItemTags.Items.MUSHROOMS)
    ),
    MUSHROOM_SKINNY_BROWN(
        "skinny_brown",
        ItemGroups.MUSHROOM_ITEM_GROUP_KEY,
        0.5f,
        0,
        BlockEntries.MUSHROOM_BLOCK_SKINNY_BROWN,
        PlacementBehaviors.MUSHROOM_PLACEMENT,
        List.of(ItemTags.Items.MUSHROOMS)
    );

    public final String id;
    public final ResourceKey<CreativeModeTab> itemGroup;
    public final Float compostChance;
    public final Integer fuelSeconds;
    public final BlockEntries blockToPlace;
    public final BlockPlacementBehavior placementBehavior;
    public final List<TagKey<Item>> tags;

    ItemEntries(String id,
        ResourceKey<CreativeModeTab> itemGroup,
        Float compostChance,
        Integer fuelSeconds,
        BlockEntries blockToPlace,
        BlockPlacementBehavior placementBehavior,
        List<TagKey<Item>> tags)
    {
        this.id = id;
        this.itemGroup = itemGroup;
        this.compostChance = compostChance;
        this.fuelSeconds = fuelSeconds;
        this.blockToPlace = blockToPlace;
        this.placementBehavior = placementBehavior;
        this.tags = List.copyOf(tags);
    }
}
