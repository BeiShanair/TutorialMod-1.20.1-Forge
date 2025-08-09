package com.besson.tutorial.block;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> ICE_ETHER_BLOCK =
            registerBlock("ice_ether_block", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 3.0F)));
    public static final RegistryObject<Block> RAW_ICE_ETHER_BLOCK =
            registerBlock("raw_ice_ether_block", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 3.0F)));
    public static final RegistryObject<Block> ICE_ETHER_ORE =
            registerBlock("ice_ether_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    private static <T extends Block> void registerBlockItems(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> blocks = BLOCKS.register(name, block);
        registerBlockItems(name, blocks);
        return blocks;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
