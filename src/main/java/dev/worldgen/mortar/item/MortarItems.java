package dev.worldgen.mortar.item;

import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.equipment.Equippable;

import static dev.worldgen.mortar.item.MortarItemUtils.*;

public interface MortarItems {
    DyedItemSet BEDS = DyedItemSet.create(color -> register(
        key(color, "bed"),
        settings -> new BedItem(MortarBlocks.BEDS.match(color), blockName(settings).stacksTo(1))
    ));
    DyedItemSet BANNERS = DyedItemSet.create(color -> register(
        key(color, "banner"),
        settings -> new BannerItem(MortarBlocks.BANNERS.match(color), MortarBlocks.WALL_BANNERS.match(color), blockName(settings).stacksTo(16))
    ));
    DyedItemSet BUNDLES = DyedItemSet.create(color -> register(
        key(color, "bundle"),
        s -> new BundleItem(s.stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
    ));
    DyedItemSet CANDLES = DyedItemSet.generic(MortarBlocks.CANDLES);
    DyedItemSet CARPETS = DyedItemSet.create(color -> {
        ResourceKey<Item> key = key(color, "carpet");
        return register(
            key,
            settings -> new BlockItem(
                MortarBlocks.CARPETS.match(color),
                settings.useBlockDescriptionPrefix().component(DataComponents.EQUIPPABLE, Equippable.llamaSwag(color))
            )
        );
    });
    DyedItemSet CONCRETE_POWDERS = DyedItemSet.generic(MortarBlocks.CONCRETE_POWDERS);
    DyedItemSet CONCRETES = DyedItemSet.generic(MortarBlocks.CONCRETES);
    DyedItemSet DYES = DyedItemSet.create(color ->
        register(key(color, "dye"), settings -> new DyeItem(settings.component(DataComponents.DYE, color)))
    );
    DyedItemSet GLAZED_TERRACOTTAS = DyedItemSet.generic(MortarBlocks.GLAZED_TERRACOTTAS);
    DyedItemSet HARNESSES = DyedItemSet.create(color ->
        register(
            key(color, "harness"),
            settings -> new Item(settings.stacksTo(1).component(DataComponents.EQUIPPABLE, Equippable.harness(color)))
        )
    );
    DyedItemSet SHULKER_BOXES = DyedItemSet.create(color ->
        block(MortarBlocks.SHULKER_BOXES.match(color), color, "shulker_box", settings -> blockName(settings).stacksTo(1))
    );
    DyedItemSet STAINED_GLASSES = DyedItemSet.generic(MortarBlocks.STAINED_GLASSES);
    DyedItemSet STAINED_GLASS_PANES = DyedItemSet.generic(MortarBlocks.STAINED_GLASS_PANES);
    DyedItemSet TERRACOTTAS = DyedItemSet.generic(MortarBlocks.TERRACOTTAS);
    DyedItemSet WOOLS = DyedItemSet.generic(MortarBlocks.WOOLS);

    static Item.Properties blockName(Item.Properties settings) {
        return settings.useBlockDescriptionPrefix();
    }

    static void init() {
    }
}
