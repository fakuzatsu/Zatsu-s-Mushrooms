package zatsu.mushroommod.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import zatsu.mushroommod.RegUtils;

import java.util.function.Supplier;

public class RecipeSerializers 
{
	public static final Supplier<RecipeSerializer<MushroomStewRecipe>> MUSHROOM_STEW =
			RegUtils.regRecipeSerializer("mushroom_stew", () -> new RecipeSerializer<MushroomStewRecipe>()
            {
				@Override
				public MapCodec<MushroomStewRecipe> codec()
                {
					return CraftingBookCategory.CODEC.xmap(
						MushroomStewRecipe::new,
						recipe -> CraftingBookCategory.MISC
					).fieldOf("category");
				}

				@Override
				public StreamCodec<RegistryFriendlyByteBuf, MushroomStewRecipe> streamCodec()
                {
					return StreamCodec.of(
						(buf, recipe) -> buf.writeEnum(CraftingBookCategory.MISC),
						buf -> new MushroomStewRecipe(buf.readEnum(CraftingBookCategory.class))
					);
				}
			});

	public static void init()
    {
		// This method ensures the class is loaded and the static initializers run
	}
}
