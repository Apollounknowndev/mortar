package dev.worldgen.mortar.item;

import dev.worldgen.mortar.Mortar;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class MortarItemUtils {
    public static Item block(Block block, DyeColor color, String suffix, UnaryOperator<Item.Settings> operator) {
        return register(key(color, suffix), s -> new BlockItem(block, operator.apply(s.useBlockPrefixedTranslationKey())));
    }

    public static RegistryKey<Item> key(DyeColor color, String suffix) {
        return key(color.getId() + "_" + suffix);
    }

    public static RegistryKey<Item> key(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Mortar.id(name));
    }

    public static Item.Settings settings(RegistryKey<Item> key) {
        return new Item.Settings().registryKey(key);
    }

    public static Item register(RegistryKey<Item> key, Function<Item.Settings, Item> creator) {
        Item item = Registry.register(Registries.ITEM, key, creator.apply(settings(key)));
        Item anchor = Registries.ITEM.get(getAnchorId(key.getValue()));

        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((group, entries) -> {
            if (group.contains(anchor.getDefaultStack())) {
                entries.addAfter(anchor, item);
            }
        });

        return item;
    }

    private static Identifier getAnchorId(Identifier id) {
        String path = id.getPath();
        path = path
            .replace("maroon", "brown")
            .replace("scarlet", "red")
            .replace("amber", "orange")
            .replace("pear", "yellow")
            .replace("pine", "green")
            .replace("slate", "blue")
            .replace("lavender", "magenta")
            .replace("salmon", "pink");
        return Identifier.ofVanilla(path);
    }
}
