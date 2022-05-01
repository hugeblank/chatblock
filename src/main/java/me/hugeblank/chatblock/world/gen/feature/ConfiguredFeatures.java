package me.hugeblank.chatblock.world.gen.feature;

import me.hugeblank.chatblock.Init;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ConfiguredFeatures {
    public static final RegistryEntry<? extends ConfiguredFeature<?,?>> ORE_JACKGRASS;

    public static void init() {}

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<? extends ConfiguredFeature<?,?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Init.ID, id), new ConfiguredFeature(feature, config));
    }

    static {
        ORE_JACKGRASS = register("ore_jackgrass", Feature.ORE, new OreFeatureConfig(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), Init.JACKGRASS.getDefaultState(), 3));
    }
}
