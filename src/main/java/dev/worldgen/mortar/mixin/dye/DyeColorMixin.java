package dev.worldgen.mortar.mixin.dye;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static org.spongepowered.asm.mixin.MixinIntrinsics.currentEnumOrdinal;

@Mixin(DyeColor.class)
public enum DyeColorMixin {
    MAROON(currentEnumOrdinal(), "maroon", 0x7c3535, MapColor.CRIMSON_HYPHAE, MapColor.CRIMSON_HYPHAE, 7684924, 8069666),
    SCARLET(currentEnumOrdinal(), "scarlet", 0xe34300, MapColor.TERRACOTTA_ORANGE, MapColor.TERRACOTTA_ORANGE, 15426601, 16728836),
    AMBER(currentEnumOrdinal(), "amber", 0xe89700, MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW, 14461518, 16749312),
    PEAR(currentEnumOrdinal(), "pear", 0xc6e259, MapColor.GRASS, MapColor.GRASS, 12902529, 12710194),
    PINE(currentEnumOrdinal(), "pine", 0x2c4a30, MapColor.TERRACOTTA_GREEN, MapColor.TERRACOTTA_GREEN, 4219972, 2709552),
    SLATE(currentEnumOrdinal(), "slate", 0x465274, MapColor.TERRACOTTA_BLUE, MapColor.TERRACOTTA_BLUE, 6055297, 4676237),
    LAVENDER(currentEnumOrdinal(), "lavender", 0xcd8dcc, MapColor.ICE, MapColor.ICE, 13345486, 13660105),
    SALMON(currentEnumOrdinal(), "salmon", 0xfa6c5a, MapColor.CRIMSON_STEM, MapColor.CRIMSON_STEM, 15964041, 16731978);
    
    @Shadow
    DyeColorMixin(final int id, final String name, final int textureDiffuseColor, final MapColor mapColor, final MapColor terracottaColor, final int fireworkColor, final int textColor) {
    
    }
}
