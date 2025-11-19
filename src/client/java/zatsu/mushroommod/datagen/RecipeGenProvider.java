package zatsu.mushroommod.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import zatsu.mushroommod.item.ItemEntries;
import zatsu.mushroommod.item.ItemRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import zatsu.mushroommod.item.ItemTags;

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
				// Mushroom Stew
				shapeless(RecipeCategory.FOOD, Items.MUSHROOM_STEW)
					.requires(Items.BOWL)
					.requires(ItemTags.Items.MUSHROOMS)
					.requires(ItemTags.Items.MUSHROOMS)
					// Create an advancement that gives you the recipe
					.unlockedBy(getHasName(ItemRegistration.get(ItemEntries.MUSHROOM_BUTTON_BROWN)), has(ItemRegistration.get(ItemEntries.MUSHROOM_BUTTON_BROWN)))
					.unlockedBy(getHasName(ItemRegistration.get(ItemEntries.MUSHROOM_BUTTON_RED)), has(ItemRegistration.get(ItemEntries.MUSHROOM_BUTTON_RED)))
					.unlockedBy(getHasName(ItemRegistration.get(ItemEntries.MUSHROOM_CURVED_RED)), has(ItemRegistration.get(ItemEntries.MUSHROOM_CURVED_RED)))
					.unlockedBy(getHasName(ItemRegistration.get(ItemEntries.MUSHROOM_SKINNY_BROWN)), has(ItemRegistration.get(ItemEntries.MUSHROOM_SKINNY_BROWN)))
					.save(output);
			}
		};
	}

	@Override
	public String getName()
	{
		return "RecipeGenProvider";
	}
}
