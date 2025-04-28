package dev.worldgen.mortar.mixin.worldgen.processor;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.processor.BlockAgeStructureProcessor;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockAgeStructureProcessor.class)
public abstract class BlockAgeMixin {
    @Final
    @Shadow
    private float mossiness;

    @WrapOperation(
        method = "process(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/structure/StructureTemplate$StructureBlockInfo;Lnet/minecraft/structure/StructureTemplate$StructureBlockInfo;Lnet/minecraft/structure/StructurePlacementData;)Lnet/minecraft/structure/StructureTemplate$StructureBlockInfo;",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/processor/BlockAgeStructureProcessor;processBlocks(Lnet/minecraft/util/math/random/Random;)Lnet/minecraft/block/BlockState;")
    )
    private BlockState mortar$addChiseledMossyStoneBricks(BlockAgeStructureProcessor instance, Random random, Operation<BlockState> original, @Local(ordinal = 0) LocalRef<BlockState> originalState) {
        if (originalState.get().isOf(Blocks.CHISELED_STONE_BRICKS) && random.nextFloat() < this.mossiness) {
            return MortarBlocks.CHISELED_MOSSY_STONE_BRICKS.getDefaultState();
        } else {
            return original.call(instance, random);
        }
    }
}
