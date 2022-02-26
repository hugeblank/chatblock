package me.hugeblank.chatblock;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.hugeblank.chatblock.block.JackGrass;
import me.hugeblank.chatblock.config.CBConfig;
import me.hugeblank.chatblock.world.gen.feature.ConfiguredFeatures;
import me.hugeblank.chatblock.world.gen.feature.PlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
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

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Init implements ModInitializer {
	public static final String ID = "chatblock";
	public static CBConfig config;

	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	public static final Block JACKGRASS = new JackGrass(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK));

	public static void debug(String s) {
		if (config.getDebug()) LOGGER.info(s);
	}

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

		File configFile = new File(FabricLoader.getInstance().getConfigDirectory(), "chatblock.json");
		try (FileReader reader = new FileReader(configFile)) {
			config = new Gson().fromJson(reader, CBConfig.class);
			debug("Successfully loaded config");
		} catch (IOException e) {
			config = new CBConfig(false, true);
			LOGGER.info("Config not found, generating a new one.");
			try (FileWriter writer = new FileWriter(configFile)) {
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(config));
			} catch (IOException e2) {
				LOGGER.error("Failed to generate new config", e2);
			}
		}

		if (config.getJackgrass()) {
			registerBlock("jackgrass", JACKGRASS, ItemGroup.BUILDING_BLOCKS);
			ConfiguredFeatures.init();
			PlacedFeatures.init();
		}
	}
}
