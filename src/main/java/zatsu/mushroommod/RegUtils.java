package zatsu.mushroommod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class RegUtils
{
    public static <R, T extends R> Supplier<T> register(String name, Supplier<T> supplier, Registry<R> reg)
    {
        T object = supplier.get();
        Registry.register(reg, ResourceLocation.fromNamespaceAndPath("mushroommod", name), object);
        return () -> object;
    }

    public static <B extends RecipeSerializer<?>> Supplier<B> regRecipeSerializer(String name, Supplier<B> supplier)
    {
        return register(name, supplier, BuiltInRegistries.RECIPE_SERIALIZER);
    }
}
