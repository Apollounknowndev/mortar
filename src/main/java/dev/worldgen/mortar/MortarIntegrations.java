package dev.worldgen.mortar;

import dev.worldgen.mortar.block.MortarBlocks;
import dev.worldgen.mortar.item.MortarItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public class MortarIntegrations {
    private static final RegistryKey<LootTable> SHEEP_ENTITY = lootTable(Identifier.ofVanilla("entities/sheep"));

    public static void init() {
        LootTableEvents.MODIFY.register((key, builder, source, registries) -> {
            if (key.equals(LootTables.HERO_OF_THE_VILLAGE_SHEPHERD_GIFT_GAMEPLAY)) {
                builder.modifyPools(pool -> MortarItems.WOOLS.stream().forEach(item -> pool.with(ItemEntry.builder(item))));
            }

            if (key.equals(LootTables.SHEEP_SHEARING)) {
                builder.pool(LootPool.builder().with(LootTableEntry.builder(lootTable(Mortar.id("shearing/sheep")))));
            }

            if (key.equals(SHEEP_ENTITY)) {
                builder.pool(LootPool.builder().with(LootTableEntry.builder(lootTable(Mortar.id("entities/sheep")))));
            }
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, factories ->
            MortarItems.BANNERS.stream().forEach(item ->
                factories.add(new TradeOffers.SellItemFactory(item, 3, 1, 12, 15))
            )
        );

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 3, factories -> {
            factories.add(new TradeOffers.BuyItemFactory(Blocks.CALCITE, 16, 16, 20));
            factories.add(new TradeOffers.SellItemFactory(MortarBlocks.POLISHED_CALCITE.full(), 1, 4, 16, 10));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 4, factories -> {
            MortarItems.TERRACOTTAS.stream().forEach(item ->
                factories.add(new TradeOffers.SellItemFactory(item, 1, 1, 12, 15))
            );
            MortarItems.GLAZED_TERRACOTTAS.stream().forEach(item ->
                factories.add(new TradeOffers.SellItemFactory(item, 1, 1, 12, 15))
            );
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 2, factories -> {
            MortarItems.WOOLS.stream().forEach(item ->
                factories.add(new TradeOffers.SellItemFactory(item, 1, 1, 16, 5))
            );
            MortarItems.CARPETS.stream().forEach(item ->
                factories.add(new TradeOffers.SellItemFactory(item, 1, 4, 16, 5))
            );
            factories.add(new TradeOffers.BuyItemFactory(MortarItems.DYES.pear(), 12, 16, 10));
            factories.add(new TradeOffers.BuyItemFactory(MortarItems.DYES.lavender(), 12, 16, 10));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 3, factories -> {
            MortarItems.BEDS.stream().forEach(item ->
                factories.add(new TradeOffers.SellItemFactory(item, 3, 1, 12, 10))
            );
            factories.add(new TradeOffers.BuyItemFactory(MortarItems.DYES.scarlet(), 12, 16, 20));
            factories.add(new TradeOffers.BuyItemFactory(MortarItems.DYES.amber(), 12, 16, 20));
            factories.add(new TradeOffers.BuyItemFactory(MortarItems.DYES.salmon(), 12, 16, 20));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 4, factories -> {
            MortarItems.BANNERS.stream().forEach(item ->
                factories.add(new TradeOffers.SellItemFactory(item, 3, 1, 12, 15))
            );
            factories.add(new TradeOffers.BuyItemFactory(MortarItems.DYES.maroon(), 12, 16, 30));
            factories.add(new TradeOffers.BuyItemFactory(MortarItems.DYES.pine(), 12, 16, 30));
            factories.add(new TradeOffers.BuyItemFactory(MortarItems.DYES.slate(), 12, 16, 30));
        });

        TradeOfferHelper.registerWanderingTraderOffers(builder ->
            MortarItems.DYES.stream().forEach(item ->
                builder.addOffersToPool(TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL, new TradeOffers.SellItemFactory(item, 1, 3, 12, 1))
            )
        );
    }

    private static RegistryKey<LootTable> lootTable(Identifier id) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, id);
    }
}
