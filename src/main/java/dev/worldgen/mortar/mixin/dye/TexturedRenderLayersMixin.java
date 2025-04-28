package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.Mortar;
import dev.worldgen.mortar.misc.MortarDyes;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TexturedRenderLayers.class)
public class TexturedRenderLayersMixin {
    @Inject(
        method = {
            "createShulkerId",
            "createColorId"
        },
        at = @At("HEAD"),
        cancellable = true
    )
    private static void fixMortarColorNamespace(DyeColor dyeColor, CallbackInfoReturnable<Identifier> cir) {
        if (MortarDyes.contains(dyeColor)) {
            cir.setReturnValue(Mortar.id(dyeColor.getId()));
        }
    }
}
