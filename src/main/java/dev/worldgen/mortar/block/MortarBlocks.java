package dev.worldgen.mortar.block;

import dev.worldgen.mortar.Mortar;
import dev.worldgen.mortar.block.set.DyedBlockSet;
import dev.worldgen.mortar.block.set.GenericSet;
import dev.worldgen.mortar.mixin.integration.PointOfInterestTypesAccessor;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import java.util.List;
import java.util.Map;

import static dev.worldgen.mortar.block.MortarBlockUtils.register;
import static dev.worldgen.mortar.block.MortarBlockUtils.*;
import static dev.worldgen.mortar.block.set.DyedBlockSet.BlockCreator.colored;
import static dev.worldgen.mortar.block.set.DyedBlockSet.BlockCreator.colorless;
import static dev.worldgen.mortar.block.set.GenericSet.brickSet;
import static dev.worldgen.mortar.block.set.GenericSet.set;
import static net.minecraft.world.level.block.Blocks.*;

@SuppressWarnings("unused")
public interface MortarBlocks {
    Block STONE_WALL = buildingGroup(STONE_SLAB, wall("stone_wall", STONE));
    Block SMOOTH_STONE_STAIRS = buildingGroup(SMOOTH_STONE, stairs("smooth_stone_stairs", SMOOTH_STONE));
    Block SMOOTH_STONE_WALL = buildingGroup(SMOOTH_STONE_SLAB, wall("smooth_stone_wall", SMOOTH_STONE));
    Block CHISELED_MOSSY_STONE_BRICKS = buildingGroup(MOSSY_STONE_BRICK_SLAB, full("chiseled_mossy_stone_bricks", STONE_BRICKS));

    Block POLISHED_ANDESITE_WALL = buildingGroup(POLISHED_ANDESITE_SLAB, wall("polished_andesite_wall", ANDESITE));
    GenericSet ANDESITE_BRICKS = brickSet("andesite_brick", ANDESITE, POLISHED_ANDESITE_WALL);
    Block CHISELED_ANDESITE_BRICKS = buildingGroup(ANDESITE_BRICKS.wall(), full("chiseled_andesite_bricks", ANDESITE));

    Block POLISHED_DIORITE_WALL = buildingGroup(POLISHED_DIORITE_SLAB, wall("polished_diorite_wall", DIORITE));
    GenericSet DIORITE_BRICKS = brickSet("diorite_brick", DIORITE, POLISHED_DIORITE_WALL);
    Block CHISELED_DIORITE_BRICKS = buildingGroup(DIORITE_BRICKS.wall(), full("chiseled_diorite_bricks", DIORITE));

    Block POLISHED_GRANITE_WALL = buildingGroup(POLISHED_GRANITE_SLAB, wall("polished_granite_wall", GRANITE));
    GenericSet GRANITE_BRICKS = brickSet("granite_brick", GRANITE, POLISHED_GRANITE_WALL);
    Block CHISELED_GRANITE_BRICKS = buildingGroup(GRANITE_BRICKS.wall(), full("chiseled_granite_bricks", GRANITE));

    Block CALCITE_STAIRS = buildingGroup(CALCITE, stairs("calcite_stairs", CALCITE));
    Block CALCITE_SLAB = buildingGroup(CALCITE_STAIRS, slab("calcite_slab", CALCITE));
    Block CALCITE_WALL = buildingGroup(CALCITE_SLAB, wall("calcite_wall", CALCITE));
    GenericSet POLISHED_CALCITE = set("polished_calcite", CALCITE, CALCITE_WALL);
    GenericSet CALCITE_BRICKS = brickSet("calcite_brick", CALCITE, POLISHED_CALCITE.wall());
    Block CHISELED_CALCITE_BRICKS = buildingGroup(CALCITE_BRICKS.wall(), full("chiseled_calcite_bricks", CALCITE));

