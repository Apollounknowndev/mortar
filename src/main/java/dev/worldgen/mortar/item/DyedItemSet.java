package dev.worldgen.mortar.item;

import dev.worldgen.mortar.block.set.DyedBlockSet;
import dev.worldgen.mortar.misc.MortarDyes;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public record DyedItemSet(Item maroon, Item scarlet, Item amber, Item pear, Item pine, Item slate, Item lavender, Item salmon) {
    public static DyedItemSet generic(DyedBlockSet set) {
        return create(color ->
            MortarItemUtils.block(set.match(color), color, set.name(), MortarItems::blockName)
        );
    }

    public static DyedItemSet create(Function<DyeColor, Item> creator) {
        List<Item> items = new ArrayList<>();
        for (DyeColor color : MortarDyes.mortarValues()) {
            Item item = creator.apply(color);
            items.add(item);
        }
        return compile(items);
    }

    private static DyedItemSet compile(List<Item> items) {
        return new DyedItemSet(
            items.get(0),
            items.get(1),
            items.get(2),
            items.get(3),
            items.get(4),
            items.get(5),
            items.get(6),
            items.get(7)
        );
    }

    public void forEach(Consumer<Item> action) {
        Stream.of(this.maroon, this.scarlet, this.amber,this.pear, this.pine, this.slate, this.lavender, this.salmon).forEach(action);
    }
}
