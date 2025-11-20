package zatsu.mushroommod.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import zatsu.mushroommod.item.ItemTags;

import java.util.HashSet;
import java.util.Set;

public class MushroomStewRecipe extends CustomRecipe
{
	public MushroomStewRecipe(CraftingBookCategory category)
    {
		super(category);
	}

	@Override
    public boolean matches(CraftingInput input, Level level)
    {
        if (input.size() < 3)
            return false;

        int mushroomCount = 0;
        int bowlCount = 0;
        Set<Item> mushrooms = new HashSet<>();

        for (int i = 0; i < input.size(); i++)
        {
            ItemStack stack = input.getItem(i);

            if (stack.isEmpty())
                continue;

            if (stack.is(Items.BOWL))
            {
                bowlCount++;
            }
            else if (stack.is(ItemTags.Items.MUSHROOMS) || stack.is(ItemTags.Items.POISONOUS_MUSHROOMS))
            {
                mushroomCount++;
                mushrooms.add(stack.getItem());
            }
            else
            {
                return false;
            }
        }

        return bowlCount == 1 && mushroomCount == 2 && mushrooms.size() == 2;
    }

	@Override
	public ItemStack assemble(CraftingInput container, HolderLookup.Provider registryAccess)
    {
		return new ItemStack(Items.MUSHROOM_STEW);
	}

	@Override
	public RecipeSerializer<MushroomStewRecipe> getSerializer()
    {
		return ModRecipeSerializers.MUSHROOM_STEW.get();
	}
}

