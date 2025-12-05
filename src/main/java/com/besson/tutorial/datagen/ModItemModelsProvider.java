package com.besson.tutorial.datagen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ICE_ETHER.get());
        basicItem(ModItems.RAW_ICE_ETHER.get());
//        basicItem(ModItems.CARDBOARD.get());

        basicItem(ModItems.CORN.get());
        basicItem(ModItems.STRAWBERRY.get());
        basicItem(ModItems.CHEESE.get());

        basicItem(ModItems.ANTHRACITE.get());

        basicItem(ModItems.PROSPECTOR.get());

        buttonItem(ModBlocks.ICE_ETHER_BUTTON, ModBlocks.ICE_ETHER_BLOCK);
        fenceItem(ModBlocks.ICE_ETHER_FENCE, ModBlocks.ICE_ETHER_BLOCK);
        wallItem(ModBlocks.ICE_ETHER_WALL, ModBlocks.ICE_ETHER_BLOCK);

        basicItem(ModBlocks.ICE_ETHER_DOOR.get().asItem());
    }
    private <T extends Block> void buttonItem(RegistryObject<T> block, RegistryObject<Block> base) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                        "block/" + base.getId().getPath()));
    }
    private <T extends Block> void fenceItem(RegistryObject<T> block, RegistryObject<Block> base) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                        "block/" + base.getId().getPath()));
    }
    private <T extends Block> void wallItem(RegistryObject<T> block, RegistryObject<Block> base) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                        "block/" + base.getId().getPath()));
    }
}
