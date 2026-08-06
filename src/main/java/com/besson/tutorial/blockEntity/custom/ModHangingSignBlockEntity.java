package com.besson.tutorial.blockEntity.custom;

import com.besson.tutorial.blockEntity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModHangingSignBlockEntity extends HangingSignBlockEntity {
    public ModHangingSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MOD_HANGING_SIGN.get(), pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.MOD_HANGING_SIGN.get();
    }
}
