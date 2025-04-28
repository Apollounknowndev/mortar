package dev.worldgen.mortar.mixin.worldgen;

import com.llamalad7.mixinextras.sugar.Local;
import dev.worldgen.mortar.block.MortarBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SurfaceBuilder.class)
public abstract class SurfaceBuilderMixin {
    @Shadow
    private static void addTerracottaBands(Random random, BlockState[] terracottaBands, int minBandSize, BlockState state) {
        throw new AssertionError("Implemented via mixin");
    }

    @Inject(
        method = "createTerracottaBands",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/gen/surfacebuilder/SurfaceBuilder;addTerracottaBands(Lnet/minecraft/util/math/random/Random;[Lnet/minecraft/block/BlockState;ILnet/minecraft/block/BlockState;)V",
            ordinal = 0
        )
    )
    private static void addCustomTerracottaBands(Random random, CallbackInfoReturnable<BlockState[]> cir, @Local(ordinal = 0) BlockState[] states) {
        addTerracottaBands(random, states, 1, MortarBlocks.TERRACOTTAS.amber().getDefaultState());
        addTerracottaBands(random, states, 1, MortarBlocks.TERRACOTTAS.scarlet().getDefaultState());

        int j = 0;
        for (int k = 0; j < random.nextBetween(6, 12) && k < states.length; ++j, k += random.nextInt(16) + 4) {
            states[k] = MortarBlocks.TERRACOTTAS.salmon().getDefaultState();
            if (k - 1 > 0 && random.nextBoolean()) {
                states[k - 1] = Blocks.RED_TERRACOTTA.getDefaultState();
            }
            if (k + 1 >= states.length || !random.nextBoolean()) continue;
            states[k + 1] = Blocks.RED_TERRACOTTA.getDefaultState();
        }
    }
}
