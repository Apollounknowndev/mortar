package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.Mortar;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheets.class)
public class SheetsMixin {
    @Inject(
        method = "colorToShulkerSprite",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void fixMortarColorNamespace(DyeColor color, CallbackInfoReturnable<Identifier> cir) {
        if (Mortar.DYES.contains(color)) {
            cir.setReturnValue(Mortar.id(color.getName()));
        }
    }
}
