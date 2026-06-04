package dev.worldgen.mortar.block;

import dev.worldgen.mortar.Mortar;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import java.util.Optional;

public class MortarBlockUtils {

    public static Block buildingGroup(Block anchor, Block block) {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> entries.insertAfter(anchor, block));
        return block;
    }

    public static Block full(String name, Block copiedBlock) {
        return register(name, new Block(settings(name, copiedBlock)));
    }

    public static Block stairs(String name, Block copiedBlock) {
        return register(name, new StairBlock(copiedBlock.defaultBlockState(), settings(name, copiedBlock)));
    }

    public static Block slab(String name, Block copiedBlock) {
        return register(name, new SlabBlock(settings(name, copiedBlock)));
    }

    public static Block wall(String name, Block copiedBlock) {
        return register(name, new WallBlock(settings(name, copiedBlock)));
    }

    public static Block register(String name, Block block) {
        // Create block item
        Item.Properties settings = new Item.Properties();
        settings.setId(ResourceKey.create(Registries.ITEM, Mortar.id(name)));
        settings.useBlockDescriptionPrefix();

        Registry.register(BuiltInRegistries.ITEM, Mortar.id(name), new BlockItem(block, settings));

        return rawRegister(name, block);
    }

    public static Block rawRegister(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, Mortar.id(name), block);
    }

    public static BlockBehaviour.Properties settings(String name, Block copiedBlock) {
        BlockBehaviour.Properties settings = BlockBehaviour.Properties.ofFullCopy(copiedBlock);
        settings.setId(ResourceKey.create(Registries.BLOCK, Mortar.id(name)));
        
        if (name.startsWith("polished_calcite")) {
            settings.sound(MortarBlockSounds.POLISHED_CALCITE);
        }
        
        return settings;
    }

    public static BlockBehaviour.Properties coloredSettings(DyeColor color, String name, Block copiedBlock) {
        BlockBehaviour.Properties settings = BlockBehaviour.Properties.ofFullCopy(copiedBlock);
        settings.setId(ResourceKey.create(Registries.BLOCK, Mortar.id(name))).mapColor(color);
        // Fix loot table id for banners
        settings.overrideLootTable(Optional.of(ResourceKey.create(Registries.LOOT_TABLE, Mortar.id("blocks/" + name.replace("_wall", "")))));

        return settings;
    }
}
