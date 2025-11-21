package com.besson.tutorial.item;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.item.custom.ModFuelItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> ICE_ETHER =
            ITEMS.register("ice_ether", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ICE_ETHER =
            ITEMS.register("raw_ice_ether", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CARDBOARD =
            ITEMS.register("material/cardboard", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CORN =
            ITEMS.register("corn", () -> new Item(new Item.Properties().food(ModFoods.CORN)));
    public static final RegistryObject<Item> STRAWBERRY =
            ITEMS.register("strawberry", () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> CHEESE =
            ITEMS.register("cheese", () -> new Item(new Item.Properties().food(ModFoods.CHEESE)));

    public static final RegistryObject<Item> ANTHRACITE =
            ITEMS.register("anthracite", () -> new ModFuelItem(new Item.Properties(), 1600));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
