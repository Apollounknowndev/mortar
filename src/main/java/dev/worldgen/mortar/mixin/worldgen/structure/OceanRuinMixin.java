package dev.worldgen.mortar.mixin.worldgen.structure;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.structures.OceanRuinPieces;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(OceanRuinPieces.OceanRuinPiece.class)
public class OceanRuinMixin {
    @ModifyReturnValue(method = "makeSettings", at = @At("RETURN"))
    private static StructurePlaceSettings mortar$addMortarBlocksProcessor(StructurePlaceSettings original) {
        return original.addProcessor(new RuleProcessor(List.of(
            createReplaceRule(Blocks.CHISELED_STONE_BRICKS, MortarBlocks.CHISELED_MOSSY_STONE_BRICKS),
            createReplaceRule(Blocks.POLISHED_GRANITE, MortarBlocks.GRANITE_BRICKS.full()),
            createReplaceRule(Blocks.POLISHED_DIORITE, MortarBlocks.DIORITE_BRICKS.full())
        )));
    }

    @Unique
    private static ProcessorRule createReplaceRule(Block in, Block out) {
        return new ProcessorRule(new RandomBlockMatchTest(in, 0.5f), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, out.defaultBlockState());
    }
}
