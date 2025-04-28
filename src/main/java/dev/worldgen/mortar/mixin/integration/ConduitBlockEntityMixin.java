package dev.worldgen.mortar.mixin.integration;

import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(ConduitBlockEntity.class)
public class ConduitBlockEntityMixin {
    @Shadow
    @Mutable
    @Final
    private static Block[] ACTIVATING_BLOCKS;

    // Add chiseled prismarine bricks to the blocks that power conduits
    static {
        List<Block> conduitBlocks = new ArrayList<>(Arrays.stream(ACTIVATING_BLOCKS).toList());
        conduitBlocks.add(MortarBlocks.CHISELED_PRISMARINE_BRICKS);
        ACTIVATING_BLOCKS = conduitBlocks.toArray(new Block[0]);
    }
}
