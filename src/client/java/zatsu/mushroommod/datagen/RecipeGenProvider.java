package zatsu.mushroommod.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import zatsu.mushroommod.recipe.CraftingRecipes;

public class RecipeGenProvider extends FabricRecipeProvider
{
	public RecipeGenProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
	{
		super(output, registriesFuture);
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter)
	{
		return new RecipeProvider(registryLookup, exporter)
		{
			@Override
			public void buildRecipes()
			{
				CraftingRecipes.register(exporter);
			}
		};
	}

	@Override
	public String getName()
	{
		return "Mushroom Mod Recipes";
	}
}
