package dev.worldgen.mortar.misc;

import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

public class MortarUtils {
    // Prevents referencing MortarBlocks too early
    public static Block getShulkerBox(DyeColor color) {
        return MortarBlocks.SHULKER_BOXES.match(color);
    }
}
