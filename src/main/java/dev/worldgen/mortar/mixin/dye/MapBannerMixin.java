package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.misc.MortarDyes;
import dev.worldgen.mortar.misc.MortarMapDecorations;
import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.saveddata.maps.MapBanner;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapBanner.class)
public class MapBannerMixin {
    @Shadow @Final private DyeColor color;

    @Inject(
        method = "getDecoration",
        at = @At("HEAD"),
        cancellable = true
    )
    private void addMortarBannerDecorations(CallbackInfoReturnable<Holder<MapDecorationType>> cir) {
        if (MortarDyes.contains(this.color)) {
            cir.setReturnValue(MortarMapDecorations.DYE_TO_DECORATION.get(this.color));
        }
    }
}
