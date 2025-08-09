package com.besson.tutorial.item;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB =
            CREATIVE_MODE_TABS.register("tutorial_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ICE_ETHER.get()))
                    .title(Component.translatable("itemGroup.tutorial_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ICE_ETHER.get());
                        pOutput.accept(ModItems.RAW_ICE_ETHER.get());
                        pOutput.accept(ModBlocks.ICE_ETHER_BLOCK.get());
                        pOutput.accept(ModBlocks.ICE_ETHER_ORE.get());
                        pOutput.accept(ModBlocks.RAW_ICE_ETHER_BLOCK.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> MATERIAL =
            CREATIVE_MODE_TABS.register("material", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CARDBOARD.get()))
                    .title(Component.translatable("itemGroup.material"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.CARDBOARD.get());
                        pOutput.accept(Items.DIAMOND);
                    }).withTabsBefore(TUTORIAL_TAB.getKey())
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
