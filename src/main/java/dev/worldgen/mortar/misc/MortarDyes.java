package dev.worldgen.mortar.misc;

import dev.worldgen.mortar.mixin.dye.DyeColorCreator;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;

public abstract class MortarDyes {
    private static DyeColor[] VANILLA_VALUES = new DyeColor[0];
    private static final List<DyeColor> MORTAR_VALUES = new ArrayList<>();

    public static final DyeColor MAROON = create("maroon", 16, 0x7c3535, 7684924, 8069666, MapColor.CRIMSON_HYPHAE);
    public static final DyeColor SCARLET = create("scarlet", 17, 0xe34300, 15426601, 16728836, MapColor.TERRACOTTA_ORANGE);
    public static final DyeColor AMBER = create("amber", 18, 0xe89700, 14461518, 16749312, MapColor.TERRACOTTA_YELLOW);
    public static final DyeColor PEAR = create("pear", 19, 0xc6e259, 12902529, 12710194, MapColor.GRASS);
    public static final DyeColor PINE = create("pine", 20, 0x2c4a30, 4219972, 2709552, MapColor.TERRACOTTA_GREEN);
    public static final DyeColor SLATE = create("slate", 21, 0x465274, 6055297, 4676237, MapColor.TERRACOTTA_BLUE);
    public static final DyeColor LAVENDER = create("lavender", 22, 0xcd8dcc, 13345486, 13660105, MapColor.ICE);
    public static final DyeColor SALMON = create("salmon", 23, 0xfa6c5a, 15964041, 16731978, MapColor.CRIMSON_STEM);

    private static DyeColor create(String name, int id, int entityColor, int fireworkColor, int signColor, MapColor mapColor) {
        DyeColor color = DyeColorCreator.create(name.toUpperCase(), id, id, name, entityColor, mapColor, fireworkColor, signColor);
        MORTAR_VALUES.add(color);
        return color;
    }

    public static void setVanillaValues(DyeColor[] colors) {
        VANILLA_VALUES = colors;
    }

    public static DyeColor[] vanillaValues() {
        return VANILLA_VALUES;
    }

    public static boolean contains(DyeColor color) {
        return MORTAR_VALUES.contains(color);
    }

    public static List<DyeColor> mortarValues() {
        return MORTAR_VALUES;
    }
}