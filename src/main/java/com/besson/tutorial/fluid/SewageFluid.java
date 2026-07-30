package com.besson.tutorial.fluid;

import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class SewageFluid extends ForgeFlowingFluid {
    protected SewageFluid() {
        super(ModFluids.SEWAGE_PROPERTIES);
    }
    
    public static class Source extends SewageFluid {

        @Override
        public boolean isSource(FluidState pState) {
            return true;
        }

        @Override
        public int getAmount(FluidState pState) {
            return 8;
        }
    }
    
    public static class Flowing extends SewageFluid {

        @Override
        public boolean isSource(FluidState pState) {
            return false;
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> pBuilder) {
            super.createFluidStateDefinition(pBuilder);
            pBuilder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState pState) {
            return pState.getValue(LEVEL);
        }
    }
}
