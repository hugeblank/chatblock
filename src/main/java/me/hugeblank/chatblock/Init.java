package me.hugeblank.chatblock;

import me.hugeblank.chatblock.block.JackGrass;
import me.hugeblank.chatblock.world.gen.feature.ConfiguredFeatures;
import me.hugeblank.chatblock.world.gen.feature.PlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Init implements ModInitializer {
	public static final String ID = "chatblock";

	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	public static final Block JACKGRASS = new JackGrass(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK));

	private static void registerBlock(String name, Block block) {
		Identifier id = new Identifier(ID, name);
		Registry.register(Registry.BLOCK, id, block);
	}

	private static void registerBlock(String name, Block block, ItemGroup group) {
		Identifier id = new Identifier(ID, name);
		Registry.register(Registry.BLOCK, id, block);
		if (group != null) {
			Item item = new BlockItem(block, new Item.Settings().group(group));
			Registry.register(Registry.ITEM, id, item);
		}
	}

	@Override
	public void onInitialize() {
		registerBlock("jackgrass", JACKGRASS, ItemGroup.BUILDING_BLOCKS);
		ConfiguredFeatures.init();
		PlacedFeatures.init();
	}
}