    Block CHISELED_MUD_BRICKS = buildingGroup(MUD_BRICK_WALL, full("chiseled_mud_bricks", MUD_BRICKS));
    Block SMOOTH_SANDSTONE_WALL = buildingGroup(SMOOTH_SANDSTONE_SLAB, wall("smooth_sandstone_wall", SMOOTH_SANDSTONE));
    Block CUT_SANDSTONE_STAIRS = buildingGroup(CUT_SANDSTONE, stairs("cut_sandstone_stairs", CUT_SANDSTONE));
    Block CUT_SANDSTONE_WALL = buildingGroup(CUT_SANDSTONE_SLAB, wall("cut_sandstone_wall", CUT_SANDSTONE));
    Block SMOOTH_RED_SANDSTONE_WALL = buildingGroup(SMOOTH_RED_SANDSTONE_SLAB, wall("smooth_red_sandstone_wall", SMOOTH_RED_SANDSTONE));
    Block CUT_RED_SANDSTONE_STAIRS = buildingGroup(CUT_RED_SANDSTONE, stairs("cut_red_sandstone_stairs", CUT_RED_SANDSTONE));
    Block CUT_RED_SANDSTONE_WALL = buildingGroup(CUT_RED_SANDSTONE_SLAB, wall("cut_red_sandstone_wall", CUT_RED_SANDSTONE));
    Block PRISMARINE_BRICK_WALL = buildingGroup(PRISMARINE_BRICK_SLAB, wall("prismarine_brick_wall", PRISMARINE_BRICKS));
    Block CHISELED_PRISMARINE_BRICKS = buildingGroup(PRISMARINE_BRICK_WALL, full("chiseled_prismarine_bricks", PRISMARINE_BRICKS));
    Block DARK_PRISMARINE_WALL = buildingGroup(DARK_PRISMARINE_STAIRS, wall("dark_prismarine_wall", DARK_PRISMARINE));
    Block CHISELED_RED_NETHER_BRICKS = buildingGroup(RED_NETHER_BRICK_WALL, full("chiseled_red_nether_bricks", RED_NETHER_BRICKS));
    Block CHISELED_END_STONE_BRICKS = buildingGroup(END_STONE_BRICK_WALL, full("chiseled_end_stone_bricks", END_STONE_BRICKS));
    Block PURPUR_WALL = buildingGroup(PURPUR_SLAB, wall("purpur_wall", PURPUR_BLOCK));
    Block CHISELED_PURPUR_BLOCK = buildingGroup(PURPUR_WALL, full("chiseled_purpur_block", PURPUR_BLOCK));
    Block QUARTZ_WALL = buildingGroup(QUARTZ_SLAB, wall("quartz_wall", QUARTZ_BLOCK));
    Block SMOOTH_QUARTZ_WALL = buildingGroup(SMOOTH_QUARTZ_SLAB, wall("smooth_quartz_wall", SMOOTH_QUARTZ));
    Block QUARTZ_BRICK_STAIRS = buildingGroup(QUARTZ_BRICKS, stairs("quartz_brick_stairs", QUARTZ_BRICKS));
    Block QUARTZ_BRICK_SLAB = buildingGroup(QUARTZ_BRICK_STAIRS, slab("quartz_brick_slab", QUARTZ_BRICKS));
    Block QUARTZ_BRICK_WALL = buildingGroup(QUARTZ_BRICK_SLAB, wall("quartz_brick_wall", QUARTZ_BRICKS));

    DyedBlockSet BANNERS = DyedBlockSet.create(colored(BannerBlock::new), "banner", WHITE_BANNER, BlockEntityType.BANNER);
    DyedBlockSet BEDS = DyedBlockSet.create(colored(BedBlock::new), "bed", WHITE_BED, BlockEntityType.BED);
    DyedBlockSet CANDLES = DyedBlockSet.create(colorless(CandleBlock::new), "candle", WHITE_CANDLE);
    DyedBlockSet CANDLE_CAKES = DyedBlockSet.create(DyedBlockSet.BlockCreator.CANDLE_CAKE, "candle_cake", WHITE_CANDLE_CAKE);
    DyedBlockSet CARPETS = DyedBlockSet.create(colored(WoolCarpetBlock::new), "carpet", WHITE_CARPET);
    DyedBlockSet CONCRETES = DyedBlockSet.generic("concrete", WHITE_CONCRETE);
    DyedBlockSet CONCRETE_POWDERS = DyedBlockSet.create(DyedBlockSet.BlockCreator.CONCRETE_POWDER, "concrete_powder", WHITE_CONCRETE_POWDER);
    DyedBlockSet GLAZED_TERRACOTTAS = DyedBlockSet.create(colorless(GlazedTerracottaBlock::new), "glazed_terracotta", WHITE_GLAZED_TERRACOTTA);
    DyedBlockSet SHULKER_BOXES = DyedBlockSet.create(colored(ShulkerBoxBlock::new), "shulker_box", WHITE_SHULKER_BOX, BlockEntityType.SHULKER_BOX);
    DyedBlockSet STAINED_GLASSES = DyedBlockSet.create(colored(StainedGlassBlock::new), "stained_glass", WHITE_STAINED_GLASS);
    DyedBlockSet STAINED_GLASS_PANES = DyedBlockSet.create(colored(StainedGlassPaneBlock::new), "stained_glass_pane", WHITE_STAINED_GLASS_PANE);
    DyedBlockSet TERRACOTTAS = DyedBlockSet.generic("terracotta", WHITE_TERRACOTTA);
    DyedBlockSet WALL_BANNERS = DyedBlockSet.create(colored(WallBannerBlock::new), "wall_banner", WHITE_WALL_BANNER, BlockEntityType.BANNER);
    DyedBlockSet WOOLS = DyedBlockSet.generic("wool", WHITE_WOOL);

    Block BLUE_AMARANTH = register("blue_amaranth", new FlowerBlock(MobEffects.SPEED, 10, settings("blue_amaranth", CORNFLOWER)));
    Block SNAPDRAGON = register("snapdragon", new TallFlowerBlock(settings("snapdragon", LILAC)));

    static void init() {
        Map<BlockState, Holder<PoiType>> poiStatesToTypes = PointOfInterestTypesAccessor.getPoiStatesToTypes();
        Holder<PoiType> home = BuiltInRegistries.POINT_OF_INTEREST_TYPE.get(PoiTypes.HOME.location()).get();
        BEDS.stream().map(MortarBlocks::getBedHeads).forEach(bedHeads -> bedHeads.forEach(bedHead -> poiStatesToTypes.put(bedHead, home)));

        FlammableBlockRegistry.getDefaultInstance().add(tag("wools"), 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(tag("carpets"), 60, 20);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.addAfter(CORNFLOWER, BLUE_AMARANTH);
            entries.addAfter(LILAC, SNAPDRAGON);
        });
    }

    private static List<BlockState> getBedHeads(Block block) {
        return block.getStateDefinition().getPossibleStates().stream().filter(state -> state.getValue(BedBlock.PART) == BedPart.HEAD).toList();
    }

    private static TagKey<Block> tag(String name) {
        return TagKey.create(Registries.BLOCK, Mortar.id(name));
    }
}
