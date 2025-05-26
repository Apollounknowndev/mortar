package dev.worldgen.mortar.misc;

import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

public class MortarUtils {
    // Prevents referencing MortarBlocks too early
    public static Block getShulkerBox(DyeColor color) {
        return MortarBlocks.SHULKER_BOXES.match(color);
    }
}
