package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.misc.MortarDyes;
import dev.worldgen.mortar.misc.MortarMapDecorations;
import net.minecraft.item.map.MapBannerMarker;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapBannerMarker.class)
public class MapBannerMarkerMixin {
    @Shadow @Final private DyeColor color;

    @Inject(
        method = "getDecorationType",
        at = @At("HEAD"),
        cancellable = true
    )
    private void addMortarBannerDecorations(CallbackInfoReturnable<RegistryEntry<MapDecorationType>> cir) {
        if (MortarDyes.contains(this.color)) {
            cir.setReturnValue(MortarMapDecorations.DYE_TO_DECORATION.get(this.color));
        }
    }
}
