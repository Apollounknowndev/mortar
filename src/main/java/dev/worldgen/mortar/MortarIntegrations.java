package dev.worldgen.mortar;

import dev.worldgen.mortar.item.MortarItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public class MortarIntegrations {
    private static final ResourceKey<LootTable> SHEEP_ENTITY = lootTable(Identifier.withDefaultNamespace("entities/sheep"));

    public static void init() {
        LootTableEvents.MODIFY.register((key, builder, source, registries) -> {
            if (key.equals(BuiltInLootTables.SHEPHERD_GIFT)) {
                builder.modifyPools(pool -> MortarItems.WOOLS.forEach(item -> pool.add(LootItem.lootTableItem(item))));
            }

            if (key.equals(BuiltInLootTables.SHEAR_SHEEP)) {
                builder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(lootTable(Mortar.id("shearing/sheep")))));
            }

            if (key.equals(SHEEP_ENTITY)) {
                builder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(lootTable(Mortar.id("entities/sheep")))));
            }
        });
    }

    private static ResourceKey<LootTable> lootTable(Identifier id) {
        return ResourceKey.create(Registries.LOOT_TABLE, id);
    }
}
