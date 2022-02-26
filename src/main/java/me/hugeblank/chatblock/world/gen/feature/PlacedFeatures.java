package me.hugeblank.chatblock.world.gen.feature;

import me.hugeblank.chatblock.Init;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.List;
import java.util.function.Predicate;

public class PlacedFeatures {
    public static final PlacedFeature ORE_JACKGRASS;

    public static void init() {}

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> PlacedFeature register(String name, PlacedFeature feature, Predicate<BiomeSelectionContext> selector, GenerationStep.Feature category) {
        RegistryKey<PlacedFeature> key = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                new Identifier(Init.ID, name));

        BiomeModifications.addFeature(selector, category, key);
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, key.getValue(), feature);
    }

    static {
        ORE_JACKGRASS = register(
                "ore_jackgrass",
                ConfiguredFeatures.ORE_JACKGRASS.withPlacement(
                        modifiersWithCount(1, HeightRangePlacementModifier.uniform(YOffset.fixed(62), YOffset.fixed(128)))
                ),
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES
        );
    }
}
