package dev.worldgen.mortar;

import dev.worldgen.mortar.block.MortarBlocks;
import dev.worldgen.mortar.item.MortarItems;
import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public class MortarIntegrations {
    private static final ResourceKey<LootTable> SHEEP_ENTITY = lootTable(ResourceLocation.withDefaultNamespace("entities/sheep"));

    public static void init() {
        LootTableEvents.MODIFY.register((key, builder, source, registries) -> {
            if (key.equals(BuiltInLootTables.SHEPHERD_GIFT)) {
                ((FabricLootTableBuilder)builder).modifyPools(pool -> MortarItems.WOOLS.forEach(item -> pool.add(LootItem.lootTableItem(item))));
            }

            if (key.equals(BuiltInLootTables.SHEAR_SHEEP)) {
                builder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(lootTable(Mortar.id("shearing/sheep")))));
            }

            if (key.equals(SHEEP_ENTITY)) {
                builder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(lootTable(Mortar.id("entities/sheep")))));
            }
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, factories ->
            MortarItems.BANNERS.forEach(item ->
                factories.add(new VillagerTrades.ItemsForEmeralds(item, 3, 1, 12, 15))
            )
        );

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 3, factories -> {
            factories.add(new VillagerTrades.EmeraldForItems(Blocks.CALCITE, 16, 16, 20));
            factories.add(new VillagerTrades.ItemsForEmeralds(MortarBlocks.POLISHED_CALCITE.full(), 1, 4, 16, 10));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 4, factories -> {
            MortarItems.TERRACOTTAS.forEach(item ->
                factories.add(new VillagerTrades.ItemsForEmeralds(item, 1, 1, 12, 15))
            );
            MortarItems.GLAZED_TERRACOTTAS.forEach(item ->
                factories.add(new VillagerTrades.ItemsForEmeralds(item, 1, 1, 12, 15))
            );
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 2, factories -> {
            MortarItems.WOOLS.forEach(item ->
                factories.add(new VillagerTrades.ItemsForEmeralds(item, 1, 1, 16, 5))
            );
            MortarItems.CARPETS.forEach(item ->
                factories.add(new VillagerTrades.ItemsForEmeralds(item, 1, 4, 16, 5))
            );
            factories.add(new VillagerTrades.EmeraldForItems(MortarItems.DYES.pear(), 12, 16, 10));
            factories.add(new VillagerTrades.EmeraldForItems(MortarItems.DYES.lavender(), 12, 16, 10));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 3, factories -> {
            MortarItems.BEDS.forEach(item ->
                factories.add(new VillagerTrades.ItemsForEmeralds(item, 3, 1, 12, 10))
            );
            factories.add(new VillagerTrades.EmeraldForItems(MortarItems.DYES.scarlet(), 12, 16, 20));
            factories.add(new VillagerTrades.EmeraldForItems(MortarItems.DYES.amber(), 12, 16, 20));
            factories.add(new VillagerTrades.EmeraldForItems(MortarItems.DYES.salmon(), 12, 16, 20));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 4, factories -> {
            MortarItems.BANNERS.forEach(item ->
                factories.add(new VillagerTrades.ItemsForEmeralds(item, 3, 1, 12, 15))
            );
            factories.add(new VillagerTrades.EmeraldForItems(MortarItems.DYES.maroon(), 12, 16, 30));
            factories.add(new VillagerTrades.EmeraldForItems(MortarItems.DYES.pine(), 12, 16, 30));
            factories.add(new VillagerTrades.EmeraldForItems(MortarItems.DYES.slate(), 12, 16, 30));
        });

        TradeOfferHelper.registerWanderingTraderOffers(builder ->
            MortarItems.DYES.forEach(item ->
                builder.addOffersToPool(TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL, new VillagerTrades.ItemsForEmeralds(item, 1, 3, 12, 1))
            )
        );
    }

    private static ResourceKey<LootTable> lootTable(ResourceLocation id) {
        return ResourceKey.create(Registries.LOOT_TABLE, id);
    }
}
