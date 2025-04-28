package dev.worldgen.mortar.block.set;

import dev.worldgen.mortar.block.MortarBlockUtils;
import net.minecraft.block.Block;

public record GenericSet(Block full, Block stairs, Block slab, Block wall) {
    public static GenericSet set(String name, Block settings, Block anchor) {
        return set(name, "", settings, anchor);
    }

    public static GenericSet brickSet(String name, Block settings, Block anchor) {
        return set(name, "s", settings, anchor);
    }

    private static GenericSet set(String name, String fullSuffix, Block settings, Block anchor) {
        Block full = MortarBlockUtils.full(name + fullSuffix, settings);
        MortarBlockUtils.buildingGroup(anchor, full);

        Block stairs = MortarBlockUtils.stairs(name + "_stairs", settings);
        MortarBlockUtils.buildingGroup(full, stairs);

        Block slab = MortarBlockUtils.slab(name + "_slab", settings);
        MortarBlockUtils.buildingGroup(stairs, slab);

        Block wall = MortarBlockUtils.wall(name + "_wall", settings);
        MortarBlockUtils.buildingGroup(slab, wall);

        return new GenericSet(full, stairs, slab, wall);
    }
}
