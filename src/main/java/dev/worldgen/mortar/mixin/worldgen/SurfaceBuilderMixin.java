package dev.worldgen.mortar.mixin.worldgen;

import com.llamalad7.mixinextras.sugar.Local;
import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SurfaceSystem.class)
public abstract class SurfaceBuilderMixin {
    @Shadow
    private static void makeBands(RandomSource random, BlockState[] terracottaBands, int minBandSize, BlockState state) {
        throw new AssertionError("Implemented via mixin");
    }

    @Inject(
        method = "generateBands",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/levelgen/SurfaceSystem;makeBands(Lnet/minecraft/util/RandomSource;[Lnet/minecraft/world/level/block/state/BlockState;ILnet/minecraft/world/level/block/state/BlockState;)V",
            ordinal = 0
        )
    )
    private static void addCustomTerracottaBands(RandomSource random, CallbackInfoReturnable<BlockState[]> cir, @Local(ordinal = 0) BlockState[] states) {
        makeBands(random, states, 1, MortarBlocks.TERRACOTTAS.amber().defaultBlockState());
        makeBands(random, states, 1, MortarBlocks.TERRACOTTAS.scarlet().defaultBlockState());

        int j = 0;
        for (int k = 0; j < random.nextIntBetweenInclusive(6, 12) && k < states.length; ++j, k += random.nextInt(16) + 4) {
            states[k] = MortarBlocks.TERRACOTTAS.salmon().defaultBlockState();
            if (k - 1 > 0 && random.nextBoolean()) {
                states[k - 1] = Blocks.RED_TERRACOTTA.defaultBlockState();
            }
            if (k + 1 >= states.length || !random.nextBoolean()) continue;
            states[k + 1] = Blocks.RED_TERRACOTTA.defaultBlockState();
        }
    }
}
