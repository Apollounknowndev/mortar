package dev.worldgen.mortar;

import dev.worldgen.mortar.block.MortarBlocks;
import dev.worldgen.mortar.item.MortarItems;
import dev.worldgen.mortar.misc.MortarAttachments;
import dev.worldgen.mortar.misc.MortarMapDecorations;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mortar implements ModInitializer {
	public static final String MOD_ID = "mortar";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Have to do this before blocks/items are initialized and added
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> entries.addBefore(Blocks.BRICKS, Blocks.CALCITE));

		MortarBlocks.init();
		MortarItems.init();
		MortarAttachments.init();
		MortarMapDecorations.init();
		MortarIntegrations.init();
	}

	public static ResourceLocation id(String name) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
	}
}