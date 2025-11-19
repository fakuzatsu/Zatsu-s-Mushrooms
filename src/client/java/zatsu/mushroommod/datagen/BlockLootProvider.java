package zatsu.mushroommod.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import zatsu.mushroommod.block.BlockEntries;
import zatsu.mushroommod.block.BlockRegistration;
import net.minecraft.world.item.Item;

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
		for (BlockEntries block : BlockEntries.values())
		{
			// Build a loot pool with all drops for this block
			LootPool.Builder poolBuilder = LootPool.lootPool();
			
			for (String dropId : block.drops)
			{
				ResourceLocation id = ResourceLocation.parse(dropId);
				Item item = this.registries.lookupOrThrow(Registries.ITEM).getOrThrow(net.minecraft.resources.ResourceKey.create(Registries.ITEM, id)).value();
				poolBuilder.add(LootItem.lootTableItem(item));
			}
			
			// Add the loot table with all drops once per block
			add(
				BlockRegistration.getBlock(block),
				LootTable.lootTable().withPool(poolBuilder)
			);
		}
	}
}
