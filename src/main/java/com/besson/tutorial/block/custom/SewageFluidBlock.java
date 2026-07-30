package com.besson.tutorial.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class SewageFluidBlock extends LiquidBlock {
    public SewageFluidBlock(RegistryObject<FlowingFluid> fluid, Properties pProperties) {
        super(fluid, pProperties);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!pLevel.isClientSide() && pEntity instanceof LivingEntity livingEntity) {
            if (livingEntity.tickCount % 20 == 0) {
                livingEntity.hurt(pLevel.damageSources().magic(), 2.0f);
            }
        }
    }
}
