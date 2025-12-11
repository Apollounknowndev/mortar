package dev.worldgen.mortar.item;

import dev.worldgen.mortar.Mortar;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class MortarItemUtils {
    public static Item block(Block block, DyeColor color, String suffix, UnaryOperator<Item.Properties> operator) {
        return register(key(color, suffix), s -> new BlockItem(block, operator.apply(s.useBlockDescriptionPrefix())));
    }

    public static ResourceKey<Item> key(DyeColor color, String suffix) {
        return key(color.getName() + "_" + suffix);
    }

    public static ResourceKey<Item> key(String name) {
        return ResourceKey.create(Registries.ITEM, Mortar.id(name));
    }

    public static Item.Properties settings(ResourceKey<Item> key) {
        return new Item.Properties().setId(key);
    }

    public static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> creator) {
        Item item = Registry.register(BuiltInRegistries.ITEM, key, creator.apply(settings(key)));
        Item anchor = BuiltInRegistries.ITEM.getValue(getAnchorId(key.identifier()));

        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((group, entries) -> {
            if (group.contains(anchor.getDefaultInstance())) {
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
        return Identifier.withDefaultNamespace(path);
    }
}
