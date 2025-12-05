package com.besson.tutorial.item;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.item.custom.ModFuelItem;
import com.besson.tutorial.item.custom.ProspectorItem;
import net.minecraft.world.item.*;
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

    public static final RegistryObject<Item> PROSPECTOR =
            ITEMS.register("prospector", () -> new ProspectorItem(new Item.Properties().durability(127)));

    public static final RegistryObject<Item> FIRE_ETHER =
            ITEMS.register("fire_ether", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_ETHER_SWORD = ITEMS.register("fire_ether_sword",
            () -> new SwordItem(ModToolTiers.FIRE_ETHER, 2, 3, new Item.Properties()));
    public static final RegistryObject<Item> FIRE_ETHER_PICKAXE = ITEMS.register("fire_ether_pickaxe",
            () -> new PickaxeItem(ModToolTiers.FIRE_ETHER, 1, 2, new Item.Properties()));
    public static final RegistryObject<Item> FIRE_ETHER_SHOVEL = ITEMS.register("fire_ether_shovel",
            () -> new ShovelItem(ModToolTiers.FIRE_ETHER, 2, 3, new Item.Properties()));
    public static final RegistryObject<Item> FIRE_ETHER_AXE = ITEMS.register("fire_ether_axe",
            () -> new AxeItem(ModToolTiers.FIRE_ETHER, 2, 3, new Item.Properties()));
    public static final RegistryObject<Item> FIRE_ETHER_HOE = ITEMS.register("fire_ether_hoe",
            () -> new HoeItem(ModToolTiers.FIRE_ETHER, 2, 3, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
