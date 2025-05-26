package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.Mortar;
import dev.worldgen.mortar.misc.MortarDyes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheets.class)
public class SheetsMixin {
    @Inject(
        method = {
            "colorToShulkerMaterial",
            "colorToResourceMaterial"
        },
        at = @At("HEAD"),
        cancellable = true
    )
    private static void fixMortarColorNamespace(DyeColor dyeColor, CallbackInfoReturnable<ResourceLocation> cir) {
        if (MortarDyes.contains(dyeColor)) {
            cir.setReturnValue(Mortar.id(dyeColor.getName()));
        }
    }
}
