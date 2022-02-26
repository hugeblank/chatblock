package me.hugeblank.chatblock.world.gen.feature;

import me.hugeblank.chatblock.Init;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> ORE_JACKGRASS;

    public static void init() {}

    static {
        ORE_JACKGRASS = net.minecraft.world.gen.feature.ConfiguredFeatures.register("ore_jackgrass", Feature.ORE.configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), Init.JACKGRASS.getDefaultState(), 1)));
    }
}
