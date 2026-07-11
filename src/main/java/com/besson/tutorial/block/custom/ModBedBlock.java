package com.besson.tutorial.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModBedBlock extends BedBlock {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 8, 16);
    public ModBedBlock(DyeColor pColor, Properties pProperties) {
        super(pColor, pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        if (pState.getValue(PART) == BedPart.HEAD) {
            return RenderShape.MODEL;
        } else {
            return RenderShape.INVISIBLE;
        }
    }
}
