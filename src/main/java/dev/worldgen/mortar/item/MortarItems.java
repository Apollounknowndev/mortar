package dev.worldgen.mortar.item;

import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;

import static dev.worldgen.mortar.item.MortarItemUtils.*;

public interface MortarItems {
    DyedItemSet BEDS = DyedItemSet.create(color -> register(
        key(color, "bed"),
        settings -> new BedItem(MortarBlocks.BEDS.match(color), blockName(settings).maxCount(1))
    ));
    DyedItemSet BANNERS = DyedItemSet.create(color -> register(
        key(color, "banner"),
        settings -> new BannerItem(MortarBlocks.BANNERS.match(color), MortarBlocks.WALL_BANNERS.match(color), blockName(settings).maxCount(16))
    ));
    DyedItemSet BUNDLES = DyedItemSet.create(color -> register(
        key(color, "bundle"),
        s -> new BundleItem(s.maxCount(1).component(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT))
    ));
    DyedItemSet CANDLES = DyedItemSet.generic(MortarBlocks.CANDLES);
    DyedItemSet CARPETS = DyedItemSet.create(color -> {
        RegistryKey<Item> key = key(color, "carpet");
        return register(
            key,
            settings -> new BlockItem(
                MortarBlocks.CARPETS.match(color),
                    blockName(settings).component(DataComponentTypes.EQUIPPABLE, EquippableComponent
                    .builder(EquipmentSlot.BODY)
                    .equipSound(SoundEvents.ENTITY_LLAMA_SWAG)
                    .model(RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, key.getValue()))
                    .allowedEntities(EntityType.LLAMA, EntityType.TRADER_LLAMA)
                    .build()
                )
            )
        );
    });
    DyedItemSet CONCRETE_POWDERS = DyedItemSet.generic(MortarBlocks.CONCRETE_POWDERS);
    DyedItemSet CONCRETES = DyedItemSet.generic(MortarBlocks.CONCRETES);
    DyedItemSet DYES = DyedItemSet.create(color ->
        register(key(color, "dye"), settings -> new DyeItem(color, settings))
    );
    DyedItemSet GLAZED_TERRACOTTAS = DyedItemSet.generic(MortarBlocks.GLAZED_TERRACOTTAS);
    DyedItemSet SHULKER_BOXES = DyedItemSet.create(color ->
        block(MortarBlocks.SHULKER_BOXES.match(color), color, "shulker_box", settings -> blockName(settings).maxCount(1))
    );
    DyedItemSet STAINED_GLASSES = DyedItemSet.generic(MortarBlocks.STAINED_GLASSES);
    DyedItemSet STAINED_GLASS_PANES = DyedItemSet.generic(MortarBlocks.STAINED_GLASS_PANES);
    DyedItemSet TERRACOTTAS = DyedItemSet.generic(MortarBlocks.TERRACOTTAS);
    DyedItemSet WOOLS = DyedItemSet.generic(MortarBlocks.WOOLS);

    /*DyedItemSet HARNESSES = DyedItemSet.create(color -> register(
        key(color, "harness"),
        settings -> new Item(settings.maxCount(1))
    ));*/

    static Item.Settings blockName(Item.Settings settings) {
        return settings.useBlockPrefixedTranslationKey();
    }

    static void init() {
    }
}
