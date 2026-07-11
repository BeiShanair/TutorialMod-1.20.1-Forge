package com.besson.tutorial.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class ModFenceBlock extends Block {
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    
    public ModFenceBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH, EAST, SOUTH, WEST);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        BlockPos north = pPos.north();
        BlockPos south = pPos.south();
        BlockPos west = pPos.west();
        BlockPos east = pPos.east();
        
        BlockState northState = pLevel.getBlockState(north);
        BlockState southState = pLevel.getBlockState(south);
        BlockState westState = pLevel.getBlockState(west);
        BlockState eastState = pLevel.getBlockState(east);
        
        return this.defaultBlockState()
                .setValue(NORTH, northState.is(this))
                .setValue(EAST, eastState.is(this))
                .setValue(SOUTH, southState.is(this))
                .setValue(WEST, westState.is(this));
    }
}
