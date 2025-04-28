package dev.worldgen.mortar.misc;

import dev.worldgen.mortar.Mortar;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public interface MortarMapDecorations {
    Map<DyeColor, RegistryEntry<MapDecorationType>> DYE_TO_DECORATION = new HashMap<>();

    private static void register(DyeColor color) {
        Identifier id = Mortar.id(color.getId() + "_banner");
        RegistryEntry.Reference<MapDecorationType> decoration = Registry.registerReference(Registries.MAP_DECORATION_TYPE, id, new MapDecorationType(id, true, -1, false, true));
        DYE_TO_DECORATION.put(color, decoration);
    }

    static void init() {
        MortarDyes.mortarValues().forEach(MortarMapDecorations::register);
    }
}
