package zatsu.mushroommod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import zatsu.mushroommod.block.BlockEntries;
import zatsu.mushroommod.block.BlockEntries.RenderLayerType;
import zatsu.mushroommod.block.BlockRegistration;

public class ZatsusMushroomModClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		for (BlockEntries entry : BlockEntries.values())
		{
			if (entry.renderLayer == RenderLayerType.CUTOUT)
			{
				BlockRenderLayerMap.putBlock(
					BlockRegistration.getBlock(entry),
					ChunkSectionLayer.CUTOUT
				);
			}
			else if (entry.renderLayer == RenderLayerType.TRANSLUCENT)
			{
				BlockRenderLayerMap.putBlock(
					BlockRegistration.getBlock(entry),
					ChunkSectionLayer.TRANSLUCENT
				);
			}
			// NONE → do nothing
		}
	}
}
