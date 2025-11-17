package zatsu.mushroommod;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;

public class ModItemProperties 
{    
    // Registers the compostable property for items that have it in ModItemEntries
    private static void registerCompostables()
    {
        for (var entry : ModItemEntries.values())
        {
            if (entry.compostChance != null)
            {
                CompostingChanceRegistry.INSTANCE.add(
                        ModItemRegistration.get(entry),
                        entry.compostChance
                );
            }
        }
    }

    // Registers the fuel property for items that have it in ModItemEntries
    private static void registerFuels()
    {
        FuelRegistryEvents.BUILD.register((builder, ctx) ->
        {
            for (var entry : ModItemEntries.values())
            {
                if (entry.fuelSeconds != null)
                {
                    builder.add(
                            ModItemRegistration.get(entry),
                            entry.fuelSeconds * 20
                    );
                }
            }
        });
    }

    public static void init() 
	{
        registerCompostables();
        registerFuels();
    }
}
