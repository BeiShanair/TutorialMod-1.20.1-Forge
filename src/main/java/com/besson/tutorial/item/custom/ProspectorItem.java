package com.besson.tutorial.item.custom;

import com.besson.tutorial.tag.ModBlockTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ProspectorItem extends Item {
    public ProspectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos blockPos = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();

        if (!level.isClientSide()) {
            boolean found = false;
            if (!Screen.hasShiftDown()) {
                for (int i = 0; i <= blockPos.getY() + 64; i++) {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            BlockPos pos1 = blockPos.below(i).north(j).east(k);
                            BlockState blockState = level.getBlockState(pos1);
                            String name = blockState.getBlock().getName().getString();

                            if (isCorrectBlock(blockState)) {
                                player.sendSystemMessage(Component.literal("Found " + name + " !"));
                                found = true;
                                break;
                            }
                        }
                    }
                }
                if (!found) {
                    player.sendSystemMessage(Component.literal("No ore found!"));
                }
            } else {
                for (int i = 0; i <= blockPos.getY() + 64; i++) {
                    BlockPos pos1 = blockPos.below(i);
                    BlockState blockState = level.getBlockState(pos1);
                    String name = blockState.getBlock().getName().getString();

                    if (isCorrectBlock(blockState)) {
                        player.sendSystemMessage(Component.literal("Found " + name + " !"));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    player.sendSystemMessage(Component.literal("No ore found!"));
                }
            }
            pContext.getItemInHand().hurtAndBreak(1, player,
                    p -> p.broadcastBreakEvent(pContext.getHand()));
            return InteractionResult.SUCCESS;
        }
        return super.useOn(pContext);
    }

    private boolean isCorrectBlock(BlockState blockState) {
        return blockState.is(ModBlockTags.ORE_TAGS);
    }
}
