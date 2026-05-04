package com.besson.tutorial.datagen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.block.custom.CornCrop;
import com.besson.tutorial.block.custom.SofaBlock;
import com.besson.tutorial.block.custom.StrawberryCrop;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

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

        crop(ModBlocks.STRAWBERRY_CROP.get(), "strawberry_crop_stage", StrawberryCrop.AGE);
        crossCrop(ModBlocks.CORN_CROP.get(), "corn_crop_stage", CornCrop.AGE);

        simpleBlockWithoutBlockModel(ModBlocks.ORANGE_NIGHTSTAND);

        customHorizontalBlock(ModBlocks.SIMPLE_ORANGE_CLOCK);

        sofa(ModBlocks.SOFA, "sofa");
    }

    private <T extends Block> void sofa(RegistryObject<T> block, String name) {
        ModelFile modelSingle = models().getExistingFile(modLoc("block/" + name));
        ModelFile modelLeft = models().getExistingFile(modLoc("block/" + name + "_left"));
        ModelFile modelRight = models().getExistingFile(modLoc("block/" + name + "_right"));
        ModelFile modelMiddle = models().getExistingFile(modLoc("block/" + name + "_middle"));

        getVariantBuilder(block.get()).forAllStates(state -> {
            SofaBlock.Type type = state.getValue(SofaBlock.TYPE);
            Direction facing = state.getValue(SofaBlock.FACING);

            ModelFile t;
            switch (type) {
                case LEFT -> t = modelLeft;
                case MIDDLE -> t = modelMiddle;
                case RIGHT -> t = modelRight;
                default -> t = modelSingle;
            };

            int yRot;
            switch (facing) {
                case EAST -> yRot = 90;
                case SOUTH -> yRot = 180;
                case WEST -> yRot = 270;
                default -> yRot = 0;
            };

            return ConfiguredModel.builder()
                    .modelFile(t)
                    .rotationY(yRot)
                    .build();
        });
        simpleBlockItem(block.get(), modelSingle);
    }

    private <T extends Block> void customHorizontalBlock(RegistryObject<T> block) {
        ResourceLocation model = modLoc("block/" + block.getId().getPath());
        horizontalBlock(block.get(), models().getExistingFile(model));
        simpleBlockItem(block.get(), models().getExistingFile(model));
    }

    private <T extends Block> void simpleBlockWithoutBlockModel(RegistryObject<T> block) {
        ResourceLocation model = modLoc("block/" + block.getId().getPath());
        simpleBlock(block.get(), models().getExistingFile(model));
        simpleBlockItem(block.get(), models().getExistingFile(model));
    }

    public void crop(CropBlock block, String name, IntegerProperty property) {
        Function<BlockState, ConfiguredModel[]> function = state ->
                cropStates(state, name, property);

        getVariantBuilder(block).forAllStates(function);
    }

    public void crossCrop(CropBlock block, String name, IntegerProperty property) {
        Function<BlockState, ConfiguredModel[]> function = state ->
                crossStates(state, name, property);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cropStates(BlockState state, String modelName, IntegerProperty property) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(property),
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + modelName + state.getValue(property))).renderType("cutout"));

        return models;
    }

    private ConfiguredModel[] crossStates(BlockState state, String modelName, IntegerProperty property) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(property),
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + modelName + state.getValue(property))).renderType("cutout"));

        return models;
    }

    private <T extends Block> void blockItem(RegistryObject<T> block) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(TutorialMod.MOD_ID + ":block/" + block.getId().getPath()));
    }
    private <T extends Block> void blockItem(RegistryObject<T> block, String append) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(TutorialMod.MOD_ID + ":block/" + block.getId().getPath() + append));
    }
}
