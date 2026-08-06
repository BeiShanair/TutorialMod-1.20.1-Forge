package com.besson.tutorial.blockEntity;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.blockEntity.custom.ModHangingSignBlockEntity;
import com.besson.tutorial.blockEntity.custom.ModSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = 
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TutorialMod.MOD_ID);
    
    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = 
            BLOCK_ENTITIES.register("mod_sign", () -> 
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.ICE_ETHER_SIGN.get(), ModBlocks.ICE_ETHER_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = 
            BLOCK_ENTITIES.register("mod_hanging_sign", () -> 
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlocks.ICE_ETHER_HANGING_SIGN.get(), ModBlocks.ICE_ETHER_WALL_HANGING_SIGN.get()).build(null));
    
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
