package com.besson.tutorial.block.custom;

import com.besson.tutorial.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CornCrop extends CropBlock {
    public static final int FIRST_STAGE_AGE = 7;
    public static final int SECOND_STAGE_AGE = 1;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 8);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)
    };

    public CornCrop(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[pState.getValue(this.getAgeProperty())];
    }

    @Override
    public int getMaxAge() {
        return FIRST_STAGE_AGE + SECOND_STAGE_AGE;
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.CORN.get();
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState below = pLevel.getBlockState(pPos.below());
        return super.canSurvive(pState, pLevel, pPos) ||
                below.is(this) && below.getValue(AGE) == FIRST_STAGE_AGE;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.getRawBrightness(pPos, 0) >= 9) {
            int age = this.getAge(pState);
            if (age < this.getMaxAge()) {
                float f = getGrowthSpeed(this, pLevel, pPos);
                if (pRandom.nextInt((int) (25.0F / f) + 1) == 0) {
                    if (age == FIRST_STAGE_AGE) {
                        BlockState above = pLevel.getBlockState(pPos.above());
                        if (above.isAir()) {
                            pLevel.setBlock(pPos.above(), this.getStateForAge(age + 1), 3);
                        }
                    } else {
                        pLevel.setBlock(pPos, this.getStateForAge(age + 1), 3);
                    }
                }
            }
        }
    }

    @Override
    public void growCrops(Level pLevel, BlockPos pPos, BlockState pState) {
        int nextAge = this.getAge(pState) + this.getBonemealAgeIncrease(pLevel);
        int maxAge = this.getMaxAge();
        if (nextAge > maxAge) {
            nextAge = maxAge;
        }
        BlockState above = pLevel.getBlockState(pPos.above());
        if (this.getAge(pState) == FIRST_STAGE_AGE && above.isAir()) {
            pLevel.setBlock(pPos.above(), this.getStateForAge(nextAge), 3);
        } else {
            pLevel.setBlock(pPos, this.getStateForAge(nextAge - 1), 3);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            if (this.getAge(pState) == this.getMaxAge()) {
                pLevel.removeBlock(pPos, false);
                pLevel.setBlock(pPos.below(), this.getStateForAge(0), 3);
                popResource(pLevel, pPos, new ItemStack(ModItems.CORN.get()));
                popExperience((ServerLevel) pLevel, pPos, 5);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
