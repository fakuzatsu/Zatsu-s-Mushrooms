package zatsu.mushroommod.recipe;

import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.RecipeOutput;

public class CraftingRecipes
{
	public static void register(RecipeOutput output)
    {
		SpecialRecipeBuilder.special(MushroomStewRecipe::new).save(output, "mushroom_stew");
	}
}
