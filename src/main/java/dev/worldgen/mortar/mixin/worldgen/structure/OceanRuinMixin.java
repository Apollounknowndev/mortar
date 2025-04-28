package dev.worldgen.mortar.mixin.worldgen.structure;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.structure.OceanRuinGenerator;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTruePosRuleTest;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(OceanRuinGenerator.Piece.class)
public class OceanRuinMixin {
    @ModifyReturnValue(method = "createPlacementData(Lnet/minecraft/util/BlockRotation;FLnet/minecraft/world/gen/structure/OceanRuinStructure$BiomeTemperature;)Lnet/minecraft/structure/StructurePlacementData;", at = @At("RETURN"))
    private static StructurePlacementData mortar$addMortarBlocksProcessor(StructurePlacementData original) {
        return original.addProcessor(new RuleStructureProcessor(List.of(
            createReplaceRule(Blocks.CHISELED_STONE_BRICKS, MortarBlocks.CHISELED_MOSSY_STONE_BRICKS),
            createReplaceRule(Blocks.POLISHED_GRANITE, MortarBlocks.GRANITE_BRICKS.full()),
            createReplaceRule(Blocks.POLISHED_DIORITE, MortarBlocks.DIORITE_BRICKS.full())
        )));
    }

    @Unique
    private static StructureProcessorRule createReplaceRule(Block in, Block out) {
        return new StructureProcessorRule(new RandomBlockMatchRuleTest(in, 0.5f), AlwaysTrueRuleTest.INSTANCE, AlwaysTruePosRuleTest.INSTANCE, out.getDefaultState());
    }
}
