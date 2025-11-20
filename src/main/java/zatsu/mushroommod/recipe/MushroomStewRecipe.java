package zatsu.mushroommod.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
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
        int poisonousMushroomCount = 0;
        for (int i = 0; i < container.size(); i++)
        {
            ItemStack stack = container.getItem(i);
            if (stack.is(ItemTags.Items.POISONOUS_MUSHROOMS))
            {
                poisonousMushroomCount++;
            }
        }
        
        ItemStack result = new ItemStack(Items.MUSHROOM_STEW);
        
        if (poisonousMushroomCount > 0)
        {
            int duration = poisonousMushroomCount > 1 ? 200 : 120;
            SuspiciousStewEffects effects = new SuspiciousStewEffects(
                java.util.List.of(
                    new SuspiciousStewEffects.Entry(MobEffects.POISON, duration)
                )
            );
            result.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, effects);
        }
        
        return result;
    }

	@Override
	public RecipeSerializer<MushroomStewRecipe> getSerializer()
    {
		return ModRecipeSerializers.MUSHROOM_STEW.get();
	}
}

