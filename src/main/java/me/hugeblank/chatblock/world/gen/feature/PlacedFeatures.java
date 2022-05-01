package me.hugeblank.chatblock.world.gen.feature;

import me.hugeblank.chatblock.Init;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;
import java.util.function.Predicate;

public class PlacedFeatures {
    public static final RegistryEntry<PlacedFeature> ORE_JACKGRASS;

    public static void init() {}

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    private static RegistryEntry<PlacedFeature> register(String name, RegistryEntry<? extends ConfiguredFeature<?, ?>> featureEntry, Predicate<BiomeSelectionContext> selector, GenerationStep.Feature category, List<PlacementModifier> modifiers) {
        RegistryKey<PlacedFeature> key = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Init.ID, name));
        RegistryEntry<PlacedFeature> entry = BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, key, new PlacedFeature(RegistryEntry.upcast(featureEntry), modifiers));
        BiomeModifications.addFeature(selector, category, key);
        return entry;
    }

    static {
        ORE_JACKGRASS = register(
                "ore_jackgrass",
                ConfiguredFeatures.ORE_JACKGRASS,
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                modifiersWithCount(128, HeightRangePlacementModifier.uniform(YOffset.fixed(48), YOffset.fixed(320)))
        );
    }
}
