package zatsu.mushroommod.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import zatsu.mushroommod.ZatsusMushroomMod;

import java.util.HashMap;
import java.util.Map;

public class BlockRegistration
{
    public static final Map<BlockEntries, Block> BLOCKS = new HashMap<>();
    public static final Map<BlockEntries, Item> BLOCK_ITEMS = new HashMap<>();

    public static void registerAll()
    {
        for (var entry : BlockEntries.values())
        {
            // Block ID
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                    ZatsusMushroomMod.MOD_ID,
                    entry.id
            );
            ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);

            // Create fresh properties with ID
            BlockBehaviour.Properties props = entry.properties.setId(blockKey);

            // Create block
            Block block = new Block(props);

            // Register block
            Block registeredBlock = Registry.register(BuiltInRegistries.BLOCK, id, block);
            BLOCKS.put(entry, registeredBlock);

            // Optionally register a BlockItem
            if (entry.createBlockItem)
            {
                ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

                BlockItem blockItem = new BlockItem(
                        registeredBlock,
                        new Item.Properties().setId(itemKey)
                );

                Item registeredBlockItem =
                        Registry.register(BuiltInRegistries.ITEM, id, blockItem);

                BLOCK_ITEMS.put(entry, registeredBlockItem);
            }
        }
    }

    public static Block getBlock(BlockEntries entry)
    {
        return BLOCKS.get(entry);
    }

    public static Item getBlockItem(BlockEntries entry)
    {
        return BLOCK_ITEMS.get(entry);
    }

    public static void init()
    {
        registerAll();
    }
}
