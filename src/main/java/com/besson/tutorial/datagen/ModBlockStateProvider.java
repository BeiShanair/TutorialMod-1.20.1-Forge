package com.besson.tutorial.datagen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.ICE_ETHER_BLOCK.get(), cubeAll(ModBlocks.ICE_ETHER_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.RAW_ICE_ETHER_BLOCK.get(), cubeAll(ModBlocks.RAW_ICE_ETHER_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.ICE_ETHER_ORE.get(), cubeAll(ModBlocks.ICE_ETHER_ORE.get()));

        stairsBlock(ModBlocks.ICE_ETHER_STAIRS.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        slabBlock(ModBlocks.ICE_ETHER_SLAB.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        buttonBlock(ModBlocks.ICE_ETHER_BUTTON.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        pressurePlateBlock(ModBlocks.ICE_ETHER_PRESSURE_PLATE.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        fenceBlock(ModBlocks.ICE_ETHER_FENCE.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        fenceGateBlock(ModBlocks.ICE_ETHER_FENCE_GATE.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        wallBlock(ModBlocks.ICE_ETHER_WALL.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.ICE_ETHER_DOOR.get(), modLoc("block/ice_ether_door_bottom"), modLoc("block/ice_ether_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.ICE_ETHER_TRAPDOOR.get(), modLoc("block/ice_ether_trapdoor"), true, "cutout");

        blockItem(ModBlocks.ICE_ETHER_STAIRS);
        blockItem(ModBlocks.ICE_ETHER_SLAB);
        blockItem(ModBlocks.ICE_ETHER_PRESSURE_PLATE);
        blockItem(ModBlocks.ICE_ETHER_FENCE_GATE);
        blockItem(ModBlocks.ICE_ETHER_TRAPDOOR, "_bottom");
    }

    private <T extends Block> void blockItem(RegistryObject<T> block) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(TutorialMod.MOD_ID + ":block/" + block.getId().getPath()));
    }
    private <T extends Block> void blockItem(RegistryObject<T> block, String append) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(TutorialMod.MOD_ID + ":block/" + block.getId().getPath() + append));
    }
}
