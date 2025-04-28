package dev.worldgen.mortar.block;

import dev.worldgen.mortar.Mortar;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.DyeColor;

import java.util.Optional;

public class MortarBlockUtils {

    public static Block buildingGroup(Block anchor, Block block) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.addAfter(anchor, block));
        return block;
    }

    public static Block full(String name, Block copiedBlock) {
        return register(name, new Block(settings(name, copiedBlock)));
    }

    public static Block stairs(String name, Block copiedBlock) {
        return register(name, new StairsBlock(copiedBlock.getDefaultState(), settings(name, copiedBlock)));
    }

    public static Block slab(String name, Block copiedBlock) {
        return register(name, new SlabBlock(settings(name, copiedBlock)));
    }

    public static Block wall(String name, Block copiedBlock) {
        return register(name, new WallBlock(settings(name, copiedBlock)));
    }

    public static Block register(String name, Block block) {
        // Create block item
        Item.Settings settings = new Item.Settings();
        settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, Mortar.id(name)));
        settings.useBlockPrefixedTranslationKey();

        Registry.register(Registries.ITEM, Mortar.id(name), new BlockItem(block, settings));

        return rawRegister(name, block);
    }

    public static Block rawRegister(String name, Block block) {
        return Registry.register(Registries.BLOCK, Mortar.id(name), block);
    }

    public static AbstractBlock.Settings settings(String name, Block copiedBlock) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(copiedBlock);
        settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Mortar.id(name)));
        
        if (name.startsWith("polished_calcite")) {
            settings.sounds(MortarBlockSounds.POLISHED_CALCITE);
        }
        
        return settings;
    }

    public static AbstractBlock.Settings coloredSettings(DyeColor color, String name, Block copiedBlock) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(copiedBlock);
        settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Mortar.id(name))).mapColor(color);
        // Fix loot table id for banners
        settings.lootTable(Optional.of(RegistryKey.of(RegistryKeys.LOOT_TABLE, Mortar.id("blocks/" + name.replace("_wall", "")))));

        return settings;
    }
}
