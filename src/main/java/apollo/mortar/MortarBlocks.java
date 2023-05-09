package apollo.mortar;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MortarBlocks {

    public static final Block STONE_WALL = registerWallBlock("stone_wall", Blocks.STONE);
    public static final Block SMOOTH_STONE_STAIRS = registerStairsBlock("smooth_stone_stairs", Blocks.SMOOTH_STONE);
    public static final Block SMOOTH_STONE_WALL = registerWallBlock("smooth_stone_wall", Blocks.SMOOTH_STONE);
    public static final Block CHISELED_MOSSY_STONE_BRICKS = registerSimpleBlock("chiseled_mossy_stone_bricks", Blocks.STONE_BRICKS);
    public static final Block POLISHED_GRANITE_WALL = registerWallBlock("polished_granite_wall", Blocks.POLISHED_GRANITE);
    public static final Block POLISHED_DIORITE_WALL = registerWallBlock("polished_diorite_wall", Blocks.POLISHED_DIORITE);
    public static final Block POLISHED_ANDESITE_WALL = registerWallBlock("polished_andesite_wall", Blocks.POLISHED_ANDESITE);
    public static final Block CALCITE_STAIRS = registerStairsBlock("calcite_stairs", Blocks.CALCITE);
    public static final Block CALCITE_SLAB = registerSlabBlock("calcite_slab", Blocks.CALCITE);
    public static final Block CALCITE_WALL = registerWallBlock("calcite_wall", Blocks.CALCITE);
    public static final Block POLISHED_CALCITE = registerSimpleBlock("polished_calcite", Blocks.CALCITE);
    public static final Block POLISHED_CALCITE_STAIRS = registerStairsBlock("polished_calcite_stairs", Blocks.CALCITE);
    public static final Block POLISHED_CALCITE_SLAB = registerSlabBlock("polished_calcite_slab", Blocks.CALCITE);
    public static final Block POLISHED_CALCITE_WALL = registerWallBlock("polished_calcite_wall", Blocks.CALCITE);
    public static final Block TUFF_STAIRS = registerStairsBlock("tuff_stairs", Blocks.TUFF);
    public static final Block TUFF_SLAB = registerSlabBlock("tuff_slab", Blocks.TUFF);
    public static final Block TUFF_WALL = registerWallBlock("tuff_wall", Blocks.TUFF);
    public static final Block POLISHED_TUFF = registerSimpleBlock("polished_tuff", Blocks.TUFF);
    public static final Block POLISHED_TUFF_STAIRS = registerStairsBlock("polished_tuff_stairs", Blocks.TUFF);
    public static final Block POLISHED_TUFF_SLAB = registerSlabBlock("polished_tuff_slab", Blocks.TUFF);
    public static final Block POLISHED_TUFF_WALL = registerWallBlock("polished_tuff_wall", Blocks.TUFF);
    public static final Block CHISELED_MUD_BRICKS = registerSimpleBlock("chiseled_mud_bricks", Blocks.MUD_BRICKS);
    public static final Block SMOOTH_SANDSTONE_WALL = registerWallBlock("smooth_sandstone_wall", Blocks.SMOOTH_SANDSTONE);
    public static final Block CUT_SANDSTONE_STAIRS = registerStairsBlock("cut_sandstone_stairs", Blocks.CUT_SANDSTONE);
    public static final Block CUT_SANDSTONE_WALL = registerWallBlock("cut_sandstone_wall", Blocks.CUT_SANDSTONE);
    public static final Block SMOOTH_RED_SANDSTONE_WALL = registerWallBlock("smooth_red_sandstone_wall", Blocks.SMOOTH_RED_SANDSTONE);
    public static final Block CUT_RED_SANDSTONE_STAIRS = registerStairsBlock("cut_red_sandstone_stairs", Blocks.CUT_RED_SANDSTONE);
    public static final Block CUT_RED_SANDSTONE_WALL = registerWallBlock("cut_red_sandstone_wall", Blocks.CUT_RED_SANDSTONE);
    public static final Block CHISELED_PRISMARINE_BRICKS = registerSimpleBlock("chiseled_prismarine_bricks", Blocks.PRISMARINE_BRICKS);
    public static final Block PRISMARINE_BRICK_WALL = registerWallBlock("prismarine_brick_wall", Blocks.PRISMARINE_BRICKS);
    public static final Block DARK_PRISMARINE_WALL = registerWallBlock("dark_prismarine_wall", Blocks.DARK_PRISMARINE);
    public static final Block CHISELED_RED_NETHER_BRICKS = registerSimpleBlock("chiseled_red_nether_bricks", Blocks.RED_NETHER_BRICKS);
    public static final Block CHISELED_END_STONE_BRICKS = registerSimpleBlock("chiseled_end_stone_bricks", Blocks.END_STONE_BRICKS);
    public static final Block PURPUR_WALL = registerWallBlock("purpur_wall", Blocks.PURPUR_BLOCK);
    public static final Block CHISELED_PURPUR_BLOCK = registerSimpleBlock("chiseled_purpur_block", Blocks.PURPUR_BLOCK);
    public static final Block QUARTZ_WALL = registerWallBlock("quartz_wall", Blocks.QUARTZ_BLOCK);
    public static final Block SMOOTH_QUARTZ_WALL = registerWallBlock("smooth_quartz_wall", Blocks.SMOOTH_QUARTZ);
    public static final Block QUARTZ_BRICK_STAIRS = registerStairsBlock("quartz_brick_stairs", Blocks.QUARTZ_BRICKS);
    public static final Block QUARTZ_BRICK_SLAB = registerSlabBlock("quartz_brick_slab", Blocks.QUARTZ_BRICKS);
    public static final Block QUARTZ_BRICK_WALL = registerWallBlock("quartz_brick_wall", Blocks.QUARTZ_BRICKS);


    private static Block registerSimpleBlock(String name, Block copiedBlock) {
        return registerBlock(name, new Block(FabricBlockSettings.copy(copiedBlock)));
    }

    private static Block registerStairsBlock(String name, Block copiedBlock) {
        return registerBlock(name, new StairsBlock(copiedBlock.getDefaultState(), AbstractBlock.Settings.copy(copiedBlock)));
    }

    private static Block registerSlabBlock(String name, Block copiedBlock) {
        return registerBlock(name, new SlabBlock(AbstractBlock.Settings.copy(copiedBlock)));
    }

    private static Block registerWallBlock(String name, Block copiedBlock) {
        return registerBlock(name, new WallBlock(AbstractBlock.Settings.copy(copiedBlock)));
    }
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block, ItemGroup.DECORATIONS);
        return Registry.register(Registry.BLOCK, new Identifier(MortarMod.ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(MortarMod.ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerMortarBlocks() {
    }
}
