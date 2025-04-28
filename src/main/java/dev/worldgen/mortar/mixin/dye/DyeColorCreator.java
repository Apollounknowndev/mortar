package dev.worldgen.mortar.mixin.dye;

import net.minecraft.block.MapColor;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DyeColor.class)
public interface DyeColorCreator {
    @Invoker("<init>")
    static DyeColor create(String enumName, int enumId, int id, String name, int entityColor, MapColor mapColor, int fireworkColor, int signColor) {
        throw new AssertionError();
    }
}
