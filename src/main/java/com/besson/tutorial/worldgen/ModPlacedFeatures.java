package com.besson.tutorial.worldgen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ICE_ETHER_TREE_PLACED_KEY = createKey("ice_ether_tree_placed");
    public static final ResourceKey<PlacedFeature> SIMPLE_FLOWER_PLACED_KEY = createKey("simple_flower_placed");
    public static final ResourceKey<PlacedFeature> ICE_ETHER_ORE_PLACED_KEY = createKey("ice_ether_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_ICE_ETHER_ORE_PLACED_KEY = createKey("nether_ice_ether_ore_placed");
    public static final ResourceKey<PlacedFeature> END_ICE_ETHER_ORE_PLACED_KEY = createKey("end_ice_ether_ore_placed");
    
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, ICE_ETHER_TREE_PLACED_KEY,
                holderGetter.getOrThrow(ModConfiguredFeatures.ICE_ETHER_TREE_KEY),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(2, 0.1f, 2),
                        ModBlocks.ICE_ETHER_TREE_SAPLING.get()));
        
        PlacementUtils.register(context, SIMPLE_FLOWER_PLACED_KEY,
                holderGetter.getOrThrow(ModConfiguredFeatures.SIMPLE_FLOWER_KEY),
                RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        
        PlacementUtils.register(context, ICE_ETHER_ORE_PLACED_KEY, holderGetter.getOrThrow(ModConfiguredFeatures.ICE_ETHER_ORE_KEY),
                commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));
        PlacementUtils.register(context, NETHER_ICE_ETHER_ORE_PLACED_KEY, holderGetter.getOrThrow(ModConfiguredFeatures.NETHER_ICE_ETHER_ORE_KEY),
                commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(20), VerticalAnchor.absolute(80))));
        PlacementUtils.register(context, END_ICE_ETHER_ORE_PLACED_KEY, holderGetter.getOrThrow(ModConfiguredFeatures.END_ICE_ETHER_ORE_KEY),
                commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.absolute(60))));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }
    
    public static ResourceKey<PlacedFeature> createKey(String pKey) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, pKey));
    }
}
