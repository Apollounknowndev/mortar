package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.misc.MortarDyes;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DispenseItemBehavior.class)
public interface DispenseItemBehaviorMixin {
    @Redirect(
        method = "bootStrap",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/DyeColor;values()[Lnet/minecraft/world/item/DyeColor;")
    )
    private static DyeColor[] fixCrash() {
        return MortarDyes.vanillaValues();
    }
}
