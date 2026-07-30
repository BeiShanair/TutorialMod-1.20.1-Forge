package com.besson.tutorial.fluid;

import com.besson.tutorial.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = 
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TutorialMod.MOD_ID);
    
    public static final ResourceLocation WATER_STILL_RL = ResourceLocation.parse("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.parse("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = ResourceLocation.parse("block/water_overlay");
    
    public static final RegistryObject<FluidType> SEWAGE_FLUID_TYPE = FLUID_TYPES.register("sewage",
            () -> new BaseFluidType(FluidType.Properties.create().density(15).viscosity(5),
                    WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL,
                    0xFF2F4F4F,
                    new Vector3f(47f / 255f, 79f / 255f, 79f / 255f)));
    
    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
