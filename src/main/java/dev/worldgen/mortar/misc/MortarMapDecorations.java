package dev.worldgen.mortar.misc;

import dev.worldgen.mortar.Mortar;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;

import java.util.HashMap;
import java.util.Map;

public interface MortarMapDecorations {
    Map<DyeColor, Holder<MapDecorationType>> DYE_TO_DECORATION = new HashMap<>();

    private static void register(DyeColor color) {
        Identifier id = Mortar.id(color.getName() + "_banner");
        Holder.Reference<MapDecorationType> decoration = Registry.registerForHolder(BuiltInRegistries.MAP_DECORATION_TYPE, id, new MapDecorationType(id, true, -1, false, true));
        DYE_TO_DECORATION.put(color, decoration);
    }

    static void init() {
        Mortar.DYES.forEach(MortarMapDecorations::register);
    }
}
