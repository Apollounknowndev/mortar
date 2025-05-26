package dev.worldgen.mortar.mixin.worldgen.processor;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockAgeProcessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockAgeProcessor.class)
public abstract class BlockAgeMixin {
    @Final
    @Shadow
    private float mossiness;

    @WrapOperation(
        method = "processBlock",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/templatesystem/BlockAgeProcessor;maybeReplaceFullStoneBlock(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/level/block/state/BlockState;")
    )
    private BlockState mortar$addChiseledMossyStoneBricks(BlockAgeProcessor instance, RandomSource random, Operation<BlockState> original, @Local(ordinal = 0) LocalRef<BlockState> originalState) {
        if (originalState.get().is(Blocks.CHISELED_STONE_BRICKS) && random.nextFloat() < this.mossiness) {
            return MortarBlocks.CHISELED_MOSSY_STONE_BRICKS.defaultBlockState();
        } else {
            return original.call(instance, random);
        }
    }
}
