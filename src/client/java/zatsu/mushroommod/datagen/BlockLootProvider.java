package zatsu.mushroommod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import zatsu.mushroommod.block.BlockEntries;
import zatsu.mushroommod.block.BlockRegistration;
import zatsu.mushroommod.item.ItemRegistration;
import zatsu.mushroommod.item.ItemEntries;

import java.util.concurrent.CompletableFuture;

public class BlockLootProvider extends FabricBlockLootTableProvider
{
	public BlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate()
    {
		add(BlockRegistration.getBlock(BlockEntries.MUSHROOM_BLOCK_BUTTON_BROWN), LootTable.lootTable().withPool(LootPool.lootPool()
			.add(LootItem.lootTableItem(ItemRegistration.get(ItemEntries.MUSHROOM_BUTTON_BROWN)))));
		add(BlockRegistration.getBlock(BlockEntries.MUSHROOM_BLOCK_BUTTON_RED), LootTable.lootTable().withPool(LootPool.lootPool()
			.add(LootItem.lootTableItem(ItemRegistration.get(ItemEntries.MUSHROOM_BUTTON_RED)))));
		add(BlockRegistration.getBlock(BlockEntries.MUSHROOM_BLOCK_CURVED_RED), LootTable.lootTable().withPool(LootPool.lootPool()
			.add(LootItem.lootTableItem(ItemRegistration.get(ItemEntries.MUSHROOM_CURVED_RED)))));
		add(BlockRegistration.getBlock(BlockEntries.MUSHROOM_BLOCK_SKINNY_BROWN), LootTable.lootTable().withPool(LootPool.lootPool()
			.add(LootItem.lootTableItem(ItemRegistration.get(ItemEntries.MUSHROOM_SKINNY_BROWN)))));
	}
}
