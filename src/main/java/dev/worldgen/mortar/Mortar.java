package dev.worldgen.mortar;

import dev.worldgen.mortar.block.MortarBlocks;
import dev.worldgen.mortar.item.MortarItems;
import dev.worldgen.mortar.misc.MortarAttachments;
import dev.worldgen.mortar.misc.MortarMapDecorations;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mortar implements ModInitializer {
	public static final String MOD_ID = "mortar";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Have to do this before blocks/items are initialized and added
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> entries.insertBefore(Blocks.BRICKS, Blocks.CALCITE));

		MortarBlocks.init();
		MortarItems.init();
		MortarAttachments.init();
		MortarMapDecorations.init();
		MortarIntegrations.init();
	}

	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}