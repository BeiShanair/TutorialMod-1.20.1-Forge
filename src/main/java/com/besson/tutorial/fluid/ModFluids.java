package com.besson.tutorial.fluid;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = 
            DeferredRegister.create(ForgeRegistries.FLUIDS, TutorialMod.MOD_ID);
    
    public static final RegistryObject<FlowingFluid> STILL_SEWAGE = FLUIDS.register("sewage",
            SewageFluid.Source::new);
    public static final RegistryObject<FlowingFluid> FLOWING_SEWAGE = FLUIDS.register("flowing_sewage",
            SewageFluid.Flowing::new);
    public static final ForgeFlowingFluid.Properties SEWAGE_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.SEWAGE_FLUID_TYPE, STILL_SEWAGE, FLOWING_SEWAGE)
            .slopeFindDistance(2)
            .levelDecreasePerBlock(1)
            .block(ModBlocks.SEWAGE_BLOCK)
            .bucket(ModItems.SEWAGE_BUCKET);
    
    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
