package com.besson.tutorial.item;

import com.besson.tutorial.TutorialMod;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
