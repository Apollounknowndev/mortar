package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.misc.MortarDyes;
import dev.worldgen.mortar.misc.MortarUtils;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBoxBlock.class)
public class ShulkerBoxBlockMixin {
    @Inject(method = "getBlockByColor", at = @At("HEAD"), cancellable = true)
    private static void fixMortarShulkerBoxes(DyeColor color, CallbackInfoReturnable<Block> cir) {
        if (color != null && MortarDyes.contains(color)) {
            cir.setReturnValue(MortarUtils.getShulkerBox(color));
        }
    }
}
