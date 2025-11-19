package zatsu.mushroommod.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class Tags
{
    public static class Items
    {
        public static final TagKey<Item> MUSHROOMS = 
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("mushroommod", "mushrooms"));

        public static final TagKey<Item> POISONOUS_MUSHROOMS =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("mushroommod", "mushroom_poisonous"));
    }
}
