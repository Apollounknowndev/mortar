package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.misc.MortarDyes;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DispenserBehavior.class)
public interface DispenserBehaviorMixin {
    @Redirect(
        method = "registerDefaults",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/util/DyeColor;values()[Lnet/minecraft/util/DyeColor;")
    )
    private static DyeColor[] fixCrash() {
        return MortarDyes.vanillaValues();
    }
}
